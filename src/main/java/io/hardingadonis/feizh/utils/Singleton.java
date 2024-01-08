package io.hardingadonis.feizh.utils;

import io.hardingadonis.feizh.context.*;
import io.hardingadonis.feizh.context.impl.*;
import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.dao.impl.*;

public class Singleton {

  public static IDBContext dbContext;

  public static ICategoryDAO categoryDAO;

  public static IExpenseTransactionDAO expenseTransactionDAO;

  public static IIncomeTransactionDAO incomeTransactionDAO;

  public static ITransactionDAO transactionDAO;

  public static ITransferTransactionDAO transferTransactionDAO;

  public static IWalletDAO walletDAO;

  static {
    dbContext = new SQLiteDBContextImpl();

    categoryDAO = new SQLiteCategoryDAOImpl();

    expenseTransactionDAO = new SQLiteExpenseTransactionDAOImpl();

    incomeTransactionDAO = new SQLiteIncomeTransactionDAOImpl();

    transactionDAO = new SQLiteTransactionDAOImpl();

    transferTransactionDAO = new SQLiteTransferTransactionDAOImpl();

    walletDAO = new SQLiteWalletDAOImpl();
  }
}
