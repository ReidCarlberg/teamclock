/*
 * Created on May 14, 2005 by Reid
 */
package com.fivesticks.time.common.dao;

import java.util.Collection;
import java.util.List;

/**
 * @author Reid
 */
public interface GenericDAO {

    public static final String SPRING_BEAN_NAME="genericDao";
    
    public Object get(Class objectClazz, Long id);
    
    public Object get(Long id);
    
    public void save(Object target);
    
    public void saveAll(Collection target);
    
    public void delete(Object target);
    
    public void deleteAll(Collection target);
    
    public Collection find(CriteriaParameters parameters);
    
    public Collection getAll();
    
    public void setCriteriaBuilder(CriteriaDecorator criteriaBuilder);
    
    public CriteriaDecorator getCriteriaBuilder();
    
    public int execute(String hql);
    
    public int execute(String hql, Object objectWithUpdateFields);
    
    public List find(String hql);
    
    public List find(String hql, Object objectWithUpdateFields);
    
}
