/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.common;

import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2TestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoTestFactory;

public class EmptyToStringFieldNullDecoratorTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        ToDo sample = ToDoTestFactory.getPersisted(this.customer,this.project,this.user,this.systemOwner);
        sample.setAssignedToUser("");
        
        new EmptyToStringFieldNullDecorator().convert(sample);
        
        assertNull(sample.getAssignedToUser());
        
        
        ContactV2 sample2 = ContactV2TestFactory.singleton.getPersisted(this.systemOwner);
        
        sample2.setHomeAddress1("");
        sample2.setHomePostalBox("");
        
        assertNotNull(sample2.getHomeAddress1());
        assertNotNull(sample2.getHomePostalBox());
        
        new EmptyToStringFieldNullDecorator().convert(sample2);
        
        assertNull(sample2.getHomeAddress1());
        assertNull(sample2.getHomePostalBox());
        
    }
}
