import java.io.*;
import java.util.*;

public class Day3_1 {

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

        for (int i = 0; i < fWireDirection.size(); i++) {
            int value = Integer.parseInt(fWireDirection.get(i).replaceAll("[\\D]", ""));

            if (fWireDirection.get(i).contains("U")) up(fCoordinates, value, pos, false);
            else if (fWireDirection.get(i).contains("D")) down(fCoordinates, value, pos, false);
            else if (fWireDirection.get(i).contains("L")) left(fCoordinates, value, pos, false);
            else right(fCoordinates, value, pos, false);
        }

        pos[0] = pos[1] = 0;

        for (int i = 0; i < sWireDirection.size(); i++) {
            int value = Integer.parseInt(sWireDirection.get(i).replaceAll("[\\D]", ""));

            if (sWireDirection.get(i).contains("U")) up(fCoordinates, value, pos, true);
            else if (sWireDirection.get(i).contains("D")) down(fCoordinates, value, pos, true);
            else if (sWireDirection.get(i).contains("L")) left(fCoordinates, value, pos, true);
            else right(fCoordinates, value, pos, true);
        }

        for (HashMap<Integer, Integer> current : fCoordinates.keySet()) {
            if (fCoordinates.get(current) == 2) {
                for (Integer currentInner : current.keySet()) {
                    int distance = Math.abs(currentInner) + Math.abs(current.get(currentInner));
                    distances.add(distance);
                }
            }
        }

        Collections.sort(distances);
        System.out.println(distances);
    }

    public static void left(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[0]--;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
               if (coordinates.get(temp) == 1) {
                   coordinates.put(temp, 2);
               } else {
                   coordinates.put(temp,3);
               }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
            } else {
                coordinates.put(temp, 1);
            }
        }
    }

    public static void right(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[0]++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                } else {
                    coordinates.put(temp, 3);
                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
            } else {
                coordinates.put(temp, 1);
            }
        }
    }

    public static void up(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[1]++;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                } else {
                    coordinates.put(temp, 3);
                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
            } else {
                coordinates.put(temp, 1);
            }
        }
    }

    public static void down(Map<HashMap<Integer, Integer>, Integer> coordinates, int val, int [] pos, boolean needCheck) {
        for (int i = 0; i < val; i++) {
            pos[1]--;
            HashMap<Integer, Integer> temp = new HashMap<>();
            temp.put(pos[0], pos[1]);
            if (needCheck && coordinates.containsKey(temp)) {
                if (coordinates.get(temp) == 1) {
                    coordinates.put(temp, 2);
                } else {
                    coordinates.put(temp,3);
                }
            } else if (needCheck && !coordinates.containsKey(temp)) {
                coordinates.put(temp, 3);
            } else {
                coordinates.put(temp, 1);
            }
        }
    }
}
