package ahorcado;

import java.util.Random;

public class Words {

    private static final int WORD_COUNT = 15;
    private String[] words;
    private String selectedWord;
    private String[] formedWord;
    private int lives;

    public Words() {
        words = new String[WORD_COUNT];
        words[0] = "TESTIMONIO";
        words[1] = "BOCADILLO";
        words[2] = "BAILE";
        words[3] = "GIMNASIO";
        words[4] = "CBUM";
        words[5] = "FELINO";
        words[6] = "CERDO";
        words[7] = "XILOFONO";
        words[8] = "ESTERNOCLEIDOMASTOIDEO";
        words[9] = "AHORCADO";
        words[10] = "ELEFANTE";
        words[11] = "PROGRAMACION";
        words[12] = "JAVA";
        words[13] = "ORDENADOR";
        words[14] = "DESAFIO";

        selectedWord = pickRandomWord();
        formedWord = new String[selectedWord.length()];
        initializeFormedWord();
        lives = 0;
    }

    // Select a random word
    public String pickRandomWord() {
        Random random = new Random();
        return words[random.nextInt(WORD_COUNT)];
    }

    // Fill formedWord array with '*'
    public void initializeFormedWord() {
        for (int i = 0; i < formedWord.length; i++) {
            formedWord[i] = "*";
        }
    }

    // Compare a guessed letter with the selected word
    public String compare(char letter) {
        StringBuilder updatedWord = new StringBuilder();
        lives = 0;

        for (int i = 0; i < selectedWord.length(); i++) {
            if (letter == selectedWord.charAt(i) && "*".equals(formedWord[i])) {
                formedWord[i] = String.valueOf(letter);
                lives = 1;
            }
            updatedWord.append(formedWord[i]);
        }
        return updatedWord.toString();
    }

    public String getSelectedWord() {
        return selectedWord;
    }

    public int getLives() {
        return lives;
    }
}
