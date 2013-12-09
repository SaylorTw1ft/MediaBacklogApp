package media.backlog.medb.data;

public class PopularItem extends MediaItem
{
	private int hits;
	
	public PopularItem(int itemID)
	{
		super(itemID);
	}
	
	public void setHits(int hits)
	{
		this.hits = hits;
	}
	
	public int getHits()
	{
		return hits;
	}
	
	public void copyFromMediaItem(MediaItem item)
	{
		setItemName(item.getItemName());
		setCategory(item.getCategory());
		setGenre(item.getGenre());
		setRating(item.getRating());
		setPicture(item.getPicture());
	}
}
