package media.backlog.medb;

import media.backlog.medb.database.DatabaseHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginScreen extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.loadSampleData(db);
        setContentView(R.layout.activity_login);
        
		
		((Button) findViewById(R.layout.activity_organize)).setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
        	    Intent intent = new Intent();
        	    startActivity(intent);
            }
        });	
    }
}