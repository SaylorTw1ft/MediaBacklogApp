package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.PopularItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class PopularItems
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public PopularItems() {}

    /* Inner class that defines the table contents */
    public static abstract class PopularItemsEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "PopularItems";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        public static final String COLUMN_NAME_HITS = "hits";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + PopularItemsEntry.TABLE_NAME + " (" +
    		PopularItemsEntry.COLUMN_NAME_ITEMID + " INTEGER PRIMARY KEY," +
    		PopularItemsEntry.COLUMN_NAME_HITS + " INTEGER" + 
    		" )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + PopularItemsEntry.TABLE_NAME;
    }
    
    public static ArrayList<PopularItem> getPopularItems(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		PopularItemsEntry.COLUMN_NAME_ITEMID,
    		PopularItemsEntry.COLUMN_NAME_HITS
    	    };
    	
    	String sortOrder = PopularItemsEntry.COLUMN_NAME_HITS + " DESC";

    	Cursor cursor = db.query(
			PopularItemsEntry.TABLE_NAME,  	  // The table to query
    	    projection,                               // The columns to return
    	    null,  									  // The columns for the WHERE clause
    	    null,                            		  // The values for the WHERE clause
    	    null,									  // don't group the rows
    	    null,                                     // don't filter by row groups
    	    sortOrder                                 // The sort order
    	    );
    	
    	ArrayList<PopularItem> popularItems = new ArrayList<PopularItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int itemID = cursor.getInt(cursor.getColumnIndex(PopularItemsEntry.COLUMN_NAME_ITEMID));
        		int hits = cursor.getInt(cursor.getColumnIndex(PopularItemsEntry.COLUMN_NAME_HITS));
        		MediaItem mediaItem = Items.getItem(dbHelper, itemID);
            	PopularItem item = new PopularItem(itemID);
            	item.copyFromMediaItem(mediaItem);
            	item.setHits(hits);
        		popularItems.add(item);
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
    	
    	return popularItems;
    }
}
