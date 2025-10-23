package model;

public class Book {
    private String title;
    private String authors;
    private String publishedDate;

    public Book(String title, String authors, String publishedDate) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
    }

    public String getTitle() { return title; }
    public String getAuthors() { return authors; }
    public String getPublishedDate() { return publishedDate; }

    @Override
    public String toString() {
        return "\n📘 Título: " + title +
                "\n✍ Autores: " + authors +
                "\n📅 Data: " + publishedDate +
                "\n---------------------------";
    }
}
