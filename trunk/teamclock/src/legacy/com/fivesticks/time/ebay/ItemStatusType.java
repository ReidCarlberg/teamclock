/*
 * Created on Mar 26, 2005 by Reid
 */
package com.fivesticks.time.ebay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.util.DescriptiveEnum;

/**
 * @author Reid
 */
public class ItemStatusType extends DescriptiveEnum {

    private static Collection all;

    private static Collection updatable;

    public static final ItemStatusType ADDED = new ItemStatusType("ADDED",
            "Added");

    /*
     * 2005-08-17 RSC
     */
    // public static final ItemStatusType READY_TO_LIST = new ItemStatusType(
    // "READY_TO_LIST", "Ready to List");
    public static final ItemStatusType ACTIVE_ITEM = new ItemStatusType(
            "ACTIVE_ITEM", "Active Item");

    public static final ItemStatusType WAITING_FOR_PAYMENT = new ItemStatusType(
            "WAITING_FOR_PAYMENT", "Waiting for Payment");

    /*
     * 2005-08-17 RSC
     */
    // public static final ItemStatusType WAITING_TO_CLEAR = new ItemStatusType(
    // "WAITING_TO_CLEAR", "Waiting to Clear");
    // public static final ItemStatusType READY_FOR_SHIPMENT = new
    // ItemStatusType(
    // "READY_FOR_SHIPMENT", "Ready to Ship");
    public static final ItemStatusType ITEM_IN_TRANSIT = new ItemStatusType(
            "ITEM_IN_TRANSIT", "Item in Transit");

    public static final ItemStatusType READY_TO_PAY_NET = new ItemStatusType(
            "READY_TO_PAY_NET", "Ready to Pay Net");

    public static final ItemStatusType POSTED_TO_ACCOUNT = new ItemStatusType(
            "POSTED_TO_ACCOUNT", "Posted to Account");

    public static final ItemStatusType NOT_SOLD_RELIST = new ItemStatusType(
            "NOT_SOLD_RELIST", "Not Sold, Relist");

    public static final ItemStatusType NOT_SOLD_WAITING_FOR_PICKUP = new ItemStatusType(
            "NOT_SOLD_WAITING_FOR_PICKUP", "Not sold, waiting for pickup");

    public static final ItemStatusType CLOSED_CANCELLED = new ItemStatusType(
            "CLOSED_CANCELLED", "Closed - Cancelled");

    public static final ItemStatusType CLOSED_NOT_SOLD = new ItemStatusType(
            "CLOSED_NOT_SOLD", "Closed - Not Sold");

    public static final ItemStatusType CLOSED_SOLD = new ItemStatusType(
            "CLOSED_SOLD", "Closed - Sold");

    public static final ItemStatusType EXCEPTION = new ItemStatusType(
            "EXCEPTION", "Exception");

    /**
     * @param shortName
     * @param friendlyName
     */
    public ItemStatusType(String shortName, String friendlyName) {
        super(shortName, friendlyName);
        addToCollection(this);
    }

    private static void addToCollection(ItemStatusType target) {
        if (getAll() == null)
            all = new ArrayList();

        all.add(target);
    }

    public static Collection getAll() {
        return all;
    }

    public static Collection getUpdatable() {
        if (updatable == null) {
            updatable = new ArrayList();
            updatable.add(ItemStatusType.ADDED);
            updatable.add(ItemStatusType.ACTIVE_ITEM);
            updatable.add(ItemStatusType.CLOSED_CANCELLED);
            updatable.add(ItemStatusType.CLOSED_NOT_SOLD);
            updatable.add(ItemStatusType.ITEM_IN_TRANSIT);
            updatable.add(ItemStatusType.NOT_SOLD_RELIST);
            updatable.add(ItemStatusType.NOT_SOLD_WAITING_FOR_PICKUP);
            updatable.add(ItemStatusType.READY_TO_PAY_NET);
            updatable.add(ItemStatusType.WAITING_FOR_PAYMENT);
            updatable.add(ItemStatusType.EXCEPTION);
        }
        return updatable;
    }

    public static ItemStatusType getByName(String target) {

        if (getAll() == null)
            return null;

        ItemStatusType ret = null;

        for (Iterator iter = getAll().iterator(); iter.hasNext();) {
            ItemStatusType element = (ItemStatusType) iter.next();

            if (element.getName().equals(target)) {
                ret = element;
                break;
            }
        }

        return ret;

    }

    public static boolean isModifiable(String name) {
        return (!(name.equals(ItemStatusType.POSTED_TO_ACCOUNT.getName()) || name
                .equals(ItemStatusType.CLOSED_SOLD.getName())));
    }

}