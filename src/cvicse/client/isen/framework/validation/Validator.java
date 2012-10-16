package cvicse.client.isen.framework.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cvicse.client.isen.framework.exception.ValidationException;
import cvicse.client.isen.framework.resource.Res;
import cvicse.client.isen.app.R;

/**
 * <p>
 * Validation utility class.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 2, 2011
 */
public class Validator {

	/**
	 * Validate phone number
	 * 
	 * @param str
	 */
	public static void validatePhoneNumber(String str) {
		validate(ValidationType.NULL, str,
				Res.resources.getString(R.string.msg_val_phone_number_null));
		validate(ValidationType.PHONE_NUMBER, str,
				Res.resources.getString(R.string.msg_val_phone_number_error));
	}
	
	/**
	 * Validate username
	 * 
	 * @param str
	 */
	public static void validateUsername(String str) {
		validate(ValidationType.NULL, str, Res.resources.getString(R.string.msg_val_username_null));
		validate(ValidationType.USERNAME, str, Res.resources.getString(R.string.msg_val_username_error));
	}

	/**
	 * Validate passwd
	 * 
	 * @param str
	 */
	public static void validatePasswd(String str) {
		validate(ValidationType.NULL, str, Res.resources.getString(R.string.msg_val_passwd_null));
		validate(ValidationType.PASSWORD, str, Res.resources.getString(R.string.msg_val_passwd_error));
	}

	/**
	 * Validate string by specified ValidationType. Support variables replacing
	 * in message.
	 * 
	 * @param type
	 *            ValidationType
	 * @param str
	 * @param msg
	 */
	public static void validate(ValidationType type, String str, String msg) {
		Pattern pattern = Pattern.compile(type.getRule());
		Matcher matcher = pattern.matcher(str);
		// Validation fail, throw exception
		if (!matcher.matches()) {
			throw new ValidationException(msg);
		}
	}
	
	/**
	 * Validate if two parameter are equal
	 * 
	 * @param obj1
	 * @param obj2
	 * @param msgId
	 * @param args
	 */
	public static void validateEquals(Object obj1, Object obj2, String msg) {
		if ((obj1 == null && obj2 != null) || !obj1.equals(obj2)) {
			throw new ValidationException(msg);
		}
	}

}