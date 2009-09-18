/*
 * Created on Jun 28, 2006
 *
 */
package com.fivesticks.time.common.json;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class ObjectToJSONConverter {

    private Log log = LogFactory.getLog(ObjectToJSONConverter.class);

    private int depth = 0;
    
    public JSONObject convert(Object conversionTarget) throws Exception {

        depth++;
        
        if (depth > 10) {
            throw new RuntimeException("too much recursion in JSON Object converters");
        }
        
        JSONObject ret = new JSONObject();

        Class c = conversionTarget.getClass();

        Method[] publicFields = c.getMethods();
        Object[] args = {};

        for (int i = 0; i < publicFields.length; i++) {

            if (publicFields[i].getName().startsWith("get")
                    || publicFields[i].getName().startsWith("is")) {
                String fieldName = publicFields[i].getName();
                if (!fieldName.equalsIgnoreCase("getClass")
                        && !fieldName.equalsIgnoreCase("getOwnerKey")) {
                    if (fieldName.startsWith("get")) {
                        fieldName = fieldName.substring(3);
                    } else {
                        fieldName = fieldName.substring(2);
                    }
                    fieldName = fieldName.substring(0, 1).toLowerCase()
                            + fieldName.substring(1);

//                    Class returnType = publicFields[i].getReturnType();



                    // 2006-08-30 gets all recursive.
//                    if (isSimpleClass(returnType)) {
                        ret.put(fieldName, publicFields[i].invoke(
                                conversionTarget, args));
//                    } else if (returnType.getName().equals(
//                            Collection.class.getName())
//                            || returnType.getName()
//                                    .equals(List.class.getName())) {
//                        ret.put(fieldName, convertCollection(publicFields[i]
//                                .invoke(conversionTarget, args)));
//                    } else if (publicFields[i].getParameterTypes().length > 0) {
//                        log.info("method " + fieldName + " requires args "
//                                + args.length);
//                    } else {
//                        ret.put(fieldName, convert(publicFields[i].invoke(
//                                conversionTarget, args)));
//                    }
                }
            }

        }

        depth--;
        
        return ret;

    }

    public boolean isSimpleClass(Class returnType) {

        boolean ret = returnType.isPrimitive()
                || returnType.isInstance(new String())
                || returnType.isInstance(new Long(1))
                || returnType.isInstance(new Integer(1))
                || returnType.isInstance(new Boolean(false));

        log.info("Class " + returnType.getName() + " simple? " + ret);

        return ret;

    }

    public JSONArray convertCollection(Object o) throws Exception {
        if (!(o instanceof Collection)) {
            throw new RuntimeException(
                    "Can only convert instances of collection");
        }
        Collection target = (Collection) o;
        return convertCollection(target);
    }

    public JSONArray convertCollection(Collection targetCollection)
            throws Exception {

        JSONArray ret = new JSONArray();

        for (Iterator iter = targetCollection.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();

            // 2006-08-30 do I need to worry about this?
            if (element instanceof Collection) {
                throw new RuntimeException("only simple objects, please");
            }

            ret.put(this.convert(element));
        }

        return ret;

    }
}
