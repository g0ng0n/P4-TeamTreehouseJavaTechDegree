package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.util.List;

/**
 * Created by gonzalo.gisbert on 24/03/17.
 */
public class BlogDaoImpl implements BlogDao{

    @Override
    public boolean addEntry(BlogEntry blogEntry) {
        return false;
    }

    @Override
    public List<BlogEntry> findAllEntries() {
        return null;
    }

    @Override
    public BlogEntry findEntryBySlug(String slug) {
        return null;
    }
}
