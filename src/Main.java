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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class Main extends JFrame {

    public static final JLabel inputLabel = new JLabel("0");
    private static final JFrame frame = new JFrame("Calculator");
    private static final JPanel mainPanel = new JPanel();
    private static final JPanel textPanel = new JPanel();
    private static final JButton clrButton = new JButton("CLR");
    private static final JButton delButton = new JButton("DEL");
    private static final JButton plusMinusButton = new JButton("+/-");
    private static final JButton divisionButton = new JButton("÷");
    private static final JButton multiplyButton = new JButton("×");
    private static final JButton minusButton = new JButton("-");
    private static final JButton sumButton = new JButton("+");
    private static final JButton equalButton = new JButton("=");
    private static final JButton[] numButton = new JButton[10];
    private static final JButton decimalButton = new JButton(".");
    static DecimalFormat df = new DecimalFormat("0.##");
    private static boolean flag = true;
    private static double firstNumber, secondNumber, answer;
    private static char symbol;

    public static void main(String[] args) throws IOException {
        setUI();
        ActionListener actionListener = createActionListener();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLayout(new GridBagLayout());


        //Setting an icon
        InputStream imgStream = Main.class.getResourceAsStream("calculator.png");
        BufferedImage img = ImageIO.read(imgStream);
        frame.setIconImage(img);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.25;
        gbc.weighty = 1;

        mainPanel.setLayout(new GridBagLayout());


        textPanel.setLayout(new GridBagLayout());
        textPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        inputLabel.setFocusable(false);
        inputLabel.setAlignmentX(JLabel.CENTER);
        inputLabel.setFont(new Font(inputLabel.getFont().getFontName(), Font.PLAIN, inputLabel.getFont().getSize() * 2));
        inputLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        textPanel.add(inputLabel, gbc);

        gbc.weighty = 1;


        gbc.gridx = 0;
        gbc.gridy = 0;
        clrButton.setBackground(Color.lightGray);
        mainPanel.add(clrButton, gbc);
        gbc.gridx++;
        delButton.setBackground(Color.lightGray);

        mainPanel.add(delButton, gbc);
        gbc.gridx++;

        plusMinusButton.setBackground(Color.lightGray);
        mainPanel.add(plusMinusButton, gbc);
        gbc.gridx++;


        divisionButton.setBackground(Color.orange);

        mainPanel.add(divisionButton, gbc);
        gbc.gridy++;
        multiplyButton.setBackground(Color.orange);
        mainPanel.add(multiplyButton, gbc);
        gbc.gridy++;
        minusButton.setBackground(Color.orange);
        mainPanel.add(minusButton, gbc);
        gbc.gridy++;


        sumButton.setBackground(Color.orange);
        mainPanel.add(sumButton, gbc);
        gbc.gridy++;
        equalButton.setBackground(Color.orange);
        mainPanel.add(equalButton, gbc);
        gbc.gridy++;


        //Adding number buttons, from 1 to 9
        for (int i = 0; i < 10; i++) {
            numButton[i] = new JButton();
            numButton[i].setText(String.valueOf(i));
            numButton[i].setFocusable(false);
            numButton[i].addActionListener(actionListener);
        }
        gbc.gridx = 0;
        gbc.gridy = 1;
        for (int i = 1, j = 7; i < 10; i++) {
            mainPanel.add(numButton[j++], gbc);
            gbc.gridx++;
            if (gbc.gridx >= 3) {
                gbc.gridx = 0;
                gbc.gridy++;
                j -= 6;
            }
        }
        //Adding number button 0
        gbc.gridwidth = 2;
        numButton[0] = new JButton("0");
        mainPanel.add(numButton[0], gbc);
        gbc.gridx += 2;
        gbc.gridwidth = 1;

        //Adding '.' sign
        mainPanel.add(decimalButton, gbc);
        gbc.gridx++;


        clrButton.addActionListener(actionListener);
        delButton.addActionListener(actionListener);
        plusMinusButton.addActionListener(actionListener);
        divisionButton.addActionListener(actionListener);
        multiplyButton.addActionListener(actionListener);
        minusButton.addActionListener(actionListener);
        sumButton.addActionListener(actionListener);
        equalButton.addActionListener(actionListener);
        decimalButton.addActionListener(actionListener);
        numButton[0].addActionListener(actionListener);


        clrButton.setFocusable(false);
        delButton.setFocusable(false);
        plusMinusButton.setFocusable(false);
        divisionButton.setFocusable(false);
        multiplyButton.setFocusable(false);
        minusButton.setFocusable(false);
        sumButton.setFocusable(false);
        equalButton.setFocusable(false);
        decimalButton.setFocusable(false);
        numButton[0].setFocusable(false);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.25;
        frame.add(textPanel, gbc);

        gbc.weighty = 0.75;
        gbc.gridy++;
        frame.add(mainPanel, gbc);


        frame.pack();
        Dimension frameDimension = new Dimension();
        frameDimension.width = (int) (frame.getSize().getWidth() * 1.2);
        frameDimension.height = (int) (frameDimension.getWidth() * 1.5);
        frame.setSize(frameDimension);
        frame.setMinimumSize(frame.getSize());
        frame.revalidate();
        frame.repaint();
        frame.setLocation((screenDimension.width - frame.getSize().width) / 2, (screenDimension.height - frame.getSize().height) / 2);
        frame.setVisible(true);

    }

    private static void setUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static ActionListener createActionListener() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object src = e.getSource();
                if (src == numButton[0]) {
                    if (!inputLabel.getText().replace("0", "").isEmpty()) {
                        inputLabel.setText(inputLabel.getText() + 0);
                    }
                }

                for (int i = 1; i < 10; i++) {
                    if (src == numButton[i]) {
                        if (!inputLabel.getText().isEmpty() && inputLabel.getText().replace("0", "").isEmpty()) {
                            inputLabel.setText("");
                        }
                        inputLabel.setText(inputLabel.getText() + i);
                    }
                }
                if (src == decimalButton) {
                    if (!inputLabel.getText().contains(".")) {
                        inputLabel.setText(inputLabel.getText() + ".");
                    }
                }
                if (src == clrButton) {
                    inputLabel.setText("0");
                }
                if (src == delButton) {
                    if (inputLabel.getText().length() == 1) {
                        inputLabel.setText("0");
                    } else inputLabel.setText(inputLabel.getText().substring(0, inputLabel.getText().length() - 1));
                }
                if (src == plusMinusButton) {
                    if (inputLabel.getText().compareTo("0") != 0) {
                        if (flag) {
                            inputLabel.setText("-" + inputLabel.getText());
                            flag = !flag;
                        } else {
                            inputLabel.setText(inputLabel.getText().substring(1));
                            flag = !flag;
                        }
                    }
                }
                if (src == divisionButton) {
                    symbol = '/';
                    firstNumber = Double.parseDouble(inputLabel.getText());
                    inputLabel.setText("0");
                }
                if (src == multiplyButton) {
                    symbol = '*';
                    firstNumber = Double.parseDouble(inputLabel.getText());
                    inputLabel.setText("0");
                }
                if (src == minusButton) {
                    symbol = '-';
                    firstNumber = Double.parseDouble(inputLabel.getText());
                    inputLabel.setText("0");
                }
                if (src == sumButton) {
                    symbol = '+';
                    firstNumber = Double.parseDouble(inputLabel.getText());
                    inputLabel.setText("0");
                }
                if (src == equalButton) {
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
                    inputLabel.setText(String.valueOf(df.format(answer)));
                }
            }
        };
        return actionListener;
    }
}
