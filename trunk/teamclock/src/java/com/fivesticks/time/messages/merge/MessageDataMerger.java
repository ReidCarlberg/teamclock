/*
 * Created on Oct 17, 2005
 *
 * 
 */
package com.fivesticks.time.messages.merge;

import com.fivesticks.time.messages.Message;
import com.fivesticks.time.util.forms.FormDataMerger;
import com.fivesticks.time.util.forms.FormDataMergerException;

public interface MessageDataMerger extends FormDataMerger {

    public String getMergedSubject() throws FormDataMergerException;

    public Message getMergedMessage() throws FormDataMergerException;
}
