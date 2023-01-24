package myhull2;

import javax.swing.*;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MouseEvents extends JPanel {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int origin_x=screenSize.width/8;
    private int origin_y=screenSize.width/8;


    private int HEIGHT = 500;
    private boolean button_click;
    private int WIDTH = 400;
    private int x, y;
    private ArrayList<Integer[]> clicks;

    private JFrame frame;

    private int offset;

    public MouseEvents() {
        clicks = new ArrayList<Integer[]>();
        offset=1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.drawLine(0,origin_y,screenSize.width/2,origin_y);
        g.setColor(Color.black);
        g.drawLine(origin_x,0,origin_x, 300);

        // Draw the click points

        if(button_click==false){
            for (Integer[] point : clicks) {
                g.setColor(Color.red);
                g.fillOval(point[0], point[1], 5, 5);
            }
        } else {
            ConvexHull myhull=new ConvexHull();
            List<Point> clickList=myhull.wrapClicks(clicks);
            List<Point> newHull=myhull.convexHull(clickList);
            Polygon p=myhull.wrapPolygon(newHull);
            for (Integer[] point : clicks) {
                g.setColor(Color.red);
                g.fillOval(point[0], point[1], 5, 5);
            }

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.blue);
            g2d.drawPolygon(p);

        }








        // Draw the cursor
        g.setColor(Color.blue);
        g.fillOval(x,y, 2, 2);
    }






    public void createAndShowGUI(){
        // Create the frame
        JFrame frame = new JFrame("Events Demo");
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.getContentPane().add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton colorButton= new JButton("Draw Convex Hull");
        colorButton.addActionListener(new colorListener());
        frame.getContentPane().add(colorButton,BorderLayout.NORTH);


        // Mouse click event
        frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                clicks.add(new Integer[] {me.getX() - 5, me.getY() - 5});
                repaint();
            }
        });

        // Mouse move event
        frame.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent me) {
                x = me.getX() - 10;
                y = me.getY() - 10;
                repaint();
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MouseEvents project = new MouseEvents();
                project.createAndShowGUI();
            }
        });
    }

    class colorListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            button_click=true;
            repaint();
        }

    }
}
