package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Launches the VocabularyApp
public class Main extends Application {

//    //Comment Line Interface
//    public static void main(String[] args) throws IOException {
//        new VocabularyApp();
//    }

    private VocabularyList remember = new VocabularyList();
    private VocabularyList remembered = new VocabularyList();

    //start()
    public Stage window;

    //mainPage()
    private Scene mainPage;

    //addPage()
    private Scene addPage;

    //todoPage()
    private Scene todoPage;
    private ListView<String> todoView;

    //donePage()
    private Scene donePage;
    private ListView<String> doneView;

    //quitPage()
    private Scene quitPage;

    public static void main(String[] args) {
        launch(args);
    }

    //EFFECTS: initiates the screens for the GUI
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set up the Window
        window = primaryStage;
        window.setTitle("Remember Your Vocabularies");

        load();

        mainPage();
        addPage();
        todoPage();
        donePage();
        quitPage();

        window.setScene(mainPage);
        window.show();
    }

    //Load & Save:

    private void load() throws IOException {
        Load.loadTodo(remember);
        Load.loadDone(remembered);
    }

    private void save() throws IOException, InvalidIndexException {
        Save.saveTodo(remember);
        Save.saveDone(remembered);
    }

    //Pages:

    //EFFECTS: Creates the main page.
    private void mainPage() {
        //GridPane
        GridPane mainGrid = new GridPane();
        mainGrid.setPadding(new Insets(20, 40, 20, 40));
        mainGrid.setVgap(8);
        mainGrid.setHgap(10);

        //Initialize the Buttons
        Button newVocabulary = new Button("Add Vocabulary");
        buttonTheme(newVocabulary);
        Button checkRemember = new Button("Vocabulary-List");
        buttonTheme(checkRemember);
        Button checkRemembered = new Button("Remembered Vocabulary");
        buttonTheme(checkRemembered);
        Button quit = new Button("Save & Quit");
        buttonTheme(quit);

        //Set up the Buttons
        newVocabulary.setOnAction(e -> window.setScene(addPage));
        GridPane.setConstraints(newVocabulary, 1, 0);
        checkRemember.setOnAction((e -> window.setScene(todoPage)));
        GridPane.setConstraints(checkRemember, 1, 2);
        checkRemembered.setOnAction(e -> window.setScene(donePage));
        GridPane.setConstraints(checkRemembered, 1, 4);
        quit.setOnAction(e -> window.setScene(quitPage));
        GridPane.setConstraints(quit, 1, 6);

        //Layout Main Page
        mainGrid.setStyle("-fx-background-color: #383838");
        mainGrid.getChildren().addAll(newVocabulary, checkRemember, checkRemembered, quit);
        mainPage = new Scene(mainGrid, 250, 200);
    }

    //EFFECTS: Creates the Add new Vocabulary screen
    private void addPage() {

        //Word Input
        TextField wordInput = new TextField();
        wordInput.setPromptText("ex.Hello");
        GridPane.setConstraints(wordInput, 1, 0);

        //Definition Input
        TextField defInput = new TextField();
        defInput.setPromptText("ex.Used as a greeting or to begin a telephone conversation.");
        GridPane.setConstraints(defInput, 1, 1);

        //Add New Word Button
        Button addButton = new Button("Add Vocabulary");
        buttonTheme(addButton);

        //Return Button
        Button returnButton = new Button("Return");
        buttonTheme(returnButton);


        GridPane.setConstraints(addButton, 1, 3);
        addButton.setOnAction(e -> {
            addButtonAction(wordInput, defInput);
        });

        GridPane.setConstraints(returnButton, 1, 4);
        returnButton.setOnAction(e -> {
            returnButtonAction(wordInput, defInput);
        });
        addPageDesign(wordInput, defInput, addButton, returnButton);

    }

    //EFFECTS: creates the listView screen for remember vocab list
    private void todoPage() throws InvalidIndexException {
        Button returnMain = new Button("Done");
        buttonTheme(returnMain);
        returnMain.setOnAction(e -> window.setScene(mainPage));

        Button remembered = new Button("Remembered");
        buttonTheme(remembered);
        remembered.setOnAction(e -> {
                    try {
                        moveClicked();
                    } catch (Exception exception) {
                        AlertBox.display("Error", "The list is empty.");
                        window.setScene(todoPage);
                    }
                }
        );

        List<String> vocabularies = new ArrayList<>();

        for (int i = 0; i < remember.getSize(); i++) {
            vocabularies.add(remember.getVocabulary(i).getWord() + "-"
                    + remember.getVocabulary(i).getDefinition());
        }

        todoView = new ListView<>();
        todoView.getItems().addAll(vocabularies);

        //Layout TodoPage
        todoPageDesign(returnMain, remembered);
    }

    //EFFECTS: creates listView screen for remembered vocabs
    private void donePage() throws InvalidIndexException {
        Button returnMain = new Button("Done");
        buttonTheme(returnMain);
        returnMain.setOnAction(e -> window.setScene(mainPage));

        Button remove = new Button("Remove");
        buttonTheme(remove);
        remove.setOnAction(e -> {
            try {
                removeClicked();
            } catch (Exception exception) {
                AlertBox.display("Error", "The list is empty.");
                window.setScene(donePage);
            }
        });

        List<String> vocabularies = new ArrayList<>();

        for (int i = 0; i < remembered.getSize(); i++) {
            vocabularies.add(remembered.getVocabulary(i).getWord() + "-"
                    + remembered.getVocabulary(i).getDefinition());
        }

        doneView = new ListView<>();
        doneView.getItems().addAll(vocabularies);

        //Layout DonePage
        donePageDesign(returnMain, remove);
    }

    //EFFECTS: creates the quit and save page
    private void quitPage() {

        Button quit = new Button("Save & Quit");
        GridPane.setConstraints(quit, 1, 2);
        buttonTheme(quit);
        quit.setOnAction(e -> {
            try {
                closeProgram();
            } catch (Exception i) {
                System.out.println("something is wrong");
            }
        });

        Button back = new Button("Back to Main Page");
        GridPane.setConstraints(back, 1, 1);
        buttonTheme(back);
        back.setOnAction(e -> window.setScene(mainPage));

        //GridPane
        quitPageDesign(quit, back);
    }

    //Page Designs:

    private void returnButtonAction(TextField wordInput, TextField defInput) {
        wordInput.clear();
        defInput.clear();
        window.setScene(mainPage);
    }

    private void addButtonAction(TextField wordInput, TextField defInput) {
        try {
            if (emptyTextField(wordInput.getText(), defInput.getText())) {
                window.setScene(addPage);
            } else {
                Vocabulary vocabulary = new Vocabulary(wordInput.getText(), defInput.getText());
                remember.addNewWord(vocabulary);
                todoPage();
                returnButtonAction(wordInput, defInput);
            }
        } catch (InvalidIndexException exception) {
            AlertBox.display("Error", "Something went wrong");
        }
    }

    private void addPageDesign(TextField wordInput, TextField defInput, Button addButton, Button returnButton) {
        //GridPane
        GridPane addGrid = new GridPane();
        addGrid.setPadding(new Insets(10, 10, 10, 10));
        addGrid.setVgap(8);
        addGrid.setHgap(10);
        addGrid.setStyle("-fx-background-color: #383838");
        addGrid.getChildren().addAll(/*addLabel,*/ wordInput, defInput, addButton, returnButton);
        addPage = new Scene(addGrid, 200, 200);
    }

    private void todoPageDesign(Button returnMain, Button remembered) {
        VBox checkLayout = new VBox(20);
        checkLayout.setPadding(new Insets(20, 20, 20, 20));
        checkLayout.getChildren().addAll(todoView, remembered, returnMain);
        checkLayout.setStyle("-fx-background-color: #383838");
        todoPage = new Scene(checkLayout, 500, 500);
    }

    private void donePageDesign(Button returnMain, Button remove) {
        VBox checkLayout = new VBox(20);
        checkLayout.setPadding(new Insets(20, 20, 20, 20));
        checkLayout.getChildren().addAll(doneView, remove, returnMain);
        checkLayout.setStyle("-fx-background-color: #383838");
        donePage = new Scene(checkLayout, 500, 500);
    }

    private void quitPageDesign(Button quit, Button back) {
        GridPane quitGrid = new GridPane();
        quitGrid.setPadding(new Insets(10, 10, 10, 10));
        quitGrid.setVgap(8);
        quitGrid.setHgap(10);
        quitGrid.getChildren().addAll(back, quit);
        quitGrid.setStyle("-fx-background-color: #383838");
        quitPage = new Scene(quitGrid, 200, 100);
    }

    //Helper Functions:

    //EFFECTS: removes vocab from the remember list then move to remembered list
    private void moveClicked() throws EmptyListException, InvalidNameException, InvalidIndexException {
        String vocabulary;
        Vocabulary vocab;
        ObservableList<String> vocabularies;
        vocabularies = todoView.getSelectionModel().getSelectedItems();
        if (vocabularies.size() == 0) {
            throw new EmptyListException();
        } else {
            vocabulary = vocabularies.get(0);
            vocab = Load.stringToVocabulary(vocabulary);
            remember.remove(remember.findVocabulary(vocab.getWord()));
            todoView.getItems().remove(vocabulary);
            remembered.addNewWord(vocab);
            donePage();
        }
    }

    //EFFECTS: removes word from the remembered list
    private void removeClicked() throws EmptyListException, InvalidNameException {
        String vocabulary;
        Vocabulary vocab;
        ObservableList<String> vocabularies;
        vocabularies = doneView.getSelectionModel().getSelectedItems();

        if (vocabularies.size() == 0) {
            throw new EmptyListException();
        }

        vocabulary = vocabularies.get(0);
        vocab = Load.stringToVocabulary(vocabulary);
        remembered.remove(remembered.findVocabulary(vocab.getWord()));
        doneView.getItems().remove(vocabulary);
    }

    private void buttonTheme(Button button) {
        button.setStyle("-fx-background-color: #21af4b; -fx-text-fill: #FFFFFF; -fx-background-radius: 10");
    }

    private boolean emptyTextField(String wordInput, String defInput) {
        if (wordInput.equals("") || defInput.equals("")) {
            AlertBox.display("Error", "TextField is empty.\nThis Vocabulary cannot be added.");
            return true;
        }
        return false;
    }

    //Effect prints "File is saved" then closes the program
    private void closeProgram() throws IOException, InvalidIndexException {
        System.out.println("File is saved");
        save();
        window.close();
    }
}
