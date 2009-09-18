/*
 * Created on Jun 21, 2006
 *
 */
package com.fivesticks.time.activity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectFactory;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class ActivityBDImplTest2 extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        ActivityBD bd = ActivityBDFactory.factory
                .build(this.sessionContext);

        ActivityFilterVO filter = new ActivityFilterVO();
        filter.setStart(new Date());
        filter.setStop(new Date());

        List l = bd.getTimeTotalsByProjectClass(filter);
    }

    public void testBasicWithActivities() throws Exception {

        Lookup luA = LookupTestFactory.build(this.systemOwner,
                LookupType.PROJECT_CLASS);

        Project projA = ProjectFactory.singleton.build("test1",
                this.customer);
        projA.setProjectClassId(luA.getId());
        ProjectServiceDelegateFactory.factory.build(this.sessionContext).save(
                projA);

        Activity actA = ActivityTestFactory.singleton.build(
                this.systemOwner, projA, this.user);
        actA.setElapsed(new Double(2.0));
        actA.setChargeableElapsed(new Double(1.5));
        ActivityBDFactory.factory.build(this.sessionContext).save(actA);

        ActivityFilterVO filter = new ActivityFilterVO();
        filter.setStart(SimpleDate.factory.buildMidnight().getDate());
        filter.setStop(SimpleDate.factory.buildEndOfDay().getDate());

        Collection c = ActivityBDFactory.factory.build(this.sessionContext)
                .getTimeTotalsByProjectClass(filter);

        Collection all = ActivityBDFactory.factory.build(
                SessionContextTestFactory.getContext(this.systemOwner,
                        this.user)).searchByFilter(new ActivityFilterVO());

        assertNotNull(c);

        assertEquals(1, c.size());

        ActivitySummaryByProjectClass sum = (ActivitySummaryByProjectClass) c
                .toArray()[0];

        assertEquals(1, sum.getCount().intValue());
        assertEquals(2.0, sum.getElapsed().doubleValue(), .1);
        assertEquals(1.5, sum.getElapsedChargeable().doubleValue(), .1);

    }

    public void testBasicWithActivitiesAndDates() throws Exception {

        Lookup luA = LookupTestFactory.build(this.systemOwner,
                LookupType.PROJECT_CLASS);

        Project projA = ProjectFactory.singleton.build("test1",
                this.customer);
        projA.setProjectClassId(luA.getId());
        ProjectServiceDelegateFactory.factory.build(this.sessionContext).save(
                projA);

        SimpleDate refDate = SimpleDate.factory.buildMidnight();
        refDate.advanceDay(-2);

        Activity actAPre = ActivityTestFactory.singleton.build(
                this.systemOwner, projA, this.user);
        actAPre.setElapsed(new Double(2.0));
        actAPre.setChargeableElapsed(new Double(1.5));
        actAPre.setDate(refDate.getDate());
        ActivityBDFactory.factory.build(this.sessionContext).save(actAPre);

        Activity actA = ActivityTestFactory.singleton.build(
                this.systemOwner, projA, this.user);
        actA.setDate(new Date()); // otherwise the factory advances it.
        actA.setElapsed(new Double(2.0));
        actA.setChargeableElapsed(new Double(1.5));
        ActivityBDFactory.factory.build(this.sessionContext).save(actA);

        ActivityFilterVO filter = new ActivityFilterVO();
        filter.setStart(SimpleDate.factory.buildMidnight().getDate());
        filter.setStop(SimpleDate.factory.buildEndOfDay().getDate());

        Collection c = ActivityBDFactory.factory.build(this.sessionContext)
                .getTimeTotalsByProjectClass(filter);

        Collection all = ActivityBDFactory.factory.build(
                SessionContextTestFactory.getContext(this.systemOwner,
                        this.user)).searchByFilter(new ActivityFilterVO());

        log.info("all size " + all.size());

        assertNotNull(c);

        // there are some activities without project classes.
        assertEquals(1, c.size());

        ActivitySummaryByProjectClass sum = (ActivitySummaryByProjectClass) c
                .toArray()[0];

        assertEquals(1, sum.getCount().intValue());
        assertEquals(2.0, sum.getElapsed().doubleValue(), .1);
        assertEquals(1.5, sum.getElapsedChargeable().doubleValue(), .1);

    }

    public void testBasicWithActivitiesAndMultipleClasses() throws Exception {

        Lookup luA = LookupTestFactory.build(this.systemOwner,
                LookupType.PROJECT_CLASS);
        Lookup luB = LookupTestFactory.build(this.systemOwner,
                LookupType.PROJECT_CLASS);

        Project projA = ProjectFactory.singleton.build("test1",
                this.customer);
        projA.setProjectClassId(luA.getId());
        ProjectServiceDelegateFactory.factory.build(this.sessionContext).save(
                projA);

        Project projB = ProjectFactory.singleton.build("test2",
                this.customer);
        projB.setProjectClassId(luB.getId());
        ProjectServiceDelegateFactory.factory.build(this.sessionContext).save(
                projB);

        Activity actA = ActivityTestFactory.singleton.build(
                this.systemOwner, projA, this.user);
        actA.setElapsed(new Double(2.0));
        actA.setChargeableElapsed(new Double(1.5));
        ActivityBDFactory.factory.build(this.sessionContext).save(actA);

        Activity actB = ActivityTestFactory.singleton.build(
                this.systemOwner, projA, this.user);
        actB.setElapsed(new Double(2.0));
        actB.setChargeableElapsed(new Double(1.5));
        ActivityBDFactory.factory.build(this.sessionContext).save(actB);

    }

    public void testBasicByProject() throws Exception {

        ActivityBD bd = ActivityBDFactory.factory
                .build(this.sessionContext);

        ActivityFilterVO filter = new ActivityFilterVO();
        filter.setStart(new Date());
        filter.setStop(new Date());

        List l = bd.getTimeTotalsByProject(filter);
    }
}
