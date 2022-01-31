import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.36
// 

class outputCalc
{
    TextField input;
    JLabel data1;
    JLabel data2;
    JLabel data3;
    JLabel grid1;
    JLabel grid2;
    JLabel grid3;
    JLabel grid4;
    
    public outputCalc() {
        this.input = new TextField(2);
        this.data1 = new JLabel("");
        this.data2 = new JLabel("");
        this.data3 = new JLabel("");
        this.grid1 = new JLabel("             The Square Root of:");
        this.grid2 = new JLabel("                    Is equal to:");
        this.grid3 = new JLabel("       Has the prime factors of:");
        this.grid4 = new JLabel("And in simplist radical form is:");
        final JFrame frame = new JFrame("Square Root Calculator");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("squareRootIcon.png"));
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new GridLayout(4, 2));
        frame.add(this.grid1);
        frame.add(this.input);
        frame.add(this.grid2);
        frame.add(this.data1);
        frame.add(this.grid3);
        frame.add(this.data2);
        frame.add(this.grid4);
        frame.add(this.data3);
        this.input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final RationalCalculator number = new RationalCalculator(Integer.parseInt(outputCalc.this.input.getText()));
                    if (number.getNum() <= 1 || !RationalCalculator.trueInt(number.getNum())) {
                        throw new NumberFormatException();
                    }
                    final String[] data = number.displayData();
                    outputCalc.this.data1.setText("" + data[0]);
                    outputCalc.this.data2.setText("" + data[1]);
                    outputCalc.this.data3.setText("" + data[2]);
                }
                catch (NumberFormatException a) {
                    outputCalc.this.data1.setText("Enter an intager greater than one");
                    outputCalc.this.data2.setText("Enter an intager greater than one");
                    outputCalc.this.data3.setText("Enter an intager greater than one");
                }
            }
        });
        frame.setVisible(true);
        frame.setResizable(false);
    }
}