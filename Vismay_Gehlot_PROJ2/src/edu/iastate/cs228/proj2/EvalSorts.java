package edu.iastate.cs228.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * @author Vismay Gehlot
 * 
 * The measured performances are for the most part similar to their expected performances. Mergesort and quicksort
 * were a lot faster than selectionsort in almost all cases. The difference much more prominent in the larger cases 
 * such as word lists of length 100+. This follows the Big-O for all three sorts, as mergesort and quicksort are both 
 * O(n(log(n))) and selectionsort is O(n^2), making it take an exponentially longer time to finish sorting the given 
 * lists. For the shorter lists, selection sort was actually faster, taking only 48195 nanoseconds whereas mergesort 
 * and quicksort took 61136 and 70506 seconds each. However, as the numbers kept increasing, selectionsort grew
 * at a much faster rate than the other two sorts. At a word list length of 100, the selectionsort timer surpassed the
 * other two sorting methods. For the last two cases, I was unable to find the time it took for selectionsort to complete
 *  its sorting because it lasted longer than 10 minutes each, while the other two only took around 2-3 minutes.
 *  
 */
public class EvalSorts {
	public static final int kNumberOfWordsToSort = 10000;

	/**
	 main is responsible only for extracting fileNames from args,
     reading the files, and constructing an instance of the this 
     class configured with the input data.
	 FileNotFoundException and FileConfigurationException exceptions 
	 should be handled in main, i.e., print out appropriate message
	 to the user.
	*/
	public static void main(String args[]) {
		char[] alphabet   = null;  //ref to the Lexicon it creates. 
		String[] wordList = null;  //ref to the list of words to be sorted. 
		EvalSorts theApp  = null;  //ref to the app. 
		LexiconImpl comp  = null;  //the concrete lexicon your app uses. 
		
		
		/*
		 * 
		 *      Here you should add code that extracts the file names from the args array,
		 *      opens and reads the data from the files,constructs an instance of Lexicon from the character order file, 
		 *      and then create an instance of this class (EvalSorts) to act as a configured
		 *      instance of the application. After you have constructed the configured
		 *      instance, you should start it running (see below). 
		 *      
		 *      
		 *   
		 *  
		*/		
		try
		{
			alphabet = readCharacterOrdering(args[0]);
			comp = new LexiconImpl(alphabet);
			wordList = readWordsFile(args[1], comp);
			//configure an instance of the app
			theApp = new EvalSorts(comp, wordList, kNumberOfWordsToSort);
			//now execute that instance
			theApp.runSorts();
		}
		catch(FileConfigurationException E)
		{
			System.out.println("File was not configured correctly");
		}
		catch(FileNotFoundException F)
		{
			System.out.println("File was not found");
		}	
	}

	
	private String[] words; //ref to the word lit
	private Lexicon lex;    //ef to the relevant lexicon	
	private int numWordsToSort = kNumberOfWordsToSort;
	
	/**
	 * This constructor configures an instance of EvalSorts to sort input read
	 * my main, using the character order read by main and now embedded in
	 * an instance of Lexicon
	 * @param lex the instance of lexicon to be used
	 * @param wordList the wordlist (as array of string)  to be sorted
	 * @param numWordsToSort each sort will be repeated until it has sorted
	 *                       this many words. 
	 */
	public EvalSorts(Lexicon lex, String[] wordList, int numWordsToSort) {
		words = wordList;
		this.lex = lex;
	}

	/**
	 * runSorts() performs the sort evaluation. 
	 * 
	 * Note: The three sorters extend a common base
	 * so they share the same interface for starting the sort and collecting statistics. 
	 * Thus, you should create instances of the sorter and save references to each in an 
	 * array of base type. This allows you to use a simple loop to run all the reports and 
	 * collect the statistics.   
	 */
	public void runSorts(){
		
		SorterWithStatistics[] sorters = new SorterWithStatistics[3];
		
		sorters[0]= new QuickSort();
		sorters[1] = new MergeSort();
		sorters[2] = new SelectionSort();
		
		for (int i = 0; i < sorters.length; i++)
		{
			sorters[i].sort(words, lex);
			System.out.println(sorters[i].getReport());
		}
	}
	
	/**
	 * Reads the characters contained in filename and returns them as a character array.
	 * @param filename the file containing the list of characters
	 * @returns an char array representing the ordering of characters to be used 
	 *          or null if there is a FileNotFoundException.
	 */
	public static char[] readCharacterOrdering(String filename) throws FileNotFoundException, FileConfigurationException {
		
		File f = new File(filename);
		Scanner S = new Scanner(f);
		int count = 0;
		ArrayList<Character> check = new ArrayList<Character>();
		String temp;
		
		
		while (S.hasNextLine())
		{
			count++;
			temp = S.nextLine();
			if (temp.length() > 1)
			{
				throw new FileConfigurationException();
			}
			check.add(temp.charAt(0));
		}
		
		S = new Scanner(f);
		char[] c = new char[count];
		
		
		for (int i = 0; i < count; i++)
		{
			if(check.contains(c[i]))
			{
				throw new FileConfigurationException();
			}
		}
		
		
		for (int j = 0; j < count; j++)
		{
			c[j] = S.nextLine().charAt(0);
		}
		S.close();
		return c;
	}
	
	/**
	 * Reads the words from the file and returns them as a String array.
	 * @param filename file containing words
	 * @return the words contained in the file or null if there was a FileNotFoundException.
	 */
	public static String[] readWordsFile(String filename, Lexicon comp) throws FileNotFoundException, FileConfigurationException {
		
		File f = new File(filename);
		Scanner S = new Scanner(f);
		ArrayList<String> str = new ArrayList<String>();
		String temp = null;
		
		
		while (S.hasNextLine())
		{
			temp = S.nextLine();
			if (!comp.isValid(temp))
			{
				S.close();
				throw new FileConfigurationException();
			}
			str.add(temp);
		}
		
		S.close();
		
		String[] st = str.toArray(new String[str.size()]);
		return st;
	}

}
