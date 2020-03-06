package projectEuler;

class P021 extends Problem {

	@Override
	long solve(boolean printResults) {
		int amNumsSum = 0;
		PrimeFinder pf = new PrimeFinder(10000);

		for (int i = 3; i < 10000; i++) {
			int sigI = pf.sigma(i);
			// if i is an amicable number, then σ(i) - i would have to be its friend
			int j = sigI - i;
			int sigJ = pf.sigma(j);

			// now we actually check if they are in fact amicable numbers
			if (sigI == sigJ && i != j)
				amNumsSum += i;
		}

		if (printResults)
			System.out.println(amNumsSum + " is the sum of all amicable numbers below 10000");

		return amNumsSum;
	}

	@Override
	String getTitle() {
		return "Problem 021: Amicable Numbers";
	}

}