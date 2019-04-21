package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.GAData;

class GATest {	

	@Test
	public void testGA1() {
		int[][] city1={{0,0},{12,32},{5,25},{8,45},{33,17},
					{25,7},{15,15},{15,25},{25,15},{41,12}}; // Best route: 147 among 10 cities
		
		assertEquals(147, GAData.runGA(city1).getDistance(), 10);
	}
	
	@Test
	public void testGA2() {
		int[][] city2={{60,100},{280,200},{90,180},{150,180},
					{30,150},{110,130},{250,180}, {170,130},
					{60,150},{120,140},{160,140},{40,60},
					{130,90},{180,60},{30,50},{200,50},
					{240,70},{40,40},{70,30},{170,30}}; // Best route: 948 among 20 cities		

		assertEquals(970, GAData.runGA(city2).getDistance(), 100);
	}
	
	@Test
	public void testGA3() {
		int[][] city3={{1303, 2311}, {3638, 1315},         
					{4176, 2243}, {3711, 1398},            
					{3487, 1534}, {3325, 1555},         
					{3237, 1228}, {4195, 1003},         
					{4311, 789}, {4385, 569},
					{3006, 1969}, {2561, 1755},
					{2787, 1490}, {2380, 1675},
					{1331, 694}, {3714, 1677},
					{3917, 2178}, {4060, 2369},
					{3779, 2211},{3675, 2577},
					{4028, 2837},{4262, 2930},
					{3428, 1907},{3506, 2366},
					{3393, 2642},{3438, 3200},
					{2934, 3239},{3139, 3549},
					{2544, 2356},{2777, 2825},
					{2369, 2974}}; // Best route: 15300 among 31 cities

		assertEquals(16000, GAData.runGA(city3).getDistance(), 1000);
	}
}
