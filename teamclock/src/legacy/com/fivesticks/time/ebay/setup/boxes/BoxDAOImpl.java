/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.ParamValidator;

/**
 * @author Reid
 */
public class BoxDAOImpl extends HibernateDaoSupport implements BoxDAO {

    public void save(Box target) {
        this.getHibernateTemplate().saveOrUpdate(target);
    }
    
    public Box get(Long id) {
        return (Box) this.getHibernateTemplate().get(Box.class, id);
    }
    
    public void delete(Box target) {
        this.getHibernateTemplate().delete(target);
    }
       
    public Collection find(final BoxCriteriaParameters params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(Box.class);

                //owner key
                if (ParamValidator.isSearchable(params.getOwnerKey())) {
                    criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
                }
                //id
                if (ParamValidator.isSearchable(params.getId())) {
                    criteria.add(Expression.eq("id", params.getId()));
                }

                //length
                if (ParamValidator.isSearchable(params.getLengthMinimum())) {
                    criteria.add(Expression.ge("length", params.getLengthMinimum()));
                } else if (ParamValidator.isSearchable(params.getLength())) {
                    criteria.add(Expression.eq("length", params.getLength()));
                }
                
                //width
                if (ParamValidator.isSearchable(params.getWidthMinimum())) {
                    criteria.add(Expression.ge("width", params.getWidthMinimum()));
                } else if (ParamValidator.isSearchable(params.getWidth())) {
                    criteria.add(Expression.eq("width", params.getWidth()));
                }
                                
                //height
                if (ParamValidator.isSearchable(params.getHeightMinimum())) {
                    criteria.add(Expression.ge("height", params.getHeightMinimum()));
                } else if (ParamValidator.isSearchable(params.getHeight())) {
                    criteria.add(Expression.eq("height", params.getHeight()));
                }
                
                //active
                if (ParamValidator.isSearchable(params.getActiveOnly())) {
                    criteria.add(Expression.eq("active", params.getActiveOnly()));
                }
                
                //instock
                if (ParamValidator.isSearchable(params.getInstockOnly())) {
                    criteria.add(Expression.gt("qtyOnHand", new Integer(0)));
                }
                
                //sort by
                if (ParamValidator.isSearchable(params.getSortByLength())) {
                    criteria.addOrder(Order.asc("length"));
                } else if (ParamValidator.isSearchable(params.getSortByName())) {
                    criteria.addOrder(Order.asc("name"));
                }
                
                List ret = criteria.list();

                return ret;
            }
        });          
    }
    
    
}
