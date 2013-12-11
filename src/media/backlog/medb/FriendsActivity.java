package media.backlog.medb;

import java.util.ArrayList;

import media.backlog.medb.adapter.FriendAdapter;
import media.backlog.medb.data.MediaFriend;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Friends;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class FriendsActivity extends Activity
{
	private DatabaseHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);
		// Show the Up button in the action bar.
		setupActionBar();
		helper = new DatabaseHelper(getApplicationContext());
		ArrayList<MediaFriend> friends = Friends.getAllFriends(helper);
		ListView friendsList = (ListView) findViewById(R.id.friends_layout);
	    final FriendAdapter adapter = new FriendAdapter(this, R.layout.friends_list);
	    friendsList.setAdapter(adapter);
	    adapter.clear();
	    adapter.addAll(friends);
        adapter.notifyDataSetChanged();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
