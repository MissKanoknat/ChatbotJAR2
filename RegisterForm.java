package me.fuji97.RegisterUI;

import me.fuji97.LoginUI.LoginUI;

import javax.swing.*;
import java.awt.*;

public class RegisterForm extends JFrame {

    public RegisterForm() {

        RegisterUI registerUI = new RegisterUI();

        setTitle("ChatBOT");
        setSize(400, 350);
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setResizable(false);
        setContentPane(registerUI.getBody());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
