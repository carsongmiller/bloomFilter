package bloomFilter;

import java.io.IOException;


public class BloomFilter 
{
	private static int NUM_HASH_FUNC = 2;
	private int NUM_WORDS;
	
	private int numBits;
	public BitArray bitArray;
	
	public BloomFilter(int M) throws IOException
	{
		numBits = M;
		bitArray = new BitArray(M);
	}
	
	
	
	public int hash1(String str)
	{
		int result = 0;
		for(int i = 0; i < str.length(); i++)
		{
			result += 37*result + str.charAt(i);
		}
		return result;
	}
	
	
	
	public void add(String w)
	{
		
	}
	
	
	
	public boolean isIn(String w)
	{
		return true;
	}
	
	
	
	public void populateBitArray(String fileName)
	{
		String str = null;
		String dir = System.getProperty("user.dir") + "\\" + fileName; //get directory of basic.txt
		LineReader lineReader = new LineReader(dir);
		while(lineReader.hasNext())
		{
			NUM_WORDS++;
			str = lineReader.next();
			
			//run str through hash functions and set appropriate bits in b[]
				bitArray.set(Math.abs(str.hashCode() % numBits), str);		//hash 1
				bitArray.set(hash1(str) % numBits, str);					//hash 2
		}
	}
	
	
	
	public double accuracy()
	{
		return Math.pow(1 - Math.exp((-1*NUM_HASH_FUNC*NUM_WORDS)/numBits), NUM_HASH_FUNC);
	}
	
}
