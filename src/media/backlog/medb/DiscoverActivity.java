package media.backlog.medb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.PopularItem;
import media.backlog.medb.data.TrendingItem;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.NewReleases;
import media.backlog.medb.database.PopularItems;
import media.backlog.medb.database.TrendingAmongFriends;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

/**
 * Created by Arin on 11/13/13.
 */
public class DiscoverActivity extends Activity
{
	private DatabaseHelper helper;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        helper = new DatabaseHelper(getApplicationContext());
        
        HorizontalScrollBar scrollBar1 = (HorizontalScrollBar)
                getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_d1);
        HorizontalScrollBar scrollBar2 = (HorizontalScrollBar)
                getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_d2);
        HorizontalScrollBar scrollBar3 = (HorizontalScrollBar)
        		getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_d3);
        
        if(scrollBar1 != null)
        {
        	HorizontalScrollView scrollView1 = (HorizontalScrollView) scrollBar1.getView();
        	LinearLayout view1 = (LinearLayout) scrollView1.findViewById(R.id.horizontal_scroll_bar);
        	
        	if(view1 != null)
        	{
        		view1.removeAllViews();
        		//view1.removeAllViewsInLayout();
        		
                TextView t = new TextView(view1.getContext());
                t.setText("  ");
                view1.addView(t);
                
                ArrayList<TrendingItem> trendingItems = TrendingAmongFriends.getTrendingAmongFriendsItems(helper);

                for (int i = 0; i < trendingItems.size(); i++)
                {
                    MediaItem item1 = trendingItems.get(i);
                    LinearLayout scrollingItem = setUpScrollingItem(view1, item1);
                    view1.addView(scrollingItem);

                    TextView buffer = new TextView(view1.getContext());
                    buffer.setText("  ");
                    view1.addView(buffer);
                }
        	}
        }
        
        if(scrollBar2 != null)
        {
        	HorizontalScrollView scrollView2 = (HorizontalScrollView) scrollBar2.getView();
        	LinearLayout view2 = (LinearLayout) scrollView2.findViewById(R.id.horizontal_scroll_bar);
        	
        	if(view2 != null)
        	{
        		view2.removeAllViews();
        		//view1.removeAllViewsInLayout();
        		
                TextView t = new TextView(view2.getContext());
                t.setText("  ");
                view2.addView(t);
                
                ArrayList<PopularItem> popularItems = PopularItems.getPopularItems(helper);

                for (int i = 0; i < popularItems.size(); i++)
                {
                    MediaItem item2 = popularItems.get(i);
                    LinearLayout scrollingItem = setUpScrollingItem(view2, item2);
                    view2.addView(scrollingItem);

                    TextView buffer = new TextView(view2.getContext());
                    buffer.setText("  ");
                    view2.addView(buffer);
                }
        	}
        }
        
        if(scrollBar3 != null)
        {
        	HorizontalScrollView scrollView3 = (HorizontalScrollView) scrollBar3.getView();
        	LinearLayout view3 = (LinearLayout) scrollView3.findViewById(R.id.horizontal_scroll_bar);
        	
        	if(view3 != null)
        	{
        		view3.removeAllViews();
        		//view1.removeAllViewsInLayout();
        		
                TextView t = new TextView(view3.getContext());
                t.setText("  ");
                view3.addView(t);
                
                ArrayList<MediaItem> newReleases = NewReleases.getNewReleases(helper);

                for (int i = 0; i < newReleases.size(); i++)
                {
                    MediaItem item1 = newReleases.get(i);
                    LinearLayout scrollingItem = setUpScrollingItem(view3, item1);
                    view3.addView(scrollingItem);

                    TextView buffer = new TextView(view3.getContext());
                    buffer.setText("  ");
                    view3.addView(buffer);
                }
        	}
        }
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.discover, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                android.support.v4.app.NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private LinearLayout setUpScrollingItem(View v, MediaItem temp) {
        LinearLayout scrollingItem = new LinearLayout(v.getContext());
        scrollingItem.setLayoutParams(new LinearLayout.LayoutParams(188,250));
        scrollingItem.setOrientation(LinearLayout.VERTICAL);
        ImageButton button = new ImageButton(scrollingItem.getContext());
        button.setLayoutParams(new ViewGroup.LayoutParams(150,200));
        button.setScaleType(ScaleType.FIT_START);
        final int myId = temp.getItemID();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiscoverActivity.this, ItemActivity.class);
                Bundle b = new Bundle();
                b.putInt("id",myId);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        setThumbnailPic(temp, button);
        TextView t1 = new TextView(v.getContext());
        t1.setText(temp.getItemName());
        t1.setTextColor(Color.WHITE);
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

        catch(IOException ex) {


        }
    }
}