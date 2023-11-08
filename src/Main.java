import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
        Main gui = new Main();
        gui.go();
    }
    
    public void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\andre\\AppData\\Local\\Microsoft\\Windows\\Fonts\\Renogare-Regular.otf")));
            
            panel.add(addLabel("Shape:", "Renogare Regular"));
            panel.add(addLabel("Fixed Radius"));
            JSlider bigR = addSlider(0, 160, 10, 20);
            panel.add(bigR);
    
            panel.add(addLabel("Moving Radius"));
            JSlider littleR = addSlider(0, 80, 5, 10);
            panel.add(littleR);
    
            panel.add(addLabel("Offset"));
            JSlider o = addSlider(0, 25, 1, 5);
            panel.add(o);

            panel.add(addLabel("Loops"));
            JSlider loops = addSlider(0, 20, 1, 2);
            panel.add(loops);
    
            panel.add(addLabel("Colour:", "Renogare Regular"));
            panel.add(addLabel("Red"));
            JSlider red = addSlider(0, 255, 5, 15);
            panel.add(red);
    
            panel.add(addLabel("Green"));
            JSlider green = addSlider(0, 255, 5, 15);
            panel.add(green);
    
            panel.add(addLabel("Blue"));
            JSlider blue = addSlider(0, 255, 5, 15);
            panel.add(blue);
    
            panel.add(Box.createVerticalStrut(10));
            JButton goButton = new JButton("Go!");
            goButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(goButton);

            panel.add(Box.createVerticalStrut(10));
            JButton reset = new JButton("Reset");
            goButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(reset);
            
            panel.add(Box.createVerticalStrut(10));
            panel.setBackground(Color.WHITE);

            Canvas canvas = new Canvas();
            canvas.setBackground(Color.WHITE);
            panel.add(canvas);
            frame.add(panel);
            
            frame.setSize(800, 900);
            frame.setVisible(true);
            
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
            goButton.addActionListener(e -> canvas.paintComponents(bigR.getValue(), littleR.getValue(), o.getValue(), red.getValue(), green.getValue(), blue.getValue(), loops.getValue()));
            reset.addActionListener(e -> canvas.clear());
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public JSlider addSlider(int min, int max, int space, int labelSpace) {
        JSlider slider = new JSlider(min,max);
        slider.setMajorTickSpacing(space);
        slider.setPaintTicks(true);

        Hashtable<Integer,JLabel> labelTable = new Hashtable<>();
        int num = min;
        while(num <= max) {
            labelTable.put(num, new JLabel(Integer.toString(num)));
            num += labelSpace;
        }
        
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setBackground(Color.WHITE);
        return slider;
    }
    
    public JLabel addLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setBackground(Color.WHITE);
        return label;
    }

    public JLabel addLabel(String text, String font) {
        JLabel label = addLabel(text);
        label.setFont(new Font(font, Font.PLAIN, 20));
        label.setBackground(Color.WHITE);
        return label;
    }
}
