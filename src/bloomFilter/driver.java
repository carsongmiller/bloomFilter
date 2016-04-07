package bloomFilter;

import java.io.IOException;

public class driver {

	public static void main(String[] args) throws IOException
	{
		int M = 1000;
		BloomFilter bloom = new BloomFilter(M);
//		bloom.populateBitArray("basic.txt");
//		System.out.println(bloom.accuracy());
		
		String str = "account";
		int i = bloom.bitArray.set(Math.abs(str.hashCode() % M), str);
		System.out.println(i);
	}
}