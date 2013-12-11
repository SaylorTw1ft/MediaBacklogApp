package media.backlog.medb;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import media.backlog.medb.adapter.FriendAdapter;
import media.backlog.medb.data.MediaFriend;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Friends;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
//		Toast.makeText(getApplicationContext(), 
//				"updating org list: all_list size is " + Integer.toString(all_lists.size()), 
//				Toast.LENGTH_SHORT).show();
	    for(int i=0; i<friends.size(); i++)
	    {
	    	adapter.add(friends.get(i));
	    	System.out.println(friends.get(i).getFriendName());
	    }
	    
        adapter.notifyDataSetChanged();
	}
	
	private LinearLayout setUpFriendItem(View v, MediaFriend f) {
		LinearLayout item = new LinearLayout(v.getContext());
		//item.setLayoutParams(new LinearLayout.LayoutParams(width, height));
		item.setOrientation(LinearLayout.HORIZONTAL);
		ImageButton button = new ImageButton(item.getContext());
		button.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
		button.setScaleType(ScaleType.FIT_START);
		setThumbnailPic(f, button);
		TextView t = new TextView(v.getContext());
		t.setText(f.getFriendName());
		t.setTextColor(Color.BLACK);
		t.setTextSize(12);
		t.setGravity(Gravity.RIGHT);
		item.setMinimumHeight(170);
		//item.setMinimumWidth(250);
		item.addView(button);
		item.addView(t);
		return item;
		
	}
	
	public void setThumbnailPic(MediaFriend f, ImageButton button) {
		String path = f.getPicture();
		try {
			InputStream ims = getAssets().open(path);
			Drawable d = Drawable .createFromStream(ims,  null);
			button.setImageDrawable(d);
		}
		catch(IOException e) {
			
		}
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
