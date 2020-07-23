package model;

import java.util.ArrayList;
import java.util.List;

public class VocabularyList {
    private List<Vocabulary> vocabularylist;

    //EFFECTS: Creates new vocabularylist
    public VocabularyList() {
        vocabularylist = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: Adds new word to the vocabularylist
    public void addNewWord(Vocabulary vocabulary) {
        vocabularylist.add(vocabulary);
    }

    //EFFECTS: Returns true when the vocabularylist contains the vocabulary in parameter, otherwise false
    public boolean contains(Vocabulary vocabulary) {
        if (vocabularylist.contains(vocabulary)) {
            return true;
        }
        return false;
    }

    //REQUIRES: int parameter < vocabularylist.size()
    //EFFECTS: Returns the vocabulary at index i of the vocabularylist
    public Vocabulary getVocabulary(int i) throws InvalidIndexException {
        if (i >= vocabularylist.size() || i < 0) {
            throw new InvalidIndexException();
        }
        return vocabularylist.get(i);
    }

    //EFFECTS: Returns the length of the vocabularylist
    public int getSize() {
        return vocabularylist.size();
    }

    //EFFECTS: Returns true if the vocabularylist contains the input vocabylary name; otherwise false
    public boolean containsVocabulary(String word) {
        List<String> vocabularies = new ArrayList<>();

        for (Vocabulary vocabulary : vocabularylist) {
            vocabularies.add(vocabulary.getWord());
        }
        return vocabularies.contains(word);
    }

    //REQUIRES: The input vocabulary name must be included in the vocabularylist
    //EFFECTS: Resturns the input Vocabylary from the list.
    public Vocabulary findVocabulary(String vocabulary) throws InvalidNameException {
        Vocabulary vocab;

        for (int i = 0; i < vocabularylist.size(); i++) {
            if (vocabularylist.get(i).getWord().equals(vocabulary)) {
                try {
                    getVocabulary(i);
                    return getVocabulary(i);
                } catch (InvalidIndexException e) {
                    //Nothing
                }
            }
        }
        throw new InvalidNameException();
    }

    //EFFECTS: Removes the input vocabulary from the vocabularyList
    public void remove(Vocabulary vocabulary) {
        vocabularylist.remove(vocabulary);
    }
}
