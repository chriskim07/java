
public class ThreadSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadClass tc1 = new ThreadClass();
		tc1.start();
		
		try {
			tc1.join();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

class ThreadClass extends Thread {
	public void run() {
		System.out.println("Thread is running.");
	}
}
