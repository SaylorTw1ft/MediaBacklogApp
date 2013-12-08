package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class ListItems
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ListItems() {}

    /* Inner class that defines the table contents */
    public static abstract class ListItemEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "ListItems";
        public static final String COLUMN_NAME_LISTID = "listID";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + ListItemEntry.TABLE_NAME + " (" +
    	    ListItemEntry.COLUMN_NAME_LISTID + " INTEGER," +
    	    ListItemEntry.COLUMN_NAME_ITEMID + " INTEGER," +
    	    " PRIMARY KEY (" + ListItemEntry.COLUMN_NAME_LISTID + ", " + 
    	    ListItemEntry.COLUMN_NAME_ITEMID + ") )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + ListItemEntry.TABLE_NAME;
    }
    
    public static int getItemCountForList(DatabaseHelper dbHelper, int listID)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ListItemEntry.COLUMN_NAME_ITEMID
    	    };
    	
    	String[] selectionArgs = { String.valueOf(listID) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ListItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ListItemEntry.COLUMN_NAME_LISTID + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	return cursor.getCount();
    }
    
    public static ArrayList<MediaItem> getItemsForList(DatabaseHelper dbHelper, int listID)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	
    	String[] projection = {
			ListItemEntry.COLUMN_NAME_ITEMID
    	};
    	
    	String[] selectionArgs = { String.valueOf(listID) };
    	
    	Cursor cursor = db.query(
			ListItemEntry.TABLE_NAME,
			projection,
			ListItemEntry.COLUMN_NAME_LISTID + " = ?",
			selectionArgs,
			null,
			null,
			null
			);
    	
    	ArrayList<MediaItem> allItems = new ArrayList<MediaItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int itemID = cursor.getInt(cursor.getColumnIndex(ListItemEntry.COLUMN_NAME_ITEMID));
        		allItems.add(Items.getItem(dbHelper, itemID));
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
    	
    	return allItems;
    }
}