package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.util.*;

public interface ITransactionDAO {

    public List<Transaction> getAll();

    public Transaction get(int ID);

    public void insert(Transaction obj);

    public void update(Transaction obj);
}
