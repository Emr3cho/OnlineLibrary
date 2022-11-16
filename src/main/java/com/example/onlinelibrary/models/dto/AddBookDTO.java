package com.example.onlinelibrary.models.dto;

public class AddBookDTO {
    private String title;
    private String description;
    private String pictureURL;
    private String genre;
    private String[] authors;

    public AddBookDTO(String title, String description, String pictureURL, String genre, String[] authors) {
        this.title = title;
        this.description = description;
        this.pictureURL = pictureURL;
        this.genre = genre;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }
}
