package media.backlog.medb;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.data.MediaItem;

/**
 * Created by Arin on 12/8/13.
 */
public class ItemActivity extends Activity {
    private MediaItem item;
    private DatabaseHelper helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        setContentView(R.layout.activity_item);
        helper = new DatabaseHelper(getApplicationContext());
        item = media.backlog.medb.database.Items.getItem(helper, id);
        setItemThumbnail();
        setItemName();
        setItemGenre();
        setSimilarItems();
        setUpButtons();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putInt("id",id);
        HorizontalScrollBar horizontalScrollBar = new HorizontalScrollBar();
        horizontalScrollBar.setArguments(args);
        fragmentTransaction.add(R.id.scroll_bar_container, horizontalScrollBar).commit();
        getActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void setUpButtons()
    {
       Button share = (Button)findViewById(R.id.share_button);
       share.setOnClickListener(new ItemClick());
       Button add = (Button)findViewById(R.id.add_button);
       add.setOnClickListener(new ItemClick());
    }
    private void setSimilarItems() {
        ArrayList<MediaItem> similarItems = media.backlog.medb.database.Items.getSimilarItems(helper, item);


    }

    private void setItemGenre() {
        TextView text = (TextView) findViewById(R.id.item_genre);
        text.setText(item.getGenre());
    }

    private void setItemName() {
        setTitle(item.getItemName());

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
        Intent intent = new Intent(this, ShareActivity.class);
        startActivityForResult(intent, 1);
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