
public class ThreadRunnable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadClassRunnable m1 = new ThreadClassRunnable();
		Thread t1 = new Thread(m1);
		t1.start();

		try {
			t1.join();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

class ThreadClassRunnable implements Runnable {
	public void run() {
		System.out.println("thread is running...");
	}
}
