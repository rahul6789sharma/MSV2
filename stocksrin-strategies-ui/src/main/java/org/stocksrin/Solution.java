package org.stocksrin;

class Solution {
	
	static boolean canNotBeAesthetical;

	public int solution(int[] A) {
		
		if (testAesthetical(A)) {
			return 0;
		} else if (canNotBeAesthetical) {
			return -1;
		} else {
			int successfulToBeAestheticalCount = 0;
			int[] newArray = new int[A.length - 1];

			for (int i = 0; i < A.length; i++) // element to remove each time
			{
				for (int k = 0; k < i; k++) {
					newArray[k] = A[k];
				}
				for (int j = i + 1; j < A.length; j++) {
					newArray[j - 1] = A[j];
				}

				if (testAesthetical(newArray)) {
					successfulToBeAestheticalCount++;
				}

			}

			return successfulToBeAestheticalCount;
		}
	}

	private boolean testAesthetical(int[] a) {
		
		int up = 0;
		int down = 0;
		int equal = 0;
		int lastComparison = -2;
		int currentComparison = -2;
		boolean isAetheticalTillNow = false;

		for (int i = 0; (i < a.length - 1) && (up < 3 && down < 3 && equal < 3); i++) {
			if (heightComparison(a[i], a[i + 1]) == 1) {
				up++;
				down = 0;
				equal = 0;
				currentComparison = 1;

				if (lastComparison == -2) {
					lastComparison = 1;
					isAetheticalTillNow = true;
				} else {
					if (lastComparison != currentComparison) {
						if (lastComparison == -1 && isAetheticalTillNow == true) {
							isAetheticalTillNow = true;
						} else {
							isAetheticalTillNow = false;
						}

					} else {
						lastComparison = currentComparison;
						isAetheticalTillNow = false;
					}
				}

			} else if (heightComparison(a[i], a[i + 1]) == 0) {
				equal++;
				up = 0;
				down = 0;
				isAetheticalTillNow = false;
				lastComparison = 0;
				currentComparison = 0;
			} else if (heightComparison(a[i], a[i + 1]) == -1) {
				down++;
				up = 0;
				equal = 0;
				currentComparison = -1;

				if (lastComparison == -2) {
					lastComparison = -1;
					isAetheticalTillNow = true;
				} else {
					if (lastComparison != currentComparison) {
						if (lastComparison == 1 && isAetheticalTillNow == true) {
							isAetheticalTillNow = true;
							lastComparison = currentComparison;
						} else {
							isAetheticalTillNow = false;
						}

					} else {
						lastComparison = currentComparison;
						isAetheticalTillNow = false;
					}

				}
			}
		}
		if (up >= 3 || down >= 3 || equal >= 3) {
			canNotBeAesthetical = true;
			return false;
		}
		return isAetheticalTillNow;
	}

	private int heightComparison(int a, int b) {
		if (a < b) {
			return 1;
		} else if (a == b) {
			return 0;
		} else
			return -1;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] array = { 3, 4, 5, 3, 7 };
		int[] array2 = { 1, 3, 1, 2 };
		int[] array3 = { 1, 2, 3, 4 };
		System.out.println(s.solution(array));
		System.out.println(s.solution(array2));
		System.out.println(s.solution(array3));

	}
}