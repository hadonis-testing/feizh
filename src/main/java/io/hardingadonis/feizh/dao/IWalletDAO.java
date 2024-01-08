package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import java.util.*;

public interface IWalletDAO {

  public List<Wallet> getAll();

  public List<Wallet> getRecentUpdates();

  public Wallet get(int ID);

  public void insert(Wallet obj);

  public void update(Wallet obj);

  public void delete(int ID);
}
