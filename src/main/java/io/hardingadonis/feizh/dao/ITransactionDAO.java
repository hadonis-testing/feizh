package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import java.sql.*;
import java.util.*;
import org.json.simple.*;

public interface ITransactionDAO {

    public List<Transaction> getAll();

    public Transaction get(int ID);

    public Connection insert(Transaction obj, boolean isContinue);
    
    public JSONObject totalByDuration(TransactionType type, String duration);
    
    public JSONObject totalByCategory(TransactionType type, String duration);
}
