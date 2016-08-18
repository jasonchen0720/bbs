package com.jason.bbs.dao.base;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Created by jason on 2016/8/11.
 */
public class BasicDaoImp<T> implements BasicDao<T> {
    private static Logger log = Logger.getLogger(BasicDaoImp.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public T save(T t) {
        if (null == t) {
            throw new RuntimeException("保存对象为空!");
        }
        return (T) em.merge(t);
    }

    @Transactional
    public T update(T t) {
        if (null == t) {
            throw new RuntimeException("更新对象为空!");
        }
        return (T) em.merge(t);
    }

    @Transactional
    public void delete(T t) {
        if (null == t) {
            throw new RuntimeException("删除的对象为空!");
        }
        try {
            if (em.contains(t)) {
                em.remove(t);
            } else {
                Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t);
                em.remove(em.find(t.getClass(), id));
            }
        } catch (Exception e) {
            throw new RuntimeException("删除对象时出错!");
        }
    }

    public T get(Class<T> clz, Serializable id) {
        return (T) em.find(clz, id);
    }

    public T getByField(Class<T> clz, String fieldName, Serializable fieldValue) {

        if (fieldValue instanceof String)
            fieldValue = "'" + fieldValue + "'";
        StringBuilder jpaSql = new StringBuilder("SELECT t FROM ").append(clz.getName()).append(" t WHERE ").append(fieldName).append(" = ").append(fieldValue);
        Query query = em.createQuery(jpaSql.toString());
        try {
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            log.error("NoResultException");
            return null;
        } catch (EntityNotFoundException e) {
            log.error("EntityNotFoundException");
            return null;
        }

    }

    public List<T> query(Class<T> clz, Map<String, Object> fieldsNameValue, int begin, int max) {
        StringBuilder jpaSql = new StringBuilder("SELECT t FROM ").append(clz.getName()).append(" t WHERE 1 = 1 ");
        fieldsNameValue.keySet().forEach((String field) -> {
                    Object fieldValue = fieldsNameValue.get(field);
                    if (fieldValue instanceof String) {
                        fieldValue = "'" + fieldValue + "'";
                    }
                    jpaSql.append(" AND ").append(field).append(" = ").append(fieldValue);
                }
        );
        Query query = em.createQuery(jpaSql.toString());
        if (begin >= 0 && max > 0) {
            query.setFirstResult(begin);
            query.setMaxResults(max);
        }
        return query.getResultList();
    }

    public T uniqueQuery(Class<T> clz, Map<String, Object> fieldsNameValue) {

        StringBuilder jpaSql = new StringBuilder("SELECT t FROM ").append(clz.getName()).append(" t WHERE 1 = 1 ");
        fieldsNameValue.keySet().forEach( field -> {
                    Object fieldValue = fieldsNameValue.get(field);
                    if (fieldValue instanceof String) {
                        fieldValue = "'" + fieldValue + "'";
                    }
                    jpaSql.append(" AND ").append(field).append(" = ").append(fieldValue);
                }
        );
        Query query = em.createQuery(jpaSql.toString());
        return (T) query.getSingleResult();
    }
}
