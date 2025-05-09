import java.io.*;

public class ByteToChar{
    public static void main(String[] args) {
        String filePath = "C:\\Users\\user\\Desktop\\CG training JAVA\\Week 3\\0405_Linear Binary Search\\src\\Hello World.txt";

        try (FileInputStream fis = new FileInputStream(filePath);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader reader = new BufferedReader(isr)) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding not supported: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

