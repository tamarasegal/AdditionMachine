import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdditionMachine {
    private JButton[] digits;
    private JLabel display;
    private JButton[] operators;
    private JFrame frame;
    private JPanel bottom;
    private ActionListener1 type;

    private int asValue1;
    private int asValue2;
    private int mdValue1;
    private int mdValue2;
    private String currentOperator;


    public AdditionMachine() {
        type = new ActionListener1(this) {
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                getMachine().setText(getMachine().getText() + source.getText());
            }
        };
        currentOperator = "a";
        display = new JLabel("");
        display.setFont(new Font("Monospaced", Font.PLAIN, 50));
        asValue1 = asValue2= 0;
        mdValue1 = mdValue2 = 1;
        operators = new JButton[6];
        operators[0] = new JButton("+");
        operators[0].addActionListener(new ActionListener1(this) {
            @Override public void actionPerformed(ActionEvent e) {
                getMachine().add();
                getMachine().setText("");
            }
        } );
        operators[1] = new JButton("-");
        operators[1].addActionListener(new ActionListener1(this) {
            @Override public void actionPerformed(ActionEvent e) {
                getMachine().subtract();
                getMachine().setText("");
            }
        } );
        operators[2] = new JButton("x");
        operators[2].addActionListener(new ActionListener1(this) {
            @Override public void actionPerformed(ActionEvent e) {
                getMachine().multiply();
                getMachine().setText("");
            }
        } );
        operators[3] = new JButton("/");
        operators[3].addActionListener(new ActionListener1(this) {
            @Override public void actionPerformed(ActionEvent e) {
                getMachine().divide();
                getMachine().setText("");
            }
        } );
        operators[4] = new JButton("=");
        operators[4].addActionListener(new ActionListener1(this) {
            public void actionPerformed(ActionEvent e) {
                int answer;
                String divide;
                if (getMachine().getOperator().equals("a")) {
                    answer = getMachine().add();
                    getMachine().setText(answer + "");
                }
                if (getMachine().getOperator().equals("s")) {
                    answer = getMachine().subtract();
                    getMachine().setText(answer + "");
                }
                if (getMachine().getOperator().equals("m")) {
                    answer = getMachine().multiply();
                    getMachine().setText(answer + "");
                }
                if (getMachine().getOperator().equals("d")) {
                    divide = getMachine().divide();
                    if (!divide.equals("no")) {
                        getMachine().setText(divide + "");
                        asValue1 = asValue2 = 0;
                        mdValue1 = mdValue2 = 1;
                    }
                    else
                        getMachine().setText("");
                }

            }
        });
        operators[5] = new JButton("clear");
        operators[5].addActionListener(new ActionListener1(this) {
            @Override public void actionPerformed(ActionEvent e) {
                    getMachine().setText("");
                    asValue1 = asValue2 = 0;
                    mdValue1 = mdValue2 = 1;
            }
        } );
        frame = new JFrame();
        frame.setSize(400, 600);
        frame.setLayout(new GridLayout(2, 1));
        bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        JPanel buttonsLeft = new JPanel();
        buttonsLeft.setLayout(new GridLayout(4, 3));
        JPanel buttonsRight = new JPanel();
        buttonsRight.setLayout(new GridLayout(6, 1));
        for (int i = 0; i < 6; i++) {
            buttonsRight.add(operators[i]);
        }
        digits = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = new JButton("" + i);
            buttonsLeft.add(digits[i]);
            digits[i].addActionListener(type);
        }
        frame.add(display);
        bottom.add(buttonsLeft);
        bottom.add(buttonsRight);
        frame.add(bottom);
        bottom.setVisible(true);
        frame.setVisible(true);

    }

    public String getText() {
        return display.getText();
    }
    public void setText(String text) {
        display.setText(text);
    }

    public void aSet() {
        asValue2 = asValue1;
        if (display.getText().equals(""))
            asValue1 = 0;
        else
            asValue1 = Integer.parseInt(display.getText());
    }
    public void sSet() {
        asValue1 = asValue2;
        if (display.getText().equals(""))
            asValue1 = 0;
        else
            asValue1 = Integer.parseInt(display.getText());
    }
    public void mSet() {
        mdValue2 = mdValue2;
        if (display.getText().equals(""))
            mdValue1 = 1;
        else
            mdValue1 = Integer.parseInt(display.getText());

    }
    public void dSet() {
        mdValue2 = mdValue1;
        if (display.getText().equals(""))
            mdValue1 = 1;
        else
            mdValue1 = Integer.parseInt(display.getText());

    }
    public int add() {
        aSet();
        currentOperator = "a";
        return asValue1 + asValue2;
    }
    public int subtract() {
        sSet();
        currentOperator = "s";
        return asValue2 - asValue1;
    }

    public int multiply() {
        mSet();
        currentOperator = "m";
        return mdValue1 * mdValue2;
    }
    public String divide() {
        dSet();
        currentOperator = "d";
        int answer;
        if (mdValue1 != 0) {
            mdValue2 /= mdValue1;
            return mdValue2 + "";
        }
        JOptionPane.showMessageDialog(frame, "You can't divide by zero!");
        setText("");
        return "no";
    }

    public String getOperator() {
        return currentOperator;
    }


}
