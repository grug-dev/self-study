package com.kkpa.multithreading.forkjoinpool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class AscendentSortArrayTask extends RecursiveTask<int[]> {

	private int workLoad;
	private int[] numbersToBeSorted;

	public AscendentSortArrayTask(int workLoad, int[] numbersToBeSorted) {
		this.workLoad = workLoad;
		this.numbersToBeSorted = numbersToBeSorted;
	}

	@Override
	protected int[] compute() {
		if (numbersToBeSorted == null || numbersToBeSorted.length == 1) {
			return numbersToBeSorted;
		}

		if (numbersToBeSorted.length > workLoad) {
			List<AscendentSortArrayTask> newTasks = splitTaskAndCreate(numbersToBeSorted);
			newTasks.forEach(t -> t.fork());

			int[] resultsJoined = new int[0];

			for (AscendentSortArrayTask task : newTasks) {
				int[] tmp = task.join();
				resultsJoined = merge(resultsJoined, tmp);
			}

			return resultsJoined;

		} else {
			order(numbersToBeSorted);
		}

		return numbersToBeSorted;

	}

	public static int[] merge(int[] firstResult, int[] secondResult) {
		List<Integer> merged = new ArrayList<>();

		merged.addAll(Arrays.stream(firstResult).boxed().collect(Collectors.toList()));
		merged.addAll(Arrays.stream(secondResult).boxed().collect(Collectors.toList()));

		return merged.stream().sorted().mapToInt(i -> i).toArray();
	}

	private void order(int[] pNumbers) {
		Arrays.stream(pNumbers).sorted();
	}

	private List<AscendentSortArrayTask> splitTaskAndCreate(int[] pNumbersToBeSorted) {
		int half = pNumbersToBeSorted.length / 2;
		int start = 0;
		int end = pNumbersToBeSorted.length;

		int[] startToHalf = Arrays.copyOfRange(pNumbersToBeSorted, start, half);
		int[] halfToEnd = Arrays.copyOfRange(pNumbersToBeSorted, half, end);

		List<AscendentSortArrayTask> newTasks = new ArrayList<>();
		newTasks.add(new AscendentSortArrayTask(workLoad, startToHalf));
		newTasks.add(new AscendentSortArrayTask(workLoad, halfToEnd));

		return newTasks;

	}

}
