package me.fuji97.Model;

public class Message {
    public String role; // "user" หรือ "model"
    public String text;

    public Message(String role, String text) {
        this.role = role;
        this.text = text;
    }
}