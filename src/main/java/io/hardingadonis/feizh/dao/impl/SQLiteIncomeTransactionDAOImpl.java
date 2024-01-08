package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLiteIncomeTransactionDAOImpl implements IIncomeTransactionDAO {

  private static IncomeTransaction getFromResultSet(ResultSet rs) throws SQLException {
    int ID = rs.getInt("id");
    int sourceWalletID = rs.getInt("source_wallet_id");
    long amount = rs.getLong("amount");
    String description = rs.getString("description");
    TransactionType type = TransactionType.create(rs.getString("type"));
    int categoryID = rs.getInt("category_id");

    LocalDateTime createAt = Converter.convert(rs.getString("create_at"));

    return new IncomeTransaction(
        ID, sourceWalletID, amount, description, type, categoryID, createAt);
  }

  @Override
  public List<IncomeTransaction> getAll() {
    List<IncomeTransaction> list = new ArrayList<>();

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement("SELECT * FROM `transaction` NATURAL JOIN `income_detail`");

      ResultSet rs = smt.executeQuery();

      while (rs.next()) {
        list.add(getFromResultSet(rs));
      }

    } catch (SQLException ex) {
      System.err.println(ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }

    return list;
  }

  @Override
  public IncomeTransaction get(int ID) {
    IncomeTransaction transaction = null;

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "SELECT * FROM `transaction` NATURAL JOIN `income_detail` WHERE `id` = ?");
      smt.setInt(1, ID);

      ResultSet rs = smt.executeQuery();

      if (rs.next()) {
        transaction = getFromResultSet(rs);
      }

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }

    return transaction;
  }

  @Override
  public void insert(IncomeTransaction obj) {
    Connection conn = Singleton.transactionDAO.insert(obj, true);

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "INSERT INTO `income_detail`(`id`, `category_id`) VALUES(LAST_INSERT_ROWID(), ?)");

      smt.setInt(1, obj.getCategoryID());

      smt.executeUpdate();

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }
  }
}
