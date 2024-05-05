package demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberToWordsCalculator::new);
    }
}

class NumberToWordsCalculator extends JFrame implements ActionListener {

    private final JTextField moneyInput;
    private final JRadioButton option1, option2, option3;
    private final ButtonGroup group;
    private final JLabel selectedOptionLabel;
    private final JComboBox<String> letterCaseComboBox;
    String result;
    private final JTextField khAnswer;
    private final JTextField engAnswer;
    private final JTextField convertToDollarAnswer;
    String selectOption;

    public NumberToWordsCalculator() {
        // Set layout and frame properties
        setTitle("Number to Words Calculator");
        setSize(500, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        JLabel inputLabel = new JLabel("Convert this number:");
        inputLabel.setBounds(100, 50, 200, 30);
        add(inputLabel);

        // Input number field
        moneyInput = new JTextField();
        moneyInput.setBounds(100, 90, 200, 30);
        add(moneyInput);

        // Radio buttons for selection
        option1 = new JRadioButton("Words", true);
        option2 = new JRadioButton("Currency");
        option3 = new JRadioButton("Check Writing");

        // Set bounds for radio buttons
        option1.setBounds(100, 160, 200, 30);
        option2.setBounds(100, 190, 200, 30);
        option3.setBounds(100, 220, 200, 30);

        // Button group to group the radio buttons together
        group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        // Add action listeners to the radio buttons
//        option1.addActionListener(e -> actionOption(e));
//        option2.addActionListener(e -> actionOption(e));
//        option3.addActionListener(e -> actionOption(e));


        // Label to display the selected option
        selectedOptionLabel = new JLabel("Selected Option: Words");
        selectedOptionLabel.setBounds(100, 250, 200, 30);
        //add(selectedOptionLabel);

        // Label and combo box for letter case selection
        JLabel letterCaseLabel = new JLabel("Letter Case:");
        letterCaseLabel.setBounds(100, 300, 200, 30);
        add(letterCaseLabel);

        String[] options = {"lowercase", "UPPERCASE", "Title Case", "Sentence case"};
        letterCaseComboBox = new JComboBox<>(options);
        letterCaseComboBox.setBounds(100, 340, 200, 30);
        add(letterCaseComboBox);

        // Action listener for the combo box
        letterCaseComboBox.addActionListener(this);

        // Button
        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(40,400, 100, 30);
        add(clearBtn);
        JButton calculateBtn = new JButton("Calculate");
        calculateBtn.setBounds(240, 400, 100, 30);
        add(calculateBtn);

        // answer label
        JLabel khmerAnswer = new JLabel("អានជាភាសាខ្មែរ:");
        khmerAnswer.setBounds(40,450, 100, 30);
        add(khmerAnswer);

        JLabel englishAnswer = new JLabel("Read on English:");
        englishAnswer.setBounds(40, 500, 100, 30);
        add(englishAnswer);

        JLabel exchangeAnswer = new JLabel("Convert to Dollar:");
        exchangeAnswer.setBounds(40,550, 150, 30);
        add(exchangeAnswer);

        // answer text field
        khAnswer = new JTextField();
        khAnswer.setBounds(160,450, 300, 30);
        add(khAnswer);

        engAnswer = new JTextField();
        engAnswer.setBounds(160, 500, 300, 30);
        add(engAnswer);

        convertToDollarAnswer = new JTextField();
        convertToDollarAnswer.setBounds(160, 550, 300, 30);
        add(convertToDollarAnswer);

        // events
        calculateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processCalculate();
            }
        });

        setVisible(true);
    }


    private void processCalculate(){
        //JOptionPane.showMessageDialog(this,"Hello Hello");
        String inputMoney = moneyInput.getText();
        if (inputMoney.isEmpty()) {
            JOptionPane.showMessageDialog(this, "text file empty, please input money...!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int amount = Integer.parseInt(inputMoney);
            result = String.valueOf(amount);
            String wordsEnglish = readInEnglish(amount) + " Riel";
            String wordsKhmer = readInKhmer(amount) + " រៀល";
            String wordConvertToDollar =  covertToDollar(amount) + "$";

            String resultEnglish = null;
            String resultKhmer = null;
            String resultDollar = null;

            //if(selectOption.equalsIgnoreCase("op1")){
                resultEnglish = wordsEnglish;
                resultKhmer = wordsKhmer;
                resultDollar = wordConvertToDollar;
            //}

            engAnswer.setText(resultEnglish);
            khAnswer.setText(resultKhmer);
            convertToDollarAnswer.setText(resultDollar);


            BufferedWriter writer = new BufferedWriter(new FileWriter("money_file.txt", true));
            writer.write(inputMoney + "\n");
            writer.close();


            //JOptionPane.showMessageDialog(this, "Data stored successfully: " + inputMoney + " KHR - " + amount, "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException | IOException exception) {
            JOptionPane.showMessageDialog(this, "Error processing input.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String readInEnglish(int number) {
        String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        // No need for a separate thousands array since handling is now more dynamic
        if (number == 0) {
            return "Zero";
        }
        String words = "";
        if (number / 1000000 > 0) {
            words += readInEnglish(number / 1000000) + " Million ";
            number %= 1000000;
        }
        if (number / 1000 > 0) {
            words += readInEnglish(number / 1000) + " Thousand ";
            number %= 1000;
        }
        if (number / 100 > 0) {
            words += readInEnglish(number / 100) + " Hundred ";
            number %= 100;
        }
        if (number > 0) {
            if (number < 20) {
                words += (number < 10) ? units[number] : teens[number - 10];
            } else {
                words += tens[number / 10];
                if (number % 10 > 0) {
                    words += " " + units[number % 10];
                }
            }
        }
        return words.trim();

    }

    private String readInKhmer(int number) {
        String[] units = {"", "មួយ", "ពីរ", "បី", "បួន", "ប្រាំ", "ប្រាំមួយ", "ប្រាំពីរ", "ប្រាំបី", "ប្រាំបួន"};
        String[] tens = {"", "ដប់", "ម្ភៃ", "សាមសិប", "សែសិប", "ហាសិប", "ហុកសិប", "ចិតសិប", "ប៉ែតសិប", "កៅសិប"};
        String hundred = "រយ";
        String thousand = "ពាន់";
        String tenThousand = "មុឺន";
        String hundredThousand = "សែន";
        String million = "លាន";

        if (number == 0) {
            return "សូន្យ"; // "Zero" in Khmer
        }

        String words = "";
        if (number / 1000000 > 0) {
            words += units[number / 1000000] + million + " ";
            number %= 1000000;
        }
        if (number / 100000 > 0) {
            words += readInKhmer(number / 100000) + tenThousand + " ";
            number %= 100000;
        }
        if (number / 10000 > 0) {
            words += readInKhmer(number / 100000) + hundredThousand + " ";
            number %= 10000;
        }
        if (number / 1000 > 0) {
            words += readInKhmer(number / 1000) + thousand + " ";
            number %= 1000;
        }
        if (number / 100 > 0) {
            words += units[number / 100] + hundred + " ";
            number %= 100;
        }
        if (number > 0) {
            if (number < 10) {
                words += units[number];
            } else if (number < 20) {
                // Handling 11-19, which follows the pattern "ten-one", "ten-two", etc.
                words += tens[number / 10] + units[number % 10];
            } else {
                words += tens[number / 10];
                if ((number % 10) > 0) {
                    words += " " + units[number % 10];
                }
            }
        }
        return words.trim();
    }

    private Long  covertToDollar(int number){
        return (long) (number/4000);
    }

    public void actionOption(ActionEvent e) {
        JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
        if (selectedRadioButton == option1) {
            selectedOptionLabel.setText("Selected Option: Words");
            selectOption = "op1";
        } else if (selectedRadioButton == option2) {
            selectedOptionLabel.setText("Selected Option: Currency");
            selectOption = "op2";
        } else if (selectedRadioButton == option3) {
            selectedOptionLabel.setText("Selected Option: Check Writing");
            selectOption = "op3";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == option1) {
            selectedOptionLabel.setText("word"+selectOption);
        } else if (e.getSource() == option2) {
            selectedOptionLabel.setText("Selected Option: Currency");
            selectOption = "op2";
        } else if (e.getSource() == option3) {
            selectedOptionLabel.setText("Selected Option: Check Writing");
            selectOption = "op3";
        }*/

        // Handling selections from the JComboBox for letter case
        if (e.getSource() == letterCaseComboBox) {
            // Get the selected item from the combo box
            String selectedCase = (String) letterCaseComboBox.getSelectedItem();
            System.out.println("Selected letter case: " + selectedCase);
            // Here, you could add additional logic based on the selected letter case
        }
    }
}