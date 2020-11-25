package lv.emils;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class FrequencyLetters {

    ArrayList<String> fileCharsDispersed;

    private ArrayList<String> fileStringLines;

    Map<String, Long> letterHistogram;

    public FrequencyLetters() {
    }

    public FrequencyLetters(String filename) throws FileNotFoundException {
        readFileText(filename);
        fileCharsDispersed = splitAndFlattenStringsList(fileStringLines);
        createLetterHistogram(fileCharsDispersed);
    }

    private void readFileText(String filename) throws FileNotFoundException {
        Scanner input;
        input = new Scanner(new File(filename));

        String tempLine;
        String parsedLine;
        fileStringLines = new ArrayList<>();

        while (input.hasNext()) {
            tempLine = input.nextLine();
            parsedLine = parseStringToLettersLowercase(tempLine);
            fileStringLines.add(parsedLine);
        }
        input.close();
    }
    public ArrayList<String> splitAndFlattenStringsList(ArrayList<String> strings) {
        ArrayList<String> stringsFlattened = new ArrayList<>();
        for (String list : strings) {
            stringsFlattened.addAll(Arrays.asList(list.split("")));
        }
        return stringsFlattened;
    }

    public String parseStringToLettersLowercase(String line) {
        return line.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }

    public void createLetterHistogram(ArrayList<String> letters) {
        letterHistogram = new LinkedHashMap<>();
        List<Map.Entry<String, Long>> charEntries = letters.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(s -> s.getValue() >= 0) // include every letter
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        for (Map.Entry<String, Long> entry : charEntries) {
            letterHistogram.put(entry.getKey(), entry.getValue());
        }
    }

    // Example run to show code working in practice
    public static void main(String[] args) throws FileNotFoundException {
        FrequencyLetters freq = new FrequencyLetters(args[0]);
        System.out.println("Letter occurrences of the given text, sorted by popularity: ");
        freq.letterHistogram.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
