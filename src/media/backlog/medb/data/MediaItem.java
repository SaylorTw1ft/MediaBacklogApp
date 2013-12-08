package media.backlog.medb.data;

public class MediaItem
{
	private int itemID;
	private String itemName;
	private int category;
	private String genre;
	private int rating;
	private String picture;
	
	public MediaItem(int itemID)
	{
		this.itemID = itemID;
	}
	
	public int getItemID()
	{
		return itemID;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setCategory(int category)
	{
		this.category = category;
	}
	
	public int getCategory()
	{
		return category;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	
	public String getGenre()
	{
		return genre;
	}
	
	public void setRating(int rating)
	{
		this.rating = rating;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public void setPicture(String picture)
	{
		this.picture = picture;
	}
	
	public String getPicture()
	{
		return picture;
	}
}
