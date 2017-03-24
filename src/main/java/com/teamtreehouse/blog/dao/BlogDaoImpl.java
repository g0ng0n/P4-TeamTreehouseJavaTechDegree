package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalo.gisbert on 24/03/17.
 */
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
    public List<BlogEntry> findAllEntries() {
        return new ArrayList<>(entries);
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return null;
    }
}
