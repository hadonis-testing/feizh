package io.hardingadonis.feizh.context.impl;

import io.hardingadonis.feizh.context.*;
import java.io.*;
import java.sql.*;

public class SQLiteDBContextImpl implements IDBContext {

  private final String url;

  public SQLiteDBContextImpl() {
    this.url = System.getProperty("user.dir") + File.separator + "feizh_database.db";

    if (!new File(this.url).exists()) {
      this.createDatabase();
    }
  }

  @Override
  public Connection getConnection() {
    Connection connection = null;

    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());

      connection = DriverManager.getConnection("jdbc:sqlite:" + this.url);

      System.out.println("Info: Connect SQLite successfully!");
    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    }

    return connection;
  }

  @Override
  public void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();

        System.out.println("Info: Close SQLite successfully!");
      }
    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    }
  }

  private void createDatabase() {
    Connection connection = this.getConnection();

    try {
      String queryCreatingTable = this.loadScriptFromFile();
      Statement smt = connection.createStatement();

      smt.executeUpdate(queryCreatingTable);

      System.out.println("Info: Tables created successfully.");

    } catch (SQLException ex) {
      System.err.println("Error: " + ex.getMessage());
    } finally {
      this.closeConnection(connection);
    }
  }

  private String loadScriptFromFile() {
    StringBuilder script = new StringBuilder();

    try {

      BufferedReader reader =
          new BufferedReader(
              new InputStreamReader(
                  SQLiteDBContextImpl.class.getResourceAsStream("/database/setup.sql")));

      String line;
      while ((line = reader.readLine()) != null) {
        script.append(line).append("\n");
      }

    } catch (IOException ex) {
      System.err.println("Error: " + ex.getMessage());
    }

    return script.toString();
  }
}
