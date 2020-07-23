package ui;

import model.InvalidIndexException;
import model.InvalidNameException;
import model.Vocabulary;
import model.VocabularyList;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VocabularyApp {

    private VocabularyList remembered = new VocabularyList();
    private VocabularyList remember = new VocabularyList();
    private Scanner input;

    //REQUIRES: Input String must contain "-"
    //EFFECT: converts a String containing the name and the definition of the vocabulary to a Vocabulary
    private Vocabulary stringToVocabulary(String string) {
        List<String> vocabularyAndDefinitoin = new ArrayList<>();
        String name;
        String defintion;

        String[] vocabulary = string.split("-", 2);
        for (String part : vocabulary) {
            vocabularyAndDefinitoin.add(part);
        }

        return (new Vocabulary(vocabularyAndDefinitoin.get(0), vocabularyAndDefinitoin.get(1)));
    }

    //EFFECT: Loads to-remember vocabulary list
    private void loadTodo() throws IOException {
        List<String> todo = Files.readAllLines(Paths.get("./data/remember.txt"));
        Vocabulary vocabulary;
        for (int i = 0; i < todo.size(); i++) {
            vocabulary = stringToVocabulary(todo.get(i));
            remember.addNewWord(vocabulary);
        }
    }

    //EFFECT: Loads remembered vocabulary list
    private void loadDone() throws IOException {
        List<String> done = Files.readAllLines(Paths.get("./data/remembered.txt"));
        Vocabulary vocabulary;
        for (int i = 0; i < done.size(); i++) {
            vocabulary = stringToVocabulary(done.get(i));
            remembered.addNewWord(vocabulary);
        }
    }

    //MODIFIES: this
    //EFFECT: saves new vocabulary to the to-remember list
    private void saveTodo() throws IOException, InvalidIndexException {
        PrintWriter writer = new PrintWriter("./data/remember.txt", "UTF-8");
        for (int i = 0; i < remember.getSize(); i++) {
            writer.println(remember.getVocabulary(i).getWord() + "-" + remember.getVocabulary(i).getDefinition());
        }
        writer.close();
    }

    //MODIFIES: this
    //EFFECT: saves new vocabulary to the remembered list
    private void saveDone() throws IOException, InvalidIndexException {
        PrintWriter writer = new PrintWriter("./data/remembered.txt", "UTF-8");
        for (int i = 0; i < remembered.getSize(); i++) {
            writer.println(remembered.getVocabulary(i).getWord() + "-" + remembered.getVocabulary(i).getDefinition());
        }
        writer.close();
    }

    //EFFECTS: Creates Constructor for the VocabularyApp
    public VocabularyApp() throws IOException, InvalidIndexException, InvalidNameException {
        runVocabularyApp();
    }

    //EFFECTS: Initialize the VocabularyApp
    private void runVocabularyApp() throws IOException, InvalidIndexException, InvalidNameException {
        String command = null;
        input = new Scanner(System.in);
        loadTodo();
        loadDone();

        while (true) {
            displayMenu();
            command = input.next();

            if (command.equals("4")) {
                saveTodo();
                saveDone();
                System.out.println("See you again!");
                break;
            } else {
                runApp(command);
            }
        }
    }

    //EFFECTS: Displays the mainPage menu of the VocabularyApp
    private void displayMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("1. Add New Vocabulary");
        System.out.println("2. See My Vocabulary List");
        System.out.println("3. See Remembered Vocabularies");
        System.out.println("4. Quit");
    }

    //EFFECTS: Operates mainPage functions of the VocabularyApp
    private void runApp(String command) throws IOException, InvalidIndexException, InvalidNameException {
        if (command.equals("1")) {
            addVocabulary();
        } else if (command.equals("2")) {
            myVocabularyList();
        } else if (command.equals("3")) {
            rememberedVocabulary();
        } else {
            System.out.println("Invalid input, please try again");
        }

    }

    //MODIFIES: this
    //EFFECTS: Adds new Vocabulary to the VocabularyApp
    private void addVocabulary() throws IOException {
        System.out.print("What is the word called?: ");
        String word = input.next();

        System.out.println("What is the definition of this word?: ");
        String definition = input.next();
        definition += input.nextLine();

        remember.addNewWord(new Vocabulary(word, definition));
        System.out.println("This word has been added to your Vocabulary List!");

        System.out.println("\nWould you like to add another Vocabulary to the list?");
        System.out.println("1. Yes");
        System.out.println("2. NO");

        addVocabularyMenu(input.next());
    }

    //EFFECTS: Displays Menu for the addPage section
    private void addVocabularyMenu(String command) throws IOException {
        if (command.equals("1")) {
            addVocabulary();
        } else if (command.equals("2")) {
            System.out.println("Returning to the Main Menu....");
        } else {
            System.out.println("Invalid input, please try again");
            addVocabularyMenu(input.next());
        }
    }

    //EFFECTS: Displays the vocabularies inputted so far
    private void myVocabularyList() throws IOException, InvalidIndexException, InvalidNameException {
        for (int i = 0; i < remember.getSize(); i++) {
            System.out.println(remember.getVocabulary(i).getWord() + "-"
                    + remember.getVocabulary(i).getDefinition());
        }

        System.out.println("\nDo you want to move remembered word to remembered vocabulary list?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        myVocabularyMenu(input.next());
    }

    //EFFECTS: Displays the Menu for the Vocabulary List section
    private void myVocabularyMenu(String command) throws IOException, InvalidNameException {
        if (command.equals("1")) {
            movetoRemembered();
        } else if (command.equals("2")) {
            System.out.println("No worries, take your time!");
        } else {
            System.out.println("Invalid input, please try again");
            myVocabularyMenu(input.next());
        }
    }

    //MODIFIES: this
    //EFFECTS: Moves the remembered vocabularies to the remembered words list
    private void movetoRemembered() throws IOException, InvalidNameException {
        System.out.println("What is the word called?: ");
        String vocabulary = input.next();
        if (remember.containsVocabulary(vocabulary)) {
            Vocabulary rememberedWord = remember.findVocabulary(vocabulary);
            remember.remove(rememberedWord);
            remembered.addNewWord(rememberedWord);
            System.out.println("Word " + rememberedWord.getWord() + " has been moved to the remembered word list!");
        } else {
            System.out.println("That word is not in this list.");
        }

        System.out.println("\nWould you like to move any other word?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        movetoRememberedMenu(input.next());
    }

    //EFFECTS: Displays the menu for the move vocabulary section
    private void movetoRememberedMenu(String command) throws IOException, InvalidNameException {
        if (command.equals("1")) {
            movetoRemembered();
        } else if (command.equals("2")) {
            System.out.println("Returning to the Main Menu....");
        } else {
            System.out.println("Invalid input, please try again");
            movetoRememberedMenu(input.next());
        }
    }

    //EFFECTS: Displays all the remembered vocabularies
    private void rememberedVocabulary() throws InvalidIndexException, InvalidNameException {
        for (int i = 0; i < remembered.getSize(); i++) {
            System.out.println(remembered.getVocabulary(i).getWord() + "-"
                    + remembered.getVocabulary(i).getDefinition());
        }

        System.out.println("\nDo you want to move remembered word to remembered word list?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        rememberedVocabularyMenu(input.next());
    }

    //EFFECTS: Displays the menu fo the remembered vocabulary section
    private void rememberedVocabularyMenu(String command) throws InvalidNameException {
        if (command.equals("1")) {
            removefromRemembered();
        } else if (command.equals("2")) {
            System.out.println("Ok!");
            System.out.println("Returning to the Main Menu....");
        } else {
            System.out.println("Invalid input, please try again");
            rememberedVocabularyMenu(input.next());
        }
    }

    //MODIFIES: this
    //EFFECTS: Remove vocabulary from remembered vocabulary list
    private void removefromRemembered() throws InvalidNameException {
        System.out.println("What is the word called?: ");
        String vocabulary = input.next();
        if (remembered.containsVocabulary(vocabulary)) {
            Vocabulary removingWord = remembered.findVocabulary(vocabulary);
            remembered.remove(removingWord);
            System.out.println("Word " + removingWord.getWord()
                    + " has been removed from the remembered vocabulary list!");
        } else {
            System.out.println("That word is not in this list.");
        }

        System.out.println("\nWould you like to remove any other word?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        removefromRememberedMenu(input.next());
    }

    //EFFECTS: Displays the menu for the remove vocabulary section
    private void removefromRememberedMenu(String command) throws InvalidNameException {
        if (command.equals("1")) {
            removefromRemembered();
        } else if (command.equals("2")) {
            System.out.println("Returning to the Main Menu....");
        } else {
            System.out.println("Invalid input, please try again");
            removefromRememberedMenu(input.next());
        }
    }


}
