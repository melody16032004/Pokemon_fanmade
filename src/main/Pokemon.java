package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class Pokemon {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            GamePanel gamePanel = new GamePanel();
            JLabel text = new JLabel("Welcome to my Pokemon");
            text.setFont(new Font("power clear", Font.PLAIN, 30));
            text.setForeground(Color.RED);
            text.setHorizontalAlignment(SwingConstants.CENTER);

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(true);

            window.setTitle("Pokemon Fan make");
            window.getContentPane().add(text);
//            ImageIcon icon = new ImageIcon("/res/map/logo.png");
//            window.setIconImage(icon.getImage());
//            window.setIconImage(Toolkit.getDefaultToolkit().getImage());
            window.add(gamePanel);

//            window.getContentPane().add(text);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.startGameThread();
        });
    }

}
