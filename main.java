import java.util.ArrayList;
import java.util.HashMap;

public class StudentSolver {
	static boolean testingProgram = true;
	static boolean testingProgramOld = false;
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> c)
	{
		boolean testingPrint = true;
		if ( testingPrint )
		{
			System.out.println("Print of 2D ArrayList: ");
			for ( int i = 0; i < 100; i ++ )
			{
				for ( int j = 0; j < 10; j ++ ) 
				{
					
					System.out.print( c.get(i).get(j) + " " );
					
				}
				System.out.println();
			}
		}
		HashMap<Integer, Integer> hashOfRegisters = new HashMap<Integer, Integer>();
		int addedFirstTime = 0;
		int addedSecondTime = 0;
		for ( int i = 0; i < c.size(); i ++ )
		{
			Integer cValue = c.get(i).get(0);
			if ( testingProgramOld )
			{
				System.out.println( "I Up Here = " + i + " cValue = " + cValue );
			}
			if ( hashOfRegisters.get(cValue) == null )
			{
				hashOfRegisters.put(cValue, 1);
				if ( testingProgramOld )
				{
					addedFirstTime++;
					System.out.println( "Size of hash after : " +  hashOfRegisters.size() + " value to hash " + cValue);
				}
			}
			else
			{
				hashOfRegisters.put(cValue, hashOfRegisters.get(cValue ) + 1);
				if ( testingProgramOld )
				{
					addedSecondTime++;
				}
			}
			
		}
		if ( testingProgramOld )
		{
			System.out.println( "Size of Hash " + hashOfRegisters.size() );
			System.out.println( "1st: " + addedFirstTime + " 2nd: " + addedSecondTime);
		}
		//iterate back through 2d array with 1 for loop
		//then delete lists with registers that are not 1
		for ( int i = 0; i < c.size(); i++ )
		{
			Integer cValue = c.get(i).get(0);
			if ( testingProgramOld )
			{
				System.out.println( "I = " + i + " cValue = " + cValue );
			}
			if ( hashOfRegisters.get(cValue) > 1 )
			{
				c.remove(i);
				hashOfRegisters.put(cValue, hashOfRegisters.get(cValue) - 1);
				
			}
		}
		return c;
	}

	
	public static void main(String args[]) 
	{
		ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
		for ( int i = 0; i < 100; i ++ )
		{
			int ranReg = (int)(Math.random()*100);
//			System.out.println( ranReg);
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
//					System.out.println( ranNum);
					cnew.add( ranNum);
				}
				
			}
			c.add(cnew);
		}
		boolean testingPrint = false;
		if ( testingPrint )
		{
			System.out.println("Print of 2D ArrayList: ");
			for ( int i = 0; i < 100; i ++ )
			{
				for ( int j = 0; j < 10; j ++ ) 
				{
					
					System.out.print( c.get(i).get(j) + " " );
					
				}
				System.out.println();
			}
		}
		
		solve(c);
		System.out.println("After Duplicates Removed: size = " + c.size());
		if ( testingPrint )
		{
			System.out.println("After Duplicates Removed: size = " + c.size());
			for ( int i = 0; i < c.size(); i ++ )
			{
				System.out.println( c.get(i).get(0));
			}
		}
	}
}
