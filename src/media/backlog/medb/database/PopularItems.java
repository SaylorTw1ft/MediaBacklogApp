package media.backlog.medb.database;

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
}
