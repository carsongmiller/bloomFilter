//CSCI 4041 Project 1 - Bloom Filter
//Carson Miller
//April 8 2016

package bloomFilter;

import java.io.IOException;

public class driver {

	public static void main(String[] args) throws IOException
	{
		//I will set M (size of the bit array) to (number of words)*(number of hash functions)
		//M = 850*6 = 5100
		//I find this to be a logical choice for M which produces a satisfactory margin of error
		int M = 5100;
		
		//Assuming that the file containing the words is in the root directory of the java project
		String fileName = "basic.txt"; //name of word list file
		BloomFilter bloom = new BloomFilter(M);
		
		//Decided to write a function populateBitArray in the BloomFilter class to improve simplicity and readability
		//populateBitArray handles reading the words from basic.txt and setting the bits in the bitArray
		//Also counts the number of words read from the file
		bloom.populateBitArray(fileName);
		
		
		//Read the words from the file a second time to check if they're in the filter
			String check = null;
			String dir = System.getProperty("user.dir") + "\\" + fileName; //assuming file is in same directory as java project
			LineReader lineReader = new LineReader(dir);
			boolean foundMissingWord = false;
			System.out.println("Words not in Bloom Filter:");
			while(lineReader.hasNext())
			{
				check = lineReader.next();
				if(!bloom.isIn(check))
				{
					foundMissingWord = true;
					System.out.println(check);
				}
			}
			
			if(!foundMissingWord)
				System.out.println("(All words found)");
		
		System.out.println("\nAccuracy (possibility of error): " + bloom.accuracy());
	}
}



/*

Output:

	Words not in Bloom Filter:
	(All words found)
	
	Accuracy (possibility of error): 0.06379688767642384

*/