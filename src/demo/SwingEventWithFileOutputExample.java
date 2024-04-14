package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class SwingEventWithFileOutputAndLabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Example");

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(0, 1));

        ButtonGroup group = new ButtonGroup();
        JRadioButton radioButton1 = new JRadioButton("Option 1");
        JRadioButton radioButton2 = new JRadioButton("Option 2");
        JRadioButton radioButton3 = new JRadioButton("Option 3");
        group.add(radioButton1);
        group.add(radioButton2);
        group.add(radioButton3);

        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        radioPanel.add(radioButton3);

        JLabel resultLabel = new JLabel("Selected option will be displayed here.");

        JButton saveButton = new JButton("Save to File");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton selectedRadioButton = null;
                if (radioButton1.isSelected()) {
                    selectedRadioButton = radioButton1;
                } else if (radioButton2.isSelected()) {
                    selectedRadioButton = radioButton2;
                } else if (radioButton3.isSelected()) {
                    selectedRadioButton = radioButton3;
                }

                if (selectedRadioButton != null) {
                    String selectedText = selectedRadioButton.getText();
                    resultLabel.setText("Selected: " + selectedText);
                    writeToFile(selectedText);
                } else {
                    resultLabel.setText("Please select an option.");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(radioPanel, BorderLayout.CENTER);
        frame.getContentPane().add(resultLabel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void writeToFile(String text) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
