package ui;

import model.InvalidIndexException;
import model.VocabularyList;

import java.io.IOException;
import java.io.PrintWriter;

public class Save {

    //MODIFIES: this
    //EFFECT: saves new vocabulary to the to-remember list
    public static void saveTodo(VocabularyList remember) throws IOException, InvalidIndexException {
        PrintWriter writer = new PrintWriter("./data/remember.txt", "UTF-8");
        for (int i = 0; i < remember.getSize(); i++) {
            writer.println(remember.getVocabulary(i).getWord() + "-" + remember.getVocabulary(i).getDefinition());
        }
        writer.close();
    }

    //MODIFIES: this
    //EFFECT: saves new vocabulary to the remembered list
    public static void saveDone(VocabularyList remembered) throws IOException, InvalidIndexException {
        PrintWriter writer = new PrintWriter("./data/remembered.txt", "UTF-8");
        for (int i = 0; i < remembered.getSize(); i++) {
            writer.println(remembered.getVocabulary(i).getWord() + "-" + remembered.getVocabulary(i).getDefinition());
        }
        writer.close();
    }
}
