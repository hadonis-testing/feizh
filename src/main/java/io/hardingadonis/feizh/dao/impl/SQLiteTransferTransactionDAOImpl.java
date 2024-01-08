package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLiteTransferTransactionDAOImpl implements ITransferTransactionDAO {

  private static TransferTransaction getFromResultSet(ResultSet rs) throws SQLException {
    int ID = rs.getInt("id");
    int sourceWalletID = rs.getInt("source_wallet_id");
    long amount = rs.getLong("amount");
    String description = rs.getString("description");
    TransactionType type = TransactionType.create(rs.getString("type"));
    int targetWalletID = rs.getInt("target_wallet_id");

    LocalDateTime createAt = Converter.convert(rs.getString("create_at"));

    return new TransferTransaction(
        ID, sourceWalletID, amount, description, type, targetWalletID, createAt);
  }

  @Override
  public List<TransferTransaction> getAll() {
    List<TransferTransaction> list = new ArrayList<>();

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement("SELECT * FROM `transaction` NATURAL JOIN `transfer_detail`");

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
  public TransferTransaction get(int ID) {
    TransferTransaction transaction = null;

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "SELECT * FROM `transaction` NATURAL JOIN `transfer_detail` WHERE `id` = ?");
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
  public void insert(TransferTransaction obj) {
    Connection conn = Singleton.transactionDAO.insert(obj, true);

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "INSERT INTO `transfer_detail`(`id`, `target_wallet_id`) VALUES(LAST_INSERT_ROWID(),"
                  + " ?)");

      smt.setInt(1, obj.getTargetWalletID());

      smt.executeUpdate();

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }
  }
}
