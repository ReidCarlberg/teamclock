/*
 * Created on Apr 29, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * @author Nick 2006-07-06 reid updated
 * 
 */
public class ProjectServiceDelegateImplTest extends AbstractTimeTestCase {

    public void testDaoAdd() throws Exception {

        Project t1 = new Project();
        t1.setLongName("longname");
        t1.setShortName("shortname");
        t1.setOwnerKey(systemOwner.getKey());
        t1.setCustId(customer.getId());
        // GenericDAO dao = FstxProjectDAO.factory.build();

        ProjectServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

    }

    public void testBDFind() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Project t1 = new Project();
        t1.setLongName("longname");
        t1.setShortName("shortname");
        t1.setOwnerKey(systemOwner.getKey());
        t1.setCustId(customer.getId());

        // GenericDAO dao = FstxProjectDAO.factory.build();

        ProjectServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

        Project t2 = ProjectServiceDelegateFactory.factory.build(
                sampleContext).getFstxProject(t1.getId());

        assertTrue(t2 != null);

        assertTrue(t2.getId().equals(t1.getId()));

        assertTrue(t2.getLongName().equals(t1.getLongName()));
        assertTrue(t2.getShortName().equals(t1.getShortName()));
    }

    public void testGetAll() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Collection before = ProjectServiceDelegateFactory.factory.build(
                sampleContext).getAllActive();

        assertTrue(before != null && before.size() == 0);

        Project t1 = new Project();
        t1.setShortName("name");
        t1.setLongName("longname");
        t1.setCustId(customer.getId());
        t1.setActive(true);
        t1.setOwnerKey(systemOwner.getKey());

        // FstxProjectDAO dao = FstxProjectDAO.factory.build();
        //
        // dao.save(t1);

        ProjectServiceDelegateFactory.factory.build(sampleContext).save(t1);

        assertTrue(t1.getId() != null);

        Collection after = ProjectServiceDelegateFactory.factory.build(
                sampleContext).getAllActive();

        log.info("Before size: " + before.size());
        log.info("After size: " + after.size());

        assertTrue(after != null && after.size() == before.size() + 1);
    }

    public void testDelete() throws Exception {

        long id = this.project.getId().longValue();

        ProjectServiceDelegate sd = ProjectServiceDelegateFactory.factory
                .build(this.sessionContext);

        sd.delete(this.project);

        try {
            Project p1 = sd.getFstxProject(new Long(id));
            assertTrue(false);
        } catch (Exception e) {

        }

    }

}