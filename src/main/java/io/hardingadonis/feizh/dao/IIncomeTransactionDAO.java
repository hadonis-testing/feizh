package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.util.*;

public interface IIncomeTransactionDAO {

  public List<IncomeTransaction> getAll();

  public IncomeTransaction get(int ID);

  public void insert(IncomeTransaction obj);
}
