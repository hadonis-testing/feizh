package io.hardingadonis.feizh.controller;

import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "TransactionServlet", urlPatterns = {"/transaction"})
public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        request.setAttribute("is_transaction", true);

        request.getRequestDispatcher("/view/transaction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "detail": {
                TransactionType type = TransactionType.create(request.getParameter("type"));
                int ID = Integer.parseInt(request.getParameter("id"));

                response.setContentType("application/json");

                switch (type) {
                    case INCOME: {
                        IncomeTransaction transaction = Singleton.incomeTransactionDAO.get(ID);
                        Category category = Singleton.categoryDAO.get(transaction.getCategoryID());

                        response.getWriter().write("{\"status\":\"success\", \"category\":\"" + category.getName() + "\"}");

                        break;
                    }

                    case EXPENSE: {
                        ExpenseTransaction transaction = Singleton.expenseTransactionDAO.get(ID);
                        Category category = Singleton.categoryDAO.get(transaction.getCategoryID());

                        response.getWriter().write("{\"status\":\"success\", \"category\":\"" + category.getName() + "\"}");

                        break;
                    }

                    case TRANSFER: {
                        TransferTransaction transaction = Singleton.transferTransactionDAO.get(ID);
                        Wallet wallet = Singleton.walletDAO.get(transaction.getTargetWalletID());

                        response.getWriter().write("{\"status\":\"success\", \"wallet\":\"" + wallet.getName() + "\"}");

                        break;
                    }
                }

                response.setStatus(HttpServletResponse.SC_OK);

                break;
            }
        }
    }
}
