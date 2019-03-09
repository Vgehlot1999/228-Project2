package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Vismay Gehlot
 *
 */
public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		int temp = 0;
		for (int i = 0; i < words.length; i++)
		{
			temp = i;
			for (int j = i; j < words.length; j++)
			{
				if (comp.compare(words[j], words[temp]) < 0)
				{
					temp = j;
				}
			}
			String x;
			x = words[i];
			words[i] = words[temp];
			words[temp] = x;
		}
	}
}
