package com.solvd.db.utils;

import java.util.List;

public interface GenericDAO<T> {
    boolean create(T t);
    T getById(long id);
    List<T> getAll();
    boolean update(T t);
    boolean delete(long id);
}
