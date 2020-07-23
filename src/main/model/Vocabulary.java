package model;

public class Vocabulary {
    private String word = "";
    private String definition = "";

    //EFFECTS: Creates new Vocabulary with word and its definition
    public Vocabulary(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    //EFFECTS: Returns the word
    public String getWord() {
        return word;
    }

    //EFFECTS: Returns the definition
    public String getDefinition() {
        return definition;
    }

    //MODIFIES: this
    //EFFECTS: Changes the word
    public void setWord(String word) {
        this.word = word;
    }

    //MODIFIES: this
    //EFFECTS: Changes the definition
    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
