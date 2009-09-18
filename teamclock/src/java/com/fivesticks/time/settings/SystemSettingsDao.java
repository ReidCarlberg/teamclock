/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author REID
 *  
 */
public class SystemSettingsDao extends HibernateDaoSupport {

    private static final String SEARCH_BY_SETTING_KEY = "from com.fivesticks.time.settings.SystemSettings settings where settings.settingKey = ?";

    public SystemSettings save(SystemSettings systemSettings) {
        this.getHibernateTemplate().saveOrUpdate(
                systemSettings);
        return systemSettings;

    }

    public SystemSettings find(String settingKey) {
        SystemSettings ret = null;

        List list = this.getHibernateTemplate().find(SEARCH_BY_SETTING_KEY,
                settingKey);

        if (list.size() == 1) {
            ret = (SystemSettings) list.get(0);
        } else {
            throw new RuntimeException("list is too big or small ... anyway, "
                    + settingKey + " is not found.");
        }

        return ret;
    }

    public List find(final SystemSettingsParameters params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session
                        .createCriteria(SystemSettings.class);

                if (params.getOwnerKey() != null
                        && params.getOwnerKey().trim().length() != 0) {
                    criteria.add(Expression
                            .eq("ownerKey", params.getOwnerKey()));
                }

                if (params.getSettingKey() != null
                        && params.getSettingKey().trim().length() != 0) {
                    criteria.add(Expression.eq("settingKey", params
                            .getSettingKey()));
                }

                List ret = criteria.list();

                return ret;
            }
        });

    }

    public void delete(SystemSettings systemSettings) {

        this.getHibernateTemplate().delete(systemSettings);

    }
}