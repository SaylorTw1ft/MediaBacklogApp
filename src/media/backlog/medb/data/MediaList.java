package media.backlog.medb.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MediaList
{
	private int listID;
	private String listName;
	private boolean movie;
	private boolean game;
	private boolean music;
	private boolean book;
	private int numItems;
	
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
	
	public void setNumItems(int numItems)
	{
		this.numItems = numItems;
	}
	
	public int getNumItems()
	{
		return numItems;
	}
	
	public static void sortListsByNumberOfItems(ArrayList<MediaList> list)
	{
		PriorityQueue<MediaList> queue = new PriorityQueue<MediaList>(100, new Comparator<MediaList>()
		{
			@Override
			public int compare(MediaList a,MediaList b)
			{
				if(a.getNumItems() > b.getNumItems())
				{
					return -1;
				}
				else if(a.getNumItems() == b.getNumItems())
				{
					return a.getListName().compareTo(b.getListName());
				}
				else
				{
					return 1;
				}
			}
		});
		
		queue.addAll(list);
        list.clear();

        while(!queue.isEmpty())
		{
			list.add(queue.poll());
		}
	}
}
