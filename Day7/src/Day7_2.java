import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Amp {
    ArrayList<Integer> memory;
    int number;

    public Amp(int number) {
        this.memory = new ArrayList<>();
        this.number = number;
    }

}

public class Day7_2 {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\anton\\Desktop\\Fer\\AoC2019\\Day7\\input.txt";
        ArrayList<Integer> input = new ArrayList<>();
        List<Integer> userInputs = new ArrayList<>();
        List<Integer> finalValue = new ArrayList<>();
        List<int[]> saveAll = new ArrayList<>();
        int[] phaseSettings = {9, 8, 7, 6, 5};
        int storedValue;

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            input = (ArrayList<Integer>) stream.flatMap(line -> Arrays.stream(line.split(","))).map(Integer::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] test = {5, 6, 7, 8, 9};
        permute(test, saveAll);

        ArrayList<Integer> refreshedInput = new ArrayList<>(input);

        Amp AmpA = new Amp(0);
        Amp AmpB = new Amp(1);
        Amp AmpC = new Amp(2);
        Amp AmpD = new Amp(3);
        Amp AmpE = new Amp(4);

        for (int[] ints : saveAll) {

            phaseSettings = ints;
            userInputs.clear();
            userInputs.add(0);
            input.clear();
            input.addAll(refreshedInput);

            refreshAll(AmpA, AmpB, AmpC, AmpD, AmpE, refreshedInput);


            List<Integer> allValues = new ArrayList<>();

            int posCounter = -1;
            ArrayList<Integer> positions = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0));
            while (true) {
                posCounter++;
                if (posCounter == 5) posCounter = 0;

                Amp current;

                if (posCounter == 0) current = AmpA;
                else if (posCounter == 1) current = AmpB;
                else if (posCounter == 2) current = AmpC;
                else if (posCounter == 3) current = AmpD;
                else current = AmpE;

                input.clear();
                input.addAll(current.memory);

                if (input.get(positions.get(4)) == 99) {
                    finalValue.add(allValues.get(allValues.size() - 1));
                    break;
                }

                for (int i = positions.get(posCounter); input.get(i) != 99; i++) {
                    if (input.get(i) == 1) {
                        input.set(input.get(i + 3), (input.get(input.get(i + 1)) + input.get(input.get(i + 2))));
                        i = i + 3;
                    } else if (input.get(i) == 2) {
                        int pasMater = input.get(i + 3);
                        input.set(pasMater, input.get(input.get(i + 1)) * input.get(input.get(i + 2)));
                        i = i + 3;
                    } else if (input.get(i) == 3) {
                        if (i != 0) {
                            input.set(input.get(i + 1), userInputs.get(0));
                            userInputs.remove(0);
                        } else {
                            input.set(input.get(i + 1), phaseSettings[posCounter]);
                        }
                        i++;
                    } else if (input.get(i) == 4) {
                        storedValue = input.get(input.get(i + 1));
                        userInputs.add(storedValue);
                        i++;

                        current.memory.clear();
                        current.memory.addAll(input);

                        positions.set(posCounter, i + 1);
                        if (posCounter == 4) allValues.add(storedValue);
                        break;
                    } else if (input.get(i) == 5) {
                        if (input.get(input.get(i + 1)) != 0) i = input.get(i + 2) - 1;
                        else i += 2;
                    } else if (input.get(i) == 6) {
                        if (input.get(input.get(i + 1)) == 0) i = input.get(i + 2) - 1;
                        else i += 2;
                    } else if (input.get(i) == 7) {
                        int store = input.get(i + 3);
                        if (input.get(input.get(i + 1)) < input.get(input.get(i + 2))) input.set(store, 1);
                        else input.set(store, 0);
                        i += 3;
                    } else if (input.get(i) == 8) {
                        int store = input.get(i + 3);
                        if (input.get(input.get(i + 1)).equals(input.get(input.get(i + 2)))) input.set(store, 1);
                        else input.set(store, 0);
                        i += 3;
                    } else {
                        int n = input.get(i);
                        ArrayList<Integer> digits = splitNumber(n);
                        ArrayList<Boolean> modes = new ArrayList<>();
                        int firstParam, secondParam = 0, thirdParam, holder;
                        for (int j = 1; j < 4; j++) {
                            if (digits.get(j) == 1) modes.add(true);
                            else modes.add(false);
                        }

                        if (modes.get(0)) firstParam = input.get(i + 1);
                        else firstParam = input.get(input.get(i + 1));

                        if (digits.get(0) != 3 && digits.get(0) != 4) {
                            if (modes.get(1)) secondParam = input.get(i + 2);
                            else secondParam = input.get(input.get(i + 2));
                        }

                        if (digits.get(0) == 1) {
                            holder = firstParam + secondParam;
                            input.set(input.get(i + 3), holder);
                            i += 3;
                        } else if (digits.get(0) == 2) {
                            holder = firstParam * secondParam;
                            input.set(input.get(i + 3), holder);
                            i += 3;
                        } else if (digits.get(0) == 3) {
                            i++;
                        } else if (digits.get(0) == 4) {
                            System.out.println(firstParam);
                            userInputs.add(firstParam);
                            if (posCounter == 4) finalValue.add(firstParam);
                            i++;
                        } else if (digits.get(0) == 5) {
                            if (firstParam != 0) i = secondParam - 1;
                            else i += 2;
                        } else if (digits.get(0) == 6) {
                            if (firstParam == 0) i = secondParam - 1;
                            else i += 2;
                        } else if (digits.get(0) == 7) {
                            thirdParam = input.get(i + 3);
                            if (firstParam < secondParam) input.set(thirdParam, 1);
                            else input.set(thirdParam, 0);
                            i += 3;
                        } else if (digits.get(0) == 8) {
                            thirdParam = input.get(i + 3);
                            if (firstParam == secondParam) input.set(thirdParam, 1);
                            else input.set(thirdParam, 0);
                            i += 3;
                        }
                    }
                    positions.set(posCounter, i+1);
                }
            }
        }
        System.out.println(Collections.max(finalValue));
    }

    private static ArrayList<Integer> splitNumber(int n) {
        ArrayList<Integer> returnList = new ArrayList<>();
        returnList.add(n % 100);
        n /= 100;
        for (int i = 0; i < 3; i++) {
            returnList.add(n % 10);
            n /= 10;
        }
        return returnList;
    }

    private static void permute(int[] arr, List<int[]> saveAll) {
        permuteHelper(arr, 0, saveAll);
    }

    private static void permuteHelper(int[] arr, int index, List<int[]> saveAll) {
        if(index >= arr.length - 1){
            int[] temp = new int[5];
            System.arraycopy(arr, 0, temp, 0, arr.length - 1 + 1);
            saveAll.add(temp);
            return;
        }

        for(int i = index; i < arr.length; i++){
            int t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;

            permuteHelper(arr, index+1, saveAll);

            t = arr[index];
            arr[index] = arr[i];
            arr[i] = t;
        }
    }

    private static void refreshAll(Amp AmpA, Amp AmpB, Amp AmpC, Amp AmpD, Amp AmpE, ArrayList<Integer> refreshedInput) {
        AmpA.memory.clear();
        AmpA.memory.addAll(refreshedInput);
        AmpB.memory.clear();
        AmpB.memory.addAll(refreshedInput);
        AmpC.memory.clear();
        AmpC.memory.addAll(refreshedInput);
        AmpD.memory.clear();
        AmpD.memory.addAll(refreshedInput);
        AmpE.memory.clear();
        AmpE.memory.addAll(refreshedInput);
    }
}
