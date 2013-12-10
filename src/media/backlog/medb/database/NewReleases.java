package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class NewReleases
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public NewReleases() {}

    /* Inner class that defines the table contents */
    public static abstract class NewReleasesEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "NewReleases";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + NewReleasesEntry.TABLE_NAME + " (" +
    		NewReleasesEntry.COLUMN_NAME_ITEMID + " INTEGER PRIMARY KEY" +
    		" )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + NewReleasesEntry.TABLE_NAME;
    }
    
    public static ArrayList<MediaItem> getNewReleases(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		NewReleasesEntry.COLUMN_NAME_ITEMID
    	    };

    	Cursor cursor = db.query(
			NewReleasesEntry.TABLE_NAME,  	  // The table to query
    	    projection,                               // The columns to return
    	    null,  									  // The columns for the WHERE clause
    	    null,                            		  // The values for the WHERE clause
    	    null,									  // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	ArrayList<MediaItem> items = new ArrayList<MediaItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int itemID = cursor.getInt(cursor.getColumnIndex(NewReleasesEntry.COLUMN_NAME_ITEMID));
        		MediaItem item = Items.getItem(dbHelper, itemID);
        		items.add(item);
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
    	
    	return items;
    }
}
