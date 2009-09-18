package com.fivesticks.time.activity;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.common.dao.GenericDAOFactory;

/**
 * @author REID
 * @author SHUJI added build(SessionContext) and build(SystemOwner)
 */
public class ActivityBDFactory {

    // public FstxActivityBD build() {
    //
    // FstxActivityBDImpl ret = (FstxActivityBDImpl)
    // SpringBeanBroker.getBeanFactory()
    // .getBean(FstxActivityBD.SPRING_BEAN_NAME);
    //
    // // ret.setAuthorizer(Authorizer.singleton);
    // // ret.setFstxProjectBD(FstxProjectBD.factory.build());
    //        
    // return ret;
    //
    // }

    public static final String SPRING_BEAN_NAME = "fstxActivityBD";
    public static final ActivityBDFactory factory = new ActivityBDFactory();

    public ActivityBD build(SessionContext sessionContext) {
        ActivityBDImpl ret = (ActivityBDImpl) SpringBeanBroker
                .getBeanFactory().getBean(ActivityBDFactory.SPRING_BEAN_NAME);

        ret.setSessionContext(sessionContext);
        ret.setSystemOwner(sessionContext.getSystemOwner());
        ret.setTimezone(sessionContext.getSettings().getSystemTimeZone());
        ret.setRounder(sessionContext.getSettings().getActivityRounderType());
        
        /*
         * 2007-01-10 -- adding the generic DAO manually.
         */
        
        ret.setDao(GenericDAOFactory.factory.build());
        return ret;
    }

    /*
     * 2006-06-22 reid@fivesticks.com
     * needs session context.
     * 2006-0
     */
//    public FstxActivityBD build(SystemOwner systemOwner) {
//
//        FstxActivityBDImpl ret = (FstxActivityBDImpl) SpringBeanBroker
//                .getBeanFactory().getBean(FstxActivityBDFactory.SPRING_BEAN_NAME);
//
//        ret.setSystemOwner(systemOwner);
//        return ret;
//
//    }

    /**
     * RSC 2005-11-08 Can ignore this altogether
     */
    // public FstxActivityBD buildTest() {
    // FstxActivityBD bd = new FstxActivityBDImpl();
    //
    // /*
    // * I don't want to mock the Authorization and Authentication. I don't
    // * wnat to undermine the securinty system
    // *
    // * -nah
    // */
    // // bd.setAuthorizer(Authorizer.singleton);
    // bd
    // .setFstxActivityDAO(new FstxActivityBD_Mock_FstxActivityDAO_Impl());
    // bd.setFstxProjectBD(new FstxActivityBD_Mock_FstxProjectBD_Impl());
    //
    // return bd;
    //
    // }
}