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
	
	public static void main(String[] args)
	{
		getInput(x,y,item);
		
		System.out.println(x + " "+ y +" "+ item);
	}
	
	public static void/*??*/ getInput(int x, int y, int item)
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
		
		//Tárgyak számának meghatározása
		item = Integer.parseInt(Rows.get(Rows.size()-1));
		Rows.remove(Rows.size()-1);
		y = Rows.size();
		x = Math.toIntExact(Rows.get(0).chars().filter(ch -> ch == ' ').count());
		
		in.close();
	}

}
