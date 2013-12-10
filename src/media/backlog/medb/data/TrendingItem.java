package media.backlog.medb.data;

import java.util.ArrayList;

public class TrendingItem extends MediaItem
{
	private int numFriends;
	private ArrayList<MediaFriend> friends;
	
	public TrendingItem(int itemID)
	{
		super(itemID);
		numFriends = 0;
		friends = new ArrayList<MediaFriend>();
	}
	
	public void setNumFriends(int numFriends)
	{
		this.numFriends = numFriends;
	}
	
	public int getNumFriends()
	{
		return numFriends;
	}
	
	public void addFriend(MediaFriend friend)
	{
		friends.add(friend);
		numFriends++;
	}
	
	public ArrayList<MediaFriend> getFriends()
	{
		return friends;
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
