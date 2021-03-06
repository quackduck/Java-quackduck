
public class PowerFinder {

	public long num = 1024l; // this is 2 to the 10th

	public static void main(String[] args) {
		new PowerFinder().go();
	}

	public void go () {
				long startTime = System.currentTimeMillis();
				for (int i = 1; i <= Math.sqrt(num); i++) {
					System.out.println(i);
					for (int j = 1; j <= num; j++) {
						if (Math.pow(i, j) == num) {
							System.out.println(i + " to the " + j + " works!");
						}
						try {
							Thread.sleep(0, 1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				long endTime = System.currentTimeMillis();
				System.out.println("I took " + (endTime - startTime) + " milliseconds");

//		for (long i = 1; i <= Math.sqrt(num); i++) {
//			new Thread(new Threadtask(), Long.toString(i)).start();
//		}

	}


	public class Threadtask implements Runnable {

		public void run() {
			long thenumber = Long.parseLong(Thread.currentThread().getName());
			for(long j = 1; j <= num; j++ ) {
				if (Math.pow(thenumber, j) == num) {
					System.out.println(thenumber + " to the " + j + " works!");
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
