package bloomFilter;

import java.io.IOException;

public class driver {

	public static void main(String[] args) throws IOException
	{
		int M = 100000; //want M to be prime
		String fileName = "basic.txt"; //name of word list file
		BloomFilter bloom = new BloomFilter(M);
		bloom.populateBitArray(fileName);
		String check = "harbor";
		
		System.out.println("h1: " + Math.abs(bloom.h1(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h1(check))));
		System.out.println("h2: " + Math.abs(bloom.h2(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h2(check))));
		System.out.println("h3: " + Math.abs(bloom.h3(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h3(check))));
		System.out.println("h4: " + Math.abs(bloom.h4(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h4(check))));
		System.out.println("h5: " + Math.abs(bloom.h5(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h5(check))));
		System.out.println("h6: " + Math.abs(bloom.h6(check)) + "  " + bloom.bitArray.get(Math.abs(bloom.h6(check))));
		
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