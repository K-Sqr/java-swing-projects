/*
Emmanuel Adekoya, Ross Moniz, Josh Dunlap
Group Project
December 11, 2024
Written/online sources used: how to implement drop down menus: https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
Help obtained: none
We confirm that the above list of sources is complete AND that we have not talked to anyone else about the solution to this problem.
*/

/**
*
 * The TommieFinalsApp class provides a GUI-based application
 * to assist students during finals season. It includes a welcome screen
 * and a selection screen with toolkits for managing finals preparations.
 *
 * The application consists of two main screens:
 * 1. Welcome Frame: Allows the user to input their name and proceed.
 * 2. Selection Frame: Offers a choice between two toolkits:
 *    - Finals Interpreter Toolkit
 *    - Secret Santa Toolkit
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TommieFinalsApp {

    private JFrame welcomeFrame; // Frame for the welcome screen
    private JFrame selectionFrame; // Frame for the toolkit selection screen
    private JTextField nameField; // Input field for the user's name

    /**
     * Constructor initializes and sets up the application frames.
     */
    public TommieFinalsApp() {
        setupWelcomeFrame();
        setupSelectionFrame();
    }

    /**
     * Sets up the welcome frame with a title, user input for name, and navigation buttons.
     */
    private void setupWelcomeFrame() {
        welcomeFrame = new JFrame("Tommie Finals App");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(400, 300);

        JLabel welcomeLabel = new JLabel("Welcome!! to the Tommie Finals App", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(new Color(120, 30, 150)); // Adds a custom color to the welcome label

        JLabel nameLabel = new JLabel("Please enter your name: ", JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        nameField = new JTextField(20); // Input field for the user's name

        JButton nextButton = new JButton("Next");
        JButton exitButton = new JButton("Exit");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(nextButton);
        buttonPanel.add(exitButton);

        welcomeFrame.setLayout(new BorderLayout());
        welcomeFrame.add(welcomeLabel, BorderLayout.NORTH);
        welcomeFrame.add(inputPanel, BorderLayout.CENTER);
        welcomeFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for the "Next" button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onNextButtonClicked();
            }
        });

        // Action listener for the "Exit" button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onExitButtonClicked();
            }
        });

        welcomeFrame.setVisible(true);
    }

    /**
     * Sets up the selection frame for toolkit options with buttons for each tool.
     */
    private void setupSelectionFrame() {
        selectionFrame = new JFrame("Finals Toolkit");
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectionFrame.setSize(500, 400);

        JLabel selectLabel = new JLabel("Please select from the following Finals toolkits", JLabel.CENTER);
        selectLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton finalsInterpreterButton = new JButton("Final Exam Time Interpreter");
        JButton secretSantaButton = new JButton("Secret Santa with Friends");

        JPanel toolkitPanel = new JPanel();
        toolkitPanel.setLayout(new GridLayout(3, 2, 40, 40));
        toolkitPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adds padding to the layout
        toolkitPanel.add(finalsInterpreterButton);
        toolkitPanel.add(secretSantaButton);

        selectionFrame.setLayout(new BorderLayout());
        selectionFrame.add(selectLabel, BorderLayout.NORTH);
        selectionFrame.add(toolkitPanel, BorderLayout.CENTER);

        // Action listener for the "Final Exam Time Interpreter" button
        finalsInterpreterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFinalsInterpreterButtonClicked();
            }
        });

        // Action listener for the "Secret Santa with Friends" button
        secretSantaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSecretSantaButtonClicked();
            }
        });
    }

    /**
     * Handles the "Next" button click event in the welcome frame.
     * Verifies user input and navigates to the selection frame.
     */
    private void onNextButtonClicked() {
        String userName = nameField.getText().trim();
        if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(welcomeFrame, "Please enter your name.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(welcomeFrame, "Hi " + userName + ", glad to see you're taking initiative to prepare for finals and also have some fun with friends. Please click Next to get access to some of our Finals toolkits.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
            welcomeFrame.setVisible(false);
            selectionFrame.setVisible(true);
        }
    }

    /**
     * Handles the "Exit" button click event to close the application.
     */
    private void onExitButtonClicked() {
        System.exit(0);
    }

    /**
     * Handles the "Final Exam Time Interpreter" button click event.
     * Launches the FinalsInterpreterApp.
     */
    private void onFinalsInterpreterButtonClicked() {
        FinalsInterpreterApp interpreterApp = new FinalsInterpreterApp(selectionFrame);
        interpreterApp.setVisible(true);
        selectionFrame.setVisible(false);
    }

    /**
     * Handles the "Secret Santa with Friends" button click event.
     * Launches the SecretSantaApp.
     */
    private void onSecretSantaButtonClicked() {
        SecretSantaApp.main(null);
        selectionFrame.setVisible(false);
    }

    /**
     * The main method initializes the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new TommieFinalsApp();
    }
}
