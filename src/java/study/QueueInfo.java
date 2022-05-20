package study;

import java.util.List;

public class QueueInfo {
//	Queue<String> q;
//	Queue<String> orgQ;
//	int maxSize;
//	String messageId;
//	QueueInfo(Queue<String> q, Queue<String> orgQ, int maxSize, String messageId) {
//		this.q = q;
//		this.orgQ = orgQ;
//		this.maxSize = maxSize;
//		this.messageId = messageId;
//	}
//	
//	public void setMessageId(String messageId) {
//		this.messageId = messageId;
//	}

	List<DetailInfo> q;
	int maxSize;
	int currentNumber;
	QueueInfo(List<DetailInfo> q, int maxSize, int currentNumber) {
		this.q = q;
		this.maxSize = maxSize;
		this.currentNumber = currentNumber;
	}

}

class DetailInfo {
	String message;
	String messageId;
	DetailInfo(String message, String messageId) {
		this.message = message;
		this.messageId = messageId;
	}
}
