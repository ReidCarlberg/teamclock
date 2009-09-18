/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.customer.providers;

import java.util.Collection;

/**
 * @author Reid
 *
 */
public interface ProjectListProvider {

    public Collection getProjects() throws ProjectListProviderException;
}
