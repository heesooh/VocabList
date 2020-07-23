package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VocabularyTest {
    private Vocabulary vocabulary1;
    private Vocabulary vocabulary2;

    @BeforeEach
    void setup() {
        vocabulary1 = new Vocabulary("water", "fluid");
        vocabulary2 = new Vocabulary("dog", "animal");
    }

    @Test
    void testConstructor() {
        assertEquals("water", vocabulary1.getWord());
        assertEquals("dog", vocabulary2.getWord());
        assertEquals("fluid", vocabulary1.getDefinition());
        assertEquals("animal", vocabulary2.getDefinition());
    }

    @Test
    void testgetWord() {
        assertEquals("water", vocabulary1.getWord());
        assertEquals("dog", vocabulary2.getWord());
    }

    @Test
    void testgetDefinition() {
        assertEquals("fluid", vocabulary1.getDefinition());
        assertEquals("animal", vocabulary2.getDefinition());
    }

    @Test
    void testsetWord() {
        vocabulary1.setWord("alcohol");
        assertEquals("alcohol", vocabulary1.getWord());

        vocabulary2.setWord("cat");
        assertEquals("cat", vocabulary2.getWord());
    }

    @Test
    void testsetDefinition() {
        vocabulary1.setDefinition("yukky");
        assertEquals("yukky", vocabulary1.getDefinition());

        vocabulary2.setDefinition("cute");
        assertEquals("cute", vocabulary2.getDefinition());
    }
}