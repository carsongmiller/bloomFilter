package bloomFilter;

import java.io.*;

public class BitArray 
{
	private int[] b;
	private int numBits;
	
	public BitArray(int M) throws IOException
	{
		numBits = M; //number of bits to be held in b[]
		if(M < 0) //illegal value for M
			throw new IllegalArgumentException();
		
		else //read words from basic.txt, run through hash functions, and set bits in b[] accordingly
			b = new int[(int)Math.ceil(M/32.0)];
	}
	
	public boolean get(int n) //returns value of bit in bloom filter at index 1
	{
		if(n < 0 || n >= numBits)
			throw new IndexOutOfBoundsException("Index " + n);
		
		else
		{
			int m = 1;
			m = m << (31-(n%32));
			if((m & b[n/32]) != 0)
				return true;
			else
				return false;
		}
	}
	
	
	
	public void set(int n) //sets bit in bloom filter at index n to 1
	{
		if(n < 0 || n >= numBits)
			throw new IndexOutOfBoundsException("Index: " + n);
		
		else
		{
			int m = 1;
			m = m << (31-(n%32));
			b[n/32] = b[n/32] | m;
		}
	}
}
