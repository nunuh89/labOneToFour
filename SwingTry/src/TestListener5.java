import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestListener5 extends JFrame
{
    JButton b;

    public TestListener5()
    {
/*  A Button is created and registers an ActionListener         */
/*  The addActionListener() method is passed a reference to an  */
/*  ActionListener object.  The ActionListener is implemented   */
/*  as an anonymous inner class.  The entire anonymous inner    */
/*  class is passed as an argument to the addActionListener()   */
/*  method.                                                     */

        b = new JButton("quit");
        b.addActionListener(e -> System.exit(0));

        add(b);
        setLayout(new FlowLayout());
        setBounds(100, 100, 200, 200);
        setVisible(true);
    }

    public static void main(String args[])
    {
        TestListener5 tl = new TestListener5();
    }
}
