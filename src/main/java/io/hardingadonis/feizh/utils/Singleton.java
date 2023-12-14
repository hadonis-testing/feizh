package io.hardingadonis.feizh.utils;

import io.hardingadonis.feizh.context.*;
import io.hardingadonis.feizh.context.impl.*;

public class Singleton {

    public static IDBContext dbContext;
    
    static {
        dbContext = new SQLiteDBContextImpl();
    }
}
