package media.backlog.medb;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

/**
 * Created by Arin on 12/9/13.
 */
public class ShareActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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