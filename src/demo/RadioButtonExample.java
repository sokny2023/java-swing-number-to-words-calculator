package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButtonExample extends JFrame implements ActionListener {

    // Radio buttons
    private JRadioButton option1, option2, option3;
    // Button group to group radio buttons
    private ButtonGroup group;
    // Label to display the selected option
    private JLabel selectedOptionLabel;

    public RadioButtonExample() {
        createUI();
    }

    private void createUI() {
        // Set up the frame
        setTitle("Radio Button Example");
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize radio buttons
        option1 = new JRadioButton("Option 1", true); // true sets this option as selected by default
        option2 = new JRadioButton("Option 2");
        option3 = new JRadioButton("Option 3");

        // Add action listeners to radio buttons
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);

        // Button group to group the radio buttons together
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        // Add radio buttons to the frame
        add(option1);
        add(option2);
        add(option3);

        // Label to display the selected option
        selectedOptionLabel = new JLabel("Selected Option: Option 1");
        add(selectedOptionLabel);

        // Display the window
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the label based on the selected radio button
        if (e.getSource() == option1) {
            selectedOptionLabel.setText("Selected Option: Option 1");
        } else if (e.getSource() == option2) {
            selectedOptionLabel.setText("Selected Option: Option 2");
        } else if (e.getSource() == option3) {
            selectedOptionLabel.setText("Selected Option: Option 3");
        }
    }

    public static void main(String[] args) {
        // Run the program
        SwingUtilities.invokeLater(() -> new RadioButtonExample());
    }
}

