package com.vrucina.test.model.modelDTO;

import java.time.LocalDateTime;

public class PostDTO {
    private Long id;
    private String comment;
    private LocalDateTime date;
    private LocalDateTime updated;
    private UserDTO author;

    public PostDTO(Long id, String comment, LocalDateTime date, LocalDateTime updated, UserDTO author) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.updated = updated;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
