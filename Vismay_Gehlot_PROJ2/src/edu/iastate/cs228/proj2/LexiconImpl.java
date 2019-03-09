package edu.iastate.cs228.proj2;

import java.util.Arrays;
import java.util.Comparator;

public class LexiconImpl implements Lexicon, Comparator<String> {

    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
	public CharacterValue[] characterOrdering; 

    /***
     * Creates an array of CharacterValue from characterOrdering.  Sorts
     * it using java.util.Arrays.sort().
     * @param characterOrdering character order from configuration file
     */	
	public LexiconImpl(char[] characterOrdering) {
		
		this.characterOrdering = new CharacterValue[characterOrdering.length];
		for(int i = 0; i < characterOrdering.length; i++)
		{
			this.characterOrdering[i] = new CharacterValue(i, characterOrdering[i]);
		}
		Arrays.sort(this.characterOrdering, new CharacterSort());
	}
	
	/**
	 * private method used to sort the characterOrdering array.
	 * @author Vismay Gehlot
	 *
	 * @return the difference between the values of o1 and o2
	 */
	private static class CharacterSort implements Comparator<CharacterValue>
	{
		@Override
		public int compare(CharacterValue o1, CharacterValue o2) {
			return o1.character - o2.character;
		}
		
	}


    /***
     * Compares two words based on the configuration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, positive if a>b
     */
	@Override
	public int compare(String a, String b) {
		for (int i = 0; i < Math.min(a.length(), b.length()); i++)
		{
			if (a.charAt(i) != b.charAt(i))
			{
				return a.charAt(i) - b.charAt(i);
			}
			
			if(a.length() != b.length()) 
			{
				return a.length() - b.length();
			}
		}
		return 0; 
	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		int high = characterOrdering.length;
		int low = 0;
		int mid = (high + low)/2;
		
		while (high >= low)
		{
			if (characterOrdering[mid].character == key)
			{
				return characterOrdering[mid].value;
			}
			else if (characterOrdering[mid].character < key)
			{
				low = mid;
				mid = Math.round((high + low)/2);
			}
			else
			{
				high = mid;
				mid = Math.round((high + low)/2);
			}
		}
		return -1;
	}

	public static class CharacterValue {
		public int value;
		public char character;
		
		public CharacterValue(int value, char character) {
			this.value = value;
			this.character = character;
		}
		
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) {
				return false;
			}
			CharacterValue other = (CharacterValue) o;
			return value == other.value && character == other.character;
		}
	}
	
	/**
	 * Returns whether or not word is valid according to the alphabet
	 * known to this lexicon. 
	 * 
	 * @param word word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		for (int i = 0; i < word.length(); i++)
		{
			if (getCharacterOrdering(word.charAt(i)) == -1)
			{
				return false;
			}
		}
		return true; 

	}
	
}
