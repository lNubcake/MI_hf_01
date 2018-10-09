
public class aNode 
{
	public Boolean target = false;
	public int x;
	public int y;
	
	public aNode(int Value_x, int Value_y, Boolean Value_item)
	{
		x = Value_x;
		y = Value_y;
		target = Value_item;
	}
	
	public Boolean equals(aNode compared)
	{
		if(x == compared.x)
			if(y == compared.y)
				if(target == compared.target)
					return true;
		return false;
	}
}
