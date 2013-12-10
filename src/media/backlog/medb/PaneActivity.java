package media.backlog.medb;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Arin on 11/28/13.
 */
public class PaneActivity extends Activity {
	
	public void setUpPage(int category){
        //Movies
        if (category == 1)
        	setTitle("Movies");
        //Games
        if(category == 2)
        	setTitle("Games");
        //Books
        if(category == 3)
        	setTitle("Music");
        //Music
        if(category == 4)
        	setTitle("Books");
	}
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pane);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        int category = b.getInt("category");
        setUpPage(category);
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

    public void addList() {
    }
}