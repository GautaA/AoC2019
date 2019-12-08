import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day8_1 {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day8\\input.txt";
        String result = Files.readString(Paths.get(filePath));

        int currentZero = 0, minZero = 0, currentOne = 0, maxOne = 0, currentTwo = 0, maxTwo =  0;


        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '0') currentZero++;
            else if (result.charAt(i) == '1') currentOne++;
            else currentTwo++;

            if ((i+1) % 150 == 0) {
                if (currentZero < minZero || minZero == 0) {
                    minZero = currentZero;
                    maxOne = currentOne;
                    maxTwo = currentTwo;
                }
                currentZero = 0;
                currentOne = 0;
                currentTwo = 0;
            }
        }

        System.out.println(maxOne * maxTwo);
    }
}
