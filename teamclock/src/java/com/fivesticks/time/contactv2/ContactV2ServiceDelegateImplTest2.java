/*
 * Created on Nov 6, 2006
 *
 */
package com.fivesticks.time.contactv2;

import java.util.Collection;
import java.util.TreeSet;

import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupTestFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ContactV2ServiceDelegateImplTest2 extends AbstractTimeTestCase {

    ContactV2ServiceDelegate cv2sd;

    Lookup classOne;

    Lookup classTwo;

    ContactV2 contactOne;

    ContactV2 contactTwo;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.testutil.AbstractTimeTestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {

        super.setUp();

        cv2sd = ContactV2ServiceDelegateFactory.factory.build(this.systemOwner);

        classOne = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);

        classTwo = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);

        contactOne = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);

        contactTwo = ContactV2TestFactory.singleton
                .getPersisted(this.systemOwner);
        
    }



//    public void testSearchByClassWithHQL() throws Exception {
//
//        ContactV2CriteriaParameters p = new ContactV2CriteriaParameters();
//        p.setContactClassLuId(classOne.getId());
//        
//        Collection c = cv2sd.getList(p);
//        
//        assertNotNull(c);
//        
//        log.info(c.size());
//        
//        assertEquals(1, c.size());
//        
//        p = new ContactV2CriteriaParameters();
//        
//        c = cv2sd.getList(p);
//        
//        assertNotNull(c);
//        
//        log.info(c.size());
//        
//        assertEquals(3, c.size());
//        
//    }
    

    
    public void testSearchByLuMultiple() throws Exception {
        
        classOne = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_INTEREST);

        classTwo = LookupTestFactory.build(this.systemOwner,
                LookupType.CONTACT_CLASS);
        
        Lookup classThree = LookupTestFactory.build(this.systemOwner, LookupType.CONTACT_SOURCE);

        contactOne.setInterests(new TreeSet<Lookup>());
        contactOne.getInterests().add(classOne);
        cv2sd.save(contactOne);

        contactOne.setClasses(new TreeSet<Lookup>());
        contactOne.getClasses().add(classTwo);
        cv2sd.save(contactOne);
        
        
        ContactV2 reload = cv2sd.get(contactOne.getId());
        assertNotNull(reload);
        assertNotNull(reload.getInterests());
        assertEquals(1, reload.getInterests().size());
        assertNotNull(reload.getClasses());
        assertEquals(1, reload.getClasses().size());
        Lookup fromReload = (Lookup) reload.getInterests().toArray()[0];
        assertEquals(classOne.getId(), fromReload.getId());
        
        ContactV2CriteriaParameters p = new ContactV2CriteriaParameters();
        p.setContactInterestLuId(classOne.getId());
        p.setContactClassLuId(classTwo.getId());
        
        Collection c = cv2sd.getListCri(p);
        assertNotNull(c);
        log.info(c.size());
        assertEquals(1, c.size());
        

    }
    

}
