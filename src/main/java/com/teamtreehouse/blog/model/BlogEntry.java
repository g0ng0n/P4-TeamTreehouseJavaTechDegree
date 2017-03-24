package com.teamtreehouse.blog.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlogEntry {

    private String title;
    private String creator;
    private Set<Comment> comments;
    private String entry;

    public BlogEntry(String title, String creator, String entry) {
        this.title = title;
        this.creator = creator;
        this.entry = entry;

        comments = new HashSet<>();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (title != null ? !title.equals(blogEntry.title) : blogEntry.title != null) return false;
        return creator != null ? creator.equals(blogEntry.creator) : blogEntry.creator == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    public boolean addComment(Comment comment) {
        // Store these comments!
        return comments.add(comment);
    }

    //Getters and setters


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    private List<Comment> getComments(){
        return new ArrayList<>(comments);

    }
}
