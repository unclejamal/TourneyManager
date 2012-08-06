package com.pduda.tourney.domain.repository.jpa;

import com.pduda.tourney.domain.repository.BaseRepo;
import com.pduda.tourney.domain.repository.RepoException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public abstract class BaseJpaRepo<T, ID extends Serializable> implements BaseRepo<T, ID> {

    private Class<T> persistentClass = null;

    public BaseJpaRepo() {
        Class clazz = getClass();
        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType)) {
            clazz = clazz.getSuperclass();
        }
        persistentClass = (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract EntityManager getEntityManager();

    public void create(T object) {
        getEntityManager().persist(object);
    }

    public T createAndReturn(T object) {
        getEntityManager().persist(object);
        return object;
    }

    public void refresh(T object) {
        getEntityManager().refresh(object);
    }

    public T merge(T object) {
        return getEntityManager().merge(object);
    }

    protected List findEntities(boolean all, int maxResults, int firstResult) {

        Query q = getEntityManager().createQuery("select object(o) from " + persistentClass.getSimpleName() + " as o");
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public List findEntities() {
        return findEntities(true, -1, -1);
    }

    public List findEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    public T findEntity(ID id) {
        return getEntityManager().find(persistentClass, id);
    }

    public List<T> findEntitiesByIds(Collection<ID> ids) {
        Query query = getEntityManager().createQuery("select e from " + persistentClass.getSimpleName()
                + " e where e.id in (:ids)");
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    public int getEntitiesCount() {
        return ((Long) getEntityManager().createQuery("select count(o) from " + persistentClass.getSimpleName() + " as o").getSingleResult()).intValue();
    }

    public void destroy(ID id) throws RepoException {
        EntityManager em = getEntityManager();

        try {
            T entity = em.getReference(persistentClass, id);
            em.remove(entity);
            em.flush();

        } catch (EntityNotFoundException ex) {
            throw new RepoException(ex);
        } catch (OptimisticLockException ex) {
            throw new RepoException(ex);
        } catch (PersistenceException ex) {
            throw new RepoException(ex);
        }
    }

    public void edit(T entity) throws RepoException {

        try {
            final EntityManager em = getEntityManager();
            entity = em.merge(entity);
        } catch (IllegalArgumentException ex) {
            throw new RepoException(ex);
        }
    }
}