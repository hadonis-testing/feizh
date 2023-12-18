package io.hardingadonis.feizh.controller;

import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.setAttribute("is_category", true);

        request.getRequestDispatcher("/view/category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add": {
                try {
                    String name = request.getParameter("name");
                    CategoryType type = CategoryType.create(request.getParameter("type"));
                    String description = request.getParameter("description");

                    Singleton.categoryDAO.insert(new Category(name, description, type));

                    response.setContentType("application/json");
                    response.getWriter().write("{\"status\":\"success\"}");

                    response.setStatus(HttpServletResponse.SC_OK);
                } catch (NumberFormatException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

                break;
            }

            case "delete": {
                try {
                    int ID = Integer.parseInt(request.getParameter("id"));

                    if (Singleton.categoryDAO.get(ID) != null) {
                        Singleton.categoryDAO.delete(ID);

                        response.setContentType("application/json");
                        response.getWriter().write("{\"status\":\"success\"}");

                        response.setStatus(HttpServletResponse.SC_OK);
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

                break;
            }
        }
    }
}
