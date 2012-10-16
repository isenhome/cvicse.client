package cvicse.client.isen.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Nov 19, 2011
 */
public class BeanUtil {
	
    /**
     * Set value for specified property
     * @param <T>
     * @param obj
     * @param propertyName
     * @param value
     * @throws Exception
     */
    public static <T> void setupProperty(T obj, String propertyName, String value) throws Exception {
    	Class<?> cls = obj.getClass();
    	Field property = cls.getField(propertyName);
    	String typeName = property.getType().getSimpleName();
    	
    	if(property.getModifiers() == Modifier.PUBLIC) {
        	//public property, set value directly
    		if("String".equalsIgnoreCase(typeName)) {
    			property.set(obj, value);
    		} else if ("Integer".equalsIgnoreCase(typeName)
    				|| "int".equalsIgnoreCase(typeName)) {
    			property.setInt(obj, Integer.parseInt(value));
    		} else if ("Long".equalsIgnoreCase(typeName)
    				|| "long".equalsIgnoreCase(typeName)) {
    			property.setLong(obj, Long.parseLong(value));
    		} else {
    			throw new IllegalStateException("There is no corresponding type \"" + typeName + "\" defined for property.");
    		}
    	} else {
        	//non public property, set value by setter method
	    	String setterName = "set" + StringUtil.toInitialUpperCase(propertyName);
	    	Method method = obj.getClass().getMethod(setterName, property.getType());
	    	method.invoke(obj, value);
	    	}
    }

    /**
     * Copy bean properties. The property which is empty value will be ignored.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyPropertiesExcludeEmpty(Object dest, Object source) {
    	copyPropertiesByCase(dest, source, CopyCase.EMPTY);
    }

    /**
     * Copy bean properties. The property which is empty or null value will be ignored.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyPropertiesExcludeNullAndEmpty(Object dest, Object source) {
    	copyPropertiesByCase(dest, source, CopyCase.BOTH);
    }

    /**
     * Just invoke spring's BeanUtils for the convenience.
     * 
     * @param dest Destination object
     * @param source Source object
     */
    public static void copyProperties(Object dest, Object source) {
        
    }

    /**
     * Copy bean properties base on specified copyCase.
     * 
     * @param dest Destination object
     * @param source Source object
     * @param copyCase CopyCase object
     */
    private static void copyPropertiesByCase(Object dest, Object source, CopyCase copyCase) {
//        BeanWrapper srcWrapper = new BeanWrapperImpl(source);
//        //Iterate all properties
//        ArrayList<Object> ignorePropertyList = new ArrayList<Object>();
//        for (int i = 0; i < srcWrapper.getPropertyDescriptors().length; i++) {
//            PropertyDescriptor sourceDesc = srcWrapper.getPropertyDescriptors()[i];
//            String name = sourceDesc.getName();
//            Object value = srcWrapper.getPropertyValue(name);
//            switch(copyCase) {
//            	case NULL:
//                    //exclude the null property
//                    if(value == null) {
//                        ignorePropertyList.add(name);
//                    }
//            		break;
//            	case EMPTY:
//                    //exclude the empty property
//                    if("".equals(value)) {
//                        ignorePropertyList.add(name);
//                    }
//            		break;
//            	case BOTH:
//                    //exclude both null and empty property
//                    if(value == null || "".equals(value)) {
//                        ignorePropertyList.add(name);
//                    }
//            		break;
//            }
//        }
//        String[] ignoreProperties = new String[ignorePropertyList.size()];
//        ignoreProperties = (String[])ignorePropertyList.toArray(ignoreProperties);
//        BeanUtils.copyProperties(source, dest, ignoreProperties);
    }

    /**
     * Copy cases. Indicate which case should be ignored when copying.
     */
    private enum CopyCase{
    	NULL,
    	EMPTY,
    	BOTH
    }

}
