package media.backlog.medb;

import media.backlog.medb.database.DatabaseHelper;
import android.app.Activity;
import android.os.Bundle;

public class LoginScreen extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        dbHelper.getWritableDatabase();
        setContentView(R.layout.activity_login);
    }
}