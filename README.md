# My Personal Project: Word remembering software

## Subtitle: Easy way to increase your vocabularies

**What will the application do?**
> - Helps remembering words:
>   Users are required to input word, or the software will find the meaning for the user.
>
> - Creates Quiz for users to check if they have remembered the words correctly:
>   The amounts of words in the quiz could be changed in the settings.
>
> - Shows your progress with a graph chart.
>
**Who will use it?**
> - Anyone whose interested in learning english can use this.

**Why is this project of interest to you?**
> - I was not born in Canada so I had hard time remembering new vocabularies. Furthermore, there are many internationals
>   who need help with remembering new vocabularies.



## User Stories

### Phase 1:
> - As a user, I want to be able to create a new word
> - As a user, I want to be able to add new word to my word-list
> - As a user, I want to be able to check all the vocabularies in my word-list
> - As a user, I want to be able to check all my definitions in my word list

### Phase 2:
> - As a user, I want to be able to move the remembered word and its definition to remembered vocabulary list
> - As a user, I want to be able to remove the word and its definition from remembered vocabulary list
> - As a user, I want to be able to save my remember and remembered vocabulary list
> - AS a user, I want to be able to optionally load my remember and remembered vocabulary list

# Instructions for Grader
> - You can create a new vocabulary containing the word and the definition by clicking the "Add Vocabulary" button, then the word will be added to the Vocabulary list.
> - You can create as many vocabularies as you want by clicking the "Add Vocabulary" button.
> - You can check all the vocabularies saved in the vocabulary list by clicking the "Vocabulary-List" button.
> - In side the vocabulary list, you are also able to move the word you remembered to the remembered vocabulary list by selecting the word and clicking the "Remembered" button.
> - You can check all the remembered vocabularies im the remembered vocabulary list by clicking the "Remembered Vocabulary" Button.
> - You can remove the vocabulary that you remembered from the remembered vocabulary list.
> - Once you click "save & Quit" button it gives you the option to quit or return to the main menu.
> - When you click on "Save & Quit" again, your vocabularies will be saved. You must press the save button to save the state of the application.
> - The state of the application is updated whenever you add new word, move word or remove word.
> - The satte of the application is reloaded when the application is launched.

### Phase 4: Task 2
  - Test and design a class that is robust.  You must have at least one method that throws a checked exception.  You must have one test for the case where the exception is expected and another where the exception is not expected.
#### Original Code sent error when:
> - empty vocabulary (Vocabulary with no name or definition) were added to the list.
> - when user move the vocabulary from remember list to remembered list when there is no vocabulary.
> - when user remove list from the remembered list when there are no vocabulary in the list.
#### Improvement:
> - Created error popup window so it prevents user form adding empty vocabulary to the list
> - Added EmptyListException, which is being thrown in moveClicked and removeClicked methods. When the exception is being caught, error popup window shows up and returns to the current scene again.
> - To improve the robustness of the code, InvalidException and InvalidIndexException has been added.
> - InvalidIndexException throws an in getVocabulary method to prevent index greater or less than the size of the list
> - InvalidNameException throws an in findVocabulary method when the input vocabulary does not exist in the list

### Phase 4: Task 3
#### Coupling and Cohesion:
> - Main class has a low Cohesion since it handles various JavaFx tasks. In order to improve its low cohesion, I decided to extract the alertBox into a separate class. This way the main class is able to focus more on the
>   main pages, and the alertBox classes will handel the alerts.
> - Main class and the AlertBox class also has low coupling. The display() method created in AlertBox is not affected by how methods are implemented in the Main class.
> - Main class has coupling since it only focuses on the GUI
> - Load class and Save class has low cohesion since it only focuses on one task, and it also has low coupling with the Main class.
