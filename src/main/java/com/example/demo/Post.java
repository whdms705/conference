package com.example.demo;

import org.apache.ibatis.annotations.One;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id @GeneratedValue
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public void addCommnet(Comment comment){
        this.getComments().add(comment);
        comment.setPost(this);
    }





    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
