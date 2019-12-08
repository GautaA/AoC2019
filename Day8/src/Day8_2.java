import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Day8_2 {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day8\\input.txt";
        String result = Files.readString(Paths.get(filePath));

        char[] pos = new char[150];
        Arrays.fill(pos, '2');

        System.out.println(result.length());

        for (int i = 0; i < result.length(); i++) {
            pos[i % 150] = pos[i % 150] == '2' ? result.charAt(i) : pos[i % 150];
        }

        for (int i = 0; i < pos.length; i++) {
            if (i % 25 == 0) System.out.println();
            if (pos[i] == '1') System.out.print(pos[i]);
            else System.out.print(" ");
        }
    }
}
