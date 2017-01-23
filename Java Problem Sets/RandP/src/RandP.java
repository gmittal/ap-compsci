import java.util.Random;

public class RandP {

	private int sampleSize;
	private Random indexGen;
	private int[] sample;

	public RandP(int n) {
		indexGen = new Random();		
		sampleSize = n;
		sample = new int[n];

		for (int i = 0; i < n; i++)
			sample[i] = i+1;
	}

	public int nextInt() {
		if (sampleSize > 0) {
			/*
			 * Pull a random number from the sample set given the sample size.
			 * Return that number.
			 * Set that object in the array to whatever number is at the end of the sample set.
			 * Set the object at the end of the sample set to 0.
			 * Reduce the sample size by one.
			 * 
			 * Diagram (if confusing): https://puu.sh/twe8R/eb86d02ccb.png
			 */
			
			int index = indexGen.nextInt(sampleSize);
			int r = sample[index];
			sample[index] = sample[sampleSize-1];
			sample[sampleSize-1] = 0;
			sampleSize--;
			return r;
		} else
			return 0;
		
	}

}
