package media.backlog.medb;

import java.util.ArrayList;
import java.util.List;

import media.backlog.medb.adapter.OrgListAdapter;
import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
	
	ArrayList<MediaList> all_lists;
	ListView org_list;
	OrgListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_organize);
		
		t_movies = (ToggleButton) findViewById(R.id.toggle_org_movies);
		t_games = (ToggleButton) findViewById(R.id.toggle_org_games);
		t_books = (ToggleButton) findViewById(R.id.toggle_org_books);
		t_music = (ToggleButton) findViewById(R.id.toggle_org_music);

		movies_checked = false;
		games_checked = false;
		books_checked = false;
		music_checked = false;
		
		// db connection
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
		all_lists = Lists.getAllLists(dbHelper);
		
		// get the layout
		r = (RelativeLayout) findViewById(R.id.org_layout);

	    org_list = (ListView) findViewById(R.id.org_list);
	    adapter = new OrgListAdapter(this, R.layout.list_item, all_lists);
	    org_list.setAdapter(adapter);
		
	    update_org_list(movies_checked, games_checked, books_checked, music_checked);
	    
		org_list.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				  
			  }
		}); 
	    
	    
		t_movies.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				movies_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked);
				
			}

		});
		
		t_games.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				games_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked);
				
			}

		});		
		
		t_books.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				books_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked);
				
			}

		});		
		
		t_music.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				music_checked = ((ToggleButton)v).isChecked();
				update_org_list(movies_checked, games_checked, books_checked, music_checked);
				
			}

		});	

	
	}

	private void update_org_list(boolean movies_checked,
			boolean games_checked, boolean books_checked,
			boolean music_checked) {
		
        adapter.clear();
        for(int i=0; i<all_lists.size(); i++){
        	if(movies_checked && all_lists.get(i).getMovie()){
        		adapter.add(all_lists.get(i));
        	}else{
        		if(games_checked && all_lists.get(i).getGame()){
        			adapter.add(all_lists.get(i));
        		}else{
            		if(books_checked && all_lists.get(i).getBook()){
            			adapter.add(all_lists.get(i));
            		}else{
                		if(music_checked && all_lists.get(i).getMusic()){
                			adapter.add(all_lists.get(i));
                		}
            		}
        		}
        	}
        }
        adapter.notifyDataSetChanged();
		
	}
	
	private List<MediaList> getItems() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
