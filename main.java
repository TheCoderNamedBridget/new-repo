import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StudentSolver {
	static boolean testingProgram = false;
	static boolean testingProgramOld = false;
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> c)
	{
		boolean testingPrint = true;
		HashMap<Integer, Integer> countOfNumAppearances = new HashMap<Integer, Integer>();
		//counts num times num appears in list
		for ( int i = 0; i < c.size(); i ++ )
		{
			for ( int j = 0; j < c.get(i).size(); j ++  )
			{
				if ( countOfNumAppearances.get(c.get(i).get(j)) == null )
				{
					countOfNumAppearances.put(c.get(i).get(j), 1);
				}
				else 
				{
					countOfNumAppearances.put(c.get(i).get(j), countOfNumAppearances.get(c.get(i).get(j)) + 1);
				}
			}
		}
		
		
		HashSet<Integer> numsWithReg = new HashSet<Integer>();
		int totalNumRegisters = 0;
		int unsusedRegisters = 0;
		int curRegNum = 1;
		//iterates through creating, reusing, switching registers
		for ( int i = 0; i < c.size(); i ++ )
		{
			int numNumsWithCountOne = 0;
			for ( int j = 0; j < c.get(i).size(); j ++  )
			{
				//count of 
				if ( countOfNumAppearances.get(c.get(i).get(j)) == 1 )
				{
					numNumsWithCountOne ++;
				}
				
				
				//if any num has count 1 in list then take reg if taken reg add other count ==1 to unused reg
				
				
				//if no reg and not already in a reg and none of numbers in list count == 1 then make reg
				if ( !numsWithReg.contains(c.get(i).get(j)) && unsusedRegisters == 0 )
				{
					numsWithReg.add(c.get(i).get(j));
					totalNumRegisters++;
				}
				else if ( !numsWithReg.contains(c.get(i).get(j)) && unsusedRegisters != 0 )
				{
					numsWithReg.add(c.get(i).get(j));
					unsusedRegisters--;
					
				}
				
				countOfNumAppearances.put(c.get(i).get(j), countOfNumAppearances.get(c.get(i).get(j)) -1 );
			
			}
			unsusedRegisters += numNumsWithCountOne;
		}
		
		//delete Total rows - Total registers needed from rows
		for ( int i = 0; i < c.size(); i ++ )
		{
			
		}
//		boolean testingPrint = true;
//		if ( testingPrint )
//		{
//			System.out.println("Print of 2D ArrayList: ");
//			for ( int i = 0; i < c.size(); i ++ )
//			{
//				for ( int j = 0; j < c.get(i).size(); j ++ ) 
//				{
//					
//					System.out.print( c.get(i).get(j) + " " );
//					
//				}
//				System.out.println();
//			}
//		}
//		HashMap<Integer, Integer> hashOfRegisters = new HashMap<Integer, Integer>();
//		int addedFirstTime = 0;
//		int addedSecondTime = 0;
//		for ( int i = 0; i < c.size(); i ++ )
//		{
//			Integer cValue = c.get(i).get(0);
//			if ( testingProgramOld )
//			{
//				System.out.println( "I Up Here = " + i + " cValue = " + cValue );
//			}
//			if ( hashOfRegisters.get(cValue) == null )
//			{
//				hashOfRegisters.put(cValue, 1);
//				if ( testingProgramOld )
//				{
//					addedFirstTime++;
//					System.out.println( "Size of hash after : " +  hashOfRegisters.size() + " value to hash " + cValue);
//				}
//			}
//			else
//			{
//				hashOfRegisters.put(cValue, hashOfRegisters.get(cValue ) + 1);
//				if ( testingProgramOld )
//				{
//					addedSecondTime++;
//				}
//			}
//			
//		}
//		if ( testingProgramOld )
//		{
//			System.out.println( "Size of Hash " + hashOfRegisters.size() );
//			System.out.println( "1st: " + addedFirstTime + " 2nd: " + addedSecondTime);
//		}
//		//iterate back through 2d array with 1 for loop
//		//then delete lists with registers that are not 1
//		for ( int i = 0; i < c.size(); i++ )
//		{
//			Integer cValue = c.get(i).get(0);
//			if ( testingProgramOld )
//			{
//				System.out.println( "I = " + i + " cValue = " + cValue );
//			}
//			if ( hashOfRegisters.get(cValue) > 1 )
//			{
//				c.remove(i);
//				hashOfRegisters.put(cValue, hashOfRegisters.get(cValue) - 1);
//				
//			}
//		}
		return c;
	}

	
	public static void main(String args[]) 
	{
		if ( !testingProgram )
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
}
