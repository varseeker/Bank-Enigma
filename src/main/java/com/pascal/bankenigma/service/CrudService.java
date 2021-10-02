package com.pascal.bankenigma.service;

import java.util.List;

public interface CrudService<T> {
    public T getDataById(String id);
    public List<T> getAllData();
    public T createData(T t);
    public void deleteData(String id);
    public T updateData(T t);
}
