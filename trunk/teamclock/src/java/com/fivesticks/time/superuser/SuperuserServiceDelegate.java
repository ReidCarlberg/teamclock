/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.superuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.object.metrics.ObjectMetricCriteriaBuilder;
import com.fivesticks.time.object.metrics.ObjectMetricFilter;
import com.fivesticks.time.object.metrics.ObjectMetricTypeEnum;
import com.fivesticks.time.superuser.xwork.OwnerMetricVO;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Reid
 */
public class SuperuserServiceDelegate {

    public Collection getMostActiveUsers() throws
            SystemOwnerServiceDelegateException {

        ObjectMetricFilter filter = new ObjectMetricFilter();

        filter.setObjectType(SystemOwner.class.getName());
        filter.setMetricType(ObjectMetricTypeEnum.SYSTEM_LOGIN_COUNT.getName());

//        Collection col = ObjectMetricDAO.factory.build().find(filter);
        
        GenericDAO dao = (GenericDAO) SpringBeanBroker.getBeanFactory().getBean(GenericDAO.SPRING_BEAN_NAME);
        dao.setCriteriaBuilder(new ObjectMetricCriteriaBuilder());
        
        Collection col = dao.find(filter);
        
        Collection sorted = new TreeSet(new MetricNumericThenAlphaComparator(
                true));

        sorted.addAll(col);

        Collection ret = new ArrayList();

        SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory
                .build();

        for (Iterator iter = sorted.iterator(); iter.hasNext();) {
            ObjectMetric element = (ObjectMetric) iter.next();

//            System.out.println(element.getMetricValue());
//            System.out.println(element.getOwnerKey());

            SystemOwner owner = sd.get(element.getOwnerKey());

            OwnerMetricVO vo = new OwnerMetricVO();
            vo.setMetric(element);
            vo.setOwner(owner);

            ret.add(vo);
        }

        return ret;
    }
}