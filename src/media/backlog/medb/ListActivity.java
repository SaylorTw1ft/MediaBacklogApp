package media.backlog.medb;

import java.util.ArrayList;

import media.backlog.medb.adapter.ItemsListAdapter;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.ListItems;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by Arin on 11/28/13.
 * 
 * Updated by Zihan on 12/09/13
 */
public class ListActivity extends Activity {
	
	String list_name;
	int list_id;

	ToggleButton t_movies;
	ToggleButton t_games;
	ToggleButton t_books;
	ToggleButton t_music;
	
	boolean movies_checked;
	boolean games_checked;
	boolean books_checked;
	boolean music_checked;
	
	TextView mTextView;  
	Button mButton;  
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        list_name = b.getString("list_name");
        list_id = b.getInt("list_id");
        setTitle(list_name);
        
        t_movies = (ToggleButton) findViewById(R.id.toggle_list_movies);
		t_games = (ToggleButton) findViewById(R.id.toggle_list_games);
		t_books = (ToggleButton) findViewById(R.id.toggle_list_books);
		t_music = (ToggleButton) findViewById(R.id.toggle_list_music);

		movies_checked = true;
		games_checked = true;
		books_checked = true;
		music_checked = true;
		
		t_movies.setChecked(movies_checked);
		t_games.setChecked(games_checked);
		t_books.setChecked(books_checked);
		t_music.setChecked(music_checked);
        
		DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        final ArrayList<MediaItem> items = ListItems.getItemsForList(dbHelper, list_id);
        
		ListView items_list_view = (ListView) findViewById(R.id.list_view_list_page);
	    final ItemsListAdapter adapter = new ItemsListAdapter(this, R.layout.items_list);
	    
	    items_list_view.setAdapter(adapter);

	    update_items_list(movies_checked, games_checked, books_checked, music_checked, items, adapter);
	    
	    items_list_view.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view, 
					  int position, long id) {
				  
				  MediaItem this_item = (MediaItem) parent.getItemAtPosition(position);
				  
				  Bundle b = new Bundle();
				  b.putString("item_name", this_item.getItemName());
				  b.putInt("id", this_item.getItemID());
				  Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
				  intent.putExtras(b);
				  startActivity(intent);
				  
			  }
		}); 
	    
	    
		t_movies.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				movies_checked = ((ToggleButton)v).isChecked();
				update_items_list(movies_checked, games_checked, books_checked, music_checked, items, adapter);
				
			}

		});
		
		t_games.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				games_checked = ((ToggleButton)v).isChecked();
				update_items_list(movies_checked, games_checked, books_checked, music_checked, items, adapter);
				
			}

		});		
		
		t_books.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				books_checked = ((ToggleButton)v).isChecked();
				update_items_list(movies_checked, games_checked, books_checked, music_checked, items, adapter);
				
			}

		});		
		
		t_music.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				music_checked = ((ToggleButton)v).isChecked();
				update_items_list(movies_checked, games_checked, books_checked, music_checked, items, adapter);
				
			}



		});	
        
        
    }

	private void update_items_list(boolean movies_checked,
			boolean games_checked, boolean books_checked,
			boolean music_checked, ArrayList<MediaItem> items,
			ItemsListAdapter adapter) {
		
        adapter.clear();
        for(int i=0; i<items.size(); i++){
        	if(movies_checked && (items.get(i).getCategory() == 1)){
//        		Toast.makeText(getApplicationContext(), 
//        				items.get(i).getItemName() + " has been added to the adapter ... ", 
//        				Toast.LENGTH_SHORT).show();
        		adapter.add(items.get(i));
        		continue;
        	}else{
        		if(games_checked && (items.get(i).getCategory() == 2)){
//            		Toast.makeText(getApplicationContext(), 
//            				items.get(i).getItemName() + " has been added to the adapter ... ", 
//            				Toast.LENGTH_SHORT).show();
        			adapter.add(items.get(i));
        			continue;
        		}else{
            		if(books_checked && (items.get(i).getCategory() == 4)){
            			adapter.add(items.get(i));
//                		Toast.makeText(getApplicationContext(), 
//                				items.get(i).getItemName() + " has been added to the adapter ... ", 
//                				Toast.LENGTH_SHORT).show();
            			continue;
            		}else{
                		if(music_checked && (items.get(i).getCategory() == 3)){
                			adapter.add(items.get(i));
//                    		Toast.makeText(getApplicationContext(), 
//                    				items.get(i).getItemName() + " has been added to the adapter ... ", 
//                    				Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.list_action_add:
                addItem();
                return true;
            case R.id.list_action_edit:
                editItem();
                return true;
            case android.R.id.home:
                android.support.v4.app.NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addItem() {
		Toast.makeText(getApplicationContext(), 
				" add button has been clicked", 
				Toast.LENGTH_SHORT).show();
		
		Intent intent=new Intent(getApplicationContext(), ListAddItemActivity.class);
		Bundle b = new Bundle();
		b.putString("list_name", list_name);
		b.putInt("list_id", list_id);
		intent.putExtras(b);
		startActivity(intent);
		
    }

    public void editItem() {
		Toast.makeText(getApplicationContext(), 
				" edit button has been clicked", 
				Toast.LENGTH_SHORT).show();

    }
    

}