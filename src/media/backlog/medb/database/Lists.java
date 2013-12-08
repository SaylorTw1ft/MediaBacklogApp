package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public final class Lists
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Lists() {}

    /* Inner class that defines the table contents */
    public static abstract class ListEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Lists";
        public static final String COLUMN_NAME_LISTID = "listID";
        public static final String COLUMN_NAME_LISTNAME = "listName";
        public static final String COLUMN_NAME_MOVIE = "movie";
        public static final String COLUMN_NAME_GAME = "game";
        public static final String COLUMN_NAME_MUSIC = "music";
        public static final String COLUMN_NAME_BOOK = "book";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + ListEntry.TABLE_NAME + " (" +
    	    ListEntry.COLUMN_NAME_LISTID + " INTEGER PRIMARY KEY," +
    	    ListEntry.COLUMN_NAME_LISTNAME + " TEXT" + "," +
    	    ListEntry.COLUMN_NAME_MOVIE + " INTEGER," +
    	    ListEntry.COLUMN_NAME_GAME + " INTEGER," +
    	    ListEntry.COLUMN_NAME_MUSIC + " INTEGER," +
    	    ListEntry.COLUMN_NAME_BOOK + " INTEGER" +
    	    " )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + ListEntry.TABLE_NAME;
    }
    
    public static ArrayList<MediaList> getAllLists(DatabaseHelper dbHelper)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	
    	String[] projection = {
			ListEntry.COLUMN_NAME_LISTID
    	};
    	
    	Cursor cursor = db.query(
			ListEntry.TABLE_NAME,
			projection,
			null,
			null,
			null,
			null,
			null
			);
    	
    	ArrayList<MediaList> allLists = new ArrayList<MediaList>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
        		int listID = cursor.getInt(cursor.getColumnIndex(ListEntry.COLUMN_NAME_LISTID));
        		allLists.add(Lists.getList(dbHelper, listID));
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
    	
    	return allLists;
    }
    
    public static MediaList getList(DatabaseHelper dbHelper, int listID)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ListEntry.COLUMN_NAME_LISTID,
    	    ListEntry.COLUMN_NAME_LISTNAME,
    	    ListEntry.COLUMN_NAME_MOVIE,
    	    ListEntry.COLUMN_NAME_GAME,
    	    ListEntry.COLUMN_NAME_MUSIC,
    	    ListEntry.COLUMN_NAME_BOOK
    	    };
    	
    	String[] selectionArgs = { String.valueOf(listID) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ListEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ListEntry.COLUMN_NAME_LISTID + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	cursor.moveToFirst();
    	MediaList list = new MediaList(listID);
    	list.setListName(cursor.getString(cursor.getColumnIndex(ListEntry.COLUMN_NAME_LISTNAME)));
    	list.setMovie(1 == cursor.getInt(cursor.getColumnIndex(ListEntry.COLUMN_NAME_MOVIE)));
    	list.setGame(1 == cursor.getInt(cursor.getColumnIndex(ListEntry.COLUMN_NAME_GAME)));
    	list.setMusic(1 == cursor.getInt(cursor.getColumnIndex(ListEntry.COLUMN_NAME_MUSIC)));
    	list.setBook(1 == cursor.getInt(cursor.getColumnIndex(ListEntry.COLUMN_NAME_BOOK)));
    	list.setNumItems(ListItems.getItemCountForList(dbHelper, listID));
    	
    	return list;
    }
}