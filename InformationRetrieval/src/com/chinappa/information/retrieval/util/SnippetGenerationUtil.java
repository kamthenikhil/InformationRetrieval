package com.chinappa.information.retrieval.util;

public class SnippetGenerationUtil {

	public static int[] solve(int[][] lists) {
		int m = lists.length;
		// the current selected element from each list
		int[] pos = new int[m];
		// the current best solution positions
		int[] sol = new int[m];
		// the score (window length) of current solution
		int currSol = Integer.MAX_VALUE;
		while (true) {
			// select the list that has the increasing minimum element
			int minList = argmin(pos, lists);
			// if you can't increase the minimum, stop
			if (minList == -1)
				break;
			// calculate the window size
			int minValue = lists[minList][pos[minList]];
			int maxValue = max(pos, lists);
			int nextSol = maxValue - minValue;
			// update the solution if necessary
			if (nextSol < currSol) {
				currSol = nextSol;
				System.arraycopy(pos, 0, sol, 0, m);
			}
			// update the current minumum element
			pos[minList]++;
		}
		return sol;
	}

	private static int argmin(int[] pos, int[][] v) {
		int min = Integer.MAX_VALUE;
		int arg = -1;
		for (int i = 0; i < v.length; ++i) {
			if (v[i][pos[i]] < min) {
				min = v[i][pos[i]];
				arg = i;
			}
		}
		return arg;
	}

	private static int argmax(int[] pos, int[][] v) {
		int max = -1;
		int arg = -1;
		for (int i = 0; i < v.length; ++i) {
			if (v[i][pos[i]] > max) {
				max = v[i][pos[i]];
				arg = i;
			}
		}
		return arg;
	}

	private static int max(int[] pos, int[][] v) {
		int arg = argmax(pos, v);
		return v[arg][pos[arg]];
	}

}
