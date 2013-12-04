package media.backlog.medb.database;

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
}