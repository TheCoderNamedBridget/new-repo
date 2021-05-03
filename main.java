import java.util.ArrayList;

public class StudentSolver {
	
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> c)
	{
		return c;
	}

	
	public static void main(String args[]) 
	{
		ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
		for ( int i = 0; i < 100; i ++ )
		{
			int ranReg = (int)(Math.random()*100);
			ArrayList<Integer> cnew = new ArrayList<Integer>();
			for ( int j = 0; j < 10; j ++ ) 
			{
				
				if ( j == 0 )
				{
					cnew.add(ranReg);
				}
				else 
				{
					int ranNum = (int)(Math.random()*10);
					cnew.add( ranReg);
				}
				
			}
			c.add(cnew);
		}
		
		for ( int i = 0; i < 100; i ++ )
		{
			for ( int j = 0; j < 10; j ++ ) 
			{
				
				System.out.print( c.get(i).get(j) + " " );
				
			}
			System.out.println();
		}
		solve(c);
	}
}
