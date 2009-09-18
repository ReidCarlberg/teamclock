/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo.schedule;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author REID
 *  
 */
public class ToDoScheduleDAOImpl extends HibernateDaoSupport implements
        ToDoScheduleDAO {

//    private Log log = LogFactory.getLog(ToDoScheduleDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoDAO#save(com.fivesticks.time.todo.ToDo)
     */
    public ToDoSchedule save(ToDoSchedule target) {
        //		Object object = this.getHibernateTemplate().saveOrUpdateCopy(target);
        //		log.info("returned object " + object.getClass().getName());
        //		
        this.getHibernateTemplate().saveOrUpdate(
                target);
        
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoDAO#get(java.lang.Long)
     */
    public ToDoSchedule get(Long id) {
        return (ToDoSchedule) this.getHibernateTemplate().get(
                ToDoSchedule.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoDAO#delete(com.fivesticks.time.todo.ToDo)
     */
    public void delete(ToDoSchedule target) {
        this.getHibernateTemplate().delete(target);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDoDAO#search(com.fivesticks.time.todo.ToDoSearchParameters)
     */
    public List find(final ToDoScheduleQueryParameters params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(ToDoSchedule.class);

                List ret = criteria.list();

                return ret;
            }
        });

    }

}