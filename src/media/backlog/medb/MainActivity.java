package media.backlog.medb;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
    public void startNewIntent(int category){
        Intent intent = new Intent(MainActivity.this, PaneActivity.class);
        Bundle b = new Bundle();
        b.putInt("category", category);
        intent.putExtras(b);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView navBlock = (ImageView) findViewById(R.id.nav_block);
        /*
         * Allows user to select movie/game/music/book from the nav block image.
         */
        navBlock.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event){
        		if(event.getAction() == MotionEvent.ACTION_DOWN)
        		{
        			double x = event.getX() - 85;
        		    double y = event.getY();
        			if(y > x){
        				if(navBlock.getHeight() - y > x)
        					startNewIntent(1);
        				else
        					startNewIntent(2);
        			}
        			else{
        				if(navBlock.getHeight() - y > x)
        					startNewIntent(3);
        				else
        					startNewIntent(4);
        			}
        		}
        		return false;
        	}
        });
        //Adds in Horizontal scroll bar fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putInt("id",1);
        args.putInt("type",2);
        HorizontalScrollBar horizontalScrollBar = new HorizontalScrollBar();
        horizontalScrollBar.setArguments(args);
        fragmentTransaction.add(R.id.main_scroll_bar_container, horizontalScrollBar).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.home_action_settings:
                openSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSettings() {
    	Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
