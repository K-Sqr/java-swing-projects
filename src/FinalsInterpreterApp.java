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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The FinalsInterpreterApp class provides a GUI-based application
 * that helps users find the date and time of their final exams based on
 * class schedules.
 * 
 * The application reads from an external schedule file and displays
 * matching finals details based on the selected class day and time.
 */
public class FinalsInterpreterApp extends JFrame {
    private ClassTimeMapper timeMapper;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> timeComboBox;
    private JTextArea resultArea;
    private JFrame parentFrame;

    /**
     * Constructs a FinalsInterpreterApp window.
     *
     * @param parentFrame The parent JFrame to return to when exiting this window.
     */
    public FinalsInterpreterApp(JFrame parentFrame) {
    	 this.parentFrame = parentFrame;
        timeMapper = new ClassTimeMapper("ExamSchedule.txt");

        setTitle("Finals Time Interpreter");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        inputPanel.add(new JLabel("Select Class Day:"));
        dayComboBox = new JComboBox<>(timeMapper.getAllDays().toArray(new String[0]));
        dayComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTimePeriods();
            }
        });
        inputPanel.add(dayComboBox);

        inputPanel.add(new JLabel("Select Class Time:"));
        timeComboBox = new JComboBox<>();
        inputPanel.add(timeComboBox);

        JButton searchButton = new JButton("Find Final Exam Time");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findFinalExamTime();
            }
        });
        inputPanel.add(searchButton);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                findFinalExamTime();
            }
        });
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        updateTimePeriods();
        setVisible(true);
    }

    private void updateTimePeriods() {
        timeComboBox.removeAllItems();
        String selectedDay = (String) dayComboBox.getSelectedItem();
        if (selectedDay != null) {
            for (String time : timeMapper.getTimePeriods(selectedDay)) {
                timeComboBox.addItem(time);
            }
        }
    }
    /**
     * Finds the final exam time based on the selected class day and time.
     * Updates the resultArea with the details of the finals period or an error message.
     */
    private void findFinalExamTime() {
        String selectedDay = (String) dayComboBox.getSelectedItem();
        String selectedTime = (String) timeComboBox.getSelectedItem();

        if (selectedDay != null && selectedTime != null) {
            ClassTimeMapper.ScheduleEntry entry = timeMapper.findFinalsPeriod(selectedDay, selectedTime);
            if (entry != null) {
                resultArea.setText(String.format(
                    "Final Exam Information:\nDay: %s\nTime: %s\nDate: %s %s %d",
                    selectedDay, entry.finalsTime, entry.finalsDay, entry.finalsMonth, entry.finalsYear
                ));
            } else {
                resultArea.setText("No matching final exam time found.");
            }
        } else {
            resultArea.setText("Please select both a day and time.");
        }
    }

  
}
