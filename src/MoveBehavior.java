
import java.util.Random;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class MoveBehavior extends OneShotBehaviour
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double p[];
	int N[]; //SET OF ALL PATHS FROM THE CITY i TO ALL ADJACENT CITIES STILL NOT VISITED BY THE ANT
	boolean citiesRelations[][];
	int [][] citiesPathLe;
	double [][] pheromones = {{0,0,6,15,0},
			  {0,0,15,5,16},
			  {20,16,0,0,0},
			  {30,25,10,0,4},
			  {23,3,22,10,0}};
	int ActualCity;
	
	AntColonyInfo antColInfo;
	AntInfo antInfo;
	Ant antAgent;
	
	static final boolean DEBUG = true;
	
	public MoveBehavior ()
	{
		super();
		System.out.println("Initialiazing the Move Behavior..");


	}
	
    public void action()
    {	
    			antAgent = ((Ant)myAgent);
    			antInfo = antAgent.getAntInfo();
    			//if (DEBUG) System.out.println("[DEBUG] AntInfo getted=");
        		citiesPathLe = antAgent.getLenghtsCities();
        		citiesRelations = new boolean[citiesPathLe.length][citiesPathLe.length];
        		
				//FILL THE CITIES REL MATRIX W/BOOLEANS
	    		System.out.println("=================CITIES REL======================");
	    		for (int i = 0; i < citiesPathLe.length; i++) {
					for (int j = 0; j < citiesPathLe.length; j++) {
						citiesRelations[i][j] = citiesPathLe[i][j] > 0;
						System.out.print(" , " + citiesRelations[i][j] + " " );
					}
					System.out.println();
				}
	    		System.out.println("=================================================");	    		
	    		int nCount = 0;
	    		
	    		//COUNT THE FUTURE LENGHT FOR N
				for (int i= 0; i < citiesRelations.length;i++)
				{
						if (citiesRelations[antAgent.getActualCityNum()][i])
							nCount ++;
				}
				p = new double[nCount];
				//FILL N WITH CORRECT PATHS
	    		int j = 0;
	    		N = new int[nCount];
	    		if (DEBUG) System.out.println("[DEBUG] ActualCity="+ antAgent.getActualCityNum());
	    		if (DEBUG) System.out.println("[DEBUG] citiesRelations.length="+ citiesRelations.length);
	    		if (DEBUG) System.out.println("[DEBUG] N.length="+ N.length);
	    		System.out.println("===========ANT REACHABLE CITIES(N)==========");

	    		for (int i =0; i < citiesRelations.length;i++) 
	    		{
	    			if (citiesRelations[antAgent.getActualCityNum()][i])
	    			{

	    				N[j] = i;
	    				j++;
	    				System.out.print( " "+ j + " " );
	    			}
				}
	    		System.out.println();
	    		System.out.println("============================================");
	    		
	    		//SUM OF THE PHEROMONES AND HEURISTIC FACTOR ALL POSIBLE PATHS	
	    		int nSum = 0;
	    		for (int elementN: N) {
		    		if (DEBUG) System.out.println("[DEBUG] elementN="+ elementN);
		    		if (DEBUG) System.out.println("[DEBUG] nSum="+ pheromones[antAgent.getActualCityNum()][elementN] * citiesPathLe[antAgent.getActualCityNum()][elementN]);
		    		if (DEBUG) System.out.println("[DEBUG] Pheromones ="+     pheromones[antAgent.getActualCityNum()][elementN]);
		    		if (DEBUG) System.out.println("[DEBUG] citiesPahtLenght ="+    citiesPathLe[antAgent.getActualCityNum()][elementN]);

		    		nSum += pheromones[antAgent.getActualCityNum()][elementN] * citiesPathLe[antAgent.getActualCityNum()][elementN];
				}
	    		System.out.println("Sum of posible paths pheromones plus Cities Path Lenghts: " + nSum);
	    		
	    		
	    		//CALCULATE P FOR ALL PATHS
	    		System.out.println("===========PROBABILITES==========");
	    		for (int i = 0; i < N.length; i++) 
	    		{
	    			p[i] = (double)(pheromones[antAgent.getActualCityNum()][N[i]] * citiesPathLe[antAgent.getActualCityNum()][N[i]])/nSum;
	    			System.out.println("PROBABILITY FOR CITY " + N[i] + "is:"+  p[i]);
				}
	    		System.out.println("===============================");
	    		this.ActualCity = randomChooose(p);
	    		fillAntColonyObj();
	    		this.antInfo.setActualCity(this.ActualCity);
	    		System.out.println("Ant new city = "+ this.ActualCity);
	    		antAgent.sendAntInfo(antInfo);
    		}
    
    private void fillAntColonyObj()
    {
    	
    	antColInfo = new AntColonyInfo();
    	antColInfo.setCitiesPathLe(citiesPathLe);
    	antColInfo.setCitiesRelations(this.citiesRelations);
    	antColInfo.setN(N);
    	antColInfo.setP(p);
    	antColInfo.setPheromones(pheromones);
    	antAgent.setAntColonyInfo(antColInfo);
    }
    //CHOOSE THE PATH RANDOMLY, TAKIN IN ACCOUNT THE WEIGHT OF THE PROBABILITIES CALCULATED BEFORE
    private int randomChooose(double p[])
    {
    	
    	int start = 0;
    	int end = 0;
    	
    	Random random = new Random();
    	int randNumb = random.nextInt(99);
    	int luckyNumb = 0;
		if (DEBUG) System.out.println("[DEBUG] randomNumb ="+ randNumb);
		if (DEBUG) System.out.println("[DEBUG] Number of ranges ="+ p.length);

    	for (int i = 0; i < p.length; i++) 
    	{
    		end = (int) ((int)start + (p[i] * 100));
    		if (DEBUG) System.out.println("[DEBUG] start ="+ start + "end = "+ end);
    		if (randNumb > start && randNumb< end)
    		{
    		  luckyNumb = N[i];
    		  break;
    		}
    		start += end;
		}
    	
    	return luckyNumb;
    	
    }

    

}