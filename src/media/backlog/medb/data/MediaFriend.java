package media.backlog.medb.data;

public class MediaFriend
{
	private int friendID;
	private String friendName;
	private String picture;
	
	public MediaFriend(int friendID)
	{
		this.friendID = friendID;
	}
	
	public int getFriendID()
	{
		return friendID;
	}
	
	public void setFriendName(String friendName)
	{
		this.friendName = friendName;
	}
	
	public String getFriendName()
	{
		return friendName;
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
