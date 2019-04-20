package main;

public class Population {

	Individual head; // head point
	int speciesNum; // number of species

	/**
	 * default constructor
	 */
	public Population()
	{
		head=new Individual();
		speciesNum=GAData.SPECIES_NUM;
	}

	/**
	 * add species
	 * @param species
	 */
	void add(Individual species)
	{
		Individual point=head; // cursor
		while(point.next != null) // tail
			point=point.next;
		point.next=species;
	}
}