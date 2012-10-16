package cvicse.client.isen.framework.util;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 30, 2009
 */
public class StringUtil {

    /**
     * Fix the length for string. If the length of string is less than appointed length, prefixStr will be added in the front of this String.
     * @param str Original string
     * @param length The length you want to fix the string
     * @param prefixStr Indicate which string will be added in the front of string
     * @return Result string
     */
    public static String prefixString(String str, int length, String prefixStr) {
        if (str == null) {
            return str;
        }

        if (str.length() >= length) {
            return str;
        }

        for (int i = str.length(); i < length; i++) {
            str = prefixStr + str;
        }

        return str;
    }

    /**
     * Fix the length for string. If the length of string is less than appointed length, default value "0" will be added in the front of this String.
     * @param str Original string
     * @param length The length you want to fix the string
     * @return Result string
     */
    public static String prefixString(String str, int length) {
        return prefixString(str, length, "0");
    }

    /**
     * Conver initial letter of specified string to upper case
     * @param item Original string
     * @return Result string
     */
    public static String toInitialUpperCase(String item) {
        String returnItem = Character.toUpperCase(item.charAt(0)) + item.substring(1);
        return returnItem;
    }

    /**
     * Conver initial letter of specified string to lower case
     * @param item Original string
     * @return Result string
     */
    public static String toInitialLowerCase(String item) {
        String returnItem = Character.toLowerCase(item.charAt(0)) + item.substring(1);
        return returnItem;
    }

    /**
     * Convert string to the class name format, initial letter is upper case. The separater char between words is "_".
     * Such as: USER_NAME convert to UserName
     *
     * @param item Original string
     * @return Result string
     */
    public static String convertToUpperVarName(String item) {
        StringBuffer returnBuf = new StringBuffer();

        String[] itemArr = item.split("_");
        for (int i = 0; i < itemArr.length; i++) {
            returnBuf.append(
                toInitialUpperCase(
                    itemArr[i].toLowerCase()));
        }

        return returnBuf.toString();
    }

    /**
     * Convert string to the class name format, initial letter is lower case. The separater char between words is "_".
     * Such as: USER_NAME convert to userName
     *
     * @param item Original string
     * @return Result string
     */
    public static String convertToLowerVarName(String item) {
        String returnItem = convertToUpperVarName(item);
        returnItem = toInitialLowerCase(returnItem);

        return returnItem;
    }
    
    /**
     * Return getter method name for specified property name
     * @param fieldName
     * @return
     */
    public static String getGetterMethodName(String fieldName) {
    	return "get" + toInitialUpperCase(fieldName);
    }

    /**
     * Return setter method name for specified property name
     * @param fieldName
     * @return
     */
    public static String getSetterMethodName(String fieldName) {
    	return "set" + toInitialUpperCase(fieldName);
    }

    /**
     * Fix path, add the file separator at the end of the path and eliminate the file separator at the begin of the path.
     *
     * @param path Original path string
     * @return Result path string
     */
    public static String revisePath(String path) {
    	if(path.endsWith("/") || path.endsWith("\\")) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.startsWith("/") || path.startsWith("\\")) {
            path = path.substring(1);
        }
        path += "/";
        return path;
    }

    /**
     * Convert package string to path string, replace dot to solidus
     *
     * @param pkg Jave package string
     * @return Result path string
     */
    public static String convertPkgToPath(String pkg) {
        return revisePath(pkg.replaceAll("\\.", "/"));
    }
    
    /**
     * Extract only calss name from specified full class name.
     * @param fullClassName Including package
     * @return String only class name
     */
    public static String extractClassName(String fullClassName) {
    	return fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
    }

    /**
     * Judge whether the specified string is null or empty
     *
     * @param str Original string
     * @return boolean
     */
    public static boolean isBlank(String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * Convert a String to an boolean.
     * Accepts: 1/0, yes/no, true/false - case insensitive. If the value does
     * not map to "true,", <code>false</code> is returned.
     *
     * @param in String to be parsed.
     * @return boolean representation of String.
     */
    public final static boolean parseBoolean(String in) {
        in = noNull(in);

        if (in.length() == 0) {
            return false;
        }

        switch (in.charAt(0)) {
        case '1':
        case 'y':
        case 'Y':
        case 't':
        case 'T':
            return true;
        }

        return false;
    }

    /**
     * Return <code>string</code>, or <code>defaultString</code> if
     * <code>string</code> is <code>null</code> or <code>""</code>.
     * Never returns <code>null</code>.
     *
     * <p>Examples:</p>
     * <pre>
     * // prints "hello"
     * String s=null;
     * System.out.println(TextUtils.noNull(s,"hello");
     *
     * // prints "hello"
     * s="";
     * System.out.println(TextUtils.noNull(s,"hello");
     *
     * // prints "world"
     * s="world";
     * System.out.println(TextUtils.noNull(s, "hello");
     * </pre>
     *
     * @param string the String to check.
     * @param defaultString The default string to return if <code>string</code> is <code>null</code> or <code>""</code>
     * @return <code>string</code> if <code>string</code> is non-empty, and <code>defaultString</code> otherwise
     * @see #stringSet(java.lang.String)
     */
    public final static String noNull(String string, String defaultString) {
        return (!isBlank(string)) ? string : defaultString;
    }

    /**
     * Return <code>string</code>, or <code>""</code> if <code>string</code>
     * is <code>null</code>. Never returns <code>null</code>.
     * <p>Examples:</p>
     * <pre>
     * // prints 0
     * String s=null;
     * System.out.println(TextUtils.noNull(s).length());
     *
     * // prints 1
     * s="a";
     * System.out.println(TextUtils.noNull(s).length());
     * </pre>
     * @param string the String to check
     * @return a valid (non-null) string reference
     */
    public final static String noNull(String string) {
        return noNull(string, "");
    }
    
    /**
     * Truncate string to specified length.
     * @param str
     * @param length
     * @return Truncated string
     */
    public final static String truncateString(String str, int length) {
		if(str != null && str.length() > length) {
			str = str.substring(0, length);
		}
		return str;
	}
    
    /**
     * Format phone number as +country code + no.
     * @param countryCode
     * @param phoneNumber
     * @return
     */
    public final static String formatPhoneNumber(String countryCode, String phoneNumber) {
    	String result = phoneNumber;
//    	if(!phoneNumber.startsWith("+")) {
//    		result = "+" + noNull(countryCode) + phoneNumber;
//    	}
    	return result;
    }

}
