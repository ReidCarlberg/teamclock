/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.queuedmessages;

import java.util.List;
import java.util.TimeZone;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 */
public class QueuedMessageDAOImpl extends HibernateDaoSupport implements
        QueuedMessageDAO {

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#save(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public QueuedMessage save(QueuedMessage u) throws DAOException {

        this.getHibernateTemplate().saveOrUpdate(u);  
        return u;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#get(java.lang.Long)
     */
    public QueuedMessage get(Long id) throws DAOException {

        return (QueuedMessage) this.getHibernateTemplate().get(
                QueuedMessage.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#delete(com.fivesticks.time.prepay.PrepayTransaction)
     */
    public void delete(QueuedMessage u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#delete(java.lang.Long)
     */
    public void delete(Long id) throws DAOException {
        QueuedMessage pre = this.get(id);
        this.delete(pre);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.prepay.PrepayTransactionDAO#find(com.fivesticks.time.prepay.PrepayTransactionFilter)
     */
    public List find(final QueuedMessageFilter filter) throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        /*
         * 2006-05-01 RSC
         * goes into Zulu here since it was failing to find registration messages.
         */
        TimeZone.setDefault(TimeZone.getTimeZone("Zulu"));
        
        final Log log = LogFactory.getLog(QueuedMessageDAOImpl.class);
        
        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                ValidationHelper help = new ValidationHelper();

                Criteria c = session.createCriteria(QueuedMessage.class);

                if (!help.isStringEmpty(filter.getOwnerKey())) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }

                if (!help.isStringEmpty(filter.getFromAddress())) {
                    c
                            .add(Expression.eq("fromAddress", filter
                                    .getFromAddress()));
                }

                if (!help.isStringEmpty(filter.getToAddress())) {
                    c.add(Expression.eq("toAddress", filter.getToAddress()));
                }

                if (!help.isStringEmpty(filter.getFromName())) {
                    c.add(Expression.eq("fromName", filter.getFromName()));
                }

                if (!help.isStringEmpty(filter.getToName())) {
                    c.add(Expression.eq("toName", filter.getToName()));
                }

                if (!help.isStringEmpty(filter.getSubject())) {
                    c.add(Expression.eq("subject", filter.getSubject()));
                }

                if (!help.isStringEmpty(filter.getMessage())) {
                    c.add(Expression.eq("message", filter.getMessage()));
                }

                if (!help.isStringEmpty(filter.getMessageLike())) {
                    c.add(Expression.like("message", "%"
                            + filter.getMessageLike() + "%"));
                }

                if (filter.getSendTimeRangeStart() != null
                        && filter.getSendTimeRangeStop() != null) {
                    c.add(Expression.between("sendTime", filter
                            .getSendTimeRangeStart().toDate(), filter
                            .getSendTimeRangeStop().toDate()));
                } else if (filter.getSendTimeRangeStart() != null) {
                    c.add(Expression.ge("sendTime", filter
                            .getSendTimeRangeStart().toDate()));
                } else if (filter.getSendTimeRangeStop() != null) {
                    c.add(Expression.le("sendTime", filter
                            .getSendTimeRangeStop().toDate()));
                }

                if (filter.getBooleanSent() != null) {
                    c.add(Expression.eq("sent", filter.getBooleanSent()));
                }

                c.addOrder(Order.asc("sendTime"));

//                log.info(c.toString());
//                log.info("SendTimeRangeStop " + filter.getSendTimeRangeStop().toDate());
//                log.info("Default Time Zone " + TimeZone.getDefault());
                
                List ret = c.list();

                return ret;
            }
        });

    }

}