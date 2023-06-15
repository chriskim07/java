
public class ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName() + " is running[1]");
			}
		});
		t1.start();

		// Lambda Runnable 1
		Runnable taskR = () -> {
			System.out.println(Thread.currentThread().getName() + " is running[2]");
		};
		new Thread(taskR).start();

		// Lambda Runnable 2
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " is running[3]");
		}).start();
	}

}
