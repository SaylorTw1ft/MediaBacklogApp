package media.backlog.medb.data;

public class MediaList
{
	private int listID;
	private String listName;
	private boolean movie;
	private boolean game;
	private boolean music;
	private boolean book;
	
	public MediaList(int listID)
	{
		this.listID = listID;
	}
	
	public int getListID()
	{
		return listID;
	}
	
	public void setListName(String listName)
	{
		this.listName = listName;
	}
	
	public String getListName()
	{
		return listName;
	}
	
	public void setMovie(boolean movie)
	{
		this.movie = movie;
	}
	
	public boolean getMovie()
	{
		return movie;
	}
	
	public void setGame(boolean game)
	{
		this.game = game;
	}
	
	public boolean getGame()
	{
		return game;
	}
	
	public void setMusic(boolean music)
	{
		this.music = music;
	}
	
	public boolean getMusic()
	{
		return music;
	}
	
	public void setBook(boolean book)
	{
		this.book = book;
	}
	
	public boolean getBook()
	{
		return book;
	}
}
