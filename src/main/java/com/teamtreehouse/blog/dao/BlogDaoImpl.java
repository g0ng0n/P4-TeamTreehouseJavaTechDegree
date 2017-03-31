package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.exceptions.NotFoundException;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import java.util.ArrayList;
import java.util.List;


public class BlogDaoImpl implements BlogDao{

    private List<BlogEntry> entries;

    public BlogDaoImpl() {
        this.entries = new ArrayList<>();
    }

    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        return entries.add(blogEntry);
    }

    @Override
    public void editEntry(String title, String entry, String slug) {
        BlogEntry blogEntryUpdated = new BlogEntry(title, "user", entry);
        BlogEntry blogEntryToEdit = findEntryBySlug(slug);

        entries.set(entries.indexOf(blogEntryToEdit), blogEntryUpdated );
    }

    @Override
    public boolean deleteEntry(BlogEntry blogEntry) {
        return false;
    }


    @Override
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<>(entries);
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {

        return entries.stream().filter(blogEntry -> blogEntry.getSlug().equals(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void addComment(Comment comment, String slug) {
        BlogEntry entry = findEntryBySlug(slug);

        entry.addComment(comment);
        entries.set(entries.indexOf(entry), entry );
    }
}
