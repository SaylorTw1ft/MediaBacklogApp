package media.backlog.medb;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Arin on 11/28/13.
 */
public class PaneActivity extends Activity {
	
	public void setHomeBarColor(int color)
	{
        Fragment homeFrag = getFragmentManager().findFragmentById(R.id.home_bar);
        View mainView = homeFrag.getView();
        View homeView = mainView.findViewById(R.id.homeButton);
        View organizeView = mainView.findViewById(R.id.organizeButton);
        View discoverView = mainView.findViewById(R.id.discoverButton);
        View searchView = mainView.findViewById(R.id.searchButton);
        
        mainView.setBackgroundColor(color);
        homeView.setBackgroundColor(color);
        organizeView.setBackgroundColor(color);
        discoverView.setBackgroundColor(color);
        searchView.setBackgroundColor(color);
	}
	public void setUpPage(int category){
		//Movies
        if (category == 1)
        {
        	setTitle("Movies");
            setHomeBarColor(getResources().getColor(R.color.movie_color));
        }
        //Games
        if(category == 2)
        {
        	setTitle("Games");
        	setHomeBarColor(getResources().getColor(R.color.game_color));
        }
        //Music
        if(category == 3)
        {
        	setTitle("Music");
        	setHomeBarColor(getResources().getColor(R.color.music_color));
        }
        //Books
        if(category == 4)
        {
        	setTitle("Books");
        	setHomeBarColor(getResources().getColor(R.color.book_color));
        }
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