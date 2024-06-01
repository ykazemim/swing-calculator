import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MyButton extends JButton {
    private static final Dimension defaultButtonSize = (new JButton("JAVA")).getPreferredSize();

    public MyButton(ActionListener actionListener) {
        addActionListener(actionListener);
        setFocusable(false);
        setPreferredSize(defaultButtonSize);
        setMinimumSize(defaultButtonSize);
    }

    public MyButton(String text,ActionListener actionListener) {
        setText(text);
        addActionListener(actionListener);
        setPreferredSize(defaultButtonSize);
        setMinimumSize(defaultButtonSize);
        setFocusable(false);
    }
}