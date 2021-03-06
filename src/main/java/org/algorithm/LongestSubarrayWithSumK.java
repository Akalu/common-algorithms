package org.algorithm;

/**
 * Given an array A of n numbers and a key k, and returns the length of a
 * longest subarray of A for which the subarray sum is less than or equal to k.
 *
 */
public class LongestSubarrayWithSumK {

	public static int findLongestSubarrayLessEqualK(int[] arr, int k) {
		int n = arr.length;
		// Build the prefix sum array
		int[] prefixSum = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += arr[i];
			prefixSum[i] = sum;
		}
		// Early returns if the sum of arr is smaller than or equal to k.
		if (prefixSum[n - 1] <= k) {
			return n;
		}
		// Builds minPrefixSum.
		int[] minPrefixSum = prefixSum.clone();
		for (int i = n - 2; i >= 0; --i) {
			minPrefixSum[i] = Math.min(minPrefixSum[i], minPrefixSum[i + 1]);
		}
		int a = 0, b = 0, maxLength = 0;
		while (a < n && b < n) {
			int minCurrSum = a > 0 ? minPrefixSum[b] - prefixSum[a - 1] : minPrefixSum[b];
			if (minCurrSum <= k) {
				int currLength = b - a + 1;
				if (currLength > maxLength) {
					maxLength = currLength;
				}
				++b;
			} else { // minCurrSum > k.
				++a;
			}
		}
		return maxLength;
	}

	public static void main(String[] arg) {

		int[] arr = { 431, -15, 639, 342, -14, 565, -924, 635, 167, -70 };

		System.out.println(findLongestSubarrayLessEqualK(arr, 184));

	}

}
