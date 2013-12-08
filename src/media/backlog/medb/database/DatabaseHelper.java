package media.backlog.medb.database;

import media.backlog.medb.database.Friends.FriendsEntry;
import media.backlog.medb.database.Items.ItemEntry;
import media.backlog.medb.database.ListItems.ListItemEntry;
import media.backlog.medb.database.Lists.ListEntry;
import media.backlog.medb.database.PopularItems.PopularItemsEntry;
import media.backlog.medb.database.TrendingAmongFriends.TrendingAmongFriendsEntry;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MediaBacklogDB.db";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
    	deleteTables(db);
    	createTables(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
    	deleteTables(db);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    public void createTables(SQLiteDatabase db)
    {
    	db.execSQL(ListEntry.SQL_CREATE_TABLE);
        db.execSQL(ItemEntry.SQL_CREATE_TABLE);
        db.execSQL(ListItemEntry.SQL_CREATE_TABLE);
        db.execSQL(TrendingAmongFriendsEntry.SQL_CREATE_TABLE);
        db.execSQL(FriendsEntry.SQL_CREATE_TABLE);
        db.execSQL(PopularItemsEntry.SQL_CREATE_TABLE);
    }
    
    public void deleteTables(SQLiteDatabase db)
    {
    	db.execSQL(ListEntry.SQL_DELETE_TABLE);
        db.execSQL(ItemEntry.SQL_DELETE_TABLE);
        db.execSQL(ListItemEntry.SQL_DELETE_TABLE);
        db.execSQL(TrendingAmongFriendsEntry.SQL_DELETE_TABLE);
        db.execSQL(FriendsEntry.SQL_DELETE_TABLE);
        db.execSQL(PopularItemsEntry.SQL_DELETE_TABLE);
    }
    
    public void loadSampleData()
    {
    	
    }
}
