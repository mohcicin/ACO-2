import java.text.DecimalFormat;

import jade.core.behaviours.OneShotBehaviour;


public class UpdateBehavior extends OneShotBehaviour{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int t; // NUMBER OF DEPOSITED PHEROMONES
	boolean[][] citiesRelations /*= {{false,false,true,true,false},
									{false,false,true,true,true},
									{true,true,false,false,false},
									{true,true,true,false,true},
									{true,true,true,true,false}}*/; 
	double[][] actualPheromones/*={{0,0,6,15,0},
								{0,0,15,5,16},
								{20,16,0,0,0},
								{30,25,10,0,4},
								{23,3,22,10,0}}*/;
	int[][] citiesPathLenght/*={{0,0,6,15,0},
							  {0,0,15,5,16},
							  {20,16,0,0,0},
							  {30,25,10,0,4},
							  {23,3,22,10,0}}*/;
	static final boolean DEBUG = false;
	public UpdateBehavior(AntColonyInfo antColInfo)
	{
		System.out.println("Initializing the update behavior..");
		this.citiesRelations = antColInfo.getCitiesRelations();
		this.actualPheromones = antColInfo.getPheromones();
		this.citiesPathLenght = antColInfo.getCitiesPathLe();
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		UpdateAgent updateAgent = (UpdateAgent)myAgent;
		int[] antNewPositions = updateAgent.getAntNewPositions();
		int[] antOldPos = updateAgent.getAntPastPositions();
		double[][] newPheromones = new double[actualPheromones.length][actualPheromones.length];
		double deltaTSum= 0;
		printOldPheromones();
		if (DEBUG) System.out.println("[DEBUG] NUMBER OF ANTS="+ antNewPositions.length);
		

		System.out.println(" ==============NEW PHEROMONES=================");
		//SUM OF PHEROMONE DEPOSITED 
		for (int i = 0; i < actualPheromones.length; i++) 
		{
			for (int j = 0; j < actualPheromones.length; j++)
			{
				deltaTSum =0;
				for (int j2 = 0; j2 < antNewPositions.length; j2++) 
				{
					if (antOldPos[j2] != antNewPositions[j2])
					if (antOldPos[j2] == i && antNewPositions[j2] == j )
					{
						deltaTSum += (double)((double)1/(double)citiesPathLenght[antOldPos[j2]][antNewPositions[j2]]);
						if (DEBUG) System.out.println("[DEBUG] DeltaTSum=" +new DecimalFormat("#0.000").format(deltaTSum) );
						if (DEBUG) System.out.println("[DEBUG] antOldPos="+ antOldPos[j2]);
						if (DEBUG) System.out.println("[DEBUG] antNewPosition="+ antNewPositions[j2]);
						if (DEBUG) System.out.println("[DEBUG] citiesPathLenght="+citiesPathLenght[antOldPos[j2]][antNewPositions[j2]]);
						
					}
				}

				newPheromones[i][j] = ((1 -0.5)* actualPheromones[i][j]) + deltaTSum;
				System.out.print("" +newPheromones[i][j]+", ");

			}
			System.out.println();
		}

		System.out.println(" ==============NEW PHEROMONES=================");

	}
	
	public void printOldPheromones()
	{
		System.out.println(" ==============OLD PHEROMONES=================");

		for (double element[] : actualPheromones) {
			for (double d : element) {
				System.out.print(" "+ d + ",");
			}
			System.out.println();

		}
		
		System.out.println("===============================");

	}

}
