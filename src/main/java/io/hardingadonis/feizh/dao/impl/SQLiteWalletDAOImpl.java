package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLiteWalletDAOImpl implements IWalletDAO {

  private static Wallet getFromResultSet(ResultSet rs) throws SQLException {
    int ID = rs.getInt("id");
    String name = rs.getString("name");
    WalletType type = WalletType.create(rs.getString("type"));
    long balance = rs.getLong("balance");

    LocalDateTime createAt = Converter.convert(rs.getString("create_at"));
    LocalDateTime updateAt = Converter.convert(rs.getString("update_at"));
    LocalDateTime deleteAt = Converter.convert(rs.getString("delete_at"));

    return new Wallet(ID, name, type, balance, createAt, updateAt, deleteAt);
  }

  @Override
  public List<Wallet> getAll() {
    List<Wallet> list = new ArrayList<>();

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement("SELECT * FROM `wallet` WHERE `delete_at` IS NULL");

      ResultSet rs = smt.executeQuery();

      while (rs.next()) {
        list.add(getFromResultSet(rs));
      }

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }

    return list;
  }

  @Override
  public List<Wallet> getRecentUpdates() {
    List<Wallet> list = new ArrayList<>();

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "SELECT * FROM `wallet` WHERE `delete_at` IS NULL ORDER BY `update_at` DESC LIMIT 3");

      ResultSet rs = smt.executeQuery();

      while (rs.next()) {
        list.add(getFromResultSet(rs));
      }

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }

    return list;
  }

  @Override
  public Wallet get(int ID) {
    Wallet wallet = null;

    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement("SELECT * FROM `wallet` WHERE `id` = ? AND `delete_at` IS NULL");
      smt.setInt(1, ID);

      ResultSet rs = smt.executeQuery();

      if (rs.next()) {
        wallet = getFromResultSet(rs);
      }

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }

    return wallet;
  }

  @Override
  public void insert(Wallet obj) {
    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "INSERT INTO `wallet`(`name`, `type`, `balance`, `create_at`) VALUES(?, ?, ?, ?)");

      smt.setString(1, obj.getName());
      smt.setString(2, obj.getType().toString());
      smt.setLong(3, obj.getBalance());
      smt.setString(4, Converter.convert(LocalDateTime.now()));

      smt.executeUpdate();

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }
  }

  @Override
  public void update(Wallet obj) {
    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "UPDATE `wallet` SET `name` = ?, `type` = ?, `balance` = ?, `update_at` = ? WHERE"
                  + " `id` = ? AND `delete_at` IS NULL");

      smt.setString(1, obj.getName());
      smt.setString(2, obj.getType().toString());
      smt.setLong(3, obj.getBalance());
      smt.setString(4, Converter.convert(LocalDateTime.now()));
      smt.setInt(5, obj.getID());

      smt.executeUpdate();

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }
  }

  @Override
  public void delete(int ID) {
    Connection conn = Singleton.dbContext.getConnection();

    try {
      PreparedStatement smt =
          conn.prepareStatement(
              "UPDATE `wallet` SET `delete_at` = ? WHERE id = ? AND `delete_at` IS NULL");
      smt.setString(1, Converter.convert(LocalDateTime.now()));
      smt.setInt(2, ID);

      smt.executeUpdate();

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      Singleton.dbContext.closeConnection(conn);
    }
  }
}
