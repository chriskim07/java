import java.util.Arrays;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

public class MyPublisher implements Publisher<Integer> {

	Iterable<Integer> its = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

	@Override
	public void subscribe(Subscriber<? super Integer> subscriber) {
		// TODO Auto-generated method stub
		System.out.println("subscribe");
		subscriber.onSubscribe(new MySubscription(its, subscriber));
	}

}
