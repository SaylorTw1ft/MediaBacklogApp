package media.backlog.medb;

import java.util.ArrayList;

import media.backlog.medb.adapter.SearchResultListAdaptor;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Items;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

/**
 * Created by Arin on 11/13/13.
 * 
 * updated by Kevin on 12/8/13
 */


public class SearchActivity extends Activity{
	
	SearchResultListAdaptor adapter;
	ArrayList<MediaItem> all_Item;
	ListView search_list;
	RelativeLayout r;
	Button search_BUT;
	Button cancel_BUT;
	DatabaseHelper dbHelper;
	EditText et;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.searchresult);
		setContentView(R.layout.search_box);
		
		//cancel_BUT.setText(i.getAction().toString());

		search_BUT = (Button)findViewById(R.id.search_BUTTON);
		cancel_BUT = (Button)findViewById(R.id.cancel_BUTTON);
		et = (EditText) findViewById(R.id.searchBOX);
		//String searchString = et.getText().toString();
		
		search_BUT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//cancel_BUT.setText(intent.getStringExtra(SearchManager.QUERY));
				String searchString = et.getText().toString();
				doSearch(searchString);
			}

		});
		
		cancel_BUT.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				doCancel();
			}

		});
		//btnsearch.setOnClickListener(this);
	}
	
	
	public void onListItemClick(ListView l, 
			View v, int position, long id) { 
		// call detail activity for clicked entry 
	}

	private void handleIntent(Intent intent) {
		//
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			
			//use the query to search your data somehow
			doSearch(query);
		}

	}
	
	private void doCancel()
	{
		et.setText("");
		adapter.clear();
	}
	
	private void doSearch(String queryStr) { 
		// get a Cursor, prepare the ListAdapter
		// and set it
		
		
		dbHelper = new DatabaseHelper(getApplicationContext());
		all_Item = Items.getSearchResults(dbHelper, queryStr);
		

		search_list = (ListView) findViewById(R.id.search_list);
		adapter = new SearchResultListAdaptor(this, R.layout.search_list_display, all_Item);
		for(int i=0;i<all_Item.size();i++)
		     adapter.add(all_Item.get(i));
		
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
	
	
	
	@Override
	public boolean onSearchRequested(){
		
		startSearch("onNewIntent", false, null, false);
		return true;
	}
	
	@Override
	public void onNewIntent(Intent intent){
		handleIntent(intent);
	}
	
}