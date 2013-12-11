package media.backlog.medb.database;

import java.util.ArrayList;

import media.backlog.medb.data.MediaItem;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
        public static final String COLUMN_NAME_GENRE = "genre";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_PICTURE = "picture";
        
        public static final String SQL_CREATE_TABLE =
    	    "CREATE TABLE " + ItemEntry.TABLE_NAME + " (" +
    	    ItemEntry.COLUMN_NAME_ITEMID + " INTEGER PRIMARY KEY," +
    	    ItemEntry.COLUMN_NAME_ITEMNAME + " TEXT," +
    	    ItemEntry.COLUMN_NAME_CATEGORY + " INTEGER," +
    	    ItemEntry.COLUMN_NAME_GENRE + " TEXT," +
    	    ItemEntry.COLUMN_NAME_RATING + " INTEGER," +
    	    ItemEntry.COLUMN_NAME_PICTURE + " TEXT" +
    	    " )";

    	public static final String SQL_DELETE_TABLE =
    	    "DROP TABLE IF EXISTS " + ItemEntry.TABLE_NAME;
    }
    
    public static MediaItem getItem(DatabaseHelper dbHelper, int itemID)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ItemEntry.COLUMN_NAME_ITEMID,
    	    ItemEntry.COLUMN_NAME_ITEMNAME,
    	    ItemEntry.COLUMN_NAME_CATEGORY,
    	    ItemEntry.COLUMN_NAME_GENRE,
    	    ItemEntry.COLUMN_NAME_RATING,
    	    ItemEntry.COLUMN_NAME_PICTURE
    	    };
    	
    	String[] selectionArgs = { String.valueOf(itemID) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ItemEntry.COLUMN_NAME_ITEMID + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	cursor.moveToFirst();
    	MediaItem item = new MediaItem(itemID);
    	item.setItemName(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMNAME)));
    	item.setCategory(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_CATEGORY)));
    	item.setGenre(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_GENRE)));
    	item.setRating(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_RATING)));
    	item.setPicture(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_PICTURE)));
    	
    	return item;
    }
    
    public static int getItemId(DatabaseHelper dbHelper, String itemName)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ItemEntry.COLUMN_NAME_ITEMID,
    	    ItemEntry.COLUMN_NAME_ITEMNAME,
    	    };
    	
    	String[] selectionArgs = { itemName };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ItemEntry.COLUMN_NAME_ITEMNAME + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	if(cursor.moveToFirst())
    	{
    		return cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMID));
    	}
    	else
    	{
    		return -1;
    	}
    }
    
    public static ArrayList<MediaItem> getSearchResults(DatabaseHelper dbHelper, String searchString)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ItemEntry.COLUMN_NAME_ITEMID
    	    };
    	
    	String[] selectionArgs = { "%" + searchString + "%" };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ItemEntry.COLUMN_NAME_ITEMNAME + " LIKE ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	ArrayList<Integer> resultIDs = new ArrayList<Integer>();
    	ArrayList<MediaItem> searchResults = new ArrayList<MediaItem>();
    	ArrayList<MediaItem> results = new ArrayList<MediaItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(true)
        	{
    			int itemID = cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMID));
    			resultIDs.add(itemID);
        		searchResults.add(Items.getItem(dbHelper, itemID));
        		
        		if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    		
    		results.addAll(searchResults);
    		
    		for(MediaItem item : searchResults)
    		{
    			ArrayList<MediaItem> similarItems = Items.getSimilarItems(dbHelper, item);
    			
    			for(MediaItem sItem : similarItems)
    			{
    				if(resultIDs.indexOf(sItem.getItemID()) < 0)
        			{
        				resultIDs.add(sItem.getItemID());
        				results.add(sItem);
        			}
    			}
    		}
    	}
    	
    	return searchResults;
    }
    
    public static ArrayList<MediaItem> getSimilarItems(DatabaseHelper dbHelper, MediaItem item)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
    	String genre = item.getGenre();
		
		String[] projection = {
			ItemEntry.COLUMN_NAME_ITEMID,
    	    ItemEntry.COLUMN_NAME_ITEMNAME,
    	    ItemEntry.COLUMN_NAME_CATEGORY,
    	    ItemEntry.COLUMN_NAME_GENRE,
    	    ItemEntry.COLUMN_NAME_RATING,
    	    ItemEntry.COLUMN_NAME_PICTURE
    	    };
	    	
    	String[] selectionArgs = { genre };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ItemEntry.COLUMN_NAME_GENRE + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	cursor.moveToFirst();
    	ArrayList<MediaItem> similarItems = new ArrayList<MediaItem>();
    	
		while(true)
		{
			int searchItemID = cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMID));
			if(searchItemID != item.getItemID())
			{
				MediaItem searchItem = new MediaItem(searchItemID);
				searchItem.setItemName(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMNAME)));
				searchItem.setCategory(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_CATEGORY)));
				searchItem.setGenre(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_GENRE)));
				searchItem.setRating(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_RATING)));
				searchItem.setPicture(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_PICTURE)));
				
				similarItems.add(searchItem);
			}
			
			if(cursor.moveToNext() == false)
    		{
    			break;
    		}
    	}
		
		return similarItems;
    }
    
    public static ArrayList<MediaItem> getPaneItems(DatabaseHelper dbHelper, int category)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		String[] projection = {
			ItemEntry.COLUMN_NAME_ITEMID,
    	    ItemEntry.COLUMN_NAME_ITEMNAME,
    	    ItemEntry.COLUMN_NAME_CATEGORY,
    	    ItemEntry.COLUMN_NAME_GENRE,
    	    ItemEntry.COLUMN_NAME_RATING,
    	    ItemEntry.COLUMN_NAME_PICTURE
    	    };
	    	
    	String[] selectionArgs = { String.valueOf(category) };

    	// How you want the results sorted in the resulting Cursor
    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    	Cursor cursor = db.query(
    	    ItemEntry.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    ItemEntry.COLUMN_NAME_CATEGORY + " = ?",    // The columns for the WHERE clause
    	    selectionArgs,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null                                 // The sort order
    	    );
    	
    	ArrayList<MediaItem> paneItems = new ArrayList<MediaItem>();
    	
    	if(cursor.moveToFirst())
    	{
    		while(paneItems.size() < 10)
    		{
    			MediaItem item = new MediaItem(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMID)));
				item.setItemName(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMNAME)));
				item.setCategory(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_CATEGORY)));
				item.setGenre(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_GENRE)));
				item.setRating(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_RATING)));
				item.setPicture(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_PICTURE)));
				
				paneItems.add(item);
    			
    			if(cursor.moveToNext() == false)
        		{
        			break;
        		}
        	}
    	}
		
		return paneItems;
    }
}