package media.backlog.medb.sampledata;

public class SampleItems {
	/*
	 * Movies - 1
	 * Games - 2
	 * books - 3
	 * music - 4
	 * 
	 * "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)
	 * 		VALUES (itemId, itemName, category, genre, rating, picture)";
	 */
	
	
	//MOVIES
	String inception = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (0, 'Inception', 1, 'thriller', 4, 'defaultimage')";
	String thor = "INSERT INTO Items(itemId, itemName, category, genre, rating, picture)" +
			"VALUES (1, 'Thor', 1, 'action', 4, 'defaultimage')";
	String theDarkKnight = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (2, 'The Dark Knight', 1, 'action', 4, 'defaultimage')";
	String monstersUniversity = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (3, 'Monsters University', 1, 'comedy', 4, 'defaultimage')";
	String elysium = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (4, 'Elysium', 1, 'action', 4, 'defaultimage')";
	String theGreatGatsby = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (5, 'The Great Gatsby', 1, 'drama', 4, 'defaultimage')";
	String despicableMe2 = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (6, 'Despicable Me 2', 1, 'comedy', 4, 'defaultimage')";
	String thisIsTheEnd = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (7, 'This Is The End', 1, 'comedy', 4, 'defaultimage')";
	String ironMan3 = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (8, 'Iron Man 3', 1, 'action', 4, 'defaultimage')";
	String theFiveYearEngagement = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (9, 'The Five-Year Engagement', 1, 'romantic-comedy', 4, 'defaultimage')";
	String friendsWithBenefits = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (10, 'Friends With Benefits', 1, 'romantic-comedy', 4, 'defaultimage')";
	String crazyStupidLove = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (11, 'Crazy, Stupid, Love', 1, 'romantic-comedy', 4, 'defaultimage')";
	String noStringsAttached = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (12, 'No Strings Attached', 1, 'romantic-comedy', 4, 'defaultimage')";
	String somethingBorrowed = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture) " +
			"VALUES (13, 'Something Borrowed', 1, 'romantic-comedy', 4, 'defaultimage')";
	String hungerGames = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (38, 'The Hunger Games', 1, 'action', 4, 'defaultimage')";
	String catchFire = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (39, 'The Hunger Games: Catching Fire', 1, 'action', 5, 'defaultimage')";
	
	//GAMES
	String gtaFive = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (14, 'Grand Theft Auto V', 2, 'action', 5, 'defaultimage')";
	String zelda = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (15, 'The Legend of Zelda: A Link Between Worlds', 2," +
			" 'adventure', 5, 'defaultimage')";
	String battlefield = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (16, 'Battlefield 4', 2, 'shooter', 5, 'defaultimage')";
	String ghosts = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (17, 'Call of Duty: Ghosts', 2, 'shooter', 2, 'defaultimage')";
	String pokemonX = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (18, 'Pokemon X', 2, 'adventure', 5, 'defaultimage')";
	String pokemonY = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (19, 'Pokemon Y', 2, 'action', 5, 'defaultimage')";
	String fifa = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (20, 'FIFA 14', 2, 'sports', 4, 'defaultimage')";
	String nba2k = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (21, 'NBA 2K14', 2, 'sports', 4, 'defaultimage')";
	String nhl = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (22, 'NHL 14', 2, 'sports', 4, 'defaultimage')";
	String assassinsCreed = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (23, 'Assassin's Creed IV: Black Flag', 2, 'action', 5, 'defaultimage')";
	String forza = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (24, 'Forza Motorsport 5', 2, 'racing', 4, 'defaultimage')";
	String needForSpeed = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (25, 'Need for Speed: Rivals', 2, 'racing', 3, 'defaultimage')";
	String granTurismo = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (26, 'Gran Turismo 6', 2, 'racing', 5, 'defaultimage')";
	//matrix batman
	//BOOKS
	String greatGatsby = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (27, 'The Great Gatsby', 3, 'drama', 5, 'defaultimage')";
	String catch22 = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (28, 'Catch-22', 3, 'satire', 5, 'defaultimage')";
	String mockingbird = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (29, 'To Kill A Mockingbird', 3, 'fiction', 5, 'defaultimage')";
	String nineteen = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (30, '1984', 3, 'fiction', 5, 'defaultimage')";
	String ulysses = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (31, 'Ulysses', 3, 'fiction', 5, 'defaultimage')";
	String batman = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (32, 'Batman', 3, 'comic', 5, 'defaultimage')";
	String darkKnight = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (33, 'Legends of the Dark Knight', 3, 'comic', 5, 'defaultimage')";
	String batmanShadow = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (34, 'Batman: Shadow of the Bat', 3, 'comic', 5, 'defaultimage')";
	String theHungerGames = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (35, 'The Hunger Games', 3, 'fiction', 5, 'defaultimage')";
	String catchingFire = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (36, 'Catching Fire', 3, 'fiction', 5, 'defaultimage')";
	String MockingJay = "INSERT INTO Items (itemId, itemName, category, genre, rating, picture)" +
			"VALUES (37, 'Mockingjay', 3, 'fiction', 5, 'defaultimage')";
	
	//MUSIC
	
	
}
