package pap2023z.z09.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class FoodButton extends JButton {
    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;
    public FoodButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);

        try {
            setBackground(new Color(10, 0, 21));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT));

        super.paintComponent(g2d);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
    }
}
