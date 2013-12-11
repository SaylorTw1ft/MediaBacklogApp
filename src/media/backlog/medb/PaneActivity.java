package media.backlog.medb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.adapter.OrgListAdapter;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Items;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

/**
 * Created by Arin on 11/28/13.
 */
public class PaneActivity extends Activity {
	
	public void setHomeBarColor(int color)
	{
        Fragment homeFrag = getFragmentManager().findFragmentById(R.id.home_bar);
        View mainView = homeFrag.getView();
        View homeView = mainView.findViewById(R.id.homeButton);
        View organizeView = mainView.findViewById(R.id.organizeButton);
        View discoverView = mainView.findViewById(R.id.discoverButton);
        View searchView = mainView.findViewById(R.id.searchButton);
        
        mainView.setBackgroundColor(color);
        homeView.setBackgroundColor(color);
        organizeView.setBackgroundColor(color);
        discoverView.setBackgroundColor(color);
        searchView.setBackgroundColor(color);
	}
	public void setUpPage(int category){
		//Movies
        if (category == 1)
        {
        	setTitle("Movies");
            setHomeBarColor(getResources().getColor(R.color.movie_color));
        }
        //Games
        if(category == 2)
        {
        	setTitle("Games");
        	setHomeBarColor(getResources().getColor(R.color.game_color));
        }
        //Music
        if(category == 3)
        {
        	setTitle("Music");
        	setHomeBarColor(getResources().getColor(R.color.music_color));
        }
        //Books
        if(category == 4)
        {
        	setTitle("Books");
        	setHomeBarColor(getResources().getColor(R.color.book_color));
        }
	}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pane);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        int category = b.getInt("category");
        setUpPage(category);
        
		DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
		
		HorizontalScrollBar scrollBar = (HorizontalScrollBar)
                getFragmentManager().findFragmentById(R.id.horizontal_scroll_bar_pane);
        
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
                
                ArrayList<MediaItem> paneItems = Items.getPaneItems(helper, category);

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
		
		final ArrayList<MediaList> all_lists = Lists.getAllLists(helper);
		ListView media_list = (ListView) findViewById(R.id.media_list);
	    final OrgListAdapter adapter = new OrgListAdapter(this, R.layout.lists_list);
	    
	    media_list.setAdapter(adapter);
	    update_media_list(all_lists, adapter, category);
	    
		media_list.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, 
					  int position, long id) {
				  
				  MediaList this_item = (MediaList) parent.getItemAtPosition(position);
				  
				  Bundle b = new Bundle();
				  b.putString("list_name", this_item.getListName());
				  b.putInt("list_id", this_item.getListID());
				  Intent intent = new Intent(getApplicationContext(), ListActivity.class);
				  intent.putExtras(b);
				  startActivity(intent);
				  
			  }
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pane, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.pane_action_add:
                addList();
                return true;
            case android.R.id.home:
                android.support.v4.app.NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	private void update_media_list(ArrayList<MediaList> all_lists, OrgListAdapter adapter, int category) {
        adapter.clear();
        for(int i=0; i<all_lists.size(); i++){
        	if(category == 1 && all_lists.get(i).getMovie()){
        		adapter.add(all_lists.get(i));
        		continue;
            		}
        	if(category == 2 && all_lists.get(i).getGame()){
        		adapter.add(all_lists.get(i));
        		continue;
            		}
        	if(category == 3 && all_lists.get(i).getMusic()){
        		adapter.add(all_lists.get(i));
        		continue;
            		}
        	if(category == 4 && all_lists.get(i).getBook()){
        		adapter.add(all_lists.get(i));
        		continue;
            		}
        		}
        adapter.notifyDataSetChanged();
		
	}
    
    public void addList() {
    }

    private LinearLayout setUpScrollingItem(View v, MediaItem temp) {
        LinearLayout scrollingItem = new LinearLayout(v.getContext());
        scrollingItem.setLayoutParams(new LinearLayout.LayoutParams(188,250));
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