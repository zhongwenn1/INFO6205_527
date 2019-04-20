package main;

import java.util.Random;

/**
 *     1.createByRandomGenes initial gene(randomly) and CITY_NUM
 *     2.calFitness calculate the fitness
 *     3.printRate print the path
 */

public class Individual {

	String[] genes;
	float distance;
	float fitness;
	Individual next;
	float rate;

	/**
	 * createByRandomGenes initial gene
	 */
	Individual()
	{
		this.genes=new String[GAData.CITY_NUM];
		this.fitness=0.0f;
		this.distance=0.0f;
		this.next=null;
		rate=0.0f;
	}

	/**
	 * initial gene(randomly), calculate the fitness
	 */
	void createByRandomGenes()
	{
		//initial gene serial 1-CITY_NUM
		for(int i = 0;i < genes.length;i++)
		{
			genes[i]=Integer.toString(i+1);
		}

		//get random seed.
		Random rand=new Random();

		for(int j=0;j<genes.length;j++)
		{
			int num= j + rand.nextInt(genes.length-j);

			//swap
			String tmp;
			tmp=genes[num];
			genes[num]=genes[j];
			genes[j]=tmp;
		}
	}

	/**
	 * greedy for initial gene
	 */
	void createByGreedyGenes()
	{
		Random rand=new Random();
		int i= rand.nextInt(GAData.CITY_NUM); //random start point
		genes[0]=Integer.toString(i+1);
		int j;//end point
		int cityNum=0;
		do
		{
			cityNum++;

			//the shortest city
			float minDis=Integer.MAX_VALUE;
			int minCity=0;
			for(j=0;j<GAData.CITY_NUM;j++)
			{
				if(j != i)
				{
					//repeat or not
					boolean repeat=false;
					for(int n=0;n<cityNum;n++)
					{
						if(Integer.parseInt(genes[n]) == j+1)
						{
							repeat=true;
							break;
						}
					}
					if(repeat == false)
					{
						if(GAData.disMap[i][j] < minDis)
						{
							minDis=GAData.disMap[i][j];
							minCity=j;
						}
					}
				}
			}

			//add to Chromosome
			genes[cityNum]=Integer.toString(minCity+1);
			i=minCity;
		}while(cityNum < GAData.CITY_NUM-1);
	}

	/**
	 * 	calculate Fitness
	 */
	void calFitness()
	{
		float totalDis=0.0f;
		for(int i = 0;i < GAData.CITY_NUM;i++)
		{
			int curCity=Integer.parseInt(this.genes[i])-1;
			int nextCity=Integer.parseInt(this.genes[(i+1) % GAData.CITY_NUM])-1;

			totalDis += GAData.disMap[curCity][nextCity];
		}

		this.distance=totalDis;
		this.fitness=1.0f/totalDis;
	}

	/**
	 * deep copy
	 */
	public Individual clone()
	{   
		Individual species=new Individual();

		//copy the values
		for(int i=0;i<this.genes.length;i++)
			species.genes[i]=this.genes[i];
		species.distance=this.distance;
		species.fitness=this.fitness;

		return species; 
	}

	/**
	 * print the result
	 */
	void printRate()
	{
		System.out.print("shorest Path：");
		for(int i=0;i<genes.length;i++)
			System.out.print(genes[i]+"->");
		System.out.print(genes[0]+"\n");
		System.out.print("shortest length：" + distance);
	}
	
	/**
	 * get the best distance
	 * @return distance
	 */
	public float getDistance() {
		return distance;
	}
}