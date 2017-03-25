package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import java.util.List;

public interface BlogDao {
    boolean addEntry(BlogEntry blogEntry);
    void editEntry(String title, String entry, String slug);
    boolean deleteEntry(BlogEntry blogEntry);

    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);

    void addComment(Comment comment, String slug);
}
