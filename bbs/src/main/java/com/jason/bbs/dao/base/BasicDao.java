package com.jason.bbs.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016/8/11.
 */
public interface BasicDao<T> {
    /**
     * 把对象保存到持久层
     *
     * @param t
     * @return 若保存成功，则返回实体对象，否则返回null
     * @throws
     */
     T save(T t);

    /**
     * 更新持久层中的对象
     *
     * @param t
     * @return 若修改成功，则返回实体对象，否则返回null
     */
    T update(T t);

    /**
     * 删除持久层中的对象
     *
     * @param t
     * @return void
     */
    void delete(T t);

    /**
     * 根据类及主键加载对象
     *
     * @param clz
     * @param id
     * @return 若查找到指定主键值的持久对象，则返回该对象，否则返回null
     */
    T get(Class<T> clz, Serializable id);

    /**
     * 根据类、字段名及字段值加载对象，只加载一条符合条件的对象。
     * @param clz
     * @param fieldName
     * @param value
     * @return 若查询到指定属性及值的持久对象，则返回该对象，否则返回null
     */
    T getByField(Class<T> clz, String fieldName, Serializable value);

    /**
     * 查询符合条件的对象，从begin开始取max条记录
     * @param clz Java类
     * @param fieldsNameValue 查询条件
     * @param begin 返回有效结果开始记录数
     * @param max  返回的最多记录数
     * @return 返回查询的记录结果集
     */
    List<T> query(Class<T> clz, Map<String,Object> fieldsNameValue, int begin, int max);

    /**
     * @param clz
     * @param fieldsNameValue
     * @return 返回单一的查询结果，若没有结果则返回null
     */
    T uniqueQuery(Class<T> clz, Map<String,Object> fieldsNameValue);
}
