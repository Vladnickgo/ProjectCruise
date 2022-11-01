package com.vladnickgo.Project.controller.command.home.header;

import com.vladnickgo.Project.PagesConstant;
import com.vladnickgo.Project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AboutPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, List<String>> map=new HashMap<>();
        map.put("abc",new ArrayList<>(List.of("a","b","c")));
        String command = request.getParameter("command");
        request.setAttribute("command", command);
        request.setAttribute("map",map);
        return PagesConstant.ABOUT_PAGE;
    }

}
