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
	
	static int x = 0;
	static int y = 0;
	static int item = 0;
	static ArrayList<ArrayList<Integer>> Labyrinth = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args)
	{
		getInput();
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
			Labyrinth.add(aColumn);
		}
		setUpParameters();
	}
	
	/// This function sets up the parameters of the labyrinth
	public static void setUpParameters()
	{
		x = Labyrinth.get(0).size();
		y = Labyrinth.size()-1;
		item = Labyrinth.get(Labyrinth.size()-1).get(0);
		Labyrinth.remove(Labyrinth.size()-1);
	}
	
}
