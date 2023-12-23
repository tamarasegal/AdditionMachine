import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class ActionListener1 implements ActionListener {
    private AdditionMachine machine;
    public ActionListener1(AdditionMachine x) {
        machine = x;
    }
    public AdditionMachine getMachine() {
        return machine;
    };
    public void actionPerformed(ActionEvent e) {
    return;
    }
}
