/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RegistrationPlan {

    private static Map allPlans = new HashMap();

    private static Collection orderedPlans = new ArrayList();
    
    public static final RegistrationPlan BASIC = new RegistrationPlan("BASIC",
            "Basic", false, false, false, 1, 0.0);

    public static final RegistrationPlan SIMPLE = new RegistrationPlan(
            "SIMPLE", "Simple", true, false, false, 10, 8.0);

    public static final RegistrationPlan STARTER = new RegistrationPlan(
            "STARTER", "Starter", true, true, true, 10, 15.00);

    public static final RegistrationPlan BUSINESS1 = new RegistrationPlan(
            "BUSINESS1", "Business 1", true, true, true, 25, 29.00);

    public static final RegistrationPlan BUSINESS2 = new RegistrationPlan(
            "BUSINESS2", "Business 2", true, true, true, 50, 39.00);

    public static final RegistrationPlan BUSINESS3 = new RegistrationPlan(
            "BUSINESS3", "Business 3", true, true, true, 99, 49.00);



    
    private final String code;

    private final String name;

    private final boolean requiresCreditCard;

    private final boolean advanceTimeclockAllowed;

    private final boolean extraFeaturesAllowed;

    private final int maxActiveUsers;

    private final double price;

    public static RegistrationPlan getPlan(String key) {
        RegistrationPlan ret = (RegistrationPlan) allPlans.get(key);

        if (ret == null)
            throw new RuntimeException("Couldn't locate the plan " + key);

        return ret;
    }

    public static Collection getPlans() {
        return RegistrationPlan.orderedPlans;
    }

    public RegistrationPlan(String code, String name,
            boolean requiresCreditCard, boolean advancedTimeclockAllowed,
            boolean extraFeaturesAllowed, int maxActiveUsers, double price) {
        super();
        this.code = code;
        this.name = name;
        this.requiresCreditCard = requiresCreditCard;
        this.advanceTimeclockAllowed = advancedTimeclockAllowed;
        this.extraFeaturesAllowed = extraFeaturesAllowed;
        this.maxActiveUsers = maxActiveUsers;
        this.price = price;

        RegistrationPlan.allPlans.put(this.getCode(), this);
        RegistrationPlan.orderedPlans.add(this);
    }

    /**
     * @return Returns the advanceTimeclockAllowed.
     */
    public boolean isAdvanceTimeclockAllowed() {
        return advanceTimeclockAllowed;
    }

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @return Returns the extraFeaturesAllowed.
     */
    public boolean isExtraFeaturesAllowed() {
        return extraFeaturesAllowed;
    }

    /**
     * @return Returns the maxActiveUsers.
     */
    public int getMaxActiveUsers() {
        return maxActiveUsers;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the requiresCreditCard.
     */
    public boolean isRequiresCreditCard() {
        return requiresCreditCard;
    }

    /**
     * @return Returns the price.
     */
    public double getPrice() {
        return price;
    }

}
