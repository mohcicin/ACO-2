
import jade.core.Agent;


public class UpdateAgent extends Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int m; // NUMBER OF ANTS
//	private int[] antNewPositions;
//	private int[] antPastPositions;

	
	@Override
	protected void setup() 
	{
		// TODO Auto-generated method stub
		super.setup();
		
		m = 1;
		System.out.println("Initializing the update behavior");
		addBehaviour(new ReceiveMessagesBehavior());
	}
	
	public int getM()
	{
		
		return m;
		
	}
	
	public int[] getAntNewPositions()
	{
		return new int[]{0};	
	}
	
	public int[] getAntPastPositions()
	{
		return new int[]{3};
	}

}
