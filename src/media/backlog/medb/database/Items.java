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
    
    public static ArrayList<MediaItem> getSearchResults(DatabaseHelper dbHelper, String searchString)
    {
    	SQLiteDatabase db = dbHelper.getReadableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    ItemEntry.COLUMN_NAME_ITEMID
    	    };
    	
    	String[] selectionArgs = { String.valueOf(searchString) };

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
    	
    	ArrayList<Integer> resultIDs = new ArrayList<Integer>();
    	ArrayList<MediaItem> searchResults = new ArrayList<MediaItem>();
    	
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
    		
    		for(MediaItem item : searchResults)
    		{
    			String genre = item.getGenre();
    			
    			String[] itemProjection = {
					ItemEntry.COLUMN_NAME_ITEMID,
		    	    ItemEntry.COLUMN_NAME_ITEMNAME,
		    	    ItemEntry.COLUMN_NAME_CATEGORY,
		    	    ItemEntry.COLUMN_NAME_GENRE,
		    	    ItemEntry.COLUMN_NAME_RATING,
		    	    ItemEntry.COLUMN_NAME_PICTURE
		    	    };
    		    	
    		    	String[] itemSelectionArgs = { genre };

    		    	// How you want the results sorted in the resulting Cursor
    		    	//String sortOrder = FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    		    	Cursor itemCursor = db.query(
    		    	    ItemEntry.TABLE_NAME,  // The table to query
    		    	    itemProjection,                               // The columns to return
    		    	    ItemEntry.COLUMN_NAME_GENRE + " = ?",    // The columns for the WHERE clause
    		    	    itemSelectionArgs,                            // The values for the WHERE clause
    		    	    null,                                     // don't group the rows
    		    	    null,                                     // don't filter by row groups
    		    	    null                                 // The sort order
    		    	    );
    		    	
    		    	if(itemCursor.moveToFirst())
    		    	{
    		    		while(true)
    		    		{
    		    			int searchItemID = cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMID));
    		    			if(resultIDs.get(searchItemID) < 0)
    		    			{
    		    				MediaItem searchItem = new MediaItem(searchItemID);
    		    				searchItem.setItemName(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_ITEMNAME)));
    		    				searchItem.setCategory(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_CATEGORY)));
    		    				searchItem.setGenre(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_GENRE)));
    		    				searchItem.setRating(cursor.getInt(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_RATING)));
    		    				searchItem.setPicture(cursor.getString(cursor.getColumnIndex(ItemEntry.COLUMN_NAME_PICTURE)));
    		    				
    		    				resultIDs.add(searchItemID);
    		    				searchResults.add(searchItem);
    		    			}
    		    			
    		    			if(cursor.moveToNext() == false)
		            		{
		            			break;
		            		}
    		    		}
    		    	}
    		}
    	}
    	
    	return searchResults;
    }
    
    public static ArrayList<MediaItem> getSimilarItems(DatabaseHelper dbHelper, MediaItem item)
    {
    	//TODO: Implement this function
    	return new ArrayList<MediaItem>();
    }
}