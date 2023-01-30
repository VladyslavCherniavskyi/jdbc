package com.mysql.repository;

import java.util.List;

public interface Repository<E, I> {

    E get(I id);

    List<E> getAll();

}
