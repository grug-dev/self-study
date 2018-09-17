package com.kkpa.multithreading.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class AscendentSortCollectionTask extends RecursiveTask<List<Integer>> {

	private int workLoad;
	private List<Integer> numbersToBeSorted;

	public AscendentSortCollectionTask(int workLoad, List<Integer> numbersToBeSorted) {
		this.workLoad = workLoad;
		this.numbersToBeSorted = numbersToBeSorted;
	}

	@Override
	protected List<Integer> compute() {
		if (numbersToBeSorted == null || numbersToBeSorted.size() == 1) {
			return numbersToBeSorted;
		}

		if (numbersToBeSorted.size() > workLoad) {
			List<AscendentSortCollectionTask> newTasks = splitTaskAndCreate(numbersToBeSorted);
			newTasks.forEach(t -> t.fork());

			List<Integer> resultsJoined = new ArrayList<>();
			newTasks.forEach(t -> {
				resultsJoined.addAll(t.join());
				order(resultsJoined);
			});

			return resultsJoined;

		} else {
			order(numbersToBeSorted);
		}

		return numbersToBeSorted;

	}

	private void order(List<Integer> pNumbers) {
		pNumbers.sort(Integer::compareTo);
	}

	private List<AscendentSortCollectionTask> splitTaskAndCreate(List<Integer> pNumbersToBeSorted) {
		int half = pNumbersToBeSorted.size() / 2;
		int start = 0;
		int end = pNumbersToBeSorted.size();

		List<Integer> startToHalf = pNumbersToBeSorted.subList(start, half);
		List<Integer> halfToEnd = pNumbersToBeSorted.subList(half, end);

		List<AscendentSortCollectionTask> newTasks = new ArrayList<>();
		newTasks.add(new AscendentSortCollectionTask(workLoad, startToHalf));
		newTasks.add(new AscendentSortCollectionTask(workLoad, halfToEnd));

		return newTasks;

	}

}
