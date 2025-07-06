package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;

import static java.lang.String.format;

public class CommentImpl implements Comment {

    public static final int CONTENT_LEN_MIN = 3;
    public static final int CONTENT_LEN_MAX = 200;
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);

    private String content;
    private String author;

    public CommentImpl(String content, String author) {
        setContent(content);
        setAuthor(author);
    }

    public void setContent(String content) {
        if (content == null || content.trim().length() < CONTENT_LEN_MIN || content.trim().length() > CONTENT_LEN_MAX) {
            throw new IllegalArgumentException(CONTENT_LEN_ERR);
        }
        this.content = content.trim();
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty!");
        }
        this.author = author.trim();
    }

    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}