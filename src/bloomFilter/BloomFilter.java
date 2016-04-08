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
	
	
	
	public void add(String w)
	{
		int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;
		//run word through hash functions and set appropriate bits in b[]
		try
		{
			bitArray.set(Math.abs(h1(w)));		//hash 1
			bitArray.set(Math.abs(h2(w)));		//hash 2
			bitArray.set(Math.abs(h3(w)));		//hash 3
			bitArray.set(Math.abs(h4(w)));		//hash 4
			bitArray.set(Math.abs(h5(w)));		//hash 5
			bitArray.set(Math.abs(h6(w)));		//hash 6
			
//			System.out.println(w);
//				System.out.println("h1: " + Math.abs(h1(w)) + "  " + bitArray.get(Math.abs(h1(w))));
//				System.out.println("h2: " + Math.abs(h2(w)) + "  " + bitArray.get(Math.abs(h2(w))));
//				System.out.println("h3: " + Math.abs(h3(w)) + "  " + bitArray.get(Math.abs(h3(w))));
//				System.out.println("h4: " + Math.abs(h4(w)) + "  " + bitArray.get(Math.abs(h4(w))));
//				System.out.println("h5: " + Math.abs(h5(w)) + "  " + bitArray.get(Math.abs(h5(w))));
//				System.out.println("h6: " + Math.abs(h6(w)) + "  " + bitArray.get(Math.abs(h6(w))));
//			System.out.println("");
			
		} 
		catch (IndexOutOfBoundsException exc)
		{
			System.out.println(exc);
		}
		
		a = Math.abs(h1(w));
		b = Math.abs(h2(w));
		c = Math.abs(h3(w));
		d = Math.abs(h4(w));
		e = Math.abs(h5(w));
		f = Math.abs(h6(w));

		if(a == b)
			System.out.println("hash collision a  b  " + w);
		else if(a == c)
			System.out.println("hash collision a  c  " + w);
		else if(a == d)
			System.out.println("hash collision a  d  " + w);
		else if(a == e)
			System.out.println("hash collision a  e  " + w);
		else if(a == f)
			System.out.println("hash collision a  f  " + w);
		else if(b == c)
			System.out.println("hash collision b  c  " + w);
		else if(b == d)
			System.out.println("hash collision b  d  " + w);
		else if(b == e)
			System.out.println("hash collision b  e  " + w);
		else if(b == f)
			System.out.println("hash collision b  f  " + w);
		else if(c == d)
			System.out.println("hash collision c  d  " + w);
		else if(c == e)
			System.out.println("hash collision c  e  " + w);
		else if(c == f)
			System.out.println("hash collision c  f  " + w);
		else if(d == e)
			System.out.println("hash collision d  e  " + w);
		else if(d == f)
			System.out.println("hash collision d  f  " + w);
		else if(e == f)
			System.out.println("hash collision e  f  " + w);
			
	}
	
	
	
	public boolean isIn(String w)
	{
		if(!bitArray.get(Math.abs(h1(w)), w))
			return false;
		else if(!bitArray.get(Math.abs(h2(w))))
			return false;
		else if(!bitArray.get(Math.abs(h3(w))))
			return false;
		else if(!bitArray.get(Math.abs(h4(w))))
			return false;
		else if(!bitArray.get(Math.abs(h5(w))))
			return false;
		else if(!bitArray.get(Math.abs(h6(w))))
			return false;
		else
			return true;
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
	
	
	
	public double accuracy()
	{
		return Math.pow(1 - Math.exp(NUM_HASH*NUM_WORDS*-1/(double)numBits), NUM_HASH);
	}
	
}
