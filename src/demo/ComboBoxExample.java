package demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class ComboBoxExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComboBoxExample::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the main window (frame)
        JFrame frame = new JFrame("ComboBox Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout()); // Set layout manager

        // Create ComboBox with options
        String[] options = {"Option 1", "Option 2", "Option 3"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Add ActionListener to the combo box
        comboBox.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            String selectedOption = (String)cb.getSelectedItem();
            // Display the selected item (for demonstration)
            JOptionPane.showMessageDialog(frame, "You selected: " + selectedOption);
        });

        // Add ComboBox to the frame
        frame.add(comboBox);

        // Adjust the window size and show it
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
}


