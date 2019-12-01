import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1_1 {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day1\\input.txt";
        List<Integer> input = new LinkedList<Integer>();
        int result = 0;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            input = stream
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int current : input) {
            result += Math.floorDiv(current, 3) - 2;
        }

        System.out.println(result);

    }
}
