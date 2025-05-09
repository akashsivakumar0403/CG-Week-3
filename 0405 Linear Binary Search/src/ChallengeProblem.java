import java.io.*;

public class ChallengeProblem {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        String word = "hello";
        long startSB = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(word);
        }
        long endSB = System.nanoTime();
        System.out.println("StringBuilder time: " + (endSB - startSB) / 1_000_000 + " ms");

        long startSBuf = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbuf.append(word);
        }
        long endSBuf = System.nanoTime();
        System.out.println("StringBuffer time: " + (endSBuf - startSBuf) / 1_000_000 + " ms");

        System.out.println("------------------------------------------------");

        String filePath = "C:\\Users\\user\\Desktop\\CG training JAVA\\Week 3\\0405_Linear Binary Search\\src\\Hello World.txt";

        long startFR = System.nanoTime();
        int wordCountFR = countWordsWithFileReader(filePath);
        long endFR = System.nanoTime();
        System.out.println("FileReader word count: " + wordCountFR + ", time: " + (endFR - startFR) / 1_000_000 + " ms");

        long startISR = System.nanoTime();
        int wordCountISR = countWordsWithInputStreamReader(filePath);
        long endISR = System.nanoTime();
        System.out.println("InputStreamReader word count: " + wordCountISR + ", time: " + (endISR - startISR) / 1_000_000 + " ms");
    }

    public static int countWordsWithFileReader(String filePath) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += line.split("\\s+").length;
            }
        } catch (IOException e) {
            System.out.println("FileReader error: " + e.getMessage());
        }
        return count;
    }

    public static int countWordsWithInputStreamReader(String filePath) {
        int count = 0;
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                count += line.split("\\s+").length;
            }
        } catch (IOException e) {
            System.out.println("InputStreamReader error: " + e.getMessage());
        }
        return count;
    }
}
