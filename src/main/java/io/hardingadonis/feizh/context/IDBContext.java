package io.hardingadonis.feizh.context;

import java.sql.*;

public interface IDBContext {

    public Connection getConnection();

    public void closeConnection(Connection connection);
}
