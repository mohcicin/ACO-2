import java.io.*;

public class AntInfo implements Serializable
{
	private int lastCity;
	private int actualCity;
	
	public int getLastCity()
	{
		return lastCity;
	}
	
	public int getActualCity()
	{
		return actualCity;
	}
	
	public void setActualCity(int actualCity)
	{
		this.lastCity=this.actualCity ;
		this.actualCity = actualCity;
	}
	
	

}
