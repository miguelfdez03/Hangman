package ahorcado;

import java.util.Random;

public class Words {

    private static final int WORD_COUNT = 42;
    private String[] words;
    private String selectedWord;
    private String[] formedWord;
    private int lives;

    public Words() {
        words = new String[WORD_COUNT];
        words[0] = "VARIABLE";
        words[1] = "CONSTRUCTOR";
        words[2] = "JAVA";
        words[3] = "ALGORITMO";
        words[4] = "OBJETO";
        words[5] = "HERENCIA";
        words[6] = "INTERFAZ";
        words[7] = "CLASE";
        words[8] = "MATRIZ";
        words[9] = "POLIMORFISMO";
        words[10] = "DEPURACION";
        words[11] = "FUNCION";
        words[12] = "METODO";
        words[13] = "BUCLE";
        words[14] = "CONDICIONAL";
        words[15] = "EXCEPCION";
        words[16] = "CONCURRENCIA";
        words[17] = "COMPILADOR";
        words[18] = "FRAGMENTO";
        words[19] = "LENGUAJE";
        words[20] = "PAQUETE";
        words[21] = "MODULO";
        words[22] = "BIBLIOTECA";
        words[23] = "PROTOCOLO";
        words[24] = "SERIALIZACION";
        words[25] = "ENCAPSULAMIENTO";
        words[26] = "APLICACION";
        words[27] = "SERVIDOR";
        words[28] = "CLIENTE";
        words[29] = "CICLO";
        words[30] = "DEPENDENCIAS";
        words[31] = "IMPLEMENTACION";
        words[32] = "SOBRECARGA";
        words[33] = "PROCESAMIENTO";
        words[34] = "RECURSIVIDAD";
        words[35] = "GENERICO";
        words[36] = "CODIFICACION";
        words[37] = "SESION";
        words[38] = "TOKEN";
        words[39] = "MEMORIA";
        words[40] = "COMPUTADORA";
        words[41] = "ARCHIVO";

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
