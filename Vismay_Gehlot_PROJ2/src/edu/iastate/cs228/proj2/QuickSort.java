package edu.iastate.cs228.proj2;

import java.util.Comparator;
/**
 * 
 * @author Vismay Gehlot
 *
 */
public class QuickSort extends SorterWithStatistics {

	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		quickSort(words, comp, 0, words.length - 1);
	}
	
	/**
	 * runs quicksort
	 * @param words	an array that holds string type objects
	 * @param comp	comparator variable that compares strings
	 * @param low	starting point of the words array
	 * @param high	ending point of the words array
	 */
	private void quickSort(String[] words, Comparator<String> comp, int low, int high) {
		if (high == low)
		{
			return;
		}
		
		int l = low + 1;
		int h = high;
		String temp;
		String pivot= words[low];
		
		while (l < h)
		{
			while (l <= h && (comp.compare(words[l], pivot) <= 0 || comp.compare(pivot, words[h]) <= 0))
			{
				if (comp.compare(words[l], pivot) <= 0)
				{
					l++;
				}
				if (comp.compare(pivot, words[h]) <= 0)
				{
					h--;
				}
			}
			if (l <= h)
			{
				temp = words[l];
				words[l] = words[h];
				words[h] = temp;
				l++;
				h--;
			}
				
		}
		l = low;
		while(comp.compare(words[l+1], pivot)<=0) {
			words[l]=words[l+1];
			l++;
			if(l==high)break;
		}
		words[l]=pivot;
		if(l>low) {
			quickSort(words,comp,low,l-1);
		}
		if(l<high) {
			quickSort(words,comp,l+1,high);
		}
	}		
}

