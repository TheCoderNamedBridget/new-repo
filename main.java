import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;




/*
 * Todo Debug the problem that some values are not getting converted to the correct reg \
 * okay there is a porblem with how the/when the values are being assigned registers
 * 
 * 
 * add a check to make sure that integers are not getting double registered
 */
public class StudentSolver {
	static boolean testingProgram = true;
	static boolean testingPrintDebugger = false;
	static boolean testingProgramOld = false;
	
	
	static boolean testingPrint = true;
	
	
	public static Integer getMax(ArrayList<ArrayList<Integer>> c)
	{
		int max = 0;
		for ( int i = 0; i < c.size(); i++ )
		{
			for ( int j = 0; j < c.get(i).size(); j++ )
			{
				if ( c.get(i).get(j) > max )
				{
					max = c.get(i).get(j);
				}
			}
		}
		return max;
	}
	
	
	public static ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> c)
	{
		if ( testingPrint )
		{
			System.out.println("Original Array ");
			for ( int i = 0; i < c.size(); i ++ )
			{
				for ( int j = 0; j < c.get(i).size(); j ++ )
				{
					System.out.print( c.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}
		
		HashMap<Integer, Integer> countOfNumAppearances = new HashMap<Integer, Integer>();
		//counts num times num appears in list
		HashMap<Integer, Boolean> intExistsInLine = new HashMap<Integer, Boolean>();
		int totalNumsInArray = 0;
		int numRepeats = 0;
		for ( int i = 0; i < c.size(); i ++ )
		{
			intExistsInLine.clear();
			for ( int j = 0; j < c.get(i).size(); j ++  )
			{
				if ( intExistsInLine.get(c.get(i).get(j)) == null )
				{
					intExistsInLine.put(c.get(i).get(j), true);
				}
				else 
				{
					numRepeats++;
				}
				totalNumsInArray++;
			}
			
			Iterator<Entry<Integer, Boolean>> overExists = intExistsInLine.entrySet().iterator();
			while ( overExists.hasNext() )
			{
				Integer curInt = overExists.next().getKey();
				if ( countOfNumAppearances.get(curInt) == null )
				{
					countOfNumAppearances.put(curInt, 1);
				}
				else 
				{
					countOfNumAppearances.put(curInt, countOfNumAppearances.get(curInt) + 1);
				}
			}
		}
		/*
		 * Code listing the counts of each number in line
		 */
		Iterator<Entry<Integer, Integer>> ITR = countOfNumAppearances.entrySet().iterator();
		System.out.println("List of Counts: size = " + countOfNumAppearances.size());
		int totalNumperLine = 0;
		while ( ITR.hasNext() )
		{
			Entry<Integer, Integer> itrNext = ITR.next();
			System.out.println( "num " + itrNext.getKey() + " count:" + itrNext.getValue());
			totalNumperLine += itrNext.getValue();
		}
		/*code to see how many numbers found*/
		int total = 0;
		Iterator<Entry<Integer, Integer>> itr = countOfNumAppearances.entrySet().iterator();
		System.out.println("TOTALTOTAL " + totalNumsInArray  + " total num repeats " + numRepeats );
		System.out.println("totalNumperLine " +totalNumperLine );
		while ( itr.hasNext() )
		{
			Entry<Integer, Integer> i = itr.next();
			total += i.getValue();
			System.out.println("TOTAL Numbers in hash " + total + " for num " + i.getKey());
		}
		 
		
		
		HashMap<Integer, Integer> regAssignedToNum = new HashMap<Integer, Integer>();
		int curRegNum = 0;
		Queue<Integer> queueOfUnusedReg = new PriorityQueue<Integer>();
		//iterates through creating, reusing, switching registers
		if ( testingPrintDebugger )
		{
			System.out.println("Debugging Registers Part 1: ");
		}
		HashMap<Integer, Boolean> alreadyDeletedNumPerLine = new HashMap<Integer, Boolean>();
		
		for ( int i = 0; i < c.size(); i ++ )
		{
			alreadyDeletedNumPerLine.clear();
			for ( int j = 0; j < c.get(i).size(); j ++  )
			{
				int item = c.get(i).get(j);
				if ( alreadyDeletedNumPerLine.get(item) == null && regAssignedToNum.get(item) == null )
				{
					int regTouse = curRegNum;
					if ( queueOfUnusedReg.size() != 0 )
					{
						regTouse = queueOfUnusedReg.poll();
					}
					else if ( queueOfUnusedReg.size() == 0 )
					{
						curRegNum++;
					}
					regAssignedToNum.put(item, regTouse);
					alreadyDeletedNumPerLine.put(item, true);
					
				}
				countOfNumAppearances.put(item, countOfNumAppearances.get(item) -1 );
				
			}
				
		}
		
		//code for old part of assigning registers
//		for ( int i = 0; i < c.size(); i ++ )
//		{
//			alreadyDeletedNumPerLine.clear();
//			for ( int j = 0; j < c.get(i).size(); j ++  )
//			{
//				
//				//if no reg and not already in a reg and none of numbers in list count == 1 then make reg
//				if ( alreadyDeletedNumPerLine.get(c.get(i).get(j)) == null && regAssignedToNum.get(c.get(i).get(j)) == null )
//				{
//					if (queueOfUnusedReg.size() == 0)
//					{
//						regAssignedToNum.put(c.get(i).get(j), curRegNum);
//						System.out.println(" HERE : num " + c.get(i).get(j) + " cur reg " + curRegNum  
//								+ " count left " + countOfNumAppearances.get(c.get(i).get(j)) );
//						curRegNum++;
//					}
//					else
//					{
//						Integer reg = queueOfUnusedReg.poll();
//						regAssignedToNum.put(c.get(i).get(j), reg);
//						System.out.println(" HERE Reusing Old Reg : " + reg + " for num " + c.get(i).get(j) );
//					}
//					
////					if ( countOfNumAppearances.get(c.get(i).get(j)) == 1 )
////					{
////						queueOfUnusedReg.add(regAssignedToNum.get(c.get(i).get(j)));
////					}
//				}
//				
//				//count of 
//				//possible error here bc could be looking at a duplicate num
//				if ( alreadyDeletedNumPerLine.get(c.get(i).get(j)) == null && countOfNumAppearances.get(c.get(i).get(j)) == 1 )//if this is the last time a number is appearing
//				{
//					queueOfUnusedReg.add(regAssignedToNum.get(c.get(i).get(j)));
//					System.out.println(" HERE2 : c.get(i).get(j) = " + c.get(i).get(j) + " count = " + countOfNumAppearances.get(c.get(i).get(j)) );
//					System.out.println(" Unused reg " + queueOfUnusedReg.size());
//				}
//				
//				
//				if ( alreadyDeletedNumPerLine.get(c.get(i).get(j)) == null )
//				{
//					alreadyDeletedNumPerLine.put(c.get(i).get(j), true);
//
//					countOfNumAppearances.put(c.get(i).get(j), countOfNumAppearances.get(c.get(i).get(j)) -1 );
//				}
//				
//				if ( curRegNum > 82 )
//				{
//					System.out.println("Register over 82 at " + c.get(i).get(j) + " regNum " + curRegNum);
//				}
//			
//			}
			if ( testingPrintDebugger )
			{
				System.out.println();
			}
		
		
		System.out.println("regAssignedToNum size " + regAssignedToNum.size());
		ArrayList<ArrayList<Integer>> reg = new ArrayList<ArrayList<Integer>>();
		//convert the numbers to their register
		if ( testingPrintDebugger )
		{
			System.out.println("Debugging Registers: ");
		}
		
		for ( int i = 0; i < c.size(); i ++ )
		{
			ArrayList<Integer> placeholderForRegisters = new ArrayList<Integer>();
			for ( int j = 0; j < c.get(i).size(); j ++ )
			{
				placeholderForRegisters.add(regAssignedToNum.get(c.get(i).get(j)));
				if ( testingPrintDebugger )
				{
					System.out.print("num: " + c.get(i).get(j) );
				}
				
			}
			if ( testingPrintDebugger )
			{
				System.out.println();
			}
			
			reg.add(placeholderForRegisters);
		}
		
		if ( testingPrint )
		{
			System.out.println("Registers: ");
			for ( int i = 0; i < reg.size(); i ++ )
			{
				for ( int j = 0; j < reg.get(i).size(); j ++ )
				{
					System.out.print( reg.get(i).get(j) + " ");
				}
				System.out.println();
			}
		}

		return reg;
	}

	
	public static void main(String args[]) 
	{
		
		System.out.println("Here");
		ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
		
		try {
		      File myObj = new File("problem.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        ArrayList<Integer> cPlaceholder = new ArrayList<Integer>();
		        String placeholder = "";
		        for ( int i = 0; i < data.length(); i ++)
		        {
//		        	System.out.println("here[" + data.substring(i, i + 1) + "]");
		        	if ( !data.substring(i, i + 1).equals(" ") )
		        	{
		        		placeholder += data.substring(i, i + 1);
		        	}
		        	else 
		        	{
//		        		System.out.println("[" + placeholder + "]");
		        		cPlaceholder.add(Integer.parseInt(placeholder));
		        		placeholder = "";
		        	}
		        	
		        }
		        cPlaceholder.add(Integer.parseInt(placeholder));
		        c.add(cPlaceholder);
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		System.out.println("Testing Parser: ");
		for ( int i = 0; i < c.size(); i ++ )
		{
			for ( int j = 0; j < c.get(i).size(); j ++ )
			{
				System.out.print( c.get(i).get(j) + " ");
			}
			System.out.println();
		}
		c = solve(c);
		
		System.out.println("max = " + getMax(c));
	}
}
