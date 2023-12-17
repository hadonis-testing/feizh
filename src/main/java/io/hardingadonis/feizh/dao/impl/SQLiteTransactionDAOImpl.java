package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import org.json.simple.*;

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

    @Override
    public JSONObject totalByDuration(TransactionType type, String duration) {
        JSONObject json = new JSONObject();

        Connection conn = Singleton.dbContext.getConnection();

        try {
            String sql = null;

            switch (duration) {
                case "today": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 10) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` = DATE('now') GROUP BY `date`;";
                    break;
                }

                case "yesterday": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 10) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` = DATE('now', '-1 days') GROUP BY `date`;";
                    break;
                }

                case "1week": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 10) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` >= DATE('now', '-6 days') GROUP BY `date` ORDER BY `date`;";
                    break;
                }

                case "1month": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 10) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` >= DATE('now', '-1 month') GROUP BY `date` ORDER BY `date`;";
                    break;
                }

                case "3months": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 7) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` >= DATE('now', '-3 months') GROUP BY `date` ORDER BY `date`;";
                    break;
                }

                case "6months": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 7) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` >= DATE('now', '-6 months') GROUP BY `date` ORDER BY `date`;";
                    break;
                }

                case "1year": {
                    sql = "SELECT SUBSTR(`create_at`, 1, 7) AS `date`, SUM(`amount`) AS `total`  FROM `transaction` WHERE `type` = ? AND `date` >= DATE('now', '-1 year') GROUP BY `date` ORDER BY `date`;";
                    break;
                }
            }

            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, type.toString());

            ResultSet rs = smt.executeQuery();

            JSONArray labels = new JSONArray();
            JSONArray dataset = new JSONArray();

            while (rs.next()) {
                String label = rs.getString(1);
                long data = rs.getLong(2);

                labels.add(label);
                dataset.add(data);
            }

            json.put("labels", labels);
            json.put("dataset", dataset);

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            Singleton.dbContext.closeConnection(conn);
        }

        return json;
    }

    @Override
    public JSONObject totalByCategory(TransactionType type, String duration) {
        JSONObject json = new JSONObject();

        Connection conn = Singleton.dbContext.getConnection();

        try {
            String sql = null;

            switch (duration) {
                case "today": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 10) = DATE('now') GROUP BY `w`.`id`;";
                    break;
                }

                case "yesterday": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 10) = DATE('now', '-1 days') GROUP BY `w`.`id`;";
                    break;
                }

                case "1week": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 10) >= DATE('now', '-6 days') GROUP BY `w`.`id`;";
                    break;
                }

                case "1month": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 7) >= DATE('now', '-1 month') GROUP BY `w`.`id`;";
                    break;
                }

                case "3months": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 7) >= DATE('now', '-3 months') GROUP BY `w`.`id`;";
                    break;
                }

                case "6months": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 7) >= DATE('now', '-6 months') GROUP BY `w`.`id`;";
                    break;
                }

                case "1year": {
                    sql = "SELECT `w`.`name`, SUM(`t`.`amount`) AS `total` FROM `transaction` `t` JOIN `wallet` `w` ON `t`.`source_wallet_id` = `w`.`id` WHERE `t`.`type` = ? AND SUBSTR(`t`.`create_at`, 1, 7) >= DATE('now', '-1 year') GROUP BY `w`.`id`;";
                    break;
                }
            }

            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, type.toString());

            ResultSet rs = smt.executeQuery();

            JSONArray labels = new JSONArray();
            JSONArray dataset = new JSONArray();

            while (rs.next()) {
                String label = rs.getString(1);
                long data = rs.getLong(2);

                labels.add(label);
                dataset.add(data);
            }

            json.put("labels", labels);
            json.put("dataset", dataset);

        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            Singleton.dbContext.closeConnection(conn);
        }

        return json;
    }
}
