package media.backlog.medb;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Items;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

/**
 * Created by Arin on 11/13/13.
 * 
 * updated by Kevin on 12/8/13
 */


public class SearchActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_list);

	    // Get the intent, verify the action and get the query
	    Intent intent = getIntent();
	    ListView la;
	    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
	      String query = intent.getStringExtra(SearchManager.QUERY);
	      DatabaseHelper meDbHelper = new DatabaseHelper(getApplicationContext());
	      ArrayList<MediaItem> searchResults = Items.getSearchResults(meDbHelper, query);
	      la = (ListView)findViewById(R.id.activity_organize);
	      la.setAdapter(new ArrayAdapter<String>(SearchActivity.this,R.layout.search_list_display,searchResults));
	    }
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
}