import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SierpinskiTriangleApp extends JPanel {

    private int recursionLevel = 0;
    private JComboBox<Integer> levelChoice;

    public SierpinskiTriangleApp() {
        levelChoice = new JComboBox<>();
        for (int i = 0; i <= 6; i++) {
            levelChoice.addItem(i);
        }
        levelChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recursionLevel = (int) levelChoice.getSelectedItem();
                repaint();
            }
        });
        setLayout(new BorderLayout());
        add(levelChoice, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int[] xPoints = {width / 2, width / 4, (3 * width) / 4};
        int[] yPoints = {height / 4, (3 * height) / 4, (3 * height) / 4};

        drawSierpinski(g, xPoints, yPoints, recursionLevel);
    }

    private void drawSierpinski(Graphics g, int[] xPoints, int[] yPoints, int level) {
        if (level == 0) {
            g.setColor(Color.BLUE);
            g.fillPolygon(xPoints, yPoints, 3);
        } else {
            int[] xMidPoints = {(xPoints[0] + xPoints[1]) / 2, (xPoints[1] + xPoints[2]) / 2, (xPoints[2] + xPoints[0]) / 2};
            int[] yMidPoints = {(yPoints[0] + yPoints[1]) / 2, (yPoints[1] + yPoints[2]) / 2, (yPoints[2] + yPoints[0]) / 2};

            drawSierpinski(g, new int[]{xPoints[0], xMidPoints[0], xMidPoints[2]}, new int[]{yPoints[0], yMidPoints[0], yMidPoints[2]}, level - 1);
            drawSierpinski(g, new int[]{xMidPoints[0], xPoints[1], xMidPoints[1]}, new int[]{yMidPoints[0], yPoints[1], yMidPoints[1]}, level - 1);
            drawSierpinski(g, new int[]{xMidPoints[2], xMidPoints[1], xPoints[2]}, new int[]{yMidPoints[2], yMidPoints[1], yPoints[2]}, level - 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Triángulo de Sierpinski");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.add(new SierpinskiTriangleApp());
            frame.setVisible(true);
        });
    }
}
