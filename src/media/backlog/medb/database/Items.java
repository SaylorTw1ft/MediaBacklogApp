package media.backlog.medb.database;

import android.provider.BaseColumns;

public final class Items
{
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public Items() {}

    /* Inner class that defines the table contents */
    public static abstract class ItemEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Items";
        public static final String COLUMN_NAME_ITEMID = "itemID";
        public static final String COLUMN_NAME_ITEMNAME = "itemName";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_PICTURE = "picture";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + ItemEntry.TABLE_NAME + " (" +
    	    ItemEntry.COLUMN_NAME_ITEMID + " INTEGER PRIMARY KEY," +
    	    ItemEntry.COLUMN_NAME_ITEMNAME + " TEXT" + "," +
    	    ItemEntry.COLUMN_NAME_CATEGORY + " INTEGER," +
    	    ItemEntry.COLUMN_NAME_RATING + " INTEGER," +
    	    ItemEntry.COLUMN_NAME_PICTURE + " TEXT" +
    	    " )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;
    }
}