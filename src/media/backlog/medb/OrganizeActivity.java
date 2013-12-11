package media.backlog.medb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import media.backlog.medb.adapter.OrgListAdapter;
import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;


/**
 * Created by Arin on 11/13/13.
 * 
 * Updated by Zihan on 12/06/13.
 * 
 * 
 *  */

public class OrganizeActivity extends Activity {

	RelativeLayout r;
	ToggleButton t_movies;
	ToggleButton t_games;
	ToggleButton t_books;
	ToggleButton t_music;
	
	boolean movies_checked;
	boolean games_checked;
	boolean books_checked;
	boolean music_checked;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_organize);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        t_movies = (ToggleButton) findViewById(R.id.toggle_org_movies);
		t_games = (ToggleButton) findViewById(R.id.toggle_org_games);
		t_books = (ToggleButton) findViewById(R.id.toggle_org_books);
		t_music = (ToggleButton) findViewById(R.id.toggle_org_music);

		movies_checked = true;
		games_checked = true;
		books_checked = true;
		music_checked = true;
		
		t_movies.setChecked(movies_checked);
		t_games.setChecked(games_checked);
		t_books.setChecked(books_checked);
		t_music.setChecked(music_checked);
		
		// db connection
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
		final ArrayList<MediaList> all_lists = Lists.getAllLists(dbHelper);
		Collections.sort(all_lists, new ListComparator());
		

		ListView org_list = (ListView) findViewById(R.id.org_list);
	    final OrgListAdapter adapter = new OrgListAdapter(this, R.layout.lists_list);
	    
	    org_list.setAdapter(adapter);
		
	    update_org_list(movies_checked, games_checked, books_checked, music_checked, all_lists, adapter);
	    
		org_list.setOnItemClickListener(new OnItemClickListener() {
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
	    
	    
		t_movies.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//
//				Toast.makeText(getApplicationContext(), 
//						"movies clicked", 
//						Toast.LENGTH_SHORT).show();
				movies_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked, all_lists, adapter);
				
			}

		});
		
		t_games.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

//				Toast.makeText(getApplicationContext(), 
//						"games clicked", 
//						Toast.LENGTH_SHORT).show();
				games_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked, all_lists, adapter);
				
			}

		});		
		
		t_books.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

//				Toast.makeText(getApplicationContext(), 
//						"books clicked", 
//						Toast.LENGTH_SHORT).show();
				books_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked, all_lists, adapter);
				
			}

		});		
		
		t_music.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

//				Toast.makeText(getApplicationContext(), 
//						"music clicked", 
//						Toast.LENGTH_SHORT).show();
				music_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked, all_lists, adapter);
				
			}

		});	

	
	}

	private void update_org_list(boolean movies_checked,
			boolean games_checked, boolean books_checked,
			boolean music_checked, 
			ArrayList<MediaList> all_lists,
			OrgListAdapter adapter) {

        adapter.clear();
//		Toast.makeText(getApplicationContext(), 
//				"updating org list: all_list size is " + Integer.toString(all_lists.size()), 
//				Toast.LENGTH_SHORT).show();
        for(int i=0; i<all_lists.size(); i++)
        {
        	if(movies_checked && all_lists.get(i).getMovie()){
        		adapter.add(all_lists.get(i));
        		continue;
        	}
        	else
        	{
        		if(games_checked && all_lists.get(i).getGame()){
        			adapter.add(all_lists.get(i));
        			continue;
        		}
        		else
        		{
            		if(books_checked && all_lists.get(i).getBook()){
            			adapter.add(all_lists.get(i));
            			continue;
            		}
            		else
            		{
                		if(music_checked && all_lists.get(i).getMusic()){
                			adapter.add(all_lists.get(i));
                			continue;
                		}
            		}
        		}
        	}
        }
        adapter.notifyDataSetChanged();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.organize, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId())
        {
	        case R.id.organize_action_add:
	            addList();
	            return true;
            case android.R.id.home:
                android.support.v4.app.NavUtils.navigateUpFromSameTask(this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public void addList()
    {
        Intent intent=new Intent(getApplicationContext(), OrganizeAddListActivity.class);
        startActivity(intent);
    }

    private class ListComparator implements Comparator<MediaList> {
        @Override
        public int compare(MediaList l1, MediaList l2) {
        	if(l1.getNumItems() == l2.getNumItems()){
        		return l1.getListName().compareTo(l2.getListName());
        	}else{
        		return l2.getNumItems() - l1.getNumItems();
        	}
        }
    }
}
