package media.backlog.medb.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class TrendingAmongFriends
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public TrendingAmongFriends() {}

    /* Inner class that defines the table contents */
    public static abstract class TrendingAmongFriendsEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "TrendingAmongFriends";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        public static final String COLUMN_NAME_FRIENDID = "friendID";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + TrendingAmongFriendsEntry.TABLE_NAME + " (" +
    		TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID + " INTEGER," +
    		TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID + " INTEGER," +
    	    " PRIMARY KEY (" + TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID + ", " + 
    	    TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID + ") )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + TrendingAmongFriendsEntry.TABLE_NAME;
    }
    
    public static int getTrendingItems(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID,
			TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID
    	    };
    	
    	//String[] selectionArgs = { String.valueOf(listID) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
			TrendingAmongFriendsEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID + " = ?",    // The columns for the WHERE clause
    	    null,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	return cursor.getCount();
    }
}