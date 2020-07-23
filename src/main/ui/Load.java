package ui;

import model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Load {

    //EFFECT: Loads to-remember vocabulary list
    public static void loadTodo(VocabularyList remember) throws IOException {
        List<String> todo = Files.readAllLines(Paths.get("./data/remember.txt"));
        Vocabulary vocabulary;
        for (int i = 0; i < todo.size(); i++) {
            vocabulary = stringToVocabulary(todo.get(i));
            remember.addNewWord(vocabulary);
        }
    }

    //EFFECT: Loads remembered vocabulary list
    public static void loadDone(VocabularyList remembered) throws IOException {
        List<String> done = Files.readAllLines(Paths.get("./data/remembered.txt"));
        Vocabulary vocabulary;
        for (int i = 0; i < done.size(); i++) {
            vocabulary = stringToVocabulary(done.get(i));
            remembered.addNewWord(vocabulary);
        }
    }

    //REQUIRES: Input String must contain "-"
    //EFFECT: converts a String containing the name and the definition of the vocabulary to a Vocabulary
    public static Vocabulary stringToVocabulary(String string) {
        List<String> vocabularyAndDefinitoin = new ArrayList<>();

        String[] vocabulary = string.split("-", 2);
        for (String part : vocabulary) {
            vocabularyAndDefinitoin.add(part);
        }

        return (new Vocabulary(vocabularyAndDefinitoin.get(0), vocabularyAndDefinitoin.get(1)));
    }
}
