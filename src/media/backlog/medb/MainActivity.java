package media.backlog.medb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.TrendingAmongFriends;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {
	
    public void startNewIntent(int category){
        Intent intent = new Intent(MainActivity.this, PaneActivity.class);
        Bundle b = new Bundle();
        b.putInt("category", category);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        
		HorizontalScrollBar scrollBar = (HorizontalScrollBar)
		        getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_main);
				
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
		                
		                ArrayList<MediaItem> paneItems = TrendingAmongFriends.getTrendingAmongFriendsMediaItems(helper);

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
        
        final ImageView navBlock = (ImageView) findViewById(R.id.nav_block);
        /*
         * Allows user to select movie/game/music/book from the nav block image.
         */
        navBlock.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event){
        		if(event.getAction() == MotionEvent.ACTION_DOWN)
        		{
        			double x = event.getX() - 85;
        		    double y = event.getY();
        			if(y > x){
        				if(navBlock.getHeight() - y > x)
        					startNewIntent(1);
        				else
        					startNewIntent(2);
        			}
        			else{
        				if(navBlock.getHeight() - y > x)
        					startNewIntent(3);
        				else
        					startNewIntent(4);
        			}
        		}
        		return false;
        	}
        });

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
        t1.setTextColor(getResources().getColor(R.color.white));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
	        case R.id.home_action_notifications:
	            openNotifications();
	            return true;
            case R.id.home_action_settings:
                openSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void openNotifications()
    {
    	Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }

    public void openSettings()
    {
    	Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
