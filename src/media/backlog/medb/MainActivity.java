package media.backlog.medb;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button movie = (Button) findViewById(R.id.movie_button);
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrganizeActivity.class);
                Bundle b = new Bundle();
                b.putInt("category", 1);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        Button game = (Button) findViewById(R.id.game_button);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrganizeActivity.class);
                Bundle b = new Bundle();
                b.putInt("category", 2);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        Button book = (Button) findViewById(R.id.book_button);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrganizeActivity.class);
                Bundle b = new Bundle();
                b.putInt("category", 3);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        Button music = (Button) findViewById(R.id.music_button);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrganizeActivity.class);
                Bundle b = new Bundle();
                b.putInt("category", 4);
                intent.putExtras(b);
                startActivity(intent);
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

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

    	float eventX = event.getX();
    	float eventY = event.getY();

        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:{
        	
        }
        case MotionEvent.ACTION_POINTER_DOWN:
        case MotionEvent.ACTION_MOVE:
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
        case MotionEvent.ACTION_CANCEL: {
          break;
        }
        }

        return true;
    } 
    */
}
