package media.backlog.medb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Items;
import media.backlog.medb.data.MediaItem;

/**
 * Created by Arin on 12/8/13.
 */
public class ItemActivity extends Activity {
	private static int staticCategory = 1;
    private MediaItem item;
    private DatabaseHelper helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
		DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
		item = media.backlog.medb.database.Items.getItem(helper, id);
        int category;
        
        try
        {
        	category = b.getInt("category");
        	staticCategory = category;
        }
        catch(Exception e)
        {
        	category = staticCategory;
        }

        setTitle(item.getItemName());
        setItemName();
        setItemGenre();
        setUpButtons();
        setItemThumbnail();

        //setContentView(R.layout.activity_item);
        		
		WhiteHorizontalScrollBar scrollBar = (WhiteHorizontalScrollBar)
        getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_item);
		
        if(scrollBar != null)
        {
        	HorizontalScrollView scrollView = (HorizontalScrollView) scrollBar.getView();
        	LinearLayout view1 = (LinearLayout) scrollView.findViewById(R.id.horizontal_scroll_bar);
        	
        	if(view1 != null)
        	{
        		view1.removeAllViews();
        		//view1.removeAllViewsInLayout();
        		
                TextView t = new TextView(view1.getContext());
                t.setText("  ");
                view1.addView(t);
                
                ArrayList<MediaItem> paneItems = Items.getSimilarItems(helper, item);

                for (int i = 0; i < paneItems.size(); i++)
                {
                    MediaItem item1 = paneItems.get(i);
                    LinearLayout scrollingItem = setUpScrollingItem(view1, item1);
                    view1.addView(scrollingItem);

                    TextView buffer = new TextView(view1.getContext());
                    buffer.setText("  ");
                    view1.addView(buffer);
                }
        	}
        }
    }

	public void setUpButtons()
    {
       Button share = (Button)findViewById(R.id.share_button);
       share.setOnClickListener(new ItemClick());
       Button add = (Button)findViewById(R.id.add_button);
       add.setOnClickListener(new ItemClick());
    }
    

    private void setItemGenre() {
        TextView text = (TextView) findViewById(R.id.item_genre);
        text.setText("Type: " + item.getGenre());
    }
    private LinearLayout setUpScrollingItem(View v, MediaItem temp) {
        LinearLayout scrollingItem = new LinearLayout(v.getContext());
        scrollingItem.setLayoutParams(new LinearLayout.LayoutParams(188,240));
        scrollingItem.setOrientation(LinearLayout.VERTICAL);
        ImageButton button = new ImageButton(scrollingItem.getContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(150, 200));
        button.setScaleType(ScaleType.FIT_START);
        final int myId = temp.getItemID();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ItemActivity.class);
                Bundle b = new Bundle();
                b.putInt("id",myId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        setThumbnailPic(temp, button);
        TextView t1 = new TextView(v.getContext());
        t1.setText(temp.getItemName());
        t1.setTextColor(getResources().getColor(R.color.home_color));
        t1.setTextSize(12);
        t1.setGravity(Gravity.BOTTOM);
        scrollingItem.setMinimumHeight(200);
        scrollingItem.setMinimumWidth(134);
        scrollingItem.addView(button);
        scrollingItem.addView(t1);
        return scrollingItem;
    }
    public void setThumbnailPic(MediaItem item, ImageButton imageButton)
    {
        String path = item.getPicture();
        try {

            // get input stream

            InputStream ims = getAssets().open(path);

            // load image as Drawable

            Drawable d = Drawable.createFromStream(ims, null);

            // set image to ImageView
            imageButton.setImageDrawable(d);
        }
            catch(IOException ex)
            {
            	imageButton.setClickable(false);
            }
    }

    private void setItemName(){
    	TextView text = (TextView) findViewById(R.id.item_name_text);
    	text.setText(item.getItemName());
    }
    private void setItemThumbnail() {
        ImageView imageView = (ImageView) findViewById(R.id.item_picture);
        String path = item.getPicture();
        try {

            // get input stream

            InputStream ims = getAssets().open(path);

            // load image as Drawable

            Drawable d = Drawable.createFromStream(ims, null);

            // set image to ImageView
            imageView.setImageDrawable(d);

        }

        catch(IOException ex) {


        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addButton(View v)
    {
        Intent intent = new Intent(this, AddActivity.class);
        Bundle b = new Bundle();
        b.putInt("id",item.getItemID());
        intent.putExtras(b);
        startActivityForResult(intent, 1);
    }
    public void shareButton(View v)
    {
    	AlertDialog.Builder shareAlert = new AlertDialog.Builder(this);
    	shareAlert.setTitle("Enter Your Message: ");
    	final EditText input = new EditText(this);
    	shareAlert.setView(input);
    	shareAlert.setPositiveButton("Share", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(ItemActivity.this, "Shared", Toast.LENGTH_SHORT).show();
			}
		});
    	shareAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing		
			}
		});
    	shareAlert.show();
    }
    public class ItemClick implements OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_button:
                    addButton(v);
                    break;
                case R.id.share_button:
                    shareButton(v);
                    break;
            }
        }
    }
}