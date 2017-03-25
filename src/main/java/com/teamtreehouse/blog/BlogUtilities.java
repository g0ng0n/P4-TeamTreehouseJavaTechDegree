package com.teamtreehouse.blog;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.Request;
import spark.Response;

import static spark.Spark.halt;

/**
 * Created by gonzalo.gisbert on 25/03/17.
 */
public class BlogUtilities {



    public static void checkPassword(Request req, Response res) {
        if(req.attribute("password") == null || !req.attribute("password").equals("admin")){
            res.redirect("/sign-in");
            halt();
        }
    }


    public static void createDummyData(BlogDao dao) {

        BlogEntry blogEntry = new BlogEntry("New Entry","Batman",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,");
        blogEntry.addComment(new Comment("Robin", "this is my first comment."));
        dao.addEntry(blogEntry);

        BlogEntry blogEntry2 = new BlogEntry("Another Kind of Entry","Superman",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,");
        blogEntry.addComment(new Comment("AquaMan", "Water everywhere"));
        dao.addEntry(blogEntry2);

    }
}
