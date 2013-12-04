package media.backlog.medb.database;

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
}