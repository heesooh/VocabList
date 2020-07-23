package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VocabularyListTest {
    private VocabularyList vocabularyList;
    private Vocabulary vocabulary1;
    private Vocabulary vocabulary2;
    private Vocabulary vocabulary3;

    @BeforeEach
    void setup() {
        vocabularyList = new VocabularyList();
        vocabulary1 = new Vocabulary("water", "fluid");
        vocabulary2 = new Vocabulary("dog", "animal");
        vocabulary3 = new Vocabulary("dog dog", "");
    }

    @Test
    void testaddNewWord() {
        assertEquals(0, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary1);
        assertEquals(1, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary2);
        assertEquals(2, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary2);
        assertEquals(3, vocabularyList.getSize());
    }

    @Test
    void testGetVocabularyPass() {
        vocabularyList.addNewWord(vocabulary1);
        vocabularyList.addNewWord(vocabulary2);
        try {
            vocabularyList.getVocabulary(0);
            assertEquals(vocabulary1, vocabularyList.getVocabulary(0));
            vocabularyList.getVocabulary(1);
            assertEquals(vocabulary2, vocabularyList.getVocabulary(1));
            //expected
        } catch (InvalidIndexException e) {
            fail("Unexpected exception has been thrown.");
        }
    }

    @Test
    void testGetVocabualryFail1() {
        vocabularyList.addNewWord(vocabulary1);
        try {
            vocabularyList.getVocabulary(1);
            fail("Unexpected exception has been thrown.");
        } catch (InvalidIndexException e) {
            //expected
        }
    }

    @Test
    void testGetVocabualryFail2() {
        vocabularyList.addNewWord(vocabulary2);
        try {
            vocabularyList.getVocabulary(10);
            fail("Unexpected exception has been thrown.");
        } catch (InvalidIndexException e) {
            //expected
        }
    }

    @Test
    void testGetVocabualryFail3() {
        vocabularyList.addNewWord(vocabulary2);
        try {
            vocabularyList.getVocabulary(-10);
            fail("Unexpected exception has been thrown.");
        } catch (InvalidIndexException e) {
            //expected
        }
    }

    @Test
    void testgetSize() {
        assertEquals(0, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary1);
        assertEquals(1, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary2);
        assertEquals(2, vocabularyList.getSize());
        vocabularyList.addNewWord(vocabulary2);
        assertEquals(3, vocabularyList.getSize());
    }

    @Test
    void testcontains() {
        vocabularyList.addNewWord(vocabulary1);
        vocabularyList.addNewWord(vocabulary2);
        assertTrue(vocabularyList.contains(vocabulary1));
        assertTrue(vocabularyList.contains(vocabulary2));
        assertFalse(vocabularyList.contains(vocabulary3));
    }

    @Test
    void testcontainsVocabulary() {
        vocabularyList.addNewWord(vocabulary3);
        vocabularyList.addNewWord(vocabulary2);
        vocabularyList.addNewWord(vocabulary1);

        assertTrue(vocabularyList.containsVocabulary("water"));
        assertTrue(vocabularyList.containsVocabulary("dog"));
        assertTrue(vocabularyList.containsVocabulary("dog dog"));

        assertFalse(vocabularyList.containsVocabulary("ice"));
    }

    @Test
    void testFindVocabularyPass() {
        vocabularyList.addNewWord(vocabulary3);
        vocabularyList.addNewWord(vocabulary2);
        vocabularyList.addNewWord(vocabulary1);

        try {
            vocabularyList.findVocabulary("water");
            assertEquals(vocabulary1, vocabularyList.findVocabulary("water"));
            vocabularyList.findVocabulary("dog");
            assertEquals(vocabulary2, vocabularyList.findVocabulary("dog"));
            vocabularyList.findVocabulary("dog dog");
            assertEquals(vocabulary3, vocabularyList.findVocabulary("dog dog"));
            //expected
        } catch (InvalidNameException e) {
            fail("Unexpected exception has been thrown.");
        }
    }

    @Test
    void testFindVocabularyFail() {
        vocabularyList.addNewWord(vocabulary3);
        vocabularyList.addNewWord(vocabulary2);
        vocabularyList.addNewWord(vocabulary1);

        try {
            vocabularyList.findVocabulary("random");
            fail("Unexpected exception has been thrown.");
        } catch (InvalidNameException e) {
            //expected
        }
    }


    @Test
    void testremove() {
        vocabularyList.addNewWord(vocabulary3);
        vocabularyList.addNewWord(vocabulary2);
        vocabularyList.addNewWord(vocabulary1);
        assertEquals(3, vocabularyList.getSize());

        vocabularyList.remove(vocabulary1);
        assertEquals(2, vocabularyList.getSize());
        assertFalse(vocabularyList.containsVocabulary("water"));
        assertTrue(vocabularyList.containsVocabulary("dog"));

        vocabularyList.remove(vocabulary2);
        assertEquals(1, vocabularyList.getSize());
        assertFalse(vocabularyList.containsVocabulary("water"));
        assertFalse(vocabularyList.containsVocabulary("dog"));
    }
}
