package com.example.shifaa;

public class Note {
    private String text;
    private String title;

    public Note() {
    }

    public Note(String text, String title) {
        this.text = text;
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Note " +
                "\nTitle = " + title + "\nText = " + text + '\'';
    }
}
