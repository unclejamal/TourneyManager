package com.pduda.tourney.domain.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseRepo<T, ID extends Serializable> {

    void persist(T object);
    
    T persistAndReturn(T object);
    
    void refresh(T object);

    T merge(T object);

    void edit(T entity) throws RepoException;

    List findEntities();

    List<T> findEntities(int maxResults, int firstResult);

    T findEntity(ID id);

    List<T> findEntitiesByIds(Collection<ID> ids);

    int getCount();

    void remove(ID id) throws RepoException;
}