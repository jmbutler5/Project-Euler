package projectEuler;

class P028 extends ParameterizedProblem<Integer> {

    @Override
	public Integer getDefaultParameter() {
        return 1001;
    }

    @Override
    public long solve(Integer gridLength, boolean printResults) {
        // see my writeup for the derivation of this formula
        int diaLen = gridLength / 2;
        int total = diaLen * (16 * diaLen * diaLen + 30 * diaLen + 26) / 3 + 1;

        if (printResults)
            System.out.println(total + " is the sum of all diagonals diagonals.");
        return total;
    }

    @Override
    protected Integer getTestParameter() {
        return 5;
    }

    @Override
    protected long getTestSolution() {
        return 101;
    }

    @Override
	public String getTitle() {
        return "Problem 028: Number Spiral Diagonals";
    }

}
