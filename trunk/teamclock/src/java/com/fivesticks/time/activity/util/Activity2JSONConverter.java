/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.activity.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.json.AbstractJSONConverter;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;

public class Activity2JSONConverter extends AbstractJSONConverter {

    private DateFormat time = new SimpleDateFormat("h:mm a");

    private LookupServiceDelegate lookupServiceDelegate;

    public Activity2JSONConverter(SessionContext sessionContext) {
        this.initialize(sessionContext);
        lookupServiceDelegate = LookupServiceDelegateFactory.factory
                .build(sessionContext.getSystemOwner());
    }

    public JSONObject convert(Collection activities) throws Exception {

        double totalElapsed = 0.0;
        double totalChargeableElapsed = 0.0;

        JSONArray act = new JSONArray();

        Map<Long, Double> classElapsed = new HashMap<Long, Double>();
        Map<Long, Double> classChargeable = new HashMap<Long, Double>();

        for (Iterator iter = activities.iterator(); iter.hasNext();) {
            Activity element = (Activity) iter.next();

            act.put(convert(element));

            if (element.getElapsed() != null)
                totalElapsed += element.getElapsed().doubleValue();

            if (element.getChargeableElapsed() != null)
                totalChargeableElapsed += element.getChargeableElapsed()
                        .doubleValue();

            /*
             * this gives totals by project class
             */
            Project project = this.getProjectServiceDelegate().getFstxProject(
                    element.getProjectId());

            Long key = project.getProjectClassId();

            if (project.getProjectClassId() == null) {
                key = new Long(0);
            }

            Double chargeable = null;
            Double elapsed = null;

            chargeable = classChargeable.get(key);

            if (chargeable == null) {
                chargeable = new Double(0.0);
                classChargeable.put(key, chargeable);
            }

            elapsed = classElapsed.get(key);

            if (elapsed == null) {
                elapsed = new Double(0.0);
                classElapsed.put(key, elapsed);
            }

            if (element.getChargeableElapsed() != null) {
                chargeable = new Double(chargeable.doubleValue()
                        + element.getChargeableElapsed().doubleValue());
            }

            if (element.getElapsed() != null) {
                elapsed = new Double(elapsed.doubleValue()
                        + element.getElapsed().doubleValue());
            }

            classChargeable.put(key, chargeable);
            classElapsed.put(key, elapsed);

        }

        JSONObject ret = new JSONObject();

        ret.put("activities", act);
        ret.put("totalElapsed", totalElapsed);
        ret.put("totalChargeableElapsed", totalChargeableElapsed);

        JSONArray classes = new JSONArray();

        for (Iterator<Long> iter = classChargeable.keySet().iterator(); iter
                .hasNext();) {
            Long element = iter.next();

            Lookup projectClass = this.getLookupServiceDelegate().get(element);

            JSONObject cls = new JSONObject();
            if (projectClass != null) {
                cls.put("className", projectClass.getFullName());
            } else {
                cls.put("className", "deleted [" + element + "]");
            }
            cls.put("totalElapsed", classElapsed.get(element).doubleValue());
            cls.put("totalChargeableElapsed", classChargeable.get(element)
                    .doubleValue());

            classes.put(cls);
        }

        ret.put("totalsByClass", classes);

        return ret;

    }

    public JSONObject convert(Activity target) throws Exception {

        Project fstxProject = this.getProjectServiceDelegate().getFstxProject(
                target.getProjectId());
        JSONObject ret = new JSONObject();

        ret.put("id", target.getId());

        ret.put("comments", target.getComments());
        ret.put("username", target.getUsername());
        ret.put("date", SimpleDateFormat
                .getDateInstance(SimpleDateFormat.SHORT).format(
                        target.getDate()));
        ret.put("elapsed", target.getElapsed());
        ret.put("chargeableElapsed", target.getChargeableElapsed());

        ret.put("start", time.format(target.getStart()));
        ret.put("startMilliseconds", target.getStart().getTime());

        if (target.getStop() != null) {
            ret.put("stop", time.format(target.getStop()));
            ret.put("stopMilliseconds", target.getStop().getTime());
        } else {
            ret.put("stop", null);
            ret.put("stopMilliseconds", null);
        }

        /*
         * 2006-06-25 reid@fivesticks.com these are here so the dashboard list
         * is nicer.
         */
        ret.put("projectId", fstxProject.getId());
        ret.put("projectKey", fstxProject.getShortName());

        return ret;
    }

    /**
     * @return the lookupServiceDelegate
     */
    public LookupServiceDelegate getLookupServiceDelegate() {
        return lookupServiceDelegate;
    }

    /**
     * @param lookupServiceDelegate
     *            the lookupServiceDelegate to set
     */
    public void setLookupServiceDelegate(
            LookupServiceDelegate lookupServiceDelegate) {
        this.lookupServiceDelegate = lookupServiceDelegate;
    }
}
