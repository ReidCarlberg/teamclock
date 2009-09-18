/*
 * Created on Jan 21, 2005
 *
 * 
 */
package com.fivesticks.time.common.util;

import java.util.Comparator;

import com.opensymphony.xwork.util.OgnlValueStack;

/**
 * @author Nick
 * 
 * 
 */
public class FstxSorter {

    private boolean caseInsensitive;

    public FstxSorter() {
        super();
        this.caseInsensitive = false;
    }

    public FstxSorter(boolean caseInsensitive) {
        super();
        this.caseInsensitive = caseInsensitive;
    }

    public Comparator getAscending() {
        return new Comparator() {
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Comparable && !(o1 instanceof String)) {
                    return ((Comparable) o1).compareTo(o2);
                } else {
                    String s1 = caseInsensitive ? o1.toString().toLowerCase()
                            : o1.toString();
                    String s2 = caseInsensitive ? o2.toString().toLowerCase()
                            : o2.toString();
                    return s1.compareTo(s2);
                }
            }
        };
    }

    public Comparator getDescending() {
        return new Comparator() {
            public int compare(Object o1, Object o2) {
                if (o2 instanceof Comparable && !(o2 instanceof String)) {
                    return ((Comparable) o2).compareTo(o1);
                } else {
                    String s1 = caseInsensitive ? o1.toString().toLowerCase()
                            : o1.toString();
                    String s2 = caseInsensitive ? o2.toString().toLowerCase()
                            : o2.toString();
                    return s2.compareTo(s1);
                }
            }
        };
    }

    public Comparator getComparator(String anExpression, boolean ascending) {
        if (ascending)
            return getAscending(anExpression);
        else
            return getDescending(anExpression);
    }

    private Comparator getAscending(String anExpression) {
        return this.getAscending(anExpression, null);
    }

    public Comparator getAscending(final String anExpression,
            final String anotherExpression) {
        return new Comparator() {
            private OgnlValueStack stack = new OgnlValueStack();

            public int compare(Object o1, Object o2) {

                int ret = 0;

                // Try first parameter
                ret = this.handleExpression(anExpression, o1, o2);

                // If that was not sufficiant to try the nest param
                // if one was passed in.
                if (ret == 0 && anotherExpression != null) {
                    ret = this.handleExpression(anotherExpression, o1, o2);
                }

                return ret;

            }

            int handleExpression(String exp, Object o1, Object o2) {

                int ret = 0;
                // Get value for first object
                stack.push(o1);
                Object v1 = stack.findValue(exp);
                stack.pop();

                // Get value for second object
                stack.push(o2);
                Object v2 = stack.findValue(exp);
                stack.pop();

                // Ensure non-null
                if (v1 == null)
                    v1 = "";
                if (v2 == null)
                    v2 = "";

                // Compare them
                if (v1 instanceof Comparable && !(v1 instanceof String)
                        && v1.getClass().equals(v2.getClass())) {
                    ret = ((Comparable) v1).compareTo(v2);

                } else {
                    String s1 = caseInsensitive ? v1.toString().toLowerCase()
                            : v1.toString();
                    String s2 = caseInsensitive ? v2.toString().toLowerCase()
                            : v2.toString();
                    ret = s1.compareTo(s2);

                }

                return ret;
            }
        };
    }

    public Comparator getAscendingAllUnique(final String anExpression,
            final String anotherExpression) {
        return new Comparator() {
            private OgnlValueStack stack = new OgnlValueStack();

            public int compare(Object o1, Object o2) {

                int ret = 0;

                // Try first parameter
                ret = this.handleExpression(anExpression, o1, o2);

                // If that was not sufficiant to try the nest param
                // if one was passed in.
                if (ret == 0 && anotherExpression != null) {
                    ret = this.handleExpression(anotherExpression, o1, o2);
                }

                return ret == 0 ? 1 : ret;

            }

            int handleExpression(String exp, Object o1, Object o2) {

                int ret = 0;
                // Get value for first object
                stack.push(o1);
                Object v1 = stack.findValue(exp);
                stack.pop();

                // Get value for second object
                stack.push(o2);
                Object v2 = stack.findValue(exp);
                stack.pop();

                // Ensure non-null
                if (v1 == null)
                    v1 = "";
                if (v2 == null)
                    v2 = "";

                // Compare them
                if (v1 instanceof Comparable && !(v1 instanceof String)
                        && v1.getClass().equals(v2.getClass())) {
                    ret = ((Comparable) v1).compareTo(v2);

                } else {
                    String s1 = caseInsensitive ? v1.toString().toLowerCase()
                            : v1.toString();
                    String s2 = caseInsensitive ? v2.toString().toLowerCase()
                            : v2.toString();
                    ret = s1.compareTo(s2);

                }

                return ret;
            }
        };
    }

    public Comparator getDescending(final String anExpression) {
        return new Comparator() {
            private OgnlValueStack stack = new OgnlValueStack();

            public int compare(Object o1, Object o2) {
                // Get value for first object
                stack.push(o1);
                String v1 = stack.findValue(anExpression).toString();
                stack.pop();

                // Get value for second object
                stack.push(o2);
                String v2 = stack.findValue(anExpression).toString();
                stack.pop();

                // Ensure non-null
                if (v1 == null)
                    v1 = "";
                if (v2 == null)
                    v2 = "";

                // Compare them
                if (v2 instanceof Comparable && !(v2 instanceof String)
                        && v1.getClass().equals(v2.getClass())) {
                    return ((Comparable) v2).compareTo(v1);
                } else {
                    String s1 = caseInsensitive ? v1.toString().toLowerCase()
                            : v1.toString();
                    String s2 = caseInsensitive ? v2.toString().toLowerCase()
                            : v2.toString();
                    return s2.compareTo(s1);
                }
            }
        };
    }

    public Comparator getDescending(final String anExpression,
            final String anotherExpression) {

        return new Comparator() {
            private OgnlValueStack stack = new OgnlValueStack();

            public int compare(Object o1, Object o2) {

                int ret = 0;

                // Try first parameter
                ret = this.handleExpression(anExpression, o1, o2);

                // If that was not sufficiant to try the nest param
                // if one was passed in.
                if (ret == 0 && anotherExpression != null) {
                    ret = this.handleExpression(anotherExpression, o1, o2);
                }

                return ret;

            }

            int handleExpression(String exp, Object o1, Object o2) {

                int ret = 0;
                // Get value for first object

                /*
                 * For desc we just change the v1 and v2 values so v2.
                 */
                stack.push(o1);
                Object v2 = stack.findValue(exp);
                stack.pop();

                // Get value for second object
                stack.push(o2);
                Object v1 = stack.findValue(exp);
                stack.pop();

                // Ensure non-null
                if (v1 == null)
                    v1 = "";
                if (v2 == null)
                    v2 = "";

                // Compare them
                if (v1 instanceof Comparable && !(v1 instanceof String)
                        && v1.getClass().equals(v2.getClass())) {
                    ret = ((Comparable) v1).compareTo(v2);

                } else {
                  String s1 = caseInsensitive ? v1.toString().toLowerCase()
                            : v1.toString();
                    String s2 = caseInsensitive ? v2.toString().toLowerCase()
                            : v2.toString();
                    ret = s1.compareTo(s2);

                }

                return ret;
            }
        };
    }

    public Comparator getDescendingAllUnique(final String anExpression,
            final String anotherExpression) {

        return new Comparator() {
            private OgnlValueStack stack = new OgnlValueStack();

            public int compare(Object o1, Object o2) {

                int ret = 0;

                // Try first parameter
                ret = this.handleExpression(anExpression, o1, o2);

                // If that was not sufficiant to try the nest param
                // if one was passed in.
                if (ret == 0 && anotherExpression != null) {
                    ret = this.handleExpression(anotherExpression, o1, o2);
                }

                return ret == 0 ? 1 : ret;

            }

            int handleExpression(String exp, Object o1, Object o2) {

                int ret = 0;
                // Get value for first object

                /*
                 * For desc we just change the v1 and v2 values so v2.
                 */
                stack.push(o1);
                Object v2 = stack.findValue(exp);
                stack.pop();

                // Get value for second object
                stack.push(o2);
                Object v1 = stack.findValue(exp);
                stack.pop();

                // Ensure non-null
                if (v1 == null)
                    v1 = "";
                if (v2 == null)
                    v2 = "";

                // Compare them
                if (v1 instanceof Comparable && !(v1 instanceof String)
                        && v1.getClass().equals(v2.getClass())) {
                    ret = ((Comparable) v1).compareTo(v2);

                } else {
                    String s1 = caseInsensitive ? v1.toString().toLowerCase()
                            : v1.toString();
                    String s2 = caseInsensitive ? v2.toString().toLowerCase()
                            : v2.toString();
                    ret = s1.compareTo(s2);

                }

                return ret;
            }
        };
    }

}