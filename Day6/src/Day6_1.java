import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6_1 {

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

        int directOrbits = 0;
        int indirectOrbits = 0;

        for (String current : allNodes.keySet()) {
            directOrbits += allNodes.get(current).size();
            for (String value : allNodes.get(current)) {
                if (allNodes.get(value) != null) indirectOrbits += checkKids(value, allNodes, new ArrayList<String>());
            }
        }

        System.out.println(directOrbits + indirectOrbits);
    }

    private static int checkKids(String value, Map<String, ArrayList<String>> allNodes, ArrayList<String> passedNodes) {
        int count = 0;
        if (allNodes.get(value) == null) {
            passedNodes.add(value);
            return 0;
        }
        if (passedNodes.contains(value)) return 0;
        else {
            count = allNodes.get(value).size();
            passedNodes.add(value);
            for (String inner : allNodes.get(value)) {
                count += checkKids(inner, allNodes, passedNodes);
            }
            return count;
        }
    }
}