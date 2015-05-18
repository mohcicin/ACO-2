import java.io.*;
public class AntColonyInfo implements Serializable
{
	
	private double p[];
	private int n[]; //SET OF ALL PATHS FROM THE CITY i TO ALL ADJACENT CITIES STILL NOT VISITED BY THE ANT
	private boolean citiesRelations[][];
	private int [][] citiesPathLe;
	private double [][] pheromones;
	
	public boolean[][] getCitiesRelations()
	{
		return citiesRelations;
	}
	
	public int[][] getCitiesPathLe()
	{
		return citiesPathLe;
	}
	
	public double[][] getPheromones()
	{
		return pheromones;
	}
	
	public double[] getP()
	{
		return p;
	}
	
	public int[] getN()
	{
		return n;
	}
	
	
	public void setCitiesRelations(boolean[][] citiesRel)
	{
		this.citiesRelations = citiesRel;
	}
	
	public void setCitiesPathLe(int[][] citiesPathLe)
	{
		this.citiesPathLe = citiesPathLe;
	}
	
	public void setPheromones (double[][] pheromones)
	{
		this.pheromones = pheromones;
	}
	public void setP (double p[])
	{
		this.p = p;
	}
	
	public void setN(int[] n)
	{
		this.n = n;
	}

}
