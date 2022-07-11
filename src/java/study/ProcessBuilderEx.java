public class ProcessBuilderEx {

    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        List<String> commands = Arrays.asList("test.exe", Integer.toString(num1), Integer.toString(num2));
        ProcessBuilder processBuilder = new ProcessBuilder(commands);

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("exitCode : " + exitCode);
          
            /*
            InputStream is = process.getInputStream();
            byte buffer = new byte[1024];
            int len = is.read(buffer);
            String line = new String(buffer, 0, len);
            System.out.println(line);
            */

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
