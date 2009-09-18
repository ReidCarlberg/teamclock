/*
 * Created on Jul 14, 2006
 *
 */
package com.fivesticks.time.common.parser;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ProjectUserDetailParserTest extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        String testString = this.project.getShortName() + " "
                + this.user.getUsername() + " this is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertTrue(parser.getTargetProject().getId().equals(
                this.project.getId()));

        assertTrue(parser.getTargetUser().getUsername().equals(
                this.user.getUsername()));

        assertEquals(parser.getTargetDetail(), "this is a test");

    }

    public void testBasicWithPunctation() throws Exception {

        String testString = this.project.getShortName() + " "
                + this.user.getUsername() + " this.l;'?>< is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertTrue(parser.getTargetProject().getId().equals(
                this.project.getId()));

        assertTrue(parser.getTargetUser().getUsername().equals(
                this.user.getUsername()));

        assertEquals(parser.getTargetDetail(), "this.l;'?>< is a test");

    }

    public void testUsernameOnly() throws Exception {

        String testString = this.user.getUsername() + " this is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertNull(parser.getTargetProject());

        assertTrue(parser.getTargetUser().getUsername().equals(
                this.user.getUsername()));

        assertEquals(parser.getTargetDetail(), "this is a test");

    }
    
    public void testProjectOnly() throws Exception {

        String testString = this.project.getShortName() + " this is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertNull(parser.getTargetUser());

        assertEquals(this.project.getId(), parser.getTargetProject().getId());
        
        assertEquals(parser.getTargetDetail(), "this is a test");

    }
    
    public void testNothingFound() throws Exception {
        String testString = "this is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertNull(parser.getTargetUser());
        
        assertNull(parser.getTargetProject());
        
        assertEquals(parser.getTargetDetail(), "this is a test");
    }
    
    public void testBasicWithExtraSpaces() throws Exception {

        String testString = "   " + this.project.getShortName() + "     "
                + this.user.getUsername() + "     this is a test";

        ProjectUserDetailParser parser = new ProjectUserDetailParser(
                this.sessionContext);

        parser.parse(testString);

        assertNotNull(parser.getTargetProject());
        
        assertTrue(parser.getTargetProject().getId().equals(
                this.project.getId()));

        assertNotNull(parser.getTargetUser());
        
        assertTrue(parser.getTargetUser().getUsername().equals(
                this.user.getUsername()));

        assertEquals(parser.getTargetDetail(), "this is a test");

    }
    
}
