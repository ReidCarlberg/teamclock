package com.fivesticks.time.useradmin.settings;

import java.util.Iterator;

import ognl.Ognl;
import ognl.OgnlException;

import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegate;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerAware;
import com.fivesticks.time.tokens.TokenServiceDelegate;
import com.fivesticks.time.tokens.TokenServiceDelegateException;
import com.fivesticks.time.tokens.TokenServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;

/**
 * 2005-10-6
 * 
 * @author Nick
 * 
 * 
 * As long as the UserSettingType and UserSettingVo properties are in sync this
 * will use Ognl to find and save the settings VO.
 */
public class UserSettingServiceDelegateImpl implements
        UserSettingServiceDelegate {

    private ObjectMetricServiceDelegate objectMetricServiceDelegate;

    private SystemOwner systemOwner;

    public UserSettingServiceDelegateImpl() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.util.CustomerSettingServiceDelegate#find(com.fivesticks.time.customer.FstxCustomer)
     */
    public UserSettingVO find(User user)
            throws ObjectMetricServiceDelegateException {
        ObjectMetricServiceDelegate omsd = this
                .getObjectMetricServiceDelegate();

        UserSettingVO userSettingVO = new UserSettingVO();

        for (Iterator iter = UserSettingType.getAll().iterator(); iter
                .hasNext();) {
            UserSettingType userSettingType = (UserSettingType) iter.next();

            ObjectMetric om;

            try {
                om = omsd.getMetric(user, userSettingType);
            } catch (ObjectMetricNotFoundException e) {
                om = null;
            }

            if (om != null) {

                try {
                    Ognl.setValue(userSettingType.getOgnl(), userSettingVO, om
                            .getMetricValue());
                } catch (OgnlException e) {

                    e.printStackTrace();
                }

            }

        }

        /*
         * User token
         */
        TokenServiceDelegate tsd = TokenServiceDelegateFactory.factory.build();
        try {
            String token = tsd.findToken(user);
            userSettingVO.setToken(token);
        } catch (TokenServiceDelegateException e) {
            // don't really need to do anything.
        }

        return userSettingVO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.customer.util.CustomerSettingServiceDelegate#save(com.fivesticks.time.customer.FstxCustomer,
     *      com.fivesticks.time.customer.util.CustomerSettingVO)
     */
    public void save(User user, UserSettingVO vo)
            throws ObjectMetricServiceDelegateException {

        ObjectMetricServiceDelegate omsd = this
                .getObjectMetricServiceDelegate();

        for (Iterator iter = UserSettingType.getAll().iterator(); iter
                .hasNext();) {
            UserSettingType userSettingType = (UserSettingType) iter.next();

            String value = null;
            try {
                Object o = Ognl.getValue(userSettingType.getOgnl(), vo);
                if (o != null) {
                    value = (String) o;
                }
            } catch (OgnlException e) {

                value = "";
            }

            if (value == null) {
                value = "";
            }
            omsd.setValue(user, userSettingType, value);

        }

    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public ObjectMetricServiceDelegate getObjectMetricServiceDelegate() {
        if (objectMetricServiceDelegate instanceof SystemOwnerAware) {
            SystemOwnerAware soa = (SystemOwnerAware) objectMetricServiceDelegate;
            soa.setSystemOwner(this.systemOwner);

        }
        return objectMetricServiceDelegate;
    }

    public void setObjectMetricServiceDelegate(
            ObjectMetricServiceDelegate objectMetricServiceDelegate) {
        this.objectMetricServiceDelegate = objectMetricServiceDelegate;
    }

}
