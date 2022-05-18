package java.study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MessageSol1 {

	public static void main(String[] args) {
		// SP 1 Solution
		try {
			Scanner scan = new Scanner(System.in);
			Queue<String> q = new LinkedList<>();

			while (true) {
				String input = scan.nextLine();

				if ( input.startsWith("SEND") ) {
					q.offer(input.split(" ")[1]);
//					q.add(input.substring(input.indexOf(" ") + 1)); // // Message에 빈칸이 있으면 Message 잘림 현상 방지
				} else if ( input.startsWith("RECEIVE") ) {
					if (q.size() > 0)
						System.out.println(q.poll());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
