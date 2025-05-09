import java.io.*;

public class UserInput{
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr);
             FileWriter writer = new FileWriter("user_input.txt")) {

            String inputLine;
            System.out.println("Enter text (type 'exit' to quit):");

            while (!(inputLine = reader.readLine()).equalsIgnoreCase("exit")) {
                writer.write(inputLine + System.lineSeparator());
            }

            System.out.println("Input saved to user_input.txt");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
