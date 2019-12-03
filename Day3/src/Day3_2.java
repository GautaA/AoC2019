import java.io.*;
import java.util.*;

public class Day3_2 {

    public static void main(String[] args) throws IOException {

        File filePath = new File("C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day3\\input.txt");
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        List<String> fWire = new ArrayList<String>();
        List<String> fWireDirection = new ArrayList<String>();
        List<String> sWireDirection = new ArrayList<String>();
        List<String> sWire = new ArrayList<String>();

        Map<HashMap<Integer, Integer>, Integer> fCoordinates = new HashMap<HashMap<Integer, Integer>, Integer>();

        int [] pos = {0, 0};

        String currentLine;
        fWire.add(currentLine = reader.readLine());
        fWireDirection = Arrays.asList(currentLine.split(","));
        sWire.add(currentLine = reader.readLine());
        sWireDirection = Arrays.asList(currentLine.split(","));

        List<Integer> distances = new ArrayList<Integer>();
        List<Integer> stepsCalulated = new ArrayList<Integer>();
        Map<HashMap<Integer, Integer>, Integer> fSteps = new HashMap<HashMap<Integer, Integer>, Integer>();
        Map<HashMap<Integer, Integer>, Integer> sSteps = new HashMap<HashMap<Integer, Integer>, Integer>();

        int lastSteps = 0;

        for (int i = 0; i < fWireDirection.size(); i++) {
            int value = Integer.parseInt(fWireDirection.get(i).replaceAll("[\\D]", ""));

            if (fWireDirection.get(i).contains("U")) lastSteps = up(fCoordinates, value, pos, fSteps, lastSteps, false);
            else if (fWireDirection.get(i).contains("D")) lastSteps = down(fCoordinates, value, pos, fSteps, lastSteps, false);
            else if (fWireDirection.get(i).contains("L")) lastSteps = left(fCoordinates, value, pos, fSteps, lastSteps, false);
            else lastSteps = right(fCoordinates, value, pos, fSteps, lastSteps, false);
        }

        pos[0] = pos[1] = 0;
        lastSteps = 0;

        for (int i = 0; i < sWireDirection.size(); i++) {
            int value = Integer.parseInt(sWireDirection.get(i).replaceAll("[\\D]", ""));

            if (sWireDirection.get(i).contains("U")) lastSteps = up(fCoordinates, value, pos, sSteps, lastSteps, true);
            else if (sWireDirection.get(i).contains("D")) lastSteps = down(fCoordinates, value, pos, sSteps, lastSteps, true);
            else if (sWireDirection.get(i).contains("L")) lastSteps = left(fCoordinates, value, pos, sSteps, lastSteps, true);
            else lastSteps = right(fCoordinates, value, pos, sSteps, lastSteps, true);
        }

        for (HashMap<Integer, Integer> current : fCoordinates.keySet()) {
            if (fCoordinates.get(current) == 2) {
                for (Integer currentInner : current.keySet()) {
                    int distance = Math.abs(currentInner) + Math.abs(current.get(currentInner));
                    distances.add(distance);
                    stepsCalulated.add(fSteps.get(current) + sSteps.get(current));
                }
            }
        }

        Collections.sort(distances);
        Collections.sort(stepsCalulated);
        System.out.println(distances);
        System.out.println(stepsCalulated);
    }

    public static int left(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, Map<HashMap<Integer, Integer>, Integer> steps, int lastSteps, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[0]--;
            lastSteps++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                    steps.put(temp, lastSteps);
                } else if (coordinates.get(temp) == 2){
                    coordinates.put(temp,2);
                } else {
                    coordinates.put(temp,3);
                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
                steps.put(temp, lastSteps);
            } else {
                if (coordinates.containsKey(temp)) {
                    coordinates.put(temp, 1);
                } else {
                    coordinates.put(temp, 1);
                    steps.put(temp, lastSteps);
                }
            }
        }
        return lastSteps;
    }

    public static int right(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, Map<HashMap<Integer, Integer>, Integer> steps, int lastSteps, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[0]++;
            lastSteps++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                    steps.put(temp, lastSteps);
                } else if (coordinates.get(temp) == 2){
                    coordinates.put(temp,2);

                } else {
                    coordinates.put(temp,3);

                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
                steps.put(temp, lastSteps);
            } else {
                if (coordinates.containsKey(temp)) {
                    coordinates.put(temp, 1);

                } else {
                    coordinates.put(temp, 1);
                    steps.put(temp, lastSteps);
                }
            }
        }
        return lastSteps;
    }

    public static int up(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, Map<HashMap<Integer, Integer>, Integer> steps, int lastSteps, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[1]++;
            lastSteps++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                    steps.put(temp, lastSteps);
                } else if (coordinates.get(temp) == 2){
                    coordinates.put(temp,2);

                } else {
                    coordinates.put(temp,3);

                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
                steps.put(temp, lastSteps);
            } else {
                if (coordinates.containsKey(temp)) {
                    coordinates.put(temp, 1);

                } else {
                    coordinates.put(temp, 1);
                    steps.put(temp, lastSteps);
                }
            }
        }
        return lastSteps;
    }

    public static int down(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, Map<HashMap<Integer, Integer>, Integer> steps, int lastSteps, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[1]--;
            lastSteps++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                    steps.put(temp, lastSteps);
                } else if (coordinates.get(temp) == 2) {
                    coordinates.put(temp, 2);

                } else {
                    coordinates.put(temp, 3);

                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
                steps.put(temp, lastSteps);
            } else {
                if (coordinates.containsKey(temp)) {
                    coordinates.put(temp, 1);

                } else {
                    coordinates.put(temp, 1);
                    steps.put(temp, lastSteps);
                }
            }
        }
        return lastSteps;
    }
}
