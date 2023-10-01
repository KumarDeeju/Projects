
    package Task_1;

// import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.border.LineBorder;

public class MyFrame extends JFrame {

    JLabel headingLabel;
    JButton button1;// for guess button
    JButton button2;// for play again button
    JButton button3;// for quitbutton
    JLabel label1, Note; // for heading
    JLabel subheadingLabel;// for sub heading 1
    JLabel subheadingLabel2;// for sub heading 2
    JTextField textField;// for user input
    JLabel imageLabel;// for image
    ButtonListener buttonListener;
    ButtonListener2 buttonListener2;
    ButtonListener3 buttonListener3;

    // define the range
    int max = 100;
    int min = 1;
    int range = max - min + 1;

    int rand = (int) (Math.random() * (range) + min);
    int count = 0;// for the result
    int attempts = 0;// for counting the number of attempts
    int maxLimit; // for maxLimit & Ooption (want to play again)
    int correctGuess = 0;// to not to show result on click of play again when guessed the number correct
                         // once

    // constructor
    MyFrame() {
        this.setTitle("Guess The Number !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Get the content pane
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(11, 12, 16));
        this.setBounds(500, 150, 500, 500);
        this.setLayout(null);

        // heading 1 (guess the number )
        headingLabel = new JLabel("Guess The Number ? ");
        headingLabel.setForeground(new Color(69, 162, 158));
        headingLabel.setBounds(85, 5, 400, 50);
        headingLabel.setFont(new Font("times new roman", Font.BOLD, 35));

        // heading 2 (Enter the number b/t 1-100)
        subheadingLabel = new JLabel("Enter Number Between 1 to 100 :");
        subheadingLabel.setForeground(new Color(197, 198, 199));
        subheadingLabel.setBounds(50, 100, 300, 50);
        subheadingLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        // user input textField
        textField = new JTextField(10);
        textField.setBounds(50, 160, 270, 30);
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        textField.setForeground(new Color(31, 40, 51));
        textField.setBackground(new Color(102, 252, 241));
        textField.setCaretColor(new Color(31, 40, 51));

        // heading 3 (Try to Guess the number )
        subheadingLabel2 = new JLabel("Try to Guess the number !!!");
        subheadingLabel2.setForeground(new Color(197, 198, 199));
        subheadingLabel2.setBounds(65, 200, 300, 50);
        subheadingLabel2.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));

        // imageLabel
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("Task_1/Images/boy1.png"));
        imageLabel.setBounds(255, 160, 432, 300);

        // instancing the JButtons
        button1 = new JButton("Guess");
        button2 = new JButton("Play Again");
        button3 = new JButton("Exit");

        // let's set the location and size of buttons & color
        button1.setBounds(120, 300, 100, 30);
        button1.setBorder(BorderFactory.createEtchedBorder());// set border
        button1.setBorder(new LineBorder(new Color(69, 162, 158)));
        button1.setForeground(new Color(218, 255, 251));
        button1.setBackground(new Color(11, 12, 16));
        button1.setFocusable(false);// to get rid of box aroud text on button
        buttonListener = new ButtonListener();
        button1.addActionListener(buttonListener);
        // Bind the Enter key to the custom action for the button
        button1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                "enterAction");
        button1.getActionMap().put("enterAction", enterAction);

        button2.setBounds(50, 350, 100, 30);
        button2.setBorder(BorderFactory.createEtchedBorder());// set border
        button2.setBorder(new LineBorder(new Color(69, 162, 158)));
        button2.setForeground(new Color(218, 255, 251));
        button2.setBackground(new Color(11, 12, 16));
        button2.setFocusable(false);// to get rid of box aroud text on button
        buttonListener2 = new ButtonListener2();
        button2.addActionListener(buttonListener2);

        button3.setBounds(190, 350, 100, 30);
        button3.setBorder(BorderFactory.createEtchedBorder());// set border
        button3.setBorder(new LineBorder(new Color(69, 162, 158)));
        button3.setBackground(new Color(11, 12, 16));
        button3.setForeground(new Color(218, 255, 251));
        button3.setFocusable(false);// to get rid of box aroud text on button
        buttonListener3 = new ButtonListener3();
        button3.addActionListener(buttonListener3);

        // note
        Note = new JLabel("NOTE: You have 10 attempts only !");
        Note.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        Note.setForeground(Color.RED);
        Note.setBounds(10, 420, 300, 30);

        // adding the components to the frame
        contentPane.add(Note);
        contentPane.add(imageLabel);
        contentPane.add(subheadingLabel2);
        contentPane.add(textField);
        contentPane.add(subheadingLabel);
        contentPane.add(headingLabel);
        contentPane.add(button1);
        contentPane.add(button2);
        contentPane.add(button3);
        this.setVisible(true);

    }

    // for guess button
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int a = Integer.parseInt(textField.getText());
                count += 10;

                if (a >= 1 && a <= 100) {// validation to the entered value
                    attempts++;
                    if (a < rand) {
                        subheadingLabel2.setText(a + " is low, try again!!");
                    } else if (a > rand) {
                        subheadingLabel2.setText(a + " is high, try again!!");
                    } else {
                        correctGuess++;
                        ImageIcon icon = new ImageIcon("Task_1/Images/role-model (1).png");
                        JOptionPane.showOptionDialog(null, "Your best score is " + count, "Hurrey!!!",
                                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon, null, 0);
                        subheadingLabel2.setText("Your guess is correct, You win!!");
                        // JOptionPane.showMessageDialog(null, "Your best score is " + count);
                    }
                } else {
                    ImageIcon icon1 = new ImageIcon("Task_1/Images/warning.png");
                    JOptionPane.showOptionDialog(null, "Enter only numeric digits(1-100) !", "warning",
                            JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon1, null, 0);
                }
            } catch (Exception exception) {
                ImageIcon icon2 = new ImageIcon("Task_1/Images/warning.png");
                JOptionPane.showOptionDialog(null, "Please enter valid input (numbers only) ", "warning",
                        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon2, null, 0);
            }
            if (attempts > 10) {
                ImageIcon icon3 = new ImageIcon("Task_1/Images/limitation.png");
                maxLimit = JOptionPane.showOptionDialog(null,
                        "You have reached maximum limit of guesses! Want to play again?", "Max Limit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon3, null, 0);
                if (maxLimit == 0) {
                    System.out.println("play again");
                    subheadingLabel2.setText("Try to Guess the number !!!");
                    textField.setText("");
                    rand = (int) (Math.random() * 100);
                    count = 0;
                    attempts = 0;
                } else {
                    System.exit(0);
                }
            }
        }
    }

    // for play again button
    private class ButtonListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            subheadingLabel2.setText("Try to Guess the number !!!");
            if (correctGuess < 1) {
                ImageIcon icon4 = new ImageIcon("Task_1/Images/cry.png");
                JOptionPane.showOptionDialog(null, "The correct answer was " + rand + " !!", "Info",
                        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon4, null, 0);
                correctGuess = 0;
            }
            textField.setText("");
            rand = (int) (Math.random() * 100);
            count = 0;
            attempts = 0;
            correctGuess = 0;
        }
    }

    // for quiting the game
    private class ButtonListener3 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Create a custom action for the Enter key press
    Action enterAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent f) {
            ButtonListener action = new ButtonListener();
            action.actionPerformed(f);
        }
    };

}
