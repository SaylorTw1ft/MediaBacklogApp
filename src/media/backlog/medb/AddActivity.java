package media.backlog.medb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import media.backlog.medb.adapter.OrgListAdapter;
import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.MediaList;
import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.database.Lists;
import media.backlog.medb.database.ListItems;

/**
 * Created by Arin on 12/9/13.
 */
public class AddActivity extends Activity
{
	int itemID;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        itemID = id;
        DatabaseHelper helper = new DatabaseHelper(getApplicationContext());
        final MediaItem item = media.backlog.medb.database.Items.getItem(helper, id);
        // db connection
        final DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        final ArrayList<MediaList> lists = Lists.getAllLists(dbHelper);

        ListView add_list = (ListView) findViewById(R.id.add_activity_lists);
        final OrgListAdapter adapter = new OrgListAdapter(this,R.id.add_activity_view);

        add_list.setAdapter(adapter);
        adapter.addAll(lists);
        adapter.notifyDataSetChanged();

        add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                try {

                    MediaList list = (MediaList) parent.getItemAtPosition(position);
                    ListItems.addItem(dbHelper,list.getListID(),itemID);
                }
                catch (NullPointerException e)
                    {
                        //Error message
                    }
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }
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