package media.backlog.medb;

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
        
        navBlock.setOnTouchListener(new OnTouchListener()
        {
        	public boolean onTouch(View v, MotionEvent event){
        		if(event.getAction() == MotionEvent.ACTION_DOWN)
        		{
        			//Left Movies Triangle
        			
        			if(event.getX() / navBlock.getWidth() < event.getY() / navBlock.getHeight() &&
        					navBlock.getWidth() - event.getX() > navBlock.getHeight() - event.getY()){
        				startNewIntent(1);
        			}
        			//Bottom Games Triangle
        			if(event.getX() / navBlock.getWidth() < event.getY() / navBlock.getHeight() &&
        					navBlock.getWidth() - event.getX() < navBlock.getHeight() - event.getY()){
        				startNewIntent(2);
        			}
        			//Right Books Triangle
        			if(event.getX() / navBlock.getWidth() > event.getY() / navBlock.getHeight() &&
        					navBlock.getWidth() - event.getX() < navBlock.getHeight() - event.getY()){
        				startNewIntent(3);
        			}
        			//Top Music Triangle
        			if(event.getX() / navBlock.getWidth() > event.getY() / navBlock.getHeight() &&
        					navBlock.getWidth() - event.getX() > navBlock.getHeight() - event.getY()){
        				startNewIntent(4);
        			}
        		}
        		return false;
        	}
        });

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
    }

}
