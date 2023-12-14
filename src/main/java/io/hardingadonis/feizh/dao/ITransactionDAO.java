package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.sql.*;
import java.util.*;

public interface ITransactionDAO {

    public List<Transaction> getAll();

    public Transaction get(int ID);

    public Connection insert(Transaction obj, boolean isContinue);
}
