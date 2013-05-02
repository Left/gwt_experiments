package gwt.test.client;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public class PrimesIterator {
	private int CHUNK_SIZE = 10*1024*1024;
	private ArrayList<boolean[]> primes = Lists.newArrayList();

	public boolean isPrime(int v) {
		Preconditions.checkArgument(v >= 1);
		if (v == 1) {
			return true;
		}
		
		int oldLen = getPrimesCalculated();
		if (v >= oldLen) {
			setPrimeByIndex(v, true); // Grow as required
			int newLen = getPrimesCalculated();
			
			for (int i = 2; i < newLen; ++i) {
				if (getPrimeByIndex(i)) {
					// Fill us
					// System.out.println(">" + i + ":" + (oldLen + (i - (oldLen % i))) + "," + newLen);
					for (int j = i + i; j < newLen; j += i) {
						setPrimeByIndex(j, false);
					}
				}
			}
		}

		return getPrimeByIndex(v);
	}

	private int getPrimesCalculated() {
		return primes.size() * CHUNK_SIZE + 2;
	}
	
	private boolean getPrimeByIndex(int i) {
		i-=2; // First stored is 2
		return primes.get(i/CHUNK_SIZE)[i%CHUNK_SIZE];
	}
	
	private void setPrimeByIndex(int i, boolean prime) {
		i-=2; // First stored is 2
		
		while (i >= primes.size() * CHUNK_SIZE) {
			boolean[] chunk = new boolean[CHUNK_SIZE];
			Arrays.fill(chunk, true);
			primes.add(chunk);
		}
		primes.get(i/CHUNK_SIZE)[i%CHUNK_SIZE] = prime;
	}

	public static void main(String[] args) {
		PrimesIterator instance = new PrimesIterator();
		
		int tot = 0;
		for (int i=2;i<100000000; ++i) {
			if (i % 1000000 == 0) {
				System.out.println(i + "->" + tot);
			}
			if (instance.isPrime(i)) {
				// System.out.println(i);
				tot ++;
			}
		}
	}
}
