package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Vismay Gehlot
 *
 */
public class MergeSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		mergeSort(words, comp, 0, words.length - 1);
	}

	/**
	 * runs mergesort
	 * @param words	an array that holds string type objects
	 * @param comp	comparator variable that compares strings
	 * @param low	starting point of the words array
	 * @param high	ending point of the words array
	 */
	private void mergeSort(String[] words, Comparator<String> comp, int low, int high) {
		int l = low;
		int h = high;
		int m = (high + low)/2;
		if (h == l)
		{
			return;
		}
		else if (h - l == 1)
		{
			if (comp.compare(words[l], words[h]) >= 0)
			{
				swap(words, l, h);
			}
		}
		else
		{
			mergeSort(words, comp, l, m);
			mergeSort(words, comp, m + 1, h);
			merge(words, comp, l, m, h);
		}
	}

	/**
	 * after both ends are sorted, this method merges both separate arrays into one singular array
	 * @param words	an array that holds string type objects
	 * @param comp	comparator variable that compares strings
	 * @param low	starting point of the words array
	 * @param mid	middle point of the words array
	 * @param high	ending point of the words array
	 */
	private void merge(String[] words, Comparator<String> comp, int low, int mid, int high) {
		int l = low;
		int m = mid;
		int h = high;
		while (l <= m)
		{
			if (comp.compare(words[l], words[m + 1]) >= 0)
			{
				swap(words, l, m + 1);
				push(words, comp, m + 1, h);
			}
			l++;
		}
	}

	/**
	 * pushes all the larger values of the array that are at the beginning to the end
	 * @param words	an array that holds string type objects
	 * @param comp	comparator variable that compares strings
	 * @param low	starting point of the words array
	 * @param high	ending point of the words array
	 */
	private void push(String[] words, Comparator<String> comp, int low, int high) {
		for (int i = low; i < high; i++)
		{
			if (comp.compare(words[i], words[i + 1]) >= 0)
			{
				swap(words, i , i + 1);
			}
		}
	}

	/**
	 * swaps two proposed array elements
	 * @param words 	an array that holds string type objects
	 * @param first 	index of the first word to be swapped
	 * @param second	index of the second word to be swapped
	 */
	private void swap(String[] words, int first, int second) {
		String temp = words[first];
		words[first] = words[second];
		words[second] = temp;
	}	

}
 