package ahorcado;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class Hangman extends JFrame {

    private GameFigure figurePanel;
    private JPanel componentsPanel;
    private JButton resetButton;
    private JButton startButton;
    private JButton validateLetterButton;
    private JLabel labelWord;
    private JTextField inputLetter;
    private JTextField displayWord;
    private JTextArea usedLettersArea;
    private Set<Character> usedLetters;
    private Words words;
    private int lives;

    public Hangman() {
        super("AHORCADO - ¡Diviértete Jugando!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700); // Ajustar el tamaño
        setLayout(new BorderLayout());
        setResizable(false);

        initializeComponents();
        centerWindow();
        setVisible(true);
    }

    private void initializeComponents() {
        // ==== Panel de dibujo ====
        figurePanel = new GameFigure();
        figurePanel.setBackground(new Color(234, 237, 242)); // Fondo gris claro
        figurePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(50, 85, 115))); // Bordes azules
        add(figurePanel, BorderLayout.CENTER);

        // ==== Panel de controles e información ====
        componentsPanel = new JPanel();
        componentsPanel.setBackground(new Color(245, 245, 245)); // Fondo blanco suave
        componentsPanel.setLayout(new BoxLayout(componentsPanel, BoxLayout.Y_AXIS));
        componentsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaciado interno

        // Letras ya intentadas
        JLabel usedLettersLabel = new JLabel("Letras Usadas:");
        usedLettersLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usedLettersLabel.setForeground(new Color(50, 85, 115)); // Azul oscuro
        usedLettersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usedLettersArea = new JTextArea(4, 20);
        usedLettersArea.setEditable(false);
        usedLettersArea.setLineWrap(true);
        usedLettersArea.setWrapStyleWord(true);
        usedLettersArea.setFont(new Font("Arial", Font.PLAIN, 14));
        usedLettersArea.setBackground(new Color(220, 220, 220)); // Fondo gris claro
        usedLettersArea.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));

        JScrollPane scrollPane = new JScrollPane(usedLettersArea);
        scrollPane.setMaximumSize(new Dimension(300, 80));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        usedLetters = new HashSet<>();

        // Texto de palabra
        labelWord = new JLabel("Palabra:");
        labelWord.setFont(new Font("Arial", Font.BOLD, 18));
        labelWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelWord.setForeground(new Color(50, 85, 115));

        displayWord = new JTextField();
        displayWord.setEditable(false);
        displayWord.setFont(new Font("Consolas", Font.BOLD, 24));
        displayWord.setHorizontalAlignment(JTextField.CENTER);
        displayWord.setMaximumSize(new Dimension(400, 50));
        displayWord.setBorder(BorderFactory.createLineBorder(new Color(50, 85, 115), 2));

        // TextField para ingresar letras
        inputLetter = new JTextField(1);
        inputLetter.setFont(new Font("Arial", Font.BOLD, 20));
        inputLetter.setHorizontalAlignment(JTextField.CENTER);
        inputLetter.setMaximumSize(new Dimension(70, 50));
        inputLetter.setBorder(BorderFactory.createLineBorder(new Color(50, 85, 115), 2));
        inputLetter.setEnabled(false); // Desactivado por defecto
        inputLetter.addActionListener(e -> validateLetter());

        // Botón para validar letra
        validateLetterButton = new JButton("✔ Validar Letra");
        styleButton(validateLetterButton);
        validateLetterButton.setEnabled(false); // Desactivado por defecto

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.add(new JLabel("Ingresa Letra:"));
        inputPanel.add(inputLetter);
        inputPanel.add(validateLetterButton);

        // Botones para iniciar y reiniciar juego
        resetButton = new JButton("↺ Reiniciar Juego");
        startButton = new JButton("🎮 Iniciar Juego");
        styleButton(resetButton);
        styleButton(startButton);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(startButton);
        buttonPanel.add(resetButton);

        // Añadir todo al panel de componentes
        componentsPanel.add(usedLettersLabel);
        componentsPanel.add(scrollPane);
        componentsPanel.add(Box.createVerticalStrut(10)); // Espaciado
        componentsPanel.add(labelWord);
        componentsPanel.add(Box.createVerticalStrut(5)); // Espaciado
        componentsPanel.add(displayWord);
        componentsPanel.add(Box.createVerticalStrut(20)); // Espaciado
        componentsPanel.add(inputPanel);
        componentsPanel.add(Box.createVerticalStrut(20)); // Espaciado
        componentsPanel.add(buttonPanel);

        add(componentsPanel, BorderLayout.SOUTH);

        // Listeners para botones
        startButton.addActionListener(e -> startGame());
        resetButton.addActionListener(e -> resetGame());
        validateLetterButton.addActionListener(e -> validateLetter());
    }

    // Estilo de botones
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(50, 85, 115)); // Azul oscuro
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(33, 60, 80), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Iniciar el juego
    private void startGame() {
        words = new Words(); // Inicializar una nueva palabra
        displayWord.setText("*".repeat(words.getSelectedWord().length())); // Mostrar palabra oculta
        usedLetters.clear(); // Limpiar letras usadas
        usedLettersArea.setText("");
        inputLetter.setText("");
        lives = 0;
        figurePanel.setErrorCount(0);

        // Habilitar los campos
        inputLetter.setEnabled(true);
        validateLetterButton.setEnabled(true);
    }

    // Validar letra ingresada
    private void validateLetter() {
        String letterInput = inputLetter.getText().toUpperCase();

        if (letterInput.isEmpty() || letterInput.length() > 1) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un único carácter.");
            inputLetter.setText("");
            return;
        }

        char guessedLetter = letterInput.charAt(0);
        if (usedLetters.contains(guessedLetter)) {
            JOptionPane.showMessageDialog(this, "Ya has ingresado esta letra.");
            inputLetter.setText("");
            return;
        }

        usedLetters.add(guessedLetter);
        usedLettersArea.append(guessedLetter + " ");

        String updatedWord = words.compare(guessedLetter);
        displayWord.setText(updatedWord);

        if (updatedWord.equalsIgnoreCase(words.getSelectedWord())) {
            JOptionPane.showMessageDialog(this, "¡Felicidades, has ganado!");
            resetGame();
        } else if (words.getLives() == 0) {
            lives++;
            figurePanel.setErrorCount(lives);
            if (lives == 6) {
                JOptionPane.showMessageDialog(this, "¡Has perdido! La palabra era: " + words.getSelectedWord());
                resetGame();
            }
        }

        inputLetter.setText("");
    }

    // Reiniciar juego
    private void resetGame() {
        figurePanel.setErrorCount(0);
        displayWord.setText("");
        inputLetter.setText("");
        lives = 0;
        usedLetters.clear();
        usedLettersArea.setText("");

        // Desactivar campos
        inputLetter.setEnabled(false);
        validateLetterButton.setEnabled(false);
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (screenSize.getWidth() - getWidth()) / 2, (int) (screenSize.getHeight() - getHeight()) / 2);
    }

    public static void main(String[] args) {
        new Hangman();
    }
}
