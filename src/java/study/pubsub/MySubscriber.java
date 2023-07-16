import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class MySubscriber implements Subscriber<Integer> {

	private Integer size = 3;
	private Integer maxSize = 3;
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(size);
	}

	@Override
	public void onNext(Integer item) {
		System.out.println("onNext : " + item);
		size--;
		if (size == 0) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			size = maxSize;
			subscription.request(size);
		}
	}

	@Override
	public void onError(Throwable throwable) {
		System.out.println("onError");
	}

	@Override
	public void onComplete() {
		System.out.println("onComplete");
	}

}
