package media.backlog.medb.database;

import media.backlog.medb.data.MediaFriend;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class Friends
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Friends() {}

    /* Inner class that defines the table contents */
    public static abstract class FriendsEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Friends";
        public static final String COLUMN_NAME_FRIENDID = "friendID";
        public static final String COLUMN_NAME_FRIENDNAME = "friendName";
        public static final String COLUMN_NAME_PICTURE = "picture";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + FriendsEntry.TABLE_NAME + " (" +
    		FriendsEntry.COLUMN_NAME_FRIENDID + " INTEGER PRIMARY KEY," +
    		FriendsEntry.COLUMN_NAME_FRIENDNAME + " TEXT," +
    	    FriendsEntry.COLUMN_NAME_PICTURE + " TEXT" +
    		" )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + FriendsEntry.TABLE_NAME;
    }
    
    public static MediaFriend getFriend(DatabaseHelper dbHelper, int friendID)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    FriendsEntry.COLUMN_NAME_FRIENDID,
    	    FriendsEntry.COLUMN_NAME_FRIENDNAME,
    	    FriendsEntry.COLUMN_NAME_PICTURE
    	    };
    	
    	String[] selectionArgs = { String.valueOf(friendID) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    FriendsEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    FriendsEntry.COLUMN_NAME_FRIENDID + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	cursor.moveToFirst();
    	MediaFriend friend = new MediaFriend(friendID);
    	friend.setFriendName(cursor.getString(cursor.getColumnIndex(FriendsEntry.COLUMN_NAME_FRIENDNAME)));
    	friend.setPicture(cursor.getString(cursor.getColumnIndex(FriendsEntry.COLUMN_NAME_PICTURE)));
    	
    	return friend;
    }
}