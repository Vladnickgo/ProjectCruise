package com.vladnickgo.Project.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet("/home")
public class HomeServlet extends AbstractServlet {
    public HomeServlet() {
        super("home", "defaultCommand");
    }

}