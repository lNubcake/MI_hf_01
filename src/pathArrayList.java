import java.util.ArrayList;

public class pathArrayList 
{
	public ArrayList<aNode> route = new ArrayList<aNode>();
	
	public Boolean contains(aNode node)
	{
		for(aNode a : route)
		{
			if(a.equals(node))
			{
				return true;
			}
		}
		return false;
	}
}
