package io.hardingadonis.feizh.context.impl;

import io.hardingadonis.feizh.context.*;
import java.sql.*;

public class SQLiteDBContextImpl implements IDBContext {

    @Override
    public Connection getConnection() {
        Connection connection = null;

        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());

            connection = DriverManager.getConnection("jdbc:sqlite:feizh_database.db");

            System.out.println("Connect SQLite successfully!");
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            this.closeConnection(connection);
        }

        return connection;
    }

    @Override
    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();

                System.out.println("Close SQLite successfully!");
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
}
