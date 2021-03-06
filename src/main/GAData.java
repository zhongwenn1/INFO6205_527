package main;

public class GAData {

	public static int CITY_NUM; // number of citys
	static final int SPECIES_NUM = 200; // number of species
	static final int GENERATE_NUM = 1000; // number of generation
	static final float pcl = 0.6f, pch = 0.95f; // number of crossover
	static final float pm = 0.4f; // number of mutation
	static float[][] disMap; // data of map
	
	public GAData(int[][] city){
		CITY_NUM = city.length;
		disMap = new float[CITY_NUM][CITY_NUM];
		for(int i = 0; i < CITY_NUM; i++)
		{
			for(int j = i; j < CITY_NUM; j++)
			{
				float dis = (float)Math.sqrt(Math.pow((city[i][0] - city[j][0]),2) + 
						 Math.pow((city[i][1] - city[j][1]),2));

				disMap[i][j] = dis;
				disMap[j][i] = disMap[i][j];
			}
		}  		
	}

	public static Individual runGA(int[][] cityMap) {
		GAData td = new GAData(cityMap);
		GA GA = new GA();
		Population speciesPopulation = new Population();
		Individual bestRate=GA.run(speciesPopulation);
		return bestRate;
	}
	
	public static void main(String[] args) {
		CITY_NUM = 20;
		int[][] map = new int[CITY_NUM][CITY_NUM];
		int MAX_DISTANCE = 20000;
        //Create a random map
        for (int i = 0; i < CITY_NUM; i++) {
            for (int j = 0; j < CITY_NUM; j++) {
                if (i < j) {
                	map[i][j] = (int) (Math.random() * MAX_DISTANCE);
                	map[j][i] = map[i][j];
                }
            }
        }  
        runGA(map).printRate();
	}
}
