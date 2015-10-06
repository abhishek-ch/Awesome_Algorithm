class AllCombinationOfNumberinArrayForGivenSum {
	
	public static void main(String[] args) throws java.lang.Exception {
		new AllCombinationOfNumberinArrayForGivenSum().run();
	}

	int N = 10;
	int[] arr = { 2, 3, 6, 7 };
	int[] vals = new int[N];

	void run() {
		printCombinations(N, 0, 0);
	}

	// from : consider numbers in arr from index "from"
	// index: add new number in array vals at index "index"
	void printCombinations(int target, int from, int index) {
		if (target == 0) {
			for (int i = 0; i < index; i++) {
				System.out.print(vals[i] + " ");
			}
			System.out.println();
		} else if (target < 0 || from >= arr.length) {
			return;
		} else {
			vals[index] = arr[from];
			// take arr[from] in set
			printCombinations(target - arr[from], from, index + 1);

			// dont take arr[from] in set
			printCombinations(target, from + 1, index);
		}
	}
}
