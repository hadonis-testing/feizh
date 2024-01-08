package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.util.*;

public interface ITransferTransactionDAO {

  public List<TransferTransaction> getAll();

  public TransferTransaction get(int ID);

  public void insert(TransferTransaction obj);
}
