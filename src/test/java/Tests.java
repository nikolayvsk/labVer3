import org.example.WordCounter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;  
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Tests {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private WordCounter wordCounter;

    @Spy
    Map<String, Integer> wordCounts = new HashMap<>();

    @Spy
    private WordCounter wordCounterSpy;

    public String input = "hello hello hello hellohello helo helo";
    @Before
    public void setUp() {
        when(scanner.nextLine()).thenReturn(input);
    }

    @Test
    public void testDifferentSymbolsAndNumbers() {
        Map<String, Integer> result = WordCounter.countWords("sky;2123 !!!123dog, cat, rock::cat,+ cat, sky ==+\n");
        assertEquals(Map.of("sky", 2, "rock", 1, "cat",3, "dog", 1), result);
    }

    @Test
    public void testValues() {
        Map<String, Integer> result = WordCounter.countWords(scanner.nextLine());
        assertEquals(3, result.size());
        assertEquals(3, result.get("hello").intValue());
        assertEquals(1, result.get("hellohello").intValue());
        assertEquals(2, result.get("helo").intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        Map<String, Integer> result = wordCounter.countWords(null);
    }


    @Test
    public void testCountWordsCalledTimes() {
        WordCounter mock = Mockito.mock(WordCounter.class);
        String input = "blank";
        Mockito.verify(mock, Mockito.times(1)).countWords(input);
    }

    @Test
    public void testUsingAnnotations() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("hello", 3);
        expected.put("hellohello", 1);
        expected.put("helo", 2);
        Map<String, Integer> result = WordCounter.countWords(scanner.nextLine());
        assertEquals(expected, result);
    }

    @Test
    public void testSpyMock() {
        Map<String, Integer> result = wordCounterSpy.countWords("");
        Assert.assertEquals(Collections.emptyMap(), result);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowingException() {
        doThrow(new RuntimeException()).when(wordCounts).put(anyString(), anyInt());
        wordCounter.countWords(null);
    }

    @AfterEach
    public void validate() {
        validateMockitoUsage();
    }

}
