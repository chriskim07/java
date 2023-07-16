import java.util.Iterator;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscription implements Subscription {

	private Iterator<Integer> it;
	private Subscriber subscriber;

	public MySubscription(Iterable<Integer> its, Subscriber subscriber) {
		System.out.println("MySubscription!");
		this.it = its.iterator();
		this.subscriber = subscriber;
	}

	@Override
	public void request(long n) {
		System.out.println("MySubscription request : " + n);
		while (n-- > 0) {
			if (it.hasNext()) {
				subscriber.onNext(it.next());
			} else {
				subscriber.onComplete();
			}
		}

	}

	@Override
	public void cancel() {
		System.out.println("MySubscription cancel");
	}

}
