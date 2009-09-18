/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.comments;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 */
public class ObjectCommentFilter extends ObjectComment implements CriteriaParameters {

    private String commentLike;

    /**
     * @return Returns the commentLike.
     */
    public String getCommentLike() {
        return commentLike;
    }

    /**
     * @param commentLike
     *            The commentLike to set.
     */
    public void setCommentLike(String commentLike) {
        this.commentLike = commentLike;
    }
}