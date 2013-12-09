package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import media.backlog.medb.data.TrendingItem;

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
    
    public static ArrayList<TrendingItem> getTrendingAmongFriendsItems(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID,
			"COUNT(" + TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID + ")"
    	    };

    	// How you want the results sorted in the resulting Cursor
    	String sortOrder = "COUNT(" + TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID + ")" + " DESC";

    	Cursor cursor = db.query(
			TrendingAmongFriendsEntry.TABLE_NAME,  	  // The table to query
    	    projection,                               // The columns to return
    	    null,    								  // The columns for the WHERE clause
    	    null,                            		  // The values for the WHERE clause
    	    TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID, // don't group the rows
    	    null,                                     // don't filter by row groups
    	    sortOrder                                 // The sort order
    	    );
    	
    	ArrayList<TrendingItem> trendingItems = new ArrayList<TrendingItem>();
    	cursor.moveToFirst();
    	
    	while(true)
    	{
    		int itemID = cursor.getInt(cursor.getColumnIndex(TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID));
    		trendingItems.add(TrendingAmongFriends.getTrendingItem(dbHelper, itemID));
    		
    		if(cursor.moveToNext() == false)
    		{
    			break;
    		}
    	}
    	
    	return trendingItems;
    }
    
    private static TrendingItem getTrendingItem(DatabaseHelper dbHelper, int itemID)
    {
    	MediaItem mediaItem = Items.getItem(dbHelper, itemID);
    	TrendingItem item = new TrendingItem(itemID);
    	item.copyFromMediaItem(mediaItem);
    	
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    		TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID
    	    };
    	
    	String[] selectionArgs = { String.valueOf(itemID) };

    	Cursor cursor = db.query(
			TrendingAmongFriendsEntry.TABLE_NAME,  	  // The table to query
    	    projection,                               // The columns to return
    	    TrendingAmongFriendsEntry.COLUMN_NAME_ITEMID + " = ?",  // The columns for the WHERE clause
    	    selectionArgs,                            		  // The values for the WHERE clause
    	    null,									  // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int friendID = cursor.getInt(cursor.getColumnIndex(TrendingAmongFriendsEntry.COLUMN_NAME_FRIENDID));
        		item.addFriend(Friends.getFriend(dbHelper, friendID));
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
    	
    	return item;
    }
}