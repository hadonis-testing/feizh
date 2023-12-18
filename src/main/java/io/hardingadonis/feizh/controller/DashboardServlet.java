package io.hardingadonis.feizh.controller;

import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import org.json.simple.*;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.setAttribute("is_dashboard", true);

        request.getRequestDispatcher("/view/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        
        String duration = request.getParameter("duration");
        
        JSONObject json = new JSONObject();
        
        json.put("total-expenses", Singleton.transactionDAO.totalByDuration(TransactionType.EXPENSE, duration));
        json.put("total-incomes", Singleton.transactionDAO.totalByDuration(TransactionType.INCOME, duration));
        
        json.put("expense-by-category", Singleton.transactionDAO.totalByCategory(TransactionType.EXPENSE, duration));
        json.put("income-by-category", Singleton.transactionDAO.totalByCategory(TransactionType.INCOME, duration));
        
        response.getWriter().write(json.toJSONString());
    }
}
