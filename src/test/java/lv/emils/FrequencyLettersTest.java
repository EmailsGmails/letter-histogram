package lv.emils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FrequencyLettersTest {

    private FrequencyLetters frequencyLetters;

    @Before
    public void setUp() {
        frequencyLetters = new FrequencyLetters();

    }

    @Test
    public void parseStringToLettersLowercaseTest() {
        String parsedLine = frequencyLetters.parseStringToLettersLowercase("Abc Def; Testing-Method.");

        assertEquals("abcdeftestingmethod", parsedLine);
    }

    @Test
    public void splitAndFlattenStringsListTest() {
        ArrayList<String> exampleStrings = new ArrayList<>();
        exampleStrings.add("Str1");
        exampleStrings.add("Str2");
        ArrayList<String> resultStrings = frequencyLetters.splitAndFlattenStringsList(exampleStrings);
        ArrayList<String> expectedStrings = new ArrayList<>();
        expectedStrings.addAll(Arrays.asList( "S", "t", "r", "1", "S", "t", "r", "2"));

        assertEquals(expectedStrings, resultStrings);
    }

    @Test
    public void createLetterHistogramTest() {
        ArrayList<String> letters = new ArrayList<>();
        letters.addAll(Arrays.asList( "g", "g", "l", "t", "w", "t", "s", "z", "a", "a", "b", "a", "q"));
        frequencyLetters.createLetterHistogram(letters);

        assertEquals(3, frequencyLetters.letterHistogram.get("a"));
        assertEquals(2, frequencyLetters.letterHistogram.get("g"));
        assertEquals(1, frequencyLetters.letterHistogram.get("q"));
    }

}
