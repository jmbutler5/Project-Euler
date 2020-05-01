package projectEuler;

abstract class ProblemTimer extends Timer {
	protected ProblemTimer previousTimer;
	protected Problem[] problems;

	private Problem getProblem(int problemID) {
		if (problemID < 0 || problemID >= problems.length)
			return null;

		return problems[problemID];
	}

	private int[] getAllIDs() {
		int size = problems.length;
		int[] ids = new int[size];

		for (int i = 0; i < size; i++)
			ids[i] = i;

		return ids;
	}

	long time(int problemID) {
		if (getProblem(problemID) == null)
			return -1;
		return Timer.time(getProblem(problemID), null, DEFUALT_TRIALS, false);
	}

	public boolean test(int problemID) {
		Problem problem = getProblem(problemID);
		if (problem == null)
			return false;
		return problem.test();
	}

	void report(int problemID) {
		Problem problem = getProblem(problemID);

		if (problem == null) {
			System.out.println("There is no problem with ID " + problemID);
			return;
		}

		String title = problem.getTitle();

		if (!test(problemID)) {
			System.out.println(title + " failed its test");
			return;
		}

		long time = time(problemID);
		System.out.println(title + " finished in " + time + "ms.");
	}

	long[] time(int[] problemIDs) {
		long[] times = new long[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++)
			times[i] = time(problemIDs[i]);

		return times;
	}

	boolean[] test(int[] problemIDs) {
		boolean[] tests = new boolean[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++)
			tests[i] = test(problemIDs[i]);

		return tests;
	}

	void report(int[] problemIDs) {
		for (int i = 0; i < problemIDs.length; i++)
			report(problemIDs[i]);
	}

	long[] timeInRange() {
		return time(getAllIDs());
	}

	boolean[] testInRange() {
		return test(getAllIDs());
	}

	void reportInRange() {
		report(getAllIDs());
	}

	long[] timeAll() {
		// get the times
		long[] prevTimes = previousTimer.timeAll();
		long[] currTimes = timeInRange();

		long[] allTimes = new long[prevTimes.length + currTimes.length];
		// join the arrays into one array.
		for (int i = 0; i < prevTimes.length; i++)
			allTimes[i] = prevTimes[i];
		for (int i = 0; i < allTimes.length; i++)
			allTimes[i + prevTimes.length] = currTimes[i];

		// return the merged array
		return allTimes;
	}

	void reportAll() {
		previousTimer.reportAll();
		reportInRange();
	}
}