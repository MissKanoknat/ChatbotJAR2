package me.fuji97.chatUI;

import javax.swing.*;
import java.awt.*;

public class ChatForm extends JFrame {

    public ChatForm(String username) {

        ChatUI chatUI = new ChatUI(username); // ส่ง username

        setTitle("ChatBOT");
        setSize(700, 600);
        setFont(new Font("Tahoma", Font.PLAIN, 14));
        setResizable(false);
        setContentPane(chatUI.getBody());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
