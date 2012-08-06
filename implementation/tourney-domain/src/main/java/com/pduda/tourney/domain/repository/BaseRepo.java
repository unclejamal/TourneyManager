package com.pduda.tourney.domain.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseRepo<T, ID extends Serializable> {

    void create(T object);
    
    T createAndReturn(T object);
    
    void refresh(T object);

    T merge(T object);

    void edit(T entity) throws RepoException;

    List findEntities();

    List findEntities(int maxResults, int firstResult);

    T findEntity(ID id);

    List<T> findEntitiesByIds(Collection<ID> ids);

    int getEntitiesCount();

    void destroy(ID id) throws RepoException;
}