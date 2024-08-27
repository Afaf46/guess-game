
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGameGUI {

    private int min = 1;
    private int max = 10;
    private int rand;
    private int trials;
    private int attempts;

    private JFrame frame;
    private JTextField guessField;
    private JLabel resultLabel;
    private JButton guessButton;
    private JButton playAgainButton;

    public GuessingGameGUI() {
        frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250); // Set specific height (250)
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(220, 220, 220));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new FlowLayout());

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessLabel.setFont(new Font("Arial", Font.BOLD, 14));

        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.PLAIN, 14));
        guessField.setPreferredSize(new Dimension(100, 30));

        guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.setBackground(new Color(70, 130, 180));
        guessButton.setForeground(Color.WHITE);
        guessButton.setFocusPainted(false);
        guessButton.setPreferredSize(new Dimension(100, 30));

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 16)); // Increase font size
        playAgainButton.setBackground(new Color(34, 139, 34)); // Dark green background
        playAgainButton.setForeground(Color.WHITE); // White text
        playAgainButton.setFocusPainted(false);
        playAgainButton.setPreferredSize(new Dimension(150, 50)); // Increase button size
        playAgainButton.setMargin(new Insets(10, 20, 10, 20)); // Padding inside the button
        playAgainButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border for better appearance
        playAgainButton.setVisible(false); // Initially hidden

        inputPanel.add(guessLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(playAgainButton);

        resultLabel = new JLabel("<html>You have " + trials + " trials to guess the number between " + min + " and " + max + ".</html>");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(inputPanel, BorderLayout.CENTER); // Center alignment for inputPanel
        frame.add(resultLabel, BorderLayout.NORTH);

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window on the screen

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        startNewGame();
    }

    private void startNewGame() {
        rand = min + (int) (Math.random() * ((max - min) + 1));
        trials = min + (int) (Math.random() * ((max - min) + 1)); // Random trials
        attempts = 0;
        resultLabel.setText("<html>You have " + trials + " trials to guess the number between " + min + " and " + max + ".</html>");
        guessButton.setEnabled(true);
        playAgainButton.setVisible(false); // Hide the button at the start
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess == rand) {
                resultLabel.setText("<html>Congrats! You guessed right.<br><br>Click 'Play Again' to start a new game.</html>");
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true); // Show the button after a correct guess
            } else if (attempts >= trials) {
                resultLabel.setText("<html>You've reached your limit of " + trials + " trials!<br><br>Click 'Play Again' to start a new game.</html>");
                guessButton.setEnabled(false);
                playAgainButton.setVisible(true); // Show the button when trials are exhausted
            } else if (guess > rand) {
                resultLabel.setText("<html>High. Try again.</html>");
            } else {
                resultLabel.setText("<html>Low. Try again.</html>");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("<html>Please enter a valid number.</html>");
        }

        guessField.setText("");
    }

    private void resetGame() {
        startNewGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessingGameGUI();
            }
        });
    }
}
