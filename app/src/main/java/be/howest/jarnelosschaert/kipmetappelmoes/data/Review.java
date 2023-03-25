package be.howest.jarnelosschaert.kipmetappelmoes.data;

import java.time.LocalDate;
import java.util.Date;

public class Review {
    private final int id;
    private final User user;
    private final String content;
    private final int rating;
    private final LocalDate date;

    public Review(int id, User user, String content, int rating, LocalDate date) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.rating = rating;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getDate() {
        return date;
    }
}
