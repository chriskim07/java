package java.study;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class MessageSol2 {

	public static void main(String[] args) {
		
		// SP 2 Solution
		try {
			Scanner scan = new Scanner(System.in);
			Map<String, QueueInfo> map = new HashMap<String, QueueInfo>();
			while (true) {
				
				String input = scan.nextLine();
				String[] inputArr = input.split(" ");
				
				if ( inputArr[0].equals("CREATE") ) {
					if( map.containsKey(inputArr[1])) {
						System.out.println("Queue Exist");
					} else {
						Queue<String> q = new LinkedList<>();
						map.put(inputArr[1], new QueueInfo(q, Integer.parseInt(inputArr[2])));
					}
					
				} else if ( inputArr[0].equals("SEND") ) {
					if( map.containsKey(inputArr[1]) ) {
						QueueInfo info = map.get(inputArr[1]);
						if( info.q.size() == info.maxSize ) {
							System.out.println("Queue Full");
						} else {
							info.q.offer(inputArr[2]);
						}
					}
					
				} else if ( inputArr[0].equals("RECEIVE") ) {
					if( map.containsKey(inputArr[1]) ) {
						QueueInfo info = map.get(inputArr[1]);
						if(info.q.size() > 0) {
							System.out.println(info.q.poll());
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class QueueInfo {
//	String name;
	Queue<String> q;
	int maxSize;
	QueueInfo(Queue<String> q, int maxSize) {
//		this.name = name;
		this.q = q;
		this.maxSize = maxSize;
	}
}
