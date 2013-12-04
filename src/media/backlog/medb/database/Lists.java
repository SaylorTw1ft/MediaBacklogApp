package media.backlog.medb.database;

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
}