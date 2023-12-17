package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLiteTransactionDAOImpl implements ITransactionDAO {

    private static Transaction getFromResultSet(ResultSet rs) throws SQLException {
        int ID = rs.getInt("id");
        int sourceWalletID = rs.getInt("source_wallet_id");
        long amount = rs.getLong("amount");
        String description = rs.getString("description");
        TransactionType type = TransactionType.create(rs.getString("type"));

        LocalDateTime createAt = Converter.convert(rs.getString("create_at"));

        return new Transaction(ID, sourceWalletID, amount, description, type, createAt);
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> list = new ArrayList<>();

        Connection conn = Singleton.dbContext.getConnection();

        try {
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `transaction` ORDER BY `id` DESC");

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
    public Transaction get(int ID) {
        Transaction transaction = null;

        Connection conn = Singleton.dbContext.getConnection();

        try {
            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `transaction` WHERE `id` = ?");
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
    public Connection insert(Transaction obj, boolean isContinue) {
        Connection conn = Singleton.dbContext.getConnection();
        
        try {
            PreparedStatement smt = conn.prepareStatement("INSERT INTO `transaction`(`source_wallet_id`, `amount`, `description`, `type`, `create_at`) VALUES(?, ?, ?, ?, ?)");

            smt.setInt(1, obj.getSourceWalletID());
            smt.setLong(2, obj.getAmount());
            smt.setString(3, obj.getDescription());
            smt.setString(4, obj.getType().toString());
            smt.setString(5, Converter.convert(LocalDateTime.now()));
            
            smt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            if (!isContinue) {
                Singleton.dbContext.closeConnection(conn);

                return null;
            } else {
                return conn;
            }
        }
    }
}
