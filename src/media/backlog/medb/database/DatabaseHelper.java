package media.backlog.medb.database;

import media.backlog.medb.database.Friends.FriendsEntry;
import media.backlog.medb.database.Items.ItemEntry;
import media.backlog.medb.database.ListItems.ListItemEntry;
import media.backlog.medb.database.Lists.ListEntry;
import media.backlog.medb.database.PopularItems.PopularItemsEntry;
import media.backlog.medb.database.TrendingAmongFriends.TrendingAmongFriendsEntry;
import media.backlog.medb.sampledata.SampleFriends;
import media.backlog.medb.sampledata.SampleItems;
import media.backlog.medb.sampledata.SampleListItems;
import media.backlog.medb.sampledata.SampleLists;
import media.backlog.medb.sampledata.SampleTrending;
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
    	loadSampleLists(db);
    	loadSampleListItems(db);
    	loadSampleItems(db);
    	loadSampleFriends(db);
    	loadSampleTrendingAmongFriends(db);
    	loadSamplePopularItems(db);
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
    
    public void loadSampleLists(SQLiteDatabase db)
    {
    	db.execSQL(SampleLists.list1);
    	db.execSQL(SampleLists.list2);
    	db.execSQL(SampleLists.list3);
    	db.execSQL(SampleLists.list4);
    	db.execSQL(SampleLists.list5);
    	db.execSQL(SampleLists.list6);
    	db.execSQL(SampleLists.list7);
    	db.execSQL(SampleLists.list8);
    	db.execSQL(SampleLists.list9);
    	db.execSQL(SampleLists.list10);
    	db.execSQL(SampleLists.list11);
    	db.execSQL(SampleLists.list12);
    	db.execSQL(SampleLists.list13);
    	db.execSQL(SampleLists.list14);
    	db.execSQL(SampleLists.list15);
    }
    
    public void loadSampleListItems(SQLiteDatabase db)
    {
    	db.execSQL(SampleListItems.listItem1);
    	db.execSQL(SampleListItems.listItem2);
    	db.execSQL(SampleListItems.listItem3);
    	db.execSQL(SampleListItems.listItem4);
    	db.execSQL(SampleListItems.listItem5);
    	db.execSQL(SampleListItems.listItem6);
    	db.execSQL(SampleListItems.listItem7);
    	db.execSQL(SampleListItems.listItem8);
    	db.execSQL(SampleListItems.listItem9);
    	db.execSQL(SampleListItems.listItem10);
    	db.execSQL(SampleListItems.listItem11);
    	db.execSQL(SampleListItems.listItem12);
    	db.execSQL(SampleListItems.listItem13);
    	db.execSQL(SampleListItems.listItem14);
    	db.execSQL(SampleListItems.listItem15);
    	db.execSQL(SampleListItems.listItem16);
    	db.execSQL(SampleListItems.listItem17);
    	db.execSQL(SampleListItems.listItem18);
    	db.execSQL(SampleListItems.listItem19);
    	db.execSQL(SampleListItems.listItem20);
    	db.execSQL(SampleListItems.listItem21);
    	db.execSQL(SampleListItems.listItem22);
    	db.execSQL(SampleListItems.listItem23);
    	db.execSQL(SampleListItems.listItem24);
    	db.execSQL(SampleListItems.listItem25);
    	db.execSQL(SampleListItems.listItem26);
    	db.execSQL(SampleListItems.listItem27);
    	db.execSQL(SampleListItems.listItem28);
    	db.execSQL(SampleListItems.listItem29);
    	db.execSQL(SampleListItems.listItem30);
    	db.execSQL(SampleListItems.listItem31);
    	db.execSQL(SampleListItems.listItem32);
    	db.execSQL(SampleListItems.listItem33);
    	db.execSQL(SampleListItems.listItem34);
    	db.execSQL(SampleListItems.listItem35);
    	db.execSQL(SampleListItems.listItem36);
    	db.execSQL(SampleListItems.listItem37);
    	db.execSQL(SampleListItems.listItem38);
    	db.execSQL(SampleListItems.listItem39);
    	db.execSQL(SampleListItems.listItem40);
    	db.execSQL(SampleListItems.listItem41);
    	db.execSQL(SampleListItems.listItem42);
    	db.execSQL(SampleListItems.listItem43);
    	db.execSQL(SampleListItems.listItem44);
    	db.execSQL(SampleListItems.listItem45);
    	db.execSQL(SampleListItems.listItem46);
    	db.execSQL(SampleListItems.listItem47);
    	db.execSQL(SampleListItems.listItem48);
    }
    
    public void loadSampleItems(SQLiteDatabase db)
    {
    	db.execSQL(SampleItems.item0);
    	db.execSQL(SampleItems.item1);
    	db.execSQL(SampleItems.item2);
    	db.execSQL(SampleItems.item3);
    	db.execSQL(SampleItems.item4);
    	db.execSQL(SampleItems.item5);
    	db.execSQL(SampleItems.item6);
    	db.execSQL(SampleItems.item7);
    	db.execSQL(SampleItems.item8);
    	db.execSQL(SampleItems.item9);
    	db.execSQL(SampleItems.item10);
    	db.execSQL(SampleItems.item11);
    	db.execSQL(SampleItems.item12);
    	db.execSQL(SampleItems.item13);
    	db.execSQL(SampleItems.item14);
    	db.execSQL(SampleItems.item15);
    	db.execSQL(SampleItems.item16);
    	db.execSQL(SampleItems.item17);
    	db.execSQL(SampleItems.item18);
    	db.execSQL(SampleItems.item19);
    	db.execSQL(SampleItems.item20);
    	db.execSQL(SampleItems.item21);
    	db.execSQL(SampleItems.item22);
    	db.execSQL(SampleItems.item23);
    	db.execSQL(SampleItems.item24);
    	db.execSQL(SampleItems.item25);
    	db.execSQL(SampleItems.item26);
    	db.execSQL(SampleItems.item27);
    	db.execSQL(SampleItems.item28);
    	db.execSQL(SampleItems.item29);
    	db.execSQL(SampleItems.item30);
    	db.execSQL(SampleItems.item31);
    	db.execSQL(SampleItems.item32);
    	db.execSQL(SampleItems.item33);
    	db.execSQL(SampleItems.item34);
    	db.execSQL(SampleItems.item35);
    	db.execSQL(SampleItems.item36);
    	db.execSQL(SampleItems.item37);
    	db.execSQL(SampleItems.item38);
    	db.execSQL(SampleItems.item39);
    	db.execSQL(SampleItems.item40);
    	db.execSQL(SampleItems.item41);
    	db.execSQL(SampleItems.item42);
    	db.execSQL(SampleItems.item43);
    	db.execSQL(SampleItems.item44);
    	db.execSQL(SampleItems.item45);
    	db.execSQL(SampleItems.item46);
    	db.execSQL(SampleItems.item47);
    	db.execSQL(SampleItems.item48);
    	db.execSQL(SampleItems.item49);
    	db.execSQL(SampleItems.item50);
    	db.execSQL(SampleItems.item51);
    	db.execSQL(SampleItems.item52);
    	db.execSQL(SampleItems.item53);
    }
    
    public void loadSampleFriends(SQLiteDatabase db)
    {
    	db.execSQL(SampleFriends.friend1);
    	db.execSQL(SampleFriends.friend2);
    	db.execSQL(SampleFriends.friend3);
    	db.execSQL(SampleFriends.friend4);
    	db.execSQL(SampleFriends.friend5);
    	db.execSQL(SampleFriends.friend6);
    	db.execSQL(SampleFriends.friend7);
    	db.execSQL(SampleFriends.friend8);
    }
    
    public void loadSampleTrendingAmongFriends(SQLiteDatabase db)
    {
    	db.execSQL(SampleTrending.trend1);
    	db.execSQL(SampleTrending.trend2);
    	db.execSQL(SampleTrending.trend3);
    	db.execSQL(SampleTrending.trend4);
    	db.execSQL(SampleTrending.trend5);
    	db.execSQL(SampleTrending.trend6);
    	db.execSQL(SampleTrending.trend7);
    	db.execSQL(SampleTrending.trend8);
    	db.execSQL(SampleTrending.trend9);
    	db.execSQL(SampleTrending.trend10);
    	db.execSQL(SampleTrending.trend11);
    	db.execSQL(SampleTrending.trend12);
    	db.execSQL(SampleTrending.trend13);
    	db.execSQL(SampleTrending.trend14);
    	db.execSQL(SampleTrending.trend15);
    	db.execSQL(SampleTrending.trend16);
    	db.execSQL(SampleTrending.trend17);
    	db.execSQL(SampleTrending.trend18);
    	db.execSQL(SampleTrending.trend19);
    	db.execSQL(SampleTrending.trend20);
    	db.execSQL(SampleTrending.trend21);
    	db.execSQL(SampleTrending.trend22);
    	db.execSQL(SampleTrending.trend23);
    	db.execSQL(SampleTrending.trend24);
    	db.execSQL(SampleTrending.trend25);
    	db.execSQL(SampleTrending.trend26);
    	db.execSQL(SampleTrending.trend27);
    	db.execSQL(SampleTrending.trend28);
    	db.execSQL(SampleTrending.trend29);
    	db.execSQL(SampleTrending.trend30);
    	db.execSQL(SampleTrending.trend31);
    	db.execSQL(SampleTrending.trend32);
    	db.execSQL(SampleTrending.trend33);
    	db.execSQL(SampleTrending.trend34);
    	db.execSQL(SampleTrending.trend35);
    	db.execSQL(SampleTrending.trend36);
 
    }
    
    public void loadSamplePopularItems(SQLiteDatabase db)
    {
    	
    }
}