package io.hardingadonis.feizh.dao.impl;

import io.hardingadonis.feizh.dao.*;
import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import io.hardingadonis.feizh.utils.*;
import java.sql.*;
import java.time.*;
import java.util.*;

public class SQLiteCategoryDAOImpl implements ICategoryDAO {

    private static Category getFromResultSet(ResultSet rs) throws SQLException {
        int ID = rs.getInt("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        CategoryType type = CategoryType.create(rs.getString("type"));

        LocalDateTime createAt = Converter.convert(rs.getString("create_at"));
        LocalDateTime updateAt = Converter.convert(rs.getString("update_at"));
        LocalDateTime deleteAt = Converter.convert(rs.getString("delete_at"));

        return new Category(ID, name, description, type, createAt, updateAt, deleteAt);
    }

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `category` WHERE `delete_at` IS NULL");

            ResultSet rs = smt.executeQuery();

            while (rs.next()) {
                list.add(getFromResultSet(rs));
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Category get(int ID) {
        Category category = null;

        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("SELECT * FROM `category` WHERE `id` = ? AND `delete_at` IS NULL");
            smt.setInt(1, ID);

            ResultSet rs = smt.executeQuery();

            if (rs.next()) {
                category = getFromResultSet(rs);
            }

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return category;
    }

    @Override
    public void insert(Category obj) {
        Connection conn = null;

        try {
            conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("INSERT INTO `category`(`name`, `description`, `type`, `create_at`) VALUES(?, ?, ?, ?)");

            smt.setString(1, obj.getName());
            smt.setString(2, obj.getDescription());
            smt.setString(3, obj.getType().toString());
            smt.setString(4, Converter.convert(LocalDateTime.now()));

            smt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            Singleton.dbContext.closeConnection(conn);
        }
    }

    @Override
    public void update(Category obj) {
        Connection conn = null;

        try {
            conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE `category` SET `name` = ?, `description` = ?, `type` = ?, `update_at` = ? WHERE `id` = ? AND `delete_at` IS NULL");

            smt.setString(1, obj.getName());
            smt.setString(2, obj.getDescription());
            smt.setString(3, obj.getType().toString());
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
        try {
            Connection conn = Singleton.dbContext.getConnection();

            PreparedStatement smt = conn.prepareStatement("UPDATE `category` SET `delete_at` = ? WHERE id = ? AND `delete_at` IS NULL");
            smt.setString(1, Converter.convert(LocalDateTime.now()));
            smt.setInt(2, ID);

            smt.executeUpdate();

            Singleton.dbContext.closeConnection(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
