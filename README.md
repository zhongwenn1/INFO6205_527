# INFO6205_527
Genetic algorithms for the traveling salesman problem  
## GA algorithm Solving TSP problem:  
### Name:	Weiyi Lan 001822514  
### Wen Zhong 001495774  
### Advisor: Dr. Robin Hillyard  
### Problem description:  
The travelling salesman problem (TSP ) asks the following question: "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city and returns to the origin city?" It is an NP-hard problem in combinatorial optimization, important in operations research and theoretical computer science.
The travelling purchaser problem and the vehicle routing problem are both generalizations of TSP.

### Program structure:


4 java file and one test case;  

Structure in each java file
![image](https://github.com/zhongwenn1/INFO6205_527/blob/master/image/GA.PNG)

Code Analysis:
GAData.java:
Set the parameters and the cityPosition map.
Individual.java:
Set the gene, distance and the fitness.
Use the greedy algorithm
 
Population.java:

User Guide:
	static int CITY_NUM; //number of citys
	static final int SPECIES_NUM=200; //number of species
	static final int GENERATE_NUM=10000; //number of generation
	static final float pcl=0.6f,pch=0.95f;//number of crossover
	static final float pm=0.4f;//number of mutation
	static final float[][] disMap; //data of map

We can set the parameters to get the different results.
 
Then set the city position, just like the coordinate, take 10 cities as an example, we set the 10 different positions into an array and get to result.
We can also write these data into a file and use the data with IO inputStream.
We can also get random map.




Test Result:
 
