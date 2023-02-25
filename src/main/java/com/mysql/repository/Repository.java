package com.mysql.repository;

import java.util.List;

public interface Repository<E, I> {

    E add(E entity);

    E get(I id);

    void remove(I id);

    E edit(E entity);

    List<E> getAll();

    void removeAll();

    List<E> addAll(List<E> entities);

    List<E> editAll(List<E> entities);

    void removeAll(List<I> ids);

}
