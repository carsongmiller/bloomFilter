package bloomFilter;

import java.io.IOException;


public class BloomFilter 
{
	private final int NUM_HASH = 6;
	public int NUM_WORDS;
	
	private int numBits;
	public BitArray bitArray;
	
	public BloomFilter(int M) throws IOException
	{
		numBits = M;
		bitArray = new BitArray(M);
	}
	
	
	
	public void populateBitArray(String fileName)
	{
		String word = null;
		String dir = System.getProperty("user.dir") + "\\" + fileName; //get directory of basic.txt
		LineReader lineReader = new LineReader(dir);
		
		while(lineReader.hasNext())
		{
			NUM_WORDS++;
			word = lineReader.next();
			add(word);					
		}
	}
	
	
	
	public void add(String w)
	{
		//run word through hash functions and set appropriate bits in b[]
		try
		{
			bitArray.set(Math.abs(h1(w)));		//hash 1
			bitArray.set(Math.abs(h2(w)));		//hash 2
			bitArray.set(Math.abs(h3(w)));		//hash 3
			bitArray.set(Math.abs(h4(w)));		//hash 4
			bitArray.set(Math.abs(h5(w)));		//hash 5
			bitArray.set(Math.abs(h6(w)));		//hash 6			
		} 
		catch (IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
	}
	
	
	
	public boolean isIn(String w)
	{
		if
		(
			bitArray.get(Math.abs(h1(w))) &&
			bitArray.get(Math.abs(h2(w))) &&
			bitArray.get(Math.abs(h3(w))) &&
			bitArray.get(Math.abs(h4(w))) &&
			bitArray.get(Math.abs(h5(w))) &&
			bitArray.get(Math.abs(h6(w)))
		)
			return true;
		
		else
			return false;
	}
	
	
	
	public double accuracy()
	{
		return Math.pow(1 - Math.exp(NUM_HASH*NUM_WORDS*-1/(double)numBits), NUM_HASH);
	}
	
	
	
/*
 * ======================================
 * 
 * Hash Functions
 * 
 * ======================================
 */
	
	public int h1(String word) //simply using Java's hashCode() method
	{
		return word.hashCode() % numBits;
	}
	
	
	
	public int h2(String word)
	{
		int result = 0;
		for(int i = 0; i < word.length(); i++)
		{
			result += 37*result + (long)word.charAt(i);
		}
		return result % numBits;
	}
	
	
	
	public int h3(String word)
	{
		//This hash function runs in O(1) time rather than O(n) that was specified by the project description
		//I figured this wouldn't pose any issues, but I thought I would make a comment just in case
		int primeSet[] = 
			{
					2, 3, 5, 7, 11, 13, 17, 19, 
					23, 29, 31, 37, 41, 43, 47, 
					53, 59, 61, 67, 71, 73, 79, 
					83, 89, 97, 101, 103, 107, 
					109, 113, 127, 131, 137, 139, 
					149, 151, 157, 163, 167, 173, 
					179, 181, 191, 193, 197, 199, 
					211, 223, 227, 229, 233, 239, 
					241, 251, 257, 263, 269, 271, 
					277, 281, 283, 293, 307, 311, 
					313, 317, 331, 337, 347, 349
			};
		
		return (word.charAt(0) * word.charAt(word.length()-1) * primeSet[word.length() % primeSet.length]) % numBits;
	}
	
	//h4-h6 are essentially the same hash function, but using different large primes
	//Through testing, I found this to produce no greater number of collisions than with the other hash functions
	
	public int h4(String word)
	{
		final int A = 54059;
		final int B = 76963;
		
		int h = 31;
		for(int i = 0; i < word.length(); i++)
			h = (h*A) ^ (word.charAt(i) * B);
		
		return h  % numBits;
	}
	
	
	
	public int h5(String word)
	{
		final int A = 25247;
		final int B = 25247;
		
		int h = 31;
		for(int i = 0; i < word.length(); i++)
			h = (h*A) ^ (word.charAt(i) * B);
		
		return h  % numBits;
	}
	
	
	
	public int h6(String word)
	{
		final int A = 60161;
		final int B = 68213;
		
		int h = 31;
		for(int i = 0; i < word.length(); i++)
			h = (h*A) ^ (word.charAt(i) * B);
		
		return h  % numBits;
	}	
}
