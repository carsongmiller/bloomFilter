package bloomFilter;

import java.io.IOException;

public class driver {

	public static void main(String[] args) throws IOException
	{
		int M = 1000000; //want M to be prime
		String fileName = "basic.txt"; //name of word list file
		BloomFilter bloom = new BloomFilter(M);
		bloom.populateBitArray(fileName);
		String check = "sheepers";
		if(bloom.isIn(check))
		{
			System.out.println(check + " is a valid word");
			System.out.println("Probability of error " + bloom.accuracy());
		}
		else
			System.out.println(check + " is not a valid word");
		//System.out.println(bloom.accuracy());
		//System.out.println(Math.pow(1 - Math.exp((-1*(long)6*(long)850)/(long)M), (long)6));
	}
}