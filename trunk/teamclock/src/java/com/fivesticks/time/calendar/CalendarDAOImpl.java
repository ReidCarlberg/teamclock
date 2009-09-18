package com.fivesticks.time.calendar;

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

import com.fivesticks.time.common.ParamValidator;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class CalendarDAOImpl extends HibernateDaoSupport implements
        CalendarDAO {

    public TcCalendar save(TcCalendar u) {

        this.getHibernateTemplate().saveOrUpdate(u);
        
        return u;
    }

    public TcCalendar get(Long id) {
        return (TcCalendar) this.getHibernateTemplate().get(
                TcCalendar.class, id);
    }

    public void delete(TcCalendar u) {
        //		removeFstxCalendar(u.getId());
        this.getHibernateTemplate().delete(u);
    }

    public void removeFstxCalendar(Long id) {
        this.getHibernateTemplate().delete(this.get(id));
    }

    /**
     * Returns a list of <code>FstxCalendar</code> s using the query specified
     * by the <code>query</code> key.
     * 
     * @param query
     *            the query key
     * @param value
     *            the value to put into the query statement. May be null
     * @return List @
     */
    public List searchFstxCalendars(String query, String value) {
        return this.getHibernateTemplate().find(query, value);
    }

    protected static Log log = LogFactory.getLog(CalendarDAOImpl.class
            .getName());

    /**
     * @param filterVO
     * @return
     */
    public List find(final CalendarFilterParameters params) {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return  hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(TcCalendar.class);

                if (params.getUsername() != null
                        && params.getUsername().length() > 0) {

                    criteria.add(Expression
                            .eq("username", params.getUsername()));
                }

                //This will bound the startdate.
                if (params.getStart() != null) {
                    SimpleDate start = SimpleDate.factory.buildMidnight(params
                            .getStart().getDate());
                    criteria.add(Expression.ge("startDate", start.getDate()));
                }
                if (params.getStop() != null) {

                    SimpleDate end = SimpleDate.factory.buildMidnight(params
                            .getStop().getDate());
                    end.advanceDay();
                    end.advanceSecond(-1);

                    criteria.add(Expression.le("endDate", end.getDate()));
                }
                if (params.getDescription() != null) {

                    criteria.add(Expression.ilike("description", "%"
                            + params.getDescription() + "%"));
                }

                if (params.getOwnerKey() != null) {
                    criteria.add(Expression
                            .eq("ownerKey", params.getOwnerKey()));

                }
                
                if (ParamValidator.isSearchable(params.getType())) {
                    criteria.add(Expression.eq("type", params.getType()));
                }
                
                criteria.addOrder(Order.asc("startDate"));

                List ret = criteria.list();

                return ret;
            }
        });

    }

}