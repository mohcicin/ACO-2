import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;


public class ReceiveMessagesBehavior extends CyclicBehaviour{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AntColonyInfo antColonyInfo;
	private AntInfo antInfo;
	public ReceiveMessagesBehavior()
	{
		System.out.println("Initializing the receiveMessageBehavior...");
		this.antColonyInfo = null;
		this.antInfo = null;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("Waiting for a message...");
        ACLMessage msg = myAgent.receive();
        if (msg == null) { block(); return; }
          Object content = null;
		try {
			content = msg.getContentObject();
			System.out.println("Message received!");

			switch (msg.getPerformative()) 
	          {
	          
	          case (ACLMessage.INFORM):
	              if (content instanceof AntColonyInfo)
	            	  antColonyInfo = (AntColonyInfo)content;
	         //         myAgent.addBehaviour(new UpdateBehavior((AntColonyInfo)content));
	              else if (content instanceof AntInfo)
	            	  antInfo = (AntInfo)content;
	              else 
	            	  System.out.print("Unable to parse message");
	          break;
	          }
			if (antColonyInfo != null && antInfo != null)
			{
				myAgent.addBehaviour(new UpdateBehavior(antColonyInfo));
			}
		} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

          

        	
	}}
