package media.backlog.medb;

import java.util.ArrayList;

import media.backlog.medb.adapter.OrgListAdapter;
import media.backlog.medb.adapter.SearchResultListAdaptor;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Items;
import media.backlog.medb.database.Lists;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.ToggleButton;

/**
 * Created by Arin on 11/13/13.
 * 
 * updated by Kevin on 12/8/13
 */


public class SearchActivity extends Activity {

	SearchResultListAdaptor adapter;
	ArrayList<MediaItem> all_Item;
	ListView search_list;
	RelativeLayout r;
	Button search_BUT;
	Button cancel_BUT;
	DatabaseHelper dbHelper;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_box);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		search_BUT = (Button)findViewById(R.id.search_BUTTON);
		cancel_BUT = (Button)findViewById(R.id.cancel_BUTTON);
		search_BUT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handleIntent(getIntent());
			
			}

		});	
		
		cancel_BUT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setContentView(R.layout.search_box);
			}

		});



		
	}
	@Override
	protected void onNewIntent(Intent intent) {

		handleIntent(intent);
	}
	public void onListItemClick(ListView l, 
			View v, int position, long id) { 
		// call detail activity for clicked entry 
	}

	private void handleIntent(Intent intent) {

		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			//use the query to search your data somehow
			doSearch(query);
		}
	}
	private void doSearch(String queryStr) { 
		// get a Cursor, prepare the ListAdapter
		// and set it
		dbHelper = new DatabaseHelper(getApplicationContext());
		all_Item=Items.getSearchResults(dbHelper, queryStr);
		r = (RelativeLayout) findViewById(R.id.org_layout);

		search_list = (ListView) findViewById(R.id.org_list);
		adapter = new SearchResultListAdaptor(this, R.layout.list_item, all_Item);
		search_list.setAdapter(adapter);
	} 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager =
				(SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView =
				(SearchView) menu.findItem(R.id.search).getActionView();
		searchView.setSearchableInfo(
				searchManager.getSearchableInfo(getComponentName()));

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