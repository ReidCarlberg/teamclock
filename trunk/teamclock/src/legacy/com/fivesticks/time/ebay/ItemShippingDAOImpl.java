/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.ParamValidator;

/**
 * @author Reid
 */
public class ItemShippingDAOImpl extends HibernateDaoSupport implements ItemShippingDAO {
    
    public void save(ItemShipping target) {
        this.getHibernateTemplate().saveOrUpdate(target);
    }
    
    public ItemShipping get(Long id) {
        return (ItemShipping) this.getHibernateTemplate().get(ItemShipping.class, id);
    }
    
    public void delete(ItemShipping target) {
        this.getHibernateTemplate().delete(target);
    }
    
    public Collection find(final ItemShippingFilter params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(ItemShipping.class);

                //owner key
                if (ParamValidator.isSearchable(params.getOwnerKey())) {
                    criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
                }
                
                //id
                if (ParamValidator.isSearchable(params.getId())) {
                    criteria.add(Expression.eq("id", params.getId()));
                }

                //ebay id
                if (ParamValidator.isSearchable(params.getEbayId())) {
                    criteria.add(Expression.eq("ebayId", params.getEbayId()));
                }

                
                List ret = criteria.list();

                return ret;
            }
        });          
    }    

    
}
