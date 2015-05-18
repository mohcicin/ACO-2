import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


public class Ant extends Agent
{
	 private int[][] lenghtsCities = {{0,0,6,15,0},
			 				  {0,0,15,5,16},
	 						  {20,16,0,0,0},
	 						  {30,25,10,0,4},
	 						  {23,3,22,10,0}};
	 private int actualCityNum = 0;
	 
	 private AntColonyInfo antColInfo;
	 private AntInfo antInfo;
	 @Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		//REGISTER ANT ANGENT TO YELLOW PAGES
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Ant");
		sd.setName("Ant");
		dfd.addServices(sd);

		try {
		DFService.register(this, dfd);
		}
		catch (FIPAException fe) {
		fe.printStackTrace();
		}

		MoveBehavior moveBeha = new MoveBehavior();
		addBehaviour(moveBeha);
		

	}
	 public int[][] getLenghtsCities()
	 {
		 
		 return lenghtsCities;
	 }
	 
	 public int getActualCityNum()
	 {
		 return actualCityNum;
	 }
	 
	 public AntInfo getAntInfo()
	 {
		 antInfo = new AntInfo();
		 antInfo.setActualCity(actualCityNum);
		 return antInfo;
	 }
	 
	 public AntColonyInfo getAntColonyInfo()
	 {
		 antColInfo = new AntColonyInfo();
		 
		 return antColInfo;
	 }
	 
	 public void setAntColonyInfo(AntColonyInfo antColInfo)
	 {
		 if (antColInfo != null)
		 {
			 this.antColInfo = antColInfo;
			 ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			 msg.addReceiver(new AID("MyAgent", AID.ISLOCALNAME));
			 try {
				msg.setContentObject(this.antColInfo);
				System.out.println("Ant Colony Info sended Correctly");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 send(msg);
			 
		 }
		
	 }
	 
	 public void sendAntInfo(AntInfo antInfo)
	 {
		 if (antInfo != null)
		 {
			 this.antInfo = antInfo;
			 ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			 msg.addReceiver(new AID("MyAgent", AID.ISLOCALNAME));
			 try {
				msg.setContentObject(this.antInfo);
				System.out.println("Ant Colony Info sended Correctly");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 send(msg);
			 
		 }
		 
	 }
	 
	 @Override
	protected void takeDown() {
		// TODO Auto-generated method stub
		super.takeDown();
		// Deregister from the yellow pages
		try {
		DFService.deregister(this);
		}
		catch (FIPAException fe) {
		fe.printStackTrace();
		}
		System.out.println("Ant Agent"+getAID().getName()+"terminating.");

	}

}
