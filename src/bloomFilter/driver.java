package bloomFilter;

import java.io.IOException;

public class driver {

	public static void main(String[] args) throws IOException
	{
		int M = 850;
		String fileName = "basic.txt"; //name of word list file
		BloomFilter bloom = new BloomFilter(M);
		bloom.populateBitArray(fileName); //add words to bit array
		
		
		//Read the words from the file a second time to check if they're in the filter
			String check = null;
			String dir = System.getProperty("user.dir") + "\\" + fileName; //get directory of basic.txt
			LineReader lineReader = new LineReader(dir);
			System.out.println("Words not in Bloom Filter:");
			while(lineReader.hasNext())
			{
				check = lineReader.next();
				if(!bloom.isIn(check))
				{
					System.out.println(check);
				}
			}
		
		System.out.println("\nAccuracy (possibility of error): " + bloom.accuracy());
	}
}