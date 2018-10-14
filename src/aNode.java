import java.util.ArrayList;

public class aNode 
{
	public Boolean target = false;
	public int x;
	public int y;
	public ArrayList<Connection> neighbours = new ArrayList<Connection>();
	
	public aNode(int Value_x, int Value_y, Boolean Value_item)
	{
		x = Value_x;
		y = Value_y;
		target = Value_item;
	}
	public aNode(aNode nodeToCopy)
	{
		x = nodeToCopy.x;
		y = nodeToCopy.y;
		target = nodeToCopy.target;
		for(Connection c : nodeToCopy.neighbours)
		{
			neighbours.add(c);
		}
	}
	
	public Boolean equals(aNode compared)
	{
		if(x == compared.x)
		{
			if(y == compared.y)
			{
				return true;
			}
		}
		return false;
	}
}
