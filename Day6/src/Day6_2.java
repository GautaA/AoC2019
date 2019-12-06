import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_2 {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day6\\input.txt";
        List<String> input = null;
        Map<String, ArrayList<String>> allNodes = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            input = stream.flatMap(l -> Arrays.stream(l.split("\\)"))).map(String::trim)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < input.size(); i += 2) {
            allNodes.computeIfAbsent(input.get(i), k -> new ArrayList<>()).add(input.get(i + 1));
        }

        List<String> sanParents = new ArrayList<>();
        List<String> myParents = new ArrayList<>();

        sanParents = calculateParents("SAN", allNodes, new ArrayList<String>());
        myParents = calculateParents("YOU", allNodes, new ArrayList<String>());

        List<String> together = new ArrayList<>();

        for (String current : myParents) {
            if (sanParents.contains(current)) together.add(current);
        }

        int steps = -1;

        for (String current : myParents) {
            steps++;
            if (current.equals(together.get(0))) break;
        }

        int index = -1;

        for (String current : sanParents) {
            index++;
            if (current.equals(together.get(0))) break;
        }

        System.out.println(steps+index);

    }

    private static ArrayList<String> calculateParents(String kid, Map<String, ArrayList<String>> allNodes, ArrayList<String> passedNodes) {
        for (String current : allNodes.keySet()) {
            for (String value : allNodes.get(current)) {
                if (value.equals(kid)) {
                    passedNodes.add(current);
                    calculateParents(current, allNodes, passedNodes);
                }
            }
        }
        return passedNodes;
    }
}