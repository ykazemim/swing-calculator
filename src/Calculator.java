/**
 * Sources:
 * stackoverflow.com
 * docs.oracle.com
 * flaticon.com
 * geeksforgeeks.org
 * coderanch.com
 */


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Calculator implements ActionListener {
    private final JLabel inputLabel = new JLabel("0");
    private final JFrame frame = new JFrame("Calculator");
    private final JPanel buttonsPanel = new JPanel();
    private final JPanel resultPanel = new JPanel();
    private final MyButton clrButton = new MyButton("CLR", this);
    private final MyButton delButton = new MyButton("DEL", this);
    private final MyButton plusMinusButton = new MyButton("+/-", this);
    private final MyButton divisionButton = new MyButton("÷", this);
    private final MyButton multiplyButton = new MyButton("×", this);
    private final MyButton minusButton = new MyButton("-", this);
    private final MyButton sumButton = new MyButton("+", this);
    private final MyButton equalButton = new MyButton("=", this);
    private final MyButton[] numButton = new MyButton[10];
    private final MyButton decimalButton = new MyButton(".", this);
    private final DecimalFormat decimalFormat = new DecimalFormat("0.##");
    private final GridBagConstraints gbc = new GridBagConstraints();
    private double firstNumber, secondNumber, answer;
    private char symbol;

    public Calculator() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Setting an icon
        try {
            Image img = ImageIO.read(new File("resources/calculator.png"));
            frame.setIconImage(img);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Failed to set the app's icon.");
        }


        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.25;
        gbc.weighty = 1;

        buttonsPanel.setLayout(new GridBagLayout());


        resultPanel.setLayout(new GridBagLayout());
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        inputLabel.setFont(new Font(inputLabel.getFont().getFontName(), Font.PLAIN, inputLabel.getFont().getSize() * 2));
        inputLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        resultPanel.add(inputLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0 / 4;
        gbc.weighty = 1;


        gbc.gridx = 0;
        gbc.gridy = 0;
        clrButton.setBackground(Color.lightGray);
        buttonsPanel.add(clrButton, gbc);
        gbc.gridx++;
        delButton.setBackground(Color.lightGray);

        buttonsPanel.add(delButton, gbc);
        gbc.gridx++;

        plusMinusButton.setBackground(Color.lightGray);
        buttonsPanel.add(plusMinusButton, gbc);
        gbc.gridx++;


        divisionButton.setBackground(Color.orange);

        buttonsPanel.add(divisionButton, gbc);
        gbc.gridy++;
        multiplyButton.setBackground(Color.orange);
        buttonsPanel.add(multiplyButton, gbc);
        gbc.gridy++;
        minusButton.setBackground(Color.orange);
        buttonsPanel.add(minusButton, gbc);
        gbc.gridy++;


        sumButton.setBackground(Color.orange);
        buttonsPanel.add(sumButton, gbc);
        gbc.gridy++;
        equalButton.setBackground(Color.orange);
        buttonsPanel.add(equalButton, gbc);
        gbc.gridy++;

        // Adding number buttons, from 1 to 9
        for (int i = 0; i < 10; i++) {
            numButton[i] = new MyButton(this);
            numButton[i].setText(String.valueOf(i));
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        for (int i = 1, j = 7; i < 10; i++) {
            buttonsPanel.add(numButton[j++], gbc);
            gbc.gridx++;
            if (gbc.gridx >= 3) {
                gbc.gridx = 0;
                gbc.gridy++;
                j -= 6;
            }
        }
        //Adding number button 0

        gbc.gridwidth = 2;
        numButton[0] = new MyButton("0", this);
        buttonsPanel.add(numButton[0], gbc);
        gbc.gridx += 2;
        gbc.gridwidth = 1;

        //Adding '.' sign
        buttonsPanel.add(decimalButton, gbc);
        gbc.gridx++;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.25;
        frame.add(resultPanel, gbc);

        gbc.weighty = 0.75;
        gbc.gridy++;
        frame.add(buttonsPanel, gbc);


        frame.pack();
        Dimension frameDimension = new Dimension();
        frameDimension.width = (int) (frame.getSize().getWidth());
        frameDimension.height = (int) (frame.getSize().getHeight() * 1.6);
        frame.setMinimumSize(frameDimension);
        frame.revalidate();
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        for (int i = 1; i < 10; i++) {
            if (src == numButton[i]) {
                if (inputLabel.getText().replace("0", "").isEmpty()) {
                    inputLabel.setText("");
                }
                inputLabel.setText(inputLabel.getText() + i);
                break;
            }
        }
        if (src == numButton[0]) {
            if (!inputLabel.getText().replace("0", "").isEmpty()) {
                inputLabel.setText(inputLabel.getText() + 0);
            }
        } else if (src == decimalButton) {
            if (!inputLabel.getText().contains(".")) {
                inputLabel.setText(inputLabel.getText() + ".");
            }
        } else if (src == clrButton) {
            inputLabel.setText("0");
        } else if (src == delButton) {
            if (inputLabel.getText().length() == 1) {
                inputLabel.setText("0");
            } else inputLabel.setText(inputLabel.getText().substring(0, inputLabel.getText().length() - 1));
        } else if (src == plusMinusButton) {
            if (inputLabel.getText().compareTo("0") != 0) {
                if (inputLabel.getText().charAt(0) != '-') {
                    inputLabel.setText("-" + inputLabel.getText());
                } else {
                    inputLabel.setText(inputLabel.getText().substring(1));
                }
            }
        } else if (src == divisionButton) {
            symbol = '/';
            firstNumber = Double.parseDouble(inputLabel.getText());
            inputLabel.setText("0");
        } else if (src == multiplyButton) {
            symbol = '*';
            firstNumber = Double.parseDouble(inputLabel.getText());
            inputLabel.setText("0");
        } else if (src == minusButton) {
            symbol = '-';
            firstNumber = Double.parseDouble(inputLabel.getText());
            inputLabel.setText("0");
        } else if (src == sumButton) {
            symbol = '+';
            firstNumber = Double.parseDouble(inputLabel.getText());
            inputLabel.setText("0");
        } else if (src == equalButton) {
            secondNumber = Double.parseDouble(inputLabel.getText());
            switch (symbol) {
                case '+':
                    answer = firstNumber + secondNumber;
                    break;
                case '-':
                    answer = firstNumber - secondNumber;
                    break;
                case '*':
                    answer = firstNumber * secondNumber;
                    break;
                case '/':
                    answer = firstNumber / secondNumber;
                    break;
            }
            inputLabel.setText(String.valueOf(decimalFormat.format(answer)));
        }
    }
}
