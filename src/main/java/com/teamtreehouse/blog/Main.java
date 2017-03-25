package com.teamtreehouse.blog;

/**
 * Created by gonzalo.gisbert on 24/03/17.
 */
import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.BlogDaoImpl;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by gonzalo.gisbert on 21/03/17.
 */
public class Main {

    private static final String FLASH_MESSAGE_KEY = "flash_message";

    public static void main(String[] args) {
        staticFileLocation("/public");

        BlogDao dao = new BlogDaoImpl();
        BlogUtilities.createDummyData(dao);

        //Filters
        before((req, res) -> {
            if (req.cookie("password") != null){
                req.attribute("password",req.cookie("password"));
            }
        });
        before("/new", (req, res) -> {
            BlogUtilities.checkPassword(req, res);
        });

        before("/entries/:slug/delete", (req, res) -> {
            BlogUtilities.checkPassword(req, res);
        });

        before("/entries/:slug/edit", (req, res) -> {
            BlogUtilities.checkPassword(req, res);
        });

        //-- ROUTES

        get("/", (req, res) -> {
           Map<String, Object> model = new HashMap<>();
           model.put("entries", dao.findAllEntries());

           return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sign-in", (req, res)->{

            return new ModelAndView(null, "password.hbs");
        }, new HandlebarsTemplateEngine());

        post("/sign-in", (req, res) -> {
            Map<String, String> model = new HashMap<>();
            model.put("password", req.queryParams("password"));
            String password = req.queryParams("password");
            res.cookie("password", password);
            res.redirect("/");
            return null;
        });

        get("/new", (req, res) -> {
            return new ModelAndView(null, "new.hbs");
        }, new HandlebarsTemplateEngine());

        post("/new", (req, res) -> {
            String title = req.queryParams("title");
            BlogEntry blogEntry = new BlogEntry(title, req.attribute("username"), req.queryParams("entry"));

            dao.addEntry(blogEntry);

            res.redirect("/");
            return null;
        });

        get("/entries/:slug/details", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findEntryBySlug(req.params("slug")));
            return new ModelAndView(model, "details.hbs");
        }, new HandlebarsTemplateEngine());

        get("/entries/:slug/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("blogEntry", dao.findEntryBySlug(req.params("slug")));
            return new ModelAndView(model, "edit.hbs");
        }, new HandlebarsTemplateEngine());

        post("/entries/:slug/edit", (req, res) -> {

            dao.editEntry(req.queryParams("title"), req.queryParams("entry"), req.params("slug"));
            res.redirect("/");
            return null;
        });


        post("/entries/:slug/comments", (req, res) -> {
            String commentAuthor = req.queryParams("name");
            String commentText = req.queryParams("comment");
            Comment comment = new Comment(commentAuthor, commentText);
            dao.addComment(comment,req.params("slug"));
            res.redirect("/entries/" + req.params("slug") + "/details");
            return null;
        });

        get("/entries/:slug/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("entry", dao.findEntryBySlug(request.params("slug")));
            return new ModelAndView(model, "delete.hbs");
        }, new HandlebarsTemplateEngine());

        post("/entries/:slug/delete", (request, response) -> {
            BlogEntry entry = dao.findEntryBySlug(request.params("slug"));
            dao.deleteEntry(entry);
            response.redirect("/");
            return null;
        });



    }

}