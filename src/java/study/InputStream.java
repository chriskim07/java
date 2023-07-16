import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InputStream {

	private static final int DELAY_TIME = 1000;

	public static void main(String[] args) {
		File file = new File("src/fileInput.txt");

		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

			while (true) {
				final String le = br.readLine();
				if (le != null) {
					System.out.println("readLine : " + le);
				} else {
					System.out.println("DELAY_TIME");
					Thread.sleep(DELAY_TIME);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + file.getPath());
		}
		System.out.println("Stop : " + file.getName());

	}

}
