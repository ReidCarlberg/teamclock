package com.fivesticks.time.timeclock;

import java.util.Collection;
import java.util.List;

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

import com.fstx.stdlib.common.DAOException;

public class TimeclockDAOImpl extends HibernateDaoSupport implements
        TimeclockDAO {

    public Timeclock save(Timeclock u) throws DAOException {

        this.getHibernateTemplate().saveOrUpdate(u);
        return u;
    }

    public Timeclock get(Long id) throws DAOException {
        return (Timeclock) this.getHibernateTemplate().get(
                Timeclock.class, id);
    }

    public void remove(Timeclock u) throws DAOException {
        this.getHibernateTemplate().delete(u);
    }

    public void remove(Long id) throws DAOException {
        this.remove(this.get(id));
    }

    /**
     * Returns a list of <code>FstxCalendar</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List
     * @throws DAOException
     */
    public List search(String query, String value) throws DAOException {
        return this.getHibernateTemplate().find(query, value);
    }

    protected static Log log = LogFactory.getLog(TimeclockDAOImpl.class
            .getName());

    /**
     * @param filter
     * @return
     */
    public Collection find(final TimeclockFilterParameters filter)
            throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria c = session.createCriteria(Timeclock.class);

                if (filter.getUsername() != null
                        && filter.getUsername().length() > 0) {
                    c.add(Expression.eq("username", filter.getUsername()));
                }

                if (filter.getOwnerKey() != null
                        && filter.getOwnerKey().length() > 0) {
                    c.add(Expression.eq("ownerKey", filter.getOwnerKey()));
                }

                if (filter.getEvent() != null && filter.getEvent().length() > 0) {
                    c.add(Expression.eq("event", filter.getEvent()));
                }

                if (filter.getEventTimestampRangeStart() != null
                        && filter.getEventTimestampRangeEnd() != null) {
                    //					c.add(Expression.ge("eventTimestamp",filter.getEventTimestampRangeStart()));
                    //					c.add(Expression.le("eventTimestamp",filter.getEventTimestampRangeEnd()));

                    c.add(Expression.between("eventTimestamp", filter
                            .getEventTimestampRangeStart(), filter
                            .getEventTimestampRangeEnd()));
                }

                if (filter.getTimestampRangeStart() != null
                        && filter.getTimestampRangeStop() != null) {
                    c.add(Expression.between("timestamp", filter
                            .getTimestampRangeStart(), filter
                            .getTimestampRangeStop()));
                }

                if (filter.getComment() != null
                        && filter.getComment().length() > 0) {
                    c.add(Expression.like("comment", "%" + filter.getComment()
                            + "%"));
                }

                /*
                 * We need to order by the eventTimestamp for the user shift
                 * list. Records are not garunteed to be entered in
                 * chronological order. Example when time was down and records
                 * need to be bulk entered by hand. -nah 5/12/04
                 *  
                 */
                c.addOrder(Order.asc("eventTimestamp"));
                c.addOrder(Order.asc("id"));
                //c.addOrder(Order.asc("id"));

//                log.info("timeclock criteria " + c.toString());
                
                List ret = c.list();

                return ret;
            }
        });

    }

}