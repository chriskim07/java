import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;

public class FileIO {

	public static void main(String[] args) throws Exception {
	  outputFile("src/FileIO_1.txt", "src/FileIO_1_output.txt");
  }
  
	static void outputFile(String inputFile, String outputFile) throws Exception {
    // List<String> inputs = Files.readAllLines(new File(inputFile).toPath(), Charset.forName("UTF-8"));
		List<String> inputs = Files.readAllLines(new File(inputFile).toPath());
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

		int cnt = 0;
		for (String input : inputs) {
			bw.write(++cnt + " : " + input);
			bw.newLine();
		}
		bw.close();
	}
  
}
