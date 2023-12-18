package io.hardingadonis.feizh.dao;

import io.hardingadonis.feizh.model.*;
import io.hardingadonis.feizh.model.detail.*;
import java.util.*;

public interface ICategoryDAO {

    public List<Category> getAll();
    
    public List<Category> getAll(CategoryType type);

    public Category get(int ID);

    public void insert(Category obj);

    public void update(Category obj);

    public void delete(int ID);
}
