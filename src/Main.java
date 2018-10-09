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
	
	public static void main(String[] args)
	{
		getInput();
		setUpParameters();
		setUpNodeMatrix();
		//setUpConnections();
		
		// TODO this works well
		/*
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("1");strs.add("2");strs.add("3");
		strs.remove("2");
		for(String s : strs)
		{
			System.out.print(s + " ");
		}
		*/
		for(ArrayList<Integer> i : IntegerLabyrinth)
		{
			for(Integer ii : i)
			{
				System.out.print(ii + " ");
			}
			System.out.print("\n");
		}
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
				if(Rows.get(Rows.size()-2).length() != Rows.get(Rows.size()-1).length())
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
			for(String aString : split)
			{
				aColumn.add(Integer.parseInt(aString));
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
					NodeLabyrinth.get(i).add(new aNode(i,j,true));
				}
			}
		}
	}
	
	///This function sets up all the connections
	public static void setUpConnections()
	{
		
		for(int i = 0; i < x; i++)
		{
			for(int j = 0; j < y; j++)
			{
				ArrayList<String> compass = new ArrayList<String>();
				// TODO think about this, gonna be good
				while(IntegerLabyrinth.get(i).get(j) != 0)
				{
					if(IntegerLabyrinth.get(i).get(j) >= 8)
					{
						//északi fal
						
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 4)
					{
						//keleti fal
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 2)
					{
						//déli fal
					}
					else if(IntegerLabyrinth.get(i).get(j) >= 1)
					{
						//nyugati fal
					}
				}
			}
		}
	}
	
	public static void validateConnection(ArrayList<Connection> Connections, Connection conectionToValidate)
	{
		
	}
}
