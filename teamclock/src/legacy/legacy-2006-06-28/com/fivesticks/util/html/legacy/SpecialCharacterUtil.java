/*
 * Created on Jun 1, 2006
 *
 */
package com.fivesticks.util.html.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SpecialCharacterUtil {

    Collection special = null;

    public static final SpecialCharacterUtil singleton = new SpecialCharacterUtil();
    
    public String encode(String string) {
        // do we need to do anything?
        /*
         * if (string.indexOf("&") == -1) { return string; }
         */

        StringBuffer ret = new StringBuffer();

        StringBuffer temp = null;

        boolean active = false;

        for (int i = 0; i < string.length(); i++) {
            String current = string.substring(i, i + 1);
            if (!active) {
                if (current.equals("&")) {
                    active = true;
                    temp = new StringBuffer();
                    temp.append(current);
                } else if (current.equals("<")) {
                    ret.append("&lt;");
                } else if (current.equals(">")) {
                    ret.append("&gt;");
                } else if (current.equals("'")) {
                    ret.append("&apos;");
                } else if (current.equals("\"")) {
                    ret.append("&quot;");
                } else {
                    ret.append(current);
                }
            } else {
                // special characters are less than 7 chars and only include
                // alphas
                if (temp.length() <= 8 && current.matches("[a-zA-Z0-9]")) {
                    temp.append(current);
                    // special chars are longer than 2 chars and terminate with
                    // a ";"
                } else if (temp.length() > 2 && current.matches(";")) {
                    temp.append(current);
                    if (isEncodedSpecialChar(temp.toString())) {
                        ret.append(temp);
                    } else {
                        ret.append("&amp;" + temp.substring(1));
                    }
                    temp = null;
                    active = false;
                    // some other
                } else {
                    ret.append("&amp;" + temp.substring(1));
                    ret.append(current);
                    temp = null;
                    active = false;
                }

            }

        }

        if (temp != null && temp.length() > 0) {
            ret.append("&amp;" + temp.substring(1));
        }

        return ret.toString();

    }

    private boolean isEncodedSpecialChar(String string) {

        if (special == null) {
            initializeSpecialCharacterList();
        }

        boolean ret = false;

        for (Iterator iter = special.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            if (element.equals(string)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    private void initializeSpecialCharacterList() {

        special = new ArrayList();
        special.add("&apos;");
        special.add("&amp;");
        special.add("&lsquo;");
        special.add("&rsquo;");
        special.add("&sbquo;");
        special.add("&ldquo;");
        special.add("&rdquo;");
        special.add("&bdquo;");
        special.add("&dagger;");
        special.add("&Dagger;");
        special.add("&permil;");
        special.add("&lsaquo;");
        special.add("&rsaquo;");
        special.add("&spades;");
        special.add("&clubs;");
        special.add("&hearts;");
        special.add("&diams;");
        special.add("&oline;");
        special.add("&larr;");
        special.add("&uarr;");
        special.add("&rarr;");
        special.add("&darr;");
        special.add("&trade;");
        special.add("&quot;");
        special.add("&amp;");
        special.add("&frasl;");
        special.add("&lt;");
        special.add("&gt;");
        special.add("&ndash;");
        special.add("&mdash;");
        special.add("&nbsp;");
        special.add("&iexcl;");
        special.add("&cent;");
        special.add("&pound;");
        special.add("&curren;");
        special.add("&yen;");
        special.add("&brvbar;");
        special.add("&brkbar");
        special.add("&sect;");
        special.add("&uml;");
        special.add("&die;");
        special.add("&copy;");
        special.add("&ordf;");
        special.add("&laquo;");
        special.add("&not;");
        special.add("&shy;");
        special.add("&reg;");
        special.add("&macr; ");
        special.add("&hibar;");
        special.add("&deg;");
        special.add("&plusmn;");
        special.add("&sup2;");
        special.add("&sup3;");
        special.add("&acute;");
        special.add("&micro;");
        special.add("&para;");
        special.add("&middot;");
        special.add("&cedil;");
        special.add("&sup1;");
        special.add("&ordm;");
        special.add("&raquo;");
        special.add("&frac14;");
        special.add("&frac12;");
        special.add("&frac34;");
        special.add("&iquest;");
        special.add("&Agrave;");
        special.add("&Aacute;");
        special.add("&Acirc;");
        special.add("&Atilde;");
        special.add("&Auml;");
        special.add("&Aring;");
        special.add("&AElig;");
        special.add("&Ccedil;");
        special.add("&Egrave;");
        special.add("&Eacute;");
        special.add("&Ecirc;");
        special.add("&Euml;");
        special.add("&Igrave;");
        special.add("&Iacute;");
        special.add("&Icirc;");
        special.add("&Iuml;");
        special.add("&ETH;");
        special.add("&Ntilde;");
        special.add("&Ograve;");
        special.add("&Oacute;");
        special.add("&Ocirc;");
        special.add("&Otilde;");
        special.add("&Ouml;");
        special.add("&times;");
        special.add("&Oslash;");
        special.add("&Ugrave;");
        special.add("&Uacute;");
        special.add("&Ucirc;");
        special.add("&Uuml;");
        special.add("&Yacute;");
        special.add("&THORN;");
        special.add("&szlig;");
        special.add("&agrave;");
        special.add("&aacute;");
        special.add("&acirc;");
        special.add("&atilde;");
        special.add("&auml;");
        special.add("&aring;");
        special.add("&aelig;");
        special.add("&ccedil;");
        special.add("&egrave;");
        special.add("&eacute;");
        special.add("&ecirc;");
        special.add("&euml;");
        special.add("&igrave;");
        special.add("&iacute;");
        special.add("&icirc;");
        special.add("&iuml;");
        special.add("&eth;");
        special.add("&ntilde;");
        special.add("&ograve;");
        special.add("&oacute;");
        special.add("&ocirc;");
        special.add("&otilde;");
        special.add("&ouml;");
        special.add("&divide;");
        special.add("&oslash;");
        special.add("&ugrave;");
        special.add("&uacute;");
        special.add("&ucirc;");
        special.add("&uuml;");
        special.add("&yacute;");
        special.add("&thorn;");
        special.add("&yuml;");

    }
}
