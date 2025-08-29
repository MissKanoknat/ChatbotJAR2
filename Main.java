package me.fuji97;

import com.formdev.flatlaf.FlatDarkLaf;
import me.fuji97.LoginUI.LoginUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            //Lib FlatLaf เอาไว้เปลี่ยน Theme GUI
            UIManager.setLookAndFeel(new FlatDarkLaf());
            // เรียกใช้ JFrame //GUI
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Login");
                frame.setContentPane(new LoginUI().getBody());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            });
        } catch (Exception ex) {
            //แสดง Error MSG
            System.err.println("Error: " + ex.getMessage());
        }

    }
}
