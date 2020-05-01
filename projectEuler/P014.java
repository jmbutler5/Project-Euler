package projectEuler;

class P014 extends Problem {

	private long[] lengthCache;

	public boolean test() {
		return collatzLength(13) == 10;
	}

	@Override
	long solve(boolean printResults) {
		lengthCache = new long[1000000];
		lengthCache[1] = 1;

		long biggestLength = 0;
		int biggestStart = 0;

		for (int i = 1; i < 1000000; ++i) {
			lengthCache[i] = collatzLength(i);

			if (lengthCache[i] > biggestLength) {
				biggestLength = lengthCache[i];
				biggestStart = i;
			}
		}

		if (printResults)
			System.out.println(biggestStart + " is the longest Collatz sequence under 1000000.");

		return biggestStart;
	}

	/**
	 * Figures out how many iterations of the collatz sequence it takes n to reach 1
	 * 
	 * @param n the starting value of the collatz sequence
	 * @return returns how long it takes the Collatz sequence to reach 1
	 */
	private long collatzLength(long n) {
		// check our cache
		if (lengthCache.length > n && lengthCache[(int) n] != 0)
			return lengthCache[(int) n];

		// recursively compute next length
		long length = 1 + collatzLength(EulerTools.nextCollatz(n));

		// update cache
		if (n < lengthCache.length)
			lengthCache[(int) n] = length;

		return length;
	}

	@Override
	String getTitle() {
		return "Problem 014: Longest Collatz Sequence";
	}

}