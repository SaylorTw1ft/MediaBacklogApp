package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class RecentActivity
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public RecentActivity() {}

    /* Inner class that defines the table contents */
    public static abstract class RecentEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "RecentActivity";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        public static final String COLUMN_NAME_RECENTID = "recentID";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + RecentEntry.TABLE_NAME + " (" +
    		RecentEntry.COLUMN_NAME_ITEMID + " INTEGER PRIMARY KEY," +
    	    RecentEntry.COLUMN_NAME_RECENTID + " INTEGER" +
    		" )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + RecentEntry.TABLE_NAME;
    }
    
    public static ArrayList<MediaItem> getRecentActivity(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		RecentEntry.COLUMN_NAME_ITEMID,
    		RecentEntry.COLUMN_NAME_RECENTID
    	    };
    	
    	String sortOrder = RecentEntry.COLUMN_NAME_RECENTID + ")" + " ASC";

    	Cursor cursor = db.query(
			RecentEntry.TABLE_NAME,  	  // The table to query
    	    projection,                               // The columns to return
    	    null,  									  // The columns for the WHERE clause
    	    null,                            		  // The values for the WHERE clause
    	    null,									  // don't group the rows
    	    null,                                     // don't filter by row groups
    	    sortOrder                                 // The sort order
    	    );
    	
    	ArrayList<MediaItem> items = new ArrayList<MediaItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int itemID = cursor.getInt(cursor.getColumnIndex(RecentEntry.COLUMN_NAME_ITEMID));
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
    
    public static void addRecentItem(DatabaseHelper dbHelper, int itemID)
    {
    	RecentActivity.removeOldestItem(dbHelper);
    	RecentActivity.updateAllRecentItems(dbHelper);
    	
    	SQLiteDatabase db = dbHelper.getWritableDatabase();

    	ContentValues values = new ContentValues();
    	values.put(RecentEntry.COLUMN_NAME_ITEMID, itemID);
    	values.put(RecentEntry.COLUMN_NAME_RECENTID, 1);

    	// Insert the new row, returning the primary key value of the new row
    	db.insert(RecentEntry.TABLE_NAME, null, values);
    }
    
    private static void removeOldestItem(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
    	String selection = RecentEntry.COLUMN_NAME_RECENTID + " = ?";
    	String[] selectionArgs = { String.valueOf(10) };
    	db.delete(RecentEntry.TABLE_NAME, selection, selectionArgs);
    }
    
    private static void updateAllRecentItems(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	
    	for(int i = 1; i < 10; i++)
    	{
    		// New value for one column
    		ContentValues values = new ContentValues();
    		values.put(RecentEntry.COLUMN_NAME_RECENTID, i + 1);

    		// Which row to update, based on the ID
    		String selection = RecentEntry.COLUMN_NAME_RECENTID + " = ?";
    		String[] selectionArgs = { String.valueOf(i) };

    		db.update(
    		    RecentEntry.TABLE_NAME,
    		    values,
    		    selection,
    		    selectionArgs);
    	}
    }
}
