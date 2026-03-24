/*
Emmanuel Adekoya, Ross Moniz, Josh Dunlap
Group Project
December 11, 2024
Written/online sources used: how to implement drop down menus: https://www.geeksforgeeks.org/java-swing-jcombobox-examples/
Help obtained: none
We confirm that the above list of sources is complete AND that we have not talked to anyone else about the solution to this problem.
*/

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * SecretSantaApp is a graphical user interface (GUI) application that allows users to add friends, assign Secret Santa matches, and search for their Secret Santa.
 * It uses Java Swing components like JTextField, JList, and JButton to interact with the user.
 */
public class SecretSantaApp extends JFrame {
    private final DefaultListModel<Friend> friendListModel = new DefaultListModel<>();
    private final Map<String, Friend> secretSantaMap = new HashMap<>();
    private final JTextField nameField = new JTextField();
    private final JTextField giftField = new JTextField();
    private final JTextField priceField = new JTextField();
    private final JTextField searchField = new JTextField();
    private final JTextArea resultArea = new JTextArea();

    /**
     * Constructor that initializes the SecretSantaApp.
     * Sets up the window, layout, and components for the app.
     */
    public SecretSantaApp() {
        setTitle("Secret Santa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFriendsListPanel(), BorderLayout.WEST);
        add(createInputPanel(), BorderLayout.CENTER);
        add(createSearchPanel(), BorderLayout.EAST);
        add(createMatchSantaPanel(), BorderLayout.SOUTH);

        setSize(690, 297);
        setMinimumSize(new Dimension(690, 297));
        setMaximumSize(new Dimension(690, 500));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates and returns the Friends List panel with a list of added friends.
     * Also provides buttons to reset and delete friends from the list.
     *
     * @return JPanel for Friends List
     */
    private JPanel createFriendsListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Friends List"));
        listPanel.setPreferredSize(new Dimension(270, 100));

        JList<Friend> friendList = new JList<>(friendListModel);
        friendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(friendList);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        JButton resetButton = new JButton("Reset List");
        JButton deleteButton = new JButton("Delete Friend");
        Dimension buttonSize = new Dimension(100, 35);
        resetButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        resetButton.addActionListener(e -> resetFriendsList());
        deleteButton.addActionListener(e -> {
            int selectedIndex = friendList.getSelectedIndex();
            if (selectedIndex != -1) {
                friendListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a friend to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(resetButton);
        buttonPanel.add(deleteButton);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);

        return listPanel;
    }

    /**
     * Creates and returns the input panel where users can add a friend by entering the friend's name, gift, and price.
     *
     * @return JPanel for adding friends
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Friend"));

        inputPanel.add(createStackedField("Friend's Name:", nameField));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createStackedField("Gift:", giftField));
        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(createStackedField("Price ($20-$50):", priceField));
        inputPanel.add(Box.createVerticalStrut(15));

        JButton addButton = new JButton("Add Friend");
        addButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        addButton.addActionListener(e -> addFriend());
        Dimension buttonSize = new Dimension(100, 35);
        addButton.setPreferredSize(buttonSize);
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        inputPanel.add(addButton);

        return inputPanel;
    }

    /**
     * Creates and returns the search panel where users can search for their Secret Santa by entering their name.
     *
     * @return JPanel for searching a Secret Santa
     */
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Find Your Secret Santa"));

        JPanel stackedField = createStackedField("Enter Your Name:", searchField);
        stackedField.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.add(stackedField);
        searchPanel.add(Box.createVerticalStrut(15));

        JButton searchButton = new JButton("Search");
        Dimension buttonSize = new Dimension(100, 35);
        searchButton.setPreferredSize(buttonSize);
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchButton.addActionListener(e -> findSecretSanta());
        searchPanel.add(searchButton);

        // Add result area
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setPreferredSize(new Dimension(200, 100));
        resultScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(resultScrollPane);

        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return searchPanel;
    }

    /**
     * Creates and returns the panel for matching Secret Santa participants.
     *
     * @return JPanel for Secret Santa matching
     */
    private JPanel createMatchSantaPanel() {
        JPanel matchPanel = new JPanel(new BorderLayout());

        JButton matchButton = new JButton("Match Secret Santas");
        matchButton.setFont(new Font("Arial", Font.BOLD, 14));
        matchButton.setPreferredSize(new Dimension(150, 50));
        matchButton.addActionListener(e -> matchSecretSantas());
        matchPanel.add(matchButton, BorderLayout.CENTER);

        return matchPanel;
    }

    /**
     * Creates and returns a stacked field panel that contains a label and a text field stacked vertically.
     *
     * @param labelText Text for the label
     * @param textField The text field for input
     * @return JPanel with a label and a text field stacked vertically
     */
    private JPanel createStackedField(String labelText, JTextField textField) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setMaximumSize(new Dimension(200, 25));
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(textField);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    /**
     * Adds a friend to the list based on the input from the user.
     * Validates input fields and ensures the price is within the correct range.
     */
    private void addFriend() {
        String name = nameField.getText().trim();
        String gift = giftField.getText().trim();
        String priceText = priceField.getText().trim();

        if (name.isEmpty() || gift.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price < 20 || price > 50) {
                JOptionPane.showMessageDialog(this, "Price must be between $20 and $50!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Friend friend = new Friend(name, gift, price);
            friendListModel.addElement(friend);

            nameField.setText("");
            giftField.setText("");
            priceField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Matches the Secret Santa participants by randomly assigning them to each other.
     * Ensures no one is assigned to themselves and the matching is done fairly.
     */
    private void matchSecretSantas() {
        if (friendListModel.size() < 2) {
            JOptionPane.showMessageDialog(this, "At least two friends are required to play Secret Santa!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Friend> friends = Collections.list(friendListModel.elements());
        ArrayList<Friend> receivers = new ArrayList<>(friends);

        Collections.shuffle(receivers);

        for (int i = 0; i < friends.size(); i++) {
            Friend giver = friends.get(i);
            Friend receiver = receivers.get(i);

            if (giver == receiver) {
                Collections.shuffle(receivers);
                i = -1;
            } else {
                secretSantaMap.put(giver.getName().toLowerCase(), receiver);
            }
        }

        JOptionPane.showMessageDialog(this, "Secret Santas have been randomly matched!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Searches for a Secret Santa match for the entered name.
     * Displays the match result in the result area.
     */
    private void findSecretSanta() {
        String searchName = searchField.getText().trim().toLowerCase();
        if (searchName.isEmpty()) {
            resultArea.setText("Please enter your name to search.");
            return;
        }

        Friend receiver = secretSantaMap.get(searchName);
        if (receiver != null) {
            resultArea.setText("You are gifting:\n" + receiver.getName() + "\nGift: " + receiver.getGift() + "\nPrice: $" + String.format("%.2f", receiver.getPrice()));
        } else {
            resultArea.setText("No match found. Make sure Secret Santa matching is done and your name is spelled correctly.");
        }
    }

    /**
     * Resets the friends list and clears all matches.
     */
    private void resetFriendsList() {
        friendListModel.clear();
        secretSantaMap.clear();
        resultArea.setText("");
        searchField.setText("");
        JOptionPane.showMessageDialog(this, "Friends list and matches have been reset!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new SecretSantaApp();
    }
    
}
