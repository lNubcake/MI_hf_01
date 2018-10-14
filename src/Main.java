import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	/*A hallgató a standard inputon kap egy i sorból és j oszlopból álló mátrixot. Majd kap egy számot, amely a
	labirintusban lévo tárgyak számát jelzi. A mátrixban ˝ 0 és 31 közötti számok szerepelnek. Minden i, j páros
	a labirintus egy helyét reprezentálja. A mátrix i. sorában és j. oszlopában lévo szám meghatározza, hogy a ˝
	labirintus adott mezojét hol határolják falak: ˝
	• Északi fal: 1,
	• Keleti fal: 2,
	• Déli fal: 4,
	• Nyugati fal: 8,
	• Tárgy: 16,
	a mátrix minden eleme ezek összegét tartalmazza (pl. ha adott mezor ˝ ol délre és nyugatra lehet menni, ˝
	illetve egy tárgyat is tartalmaz, akkor a megfelelo mátrixelem ˝ 1 + 2 + 16 = 19).
	Példa. Egy 3 × 3-as labirintus:
10 9 7
8 0 19
12 4 2
1

10 9 5 3 9 5 5 5 5 3
12 6 9 6 10 11 9 5 5 6
11 9 6 13 4 0 4 5 5 3
10 12 5 5 3 12 7 9 3 10
8 1 5 7 12 3 9 6 8 2
10 10 9 5 3 10 10 9 0 2
10 12 6 9 2 10 10 10 10 10
28 1 7 10 10 10 8 0 4 2
11 10 9 6 14 10 12 4 3 10
12 6 12 5 5 4 5 5 6 10
1
*/
	
	/* Tervek
	 * felállitani egy gráfot
	 * bejárni a gráfot az adott pontomból egy tárgyig
	 * ahányszor tárgy van
	 * majd a végére menni
	 */
	
	public static int x = 0;
	public static int y = 0;
	public static int item = 0;
	public static ArrayList<ArrayList<Integer>> IntegerLabyrinth = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<aNode>> NodeLabyrinth = new ArrayList<ArrayList<aNode>>();
	public static ArrayList<Connection> allConnection = new ArrayList<Connection>();
	public static ArrayList<aNode> path = new ArrayList<aNode>();
	public static aNode currentPosition;
	
	public static void main(String[] args)
	{
		getInput();
		setUpParameters();
		int xx = x;
		int yy = y;
		setUpNodeMatrix();
		setUpConnections();
		setUpNodeConnections();
		currentPosition = NodeLabyrinth.get(0).get(0);
		
//		for(ArrayList<Integer> i : IntegerLabyrinth)
//		{
//			for(Integer ii : i)
//			{
//				System.out.print(ii + " ");
//			}
//			System.out.print("\n");
//		}
		
		/*while(item != 0)
		{
			for(ArrayList<aNode> w : NodeLabyrinth)
				for(aNode a : w)
				{
					/*pickUp(a);*//*
					System.out.print(a.x + " " + a.y+ " ");
				}
			System.out.print("\n");
		}*/
		
		/*for(ArrayList<aNode> a : NodeLabyrinth)
		{
			for(aNode w : a)
			{
				if(w.target)
					System.out.print("tárgy");
				System.out.print(w.x + "" +w.y+" ");
			}
			System.out.print("\n\n");
		}
		*/
		// Everything is set up, not the AI comes.
		
		while(item > 0)
		{
			for(ArrayList<aNode> Row : NodeLabyrinth)
			{
				for(aNode Column : Row)
				{
					if(Column.target)
					{
						DFS(currentPosition,Column);
						System.out.println("felvesz");
					}
				}
			}
		}
		DFS(currentPosition,NodeLabyrinth.get(y-1).get(x-1));
	}
	
	/// This function waits for an input and breaks it down to Rows.
	public static void getInput()
	{
		Scanner in = new Scanner(System.in);
		ArrayList<String> Rows = new ArrayList<String>();
		
		do
		{
			Rows.add(in.nextLine());
			if(Rows.size() >=2 )
			{
				if(!Rows.get(Rows.size()-1).contains(" "))
				{
					break;
				}
			}
		}while(in.hasNextLine());
		
		processStringArray(Rows);
		
		in.close();
	}
	
	/// This function processes a StringArray to a 2D Integer matrix
	public static void processStringArray(ArrayList<String> stringToProcess)
	{
		for(String s : stringToProcess)
		{
			String[] split = s.split(" ");
			ArrayList<Integer> aColumn = new ArrayList<Integer>();
			//System.out.println("aColumn added");
			for(String aString : split)
			{
				aColumn.add(Integer.parseInt(aString));
				//System.out.println("aRow added");
			}
			IntegerLabyrinth.add(aColumn);
		}
	}
	
	/// This function sets up the parameters of the labyrinth
	public static void setUpParameters()
	{
		x = IntegerLabyrinth.get(0).size();
		y = IntegerLabyrinth.size()-1;
		item = IntegerLabyrinth.get(IntegerLabyrinth.size()-1).get(0);
		IntegerLabyrinth.remove(IntegerLabyrinth.size()-1);
	}
	
	/// This function sets up our NodeMatrix
	public static void setUpNodeMatrix()
	{
		for(int i = 0; i < y; i++)
		{
			NodeLabyrinth.add(new ArrayList<aNode>());
			for(int j = 0; j < x ; j++)
			{
				//Ha van item
				if(IntegerLabyrinth.get(i).get(j).intValue() >= 16)
				{
					
					NodeLabyrinth.get(i).add(new aNode(i,j,true));
					IntegerLabyrinth.get(i).set(j, IntegerLabyrinth.get(i).get(j)-16);
				}
				//Ha nincs
				else
				{
					NodeLabyrinth.get(i).add(new aNode(i,j,false));
				}
			}
		}
	}
	
	///This function sets up all the connections
	public static void setUpConnections()
	{
		
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				ArrayList<String> compass = new ArrayList<String>();
				compass.add("north"); compass.add("east"); compass.add("south"); compass.add("west");
				// TODO think about this, gonna be good
				while(IntegerLabyrinth.get(i).get(j) != 0)
				{
					if(IntegerLabyrinth.get(i).get(j) >= 8)
					{
						//északi fal
						IntegerLabyrinth.get(i).set(j, IntegerLabyrinth.get(i).get(j)-8);
						compass.remove("north");
						
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 4)
					{
						//keleti fal
						IntegerLabyrinth.get(i).set(j, IntegerLabyrinth.get(i).get(j)-4);
						compass.remove("east");
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 2)
					{
						//déli fal
						IntegerLabyrinth.get(i).set(j, IntegerLabyrinth.get(i).get(j)-2);
						compass.remove("south");
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 1)
					{
						//nyugati fal
						IntegerLabyrinth.get(i).set(j, IntegerLabyrinth.get(i).get(j)-1);
						compass.remove("west");
					}
				}
				for(String s : compass)
				{
					validateConnection(i,j,s);
				}
			}
		}
	}
	
	/// This functions stores the connections of a Node-s neighbours
	public static void setUpNodeConnections()
	{
		for(ArrayList<aNode> Rows : NodeLabyrinth)
		{
			for(aNode Columns : Rows)
			{
				for(Connection c : allConnection)
				{
					if(c.a.equals(Columns) || c.b.equals(Columns))
					{
						Columns.neighbours.add(c);
					}
				}
			}
		}
	}
	
	///This function checks whether the connection we wanna add is already in allConnections. In case it is not, it adds it.
	public static void validateConnection(int yy, int xx, String s)
	{
		switch(s)
		{
		case "north":
			if(xx != 0)
			{
				Connection ConnectionToAdd = new Connection(NodeLabyrinth.get(yy).get(xx),NodeLabyrinth.get(yy).get(xx-1));
				for(Connection c : allConnection)
				{
					if(c.equals(ConnectionToAdd))
					{
						return;
					}
				}
				allConnection.add(ConnectionToAdd);
			}
			break;
		case "east":
			if( yy != y-1)
			{
				Connection ConnectionToAdd = new Connection(NodeLabyrinth.get(yy).get(xx),NodeLabyrinth.get(yy+1).get(xx));
				for(Connection c : allConnection)
				{
					if(c.equals(ConnectionToAdd))
					{
						return;
					}
				}
				allConnection.add(ConnectionToAdd);
			}
			break;
		case "south":
			if( xx != x-1)
			{
				Connection ConnectionToAdd = new Connection(NodeLabyrinth.get(yy).get(xx),NodeLabyrinth.get(yy).get(xx+1));
				for(Connection c : allConnection)
				{
					if(c.equals(ConnectionToAdd))
					{
						return;
					}
				}
				allConnection.add(ConnectionToAdd);
			}
			break;
		case "west":
			if( yy != 0)
			{
				Connection ConnectionToAdd = new Connection(NodeLabyrinth.get(yy).get(xx),NodeLabyrinth.get(yy-1).get(xx));
				for(Connection c : allConnection)
				{
					if(c.equals(ConnectionToAdd))
					{
						return;
					}
				}
				allConnection.add(ConnectionToAdd);
			}
			break;
		}
	}
	
	/// This function implements a DFS, in which we can get to an item
	public static void DFS(aNode startingPoint, aNode endingPoint)
	{
		recursion(startingPoint,endingPoint);	
		for(aNode a : path)
		{
			System.out.println(a.x + " " + a.y);
		}
		item -= 1;
		endingPoint.target = false;
		currentPosition = endingPoint;
		path.clear();
		returnFlag = false;
	}
	
	public static Boolean returnFlag = false;
	public static void recursion(aNode startingPoint, aNode endingPoint)
	{
		int numberOfNeighbours = startingPoint.neighbours.size();
		while(numberOfNeighbours != 0)
		{	
			aNode next = null;
			if(returnFlag)
			{
				return;
			}
			if(startingPoint.neighbours.get(numberOfNeighbours-1).a.equals(startingPoint))
			{
				if(!path.contains(startingPoint.neighbours.get(numberOfNeighbours-1).b))
					next = new aNode(startingPoint.neighbours.get(numberOfNeighbours-1).b);
			}
			else if(startingPoint.neighbours.get(numberOfNeighbours-1).b.equals(startingPoint))
			{
				if(!path.contains(startingPoint.neighbours.get(numberOfNeighbours-1).a))
					next = new aNode(startingPoint.neighbours.get(numberOfNeighbours-1).a);
			}
			if(startingPoint.equals(endingPoint))
			{
				returnFlag = true;
				return;
			}
			try{
			path.add(next);
			}catch(Exception e)
			{
				System.out.println("exception caught");
			}
			recursion(next,endingPoint);
			numberOfNeighbours -= 1;
		}
		if(returnFlag)
			return;
		path.remove(path.size()-1);
	}
}

