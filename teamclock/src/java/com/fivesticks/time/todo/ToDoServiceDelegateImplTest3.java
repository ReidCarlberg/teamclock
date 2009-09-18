/*
 * Created on Jul 6, 2006 by Reid
 */
package com.fivesticks.time.todo;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.ActivityTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/*
 * 2006-07-06 reid
 * 
 * designed to test the total decorator
 */
public class ToDoServiceDelegateImplTest3 extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        ToDo t1 = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        
        Activity a1 = ActivityTestFactory.singleton.build(this.systemOwner, this.project, this.user);
        a1.setToDoId(t1.getId());
        a1.setElapsed(new Double(2.5));
        a1.setChargeableElapsed(new Double(1.25));
        ActivityBDFactory.factory.build(this.sessionContext).save(a1);
        
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).decorateWithTotalActivityTotals(t1);
        
        assertNotNull(t1.getTotalElapsedHours());
        assertNotNull(t1.getTotalChargeableElapsedHours());
     
        assertEquals(2.5, t1.getTotalElapsedHours().doubleValue(), 0.01);
        assertEquals(1.25, t1.getTotalChargeableElapsedHours().doubleValue(), 0.01);
    }
    
    public void testBasicMakeSureItsTotalling() throws Exception {
        
        ToDo t1 = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        
        Activity a1 = ActivityTestFactory.singleton.build(this.systemOwner, this.project, this.user);
        a1.setToDoId(t1.getId());
        a1.setElapsed(new Double(2.5));
        a1.setChargeableElapsed(new Double(1.25));
        ActivityBDFactory.factory.build(this.sessionContext).save(a1);

        Activity a2 = ActivityTestFactory.singleton.build(this.systemOwner, this.project, this.user);
        a2.setToDoId(t1.getId());
        a2.setElapsed(new Double(2.5));
        a2.setChargeableElapsed(new Double(1.25));
        ActivityBDFactory.factory.build(this.sessionContext).save(a2);
        
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).decorateWithTotalActivityTotals(t1);
        
        assertNotNull(t1.getTotalElapsedHours());
        assertNotNull(t1.getTotalChargeableElapsedHours());
     
        assertEquals(5.0, t1.getTotalElapsedHours().doubleValue(), 0.01);
        assertEquals(2.5, t1.getTotalChargeableElapsedHours().doubleValue(), 0.01);
    }
    
}
