/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 * @author Nick
 *  
 */
public class ProjectServiceDelegateFactory extends AbstractSpringObjectFactory {
    public static final String SPRING_BEAN_NAME = "projectServiceDelegate";
    public static final ProjectServiceDelegateFactory factory = new ProjectServiceDelegateFactory();

    public ProjectServiceDelegate build(SessionContext sessionContext) {
        return build(sessionContext.getSystemOwner());
    }

    public ProjectServiceDelegate build(SystemOwner systemOwner) {
        ProjectServiceDelegate ret = (ProjectServiceDelegate) this
                .getBean(ProjectServiceDelegateFactory.SPRING_BEAN_NAME);

        ret.setSystemOwner(systemOwner);
        return ret;

    }

    public ProjectServiceDelegate buildEmpty() {
        return (ProjectServiceDelegate) this.getBean(
                ProjectServiceDelegateFactory.SPRING_BEAN_NAME);
    }

    /**
     *  
     */
//    public FstxProjectBD buildTest() {
//        FstxProjectBD ret = new FstxProjectBDImpl();
//        ret.setFstxProjectDAO(new FstxProjectBD_Mock_FstxProjectDAO_Impl());
//        //        ret.setFstxTimeBD(new FstxProjectBDTest_Mock_FstxTimeBDImpl());
//        return ret;
//    }

}