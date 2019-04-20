package main;

import java.util.Random;

/**
 * GA algorithm class:
 *      1.run:run the algorithm
 *      2.createBeginningSpecies:create the species
 *      3.calFitness:calculate the rate of each species to be chosen
 *      4.select:roulette strategy for choosing the species with high fitting.
 *      5.crossover:chromosome crossover
 *      6.mutate:chromosome mutation
 *      7.getBest:get the most suitable species
 */

public class GA {
	
	public GAData td;

	public GA(GAData td) {
		this.td = td;
	}
	
	/**
	 * run the algorithm
	 * @param list
	 * @return best route of list
	 */
	public Individual run(Population list)
	{
		createBeginningSpecies(list);
		for(int i=1;i<=GAData.GENERATE_NUM;i++){
			select(list);
			crossover(list);
			mutate(list);
		}
		return getBest(list);
	}
	
	/**
	 * create the species
	 * @param list
	 */
	void createBeginningSpecies(Population list) // create the initial species
	{
		// 100% random
		int randomNum=(int)(GAData.SPECIES_NUM);
		for(int i=1;i<=randomNum;i++)
		{
			Individual species=new Individual(); // create individual species
			species.createByRandomGenes(); // initial species gene
			list.add(species);
		}
	}

	/**
	 * calculate the choosing rate
	 * @param list
	 */
	void calFitness(Population list)
	{
		float totalFitness=0.0f; // total fitness
		list.speciesNum=0;
		Individual point=list.head.next; // cursor point
		while(point != null) // tail
		{
			point.calFitness(); // calculate fitness

			totalFitness += point.fitness;
			list.speciesNum++;

			point=point.next;
		}
		
		// choose rate
		point=list.head.next; // cursor point
		while(point != null) // tail
		{
			point.rate=point.fitness/totalFitness;
			point=point.next;
		}
	}

	/**
	 * choosing the species with high fitting
	 * @param list
	 */
	void select(Population list)
	{           
		calFitness(list);
		// select the most fitting species
		float talentDis=Float.MAX_VALUE;
		Individual talentSpecies=null;
		Individual point=list.head.next; // cursor

		while(point!=null)
		{
			if(talentDis > point.distance)
			{
				talentDis=point.distance;
				talentSpecies=point;
			}
			point=point.next;
		}

		// copy the best species talentNum times
		Population newSpeciesPopulation=new Population();
		int talentNum=(int)(list.speciesNum/4);
		for(int i=1;i<=talentNum;i++)
		{
			//copy species to new table
			Individual newSpecies=talentSpecies.clone();
			newSpeciesPopulation.add(newSpecies);
		}

		//list.speciesNum    talentNum times(Roulette strategy)
		int roundNum=list.speciesNum-talentNum;
		for(int i=1;i<=roundNum;i++)
		{
			float rate=(float)Math.random();

			Individual oldPoint=list.head.next;//cursor
			while(oldPoint != null && oldPoint != talentSpecies)//tail
			{
				if(rate <= oldPoint.rate)
				{
					Individual newSpecies=oldPoint.clone();
					newSpeciesPopulation.add(newSpecies);

					break;
				}
				else
				{
					rate=rate-oldPoint.rate;
				}
				oldPoint=oldPoint.next;
			}
			if(oldPoint == null || oldPoint == talentSpecies)
			{
				// copy the tail
				point=list.head;
				while(point.next != null)
					point=point.next;
				Individual newSpecies=point.clone();
				newSpeciesPopulation.add(newSpecies);
			}

		}
		list.head=newSpeciesPopulation.head;
	}

	/**
	 * chromosome crossover
	 * @param list
	 */
	void crossover(Population list)
	{
		// pcl-pch possibility
		float rate=(float)Math.random();
		if(rate > GAData.pcl && rate < GAData.pch)
		{           
			Individual point=list.head.next;
			Random rand=new Random();
			int find=rand.nextInt(list.speciesNum);
			while(point != null && find != 0)
			{
				point=point.next;
				find--;
			}

			if(point.next != null)
			{
				int begin=rand.nextInt(GAData.CITY_NUM);

				//取point和point.next进行交叉，形成新的两个染色体
				for(int i=begin;i<GAData.CITY_NUM;i++)
				{
					//找出point.genes中与point.next.genes[i]相等的位置fir
					//找出point.next.genes中与point.genes[i]相等的位置sec
					int fir,sec;
					for(fir=0;!point.genes[fir].equals(point.next.genes[i]);fir++);
					for(sec=0;!point.next.genes[sec].equals(point.genes[i]);sec++);
					//两个基因互换
					String tmp;
					tmp=point.genes[i];
					point.genes[i]=point.next.genes[i];
					point.next.genes[i]=tmp;

					//消去互换后重复的那个基因
					point.genes[fir]=point.next.genes[i];
					point.next.genes[sec]=point.genes[i];

				}
			}
		}
	}

	/**
	 * chromosome mutation
	 * @param list
	 */
	void mutate(Population list)
	{   
		// mutation possibility: pm
		Individual point=list.head.next;
		while(point != null)
		{
			float rate=(float)Math.random();
			if(rate < GAData.pm)
			{
				// find the right and left point
				Random rand=new Random();
				int left=rand.nextInt(GAData.CITY_NUM);
				int right=rand.nextInt(GAData.CITY_NUM);
				if(left > right)
				{
					int tmp;
					tmp=left;
					left=right;
					right=tmp;
				}

				// inverse the index of left and right
				while(left < right)
				{
					String tmp;
					tmp=point.genes[left];
					point.genes[left]=point.genes[right];
					point.genes[right]=tmp;

					left++;
					right--;
				}
			}
			point=point.next;
		}
	}
	
	/**
	 * get the most fitness species
	 * @param list
	 * @return
	 */
	Individual getBest(Population list)
	{
		float distance=Float.MAX_VALUE;
		Individual bestSpecies=null;
		Individual point=list.head.next; // cursor
		while(point != null) // tail
		{
			if(distance > point.distance)
			{
				bestSpecies=point;
				distance=point.distance;
			}
			point=point.next;
		}
		return bestSpecies;
	}

}