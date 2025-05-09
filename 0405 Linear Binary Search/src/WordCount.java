import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\user\\Desktop\\CG training JAVA\\Week 3\\0405_Linear Binary Search\\src\\Hello World.txt";
        String targetWord = "line";

        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equals(targetWord)) {
                        count++;
                    }
                }
            }

            System.out.println("The word \"" + targetWord + "\" appears " + count + " times.");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
