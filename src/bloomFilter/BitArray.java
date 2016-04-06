package bloomFilter;

public class BitArray 
{
	int[] b;
	int numBits;
	
	public BitArray(int M)
	{
		numBits = M;
		if(M < 0)
			throw new IllegalArgumentException();
		else
			b = new int[(int)Math.ceil(M/32)];
	}
	
	public boolean get(int n) //returns value of bit in bloom filter at index 1
	{
		if(n < 0 || n >= numBits)
			throw new IndexOutOfBoundsException();
		
		else
		{
			int m = 1;
			m = m << (n%32);
			if((m & b[n/32]) != 0)
				return true;
			else
				return false;
		}
	}
	
	public void set(int n) //sets bit in bloom filter at index n to 1
	{
		if(n < 0 || n >= numBits)
			throw new IndexOutOfBoundsException();
		
		else
		{
			int m = 1;
			m = m << (n%32);
			b[n/32] = b[n/32] | m;
		}
	}
	
}
