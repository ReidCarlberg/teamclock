/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms;

import java.io.Serializable;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Reid
 *
 */
public class EbayForm implements SystemOwnerKeyAware, Serializable {

    private Long id;
    private String ownerKey;
    private String type;
    private String name;
    private String form;
    private String linebreak;
    
    
    /**
     * @return Returns the form.
     */
    public String getForm() {
        return form;
    }
    /**
     * @param form The form to set.
     */
    public void setForm(String form) {
        this.form = form;
    }
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }
    /**
     * @param ownerKey The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the linebreak.
     */
    public String getLinebreak() {
        return linebreak;
    }
    /**
     * @param linebreak The linebreak to set.
     */
    public void setLinebreak(String linebreak) {
        this.linebreak = linebreak;
    }
}
