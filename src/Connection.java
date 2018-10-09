
public class Connection 
{
	public aNode a;
	public aNode b;
	
	public Connection(aNode firstNodeToSet,aNode secondNodeToSet)
	{
		a = firstNodeToSet;
		b = secondNodeToSet;
	}
	
	public Boolean equals(Connection compared)
	{
		if(this.a.equals(compared.a))
		{
			if(this.b.equals(compared.b))
			{
				return true;
			}
		}
		if(this.b.equals(compared.a))
		{
			if(this.a.equals(compared.b))
			{
				return true;
			}
		}
		return false;
	}
}
