package dao;

import java.util.List;

public interface IDAO <T,K> {
    T getByKey(K key);
    List<T> getAll();
    boolean create(T entity);
    boolean deleteByKey(K key);
    boolean update(T entity);
}
