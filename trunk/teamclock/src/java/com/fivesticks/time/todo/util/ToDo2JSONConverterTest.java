/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.todo.util;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;

public class ToDo2JSONConverterTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {
        
        ToDo one = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        
        ToDo two = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        
        Collection c = ToDoServiceDelegateFactory.factory.build(this.sessionContext).findIncomplete();
        
        JSONArray a = new ToDo2JSONConverter(this.sessionContext).convert(c);
        
        assertNotNull(a);
        
        log.info(a.toString());
        
        assertNotNull(a.get(0));
        
        /*
         * 2006-07-08
         * default sort order is newest first.
         */
        JSONObject onea = a.getJSONObject(0);
        
        assertEquals(onea.get("id"), two.getId());
        
        assertNotNull(a.get(1));
        
        JSONObject twoa = a.getJSONObject(1);
        
        assertEquals(twoa.get("id"), one.getId());
    }
}
