package util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PlagiarismQueue {
	private static PlagiarismQueue instance;
	private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

	private PlagiarismQueue() {
	}

	public static synchronized PlagiarismQueue getInstance() {
		if (instance == null) {
			instance = new PlagiarismQueue();
		}
		return instance;
	}

	public void addEssayToQueue(int essayId) {
		queue.add(essayId);
	}

	public Integer takeEssay() throws InterruptedException {
		return queue.take();
	}
}
