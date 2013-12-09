package media.backlog.medb;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;

import media.backlog.medb.database.DatabaseHelper;
import media.backlog.medb.data.MediaItem;

/**
 * Created by Arin on 12/8/13.
 */
public class ItemActivity extends Activity {
    private MediaItem item;
    private DatabaseHelper helper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        setContentView(R.layout.activity_item);
        helper = new DatabaseHelper(getApplicationContext());
        item = media.backlog.medb.database.Items.getItem(helper, id);
        setItemThumbnail();
        setItemName();
        setItemGenre();
        setSimilarItems();
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setSimilarItems() {
        ArrayList<MediaItem> similarItems = media.backlog.medb.database.Items.getSimilarItems(helper, item);


    }

    private void setItemGenre() {
        TextView text = (TextView) findViewById(R.id.item_genre);
        text.setText(item.getGenre());
    }

    private void setItemName() {
        TextView text = (TextView) findViewById(R.id.item_name);
        text.setText(item.getItemName());
    }

    private void setItemThumbnail() {
        ImageView imageView = (ImageView) findViewById(R.id.item_picture);
        String path = item.getPicture();
        File drawableFile = new File(getApplicationContext().getFilesDir().getAbsolutePath() + "/res/drawable-hdpi/" + path);
        String absPath = drawableFile.getAbsolutePath();
        if (Drawable.createFromPath("C:\\\\Users\\\\Arin\\\\AndroidProjects\\\\MediaBacklogApp\\\\res\\\\drawable-hdpi\\\\movies\\\\thedarkknight.png") != null) {
            imageView.setImageDrawable(Drawable.createFromPath(absPath));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
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