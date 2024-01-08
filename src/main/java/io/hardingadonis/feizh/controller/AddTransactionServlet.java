package io.hardingadonis.feizh.controller;

import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(
    name = "AddTransactionServlet",
    urlPatterns = {"/add-transaction"})
public class AddTransactionServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");

    request.setAttribute("is_transaction", true);

    request.getRequestDispatcher("/view/add-transaction.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");

    TransactionType type = TransactionType.create(request.getParameter("type"));

    int sourceWalletID = Integer.parseInt(request.getParameter("source-wallet"));
    long amount = Long.parseLong(request.getParameter("amount"));
    String description = request.getParameter("description");

    switch (type) {
      case INCOME:
        {
          int categoryID = Integer.parseInt(request.getParameter("category"));

          Singleton.incomeTransactionDAO.insert(
              new IncomeTransaction(sourceWalletID, amount, description, type, categoryID));

          Wallet wallet = Singleton.walletDAO.get(sourceWalletID);
          wallet.setBalance(wallet.getBalance() - amount);
          Singleton.walletDAO.update(wallet);

          break;
        }

      case EXPENSE:
        {
          int categoryID = Integer.parseInt(request.getParameter("category"));

          Singleton.expenseTransactionDAO.insert(
              new ExpenseTransaction(sourceWalletID, amount, description, type, categoryID));

          Wallet wallet = Singleton.walletDAO.get(sourceWalletID);
          wallet.setBalance(wallet.getBalance() - amount);
          Singleton.walletDAO.update(wallet);
          break;
        }

      case TRANSFER:
        {
          int targetWalletID = Integer.parseInt(request.getParameter("target-wallet"));

          Singleton.transferTransactionDAO.insert(
              new TransferTransaction(sourceWalletID, amount, description, type, targetWalletID));

          Wallet sourceWallet = Singleton.walletDAO.get(sourceWalletID);
          Wallet targetWallet = Singleton.walletDAO.get(targetWalletID);
          sourceWallet.setBalance(sourceWallet.getBalance() - amount);
          targetWallet.setBalance(targetWallet.getBalance() + amount);

          Singleton.walletDAO.update(sourceWallet);
          Singleton.walletDAO.update(targetWallet);
          break;
        }
    }

    response.sendRedirect("transaction");
  }
}
