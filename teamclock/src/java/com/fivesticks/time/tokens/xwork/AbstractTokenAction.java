/*
 * Created on Apr 30, 2006
 *
 */
package com.fivesticks.time.tokens.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;

public abstract class AbstractTokenAction extends SessionContextAwareSupport {

    public abstract String getAction();
}
