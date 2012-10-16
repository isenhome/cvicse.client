package cvicse.client.isen.framework.logging;

import cvicse.client.isen.framework.Conf;
import android.util.Log;

/**
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Sep 1, 2011
 */
public class BysLogger {
    /**
     * log message header
     */
//    private static final String MSG_HEADER = "[Wireless City]";
	
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int LOG_LEVEL_VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int LOG_LEVEL_DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int LOG_LEVEL_INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int LOG_LEVEL_WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int LOG_LEVEL_ERROR = 6;

    /**
     * class Object
     */
    private static BysLogger instance = new BysLogger();

    /**
     * Rights level
     */
    private int level;

    private BysLogger() {
    	setLevel(Conf.LOG_LEVEL);
    }

    /**
     * get the instance
     *
     * @return this class instance
     * @author byron
     */
    public static BysLogger getLogger() {
        return instance;
    }
    
    public boolean isVerbose() {
    	return getLevel() <= LOG_LEVEL_VERBOSE;
    }
    
    public boolean isDebug() {
    	return getLevel() <= LOG_LEVEL_DEBUG;
    }
    
    public boolean isInfo() {
    	return getLevel() <= LOG_LEVEL_INFO;
    }
    
    public boolean isWarn() {
    	return getLevel() <= LOG_LEVEL_WARN;
    }
    
    public boolean isError() {
    	return getLevel() <= LOG_LEVEL_ERROR;
    }

    // msg encapsulation process
    private String fixMsg(String msg) {
    	String invokerLocation = null;

		StackTraceElement[] stes = (new Exception()).getStackTrace();
		if(stes.length < 4) {
			throw new IllegalStateException("Analyzing log invoker information error. ");
		}
			
		StackTraceElement ste = stes[3];
		invokerLocation = "[" + ste.getClassName() + "." + ste.getMethodName() + "(line:" + ste.getLineNumber() + ")]";

        return invokerLocation + " - " + msg;
    }

    /**
     * Fix logTag if it is null
     *
     * @param logTag
     * @return
     */
    private String fixTag(LogTag logTag) {
        LogTag result = logTag;
        if (logTag == null) {
            result = LogTag.NORMAL;
        }
        return result.toString();
    }

    // set rights level
    // @param levString rights-level description
    public void setLevel(String levString) {
        if ("VERBOSE".equalsIgnoreCase(levString)) {
            level = LOG_LEVEL_VERBOSE;
        } else if ("DEBUG".equalsIgnoreCase(levString)) {
            level = LOG_LEVEL_DEBUG;
        } else if ("INFO".equalsIgnoreCase(levString)) {
            level = LOG_LEVEL_INFO;
        } else if ("WARN".equalsIgnoreCase(levString)) {
            level = LOG_LEVEL_WARN;
        } else if ("ERROR".equalsIgnoreCase(levString)) {
            level = LOG_LEVEL_ERROR;
        } else {
            level = LOG_LEVEL_ERROR;
        }
    }
    
    /**
     * @return
     */
    public int getLevel() {
    	return level;
    }

    /**
     * Send a {@link #VERBOSE} log message.
     *
     * @param msg The message you would like logged.
     * @author byron
     */
    public int verbose(String msg) {
        return doVerbose(null, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @author byron
     */
    public int verbose(LogTag tag, String msg) {
        return doVerbose(tag, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author Roy
     */
    public int verbose(String msg, Throwable tr) {
        return doVerbose(null, msg, tr);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author byron
     */
    public int verbose(LogTag tag, String msg, Throwable tr) {
    	return doVerbose(tag, msg, tr);
    }
    
    private int doVerbose(LogTag tag, String msg, Throwable tr) {
        int result = -1;
        if (level <= LOG_LEVEL_VERBOSE) {
            if (null == tr) {
                result = Log.v(fixTag(tag), fixMsg(msg));
            } else {
                result = Log.v(fixTag(tag), fixMsg(msg), tr);
            }
        }
        return result;
    }

    /**
     * Send a {@link #DEBUG} log message.
     *
     * @param msg The message you would like logged.
     * @author byron
     */
    public int debug(String msg) {
        return doDebug(null, msg, null);
    }

    /**
     * Send a {@link #DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @author byron
     */
    public int debug(LogTag tag, String msg) {
        return doDebug(tag, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author Roy
     */
    public int debug(String msg, Throwable tr) {
        return doDebug(null, msg, tr);
    }

    /**
     * Send a {@link #DEBUG} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author byron
     */
    public int debug(LogTag tag, String msg, Throwable tr) {
    	return doDebug(tag, msg, tr);
    }
    
    private int doDebug(LogTag tag, String msg, Throwable tr) {
        int result = -1;
        if (level <= LOG_LEVEL_DEBUG) {
            if (null == tr) {
                result = Log.d(fixTag(tag), fixMsg(msg));
            } else {
                result = Log.d(fixTag(tag), fixMsg(msg), tr);
            }
        }
        return result;
    }

    /**
     * Send an {@link #INFO} log message.
     *
     * @param msg The message you would like logged.
     * @author byron
     */
    public int info(String msg) {
        return doInfo(null, msg, null);
    }

    /**
     * Send an {@link #INFO} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @author byron
     */
    public int info(LogTag tag, String msg) {
        return doInfo(tag, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author Roy
     */
    public int info(String msg, Throwable tr) {
        return doInfo(null, msg, tr);
    }

    /**
     * Send a {@link #INFO} log message and log the exception.
     *
     * @param tag    Used to identify the source of a log message. It usually
     *               identifies the class or activity where the log call occurs.
     * @param fixMsg (msg) The message you would like logged.
     * @param tr     An exception to log
     * @author byron
     */
    public int info(LogTag tag, String msg, Throwable tr) {
    	return doInfo(tag, msg, tr);
    }
    
    private int doInfo(LogTag tag, String msg, Throwable tr) {
        int result = -1;
        if (level <= LOG_LEVEL_INFO) {
            if (null == tr) {
                result = Log.i(fixTag(tag), fixMsg(msg));
            } else {
                result = Log.i(fixTag(tag), fixMsg(msg), tr);
            }
        }
        return result;
    }

    /**
     * Send a {@link #WARN} log message.
     *
     * @param fixMsg (msg) The message you would like logged.
     * @author byron
     */
    public int warn(String msg) {
        return doWarn(null, msg, null);
    }

    /**
     * Send a {@link #WARN} log message.
     *
     * @param tag    Used to identify the source of a log message. It usually
     *               identifies the class or activity where the log call occurs.
     * @param fixMsg (msg) The message you would like logged.
     * @author byron
     */
    public int warn(LogTag tag, String msg) {
        return doWarn(tag, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author Roy
     */
    public int warn(String msg, Throwable tr) {
        return doWarn(null, msg, tr);
    }

    /**
     * Send a {@link #WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author byron
     */
    public int warn(LogTag tag, String msg, Throwable tr) {
    	return doWarn(tag, msg, tr);
    }
    
    private int doWarn(LogTag tag, String msg, Throwable tr) {
        int result = -1;
        if (level <= LOG_LEVEL_WARN) {
            if (null == tr) {
                result = Log.w(fixTag(tag), fixMsg(msg));
            } else {
                result = Log.w(fixTag(tag), fixMsg(msg), tr);
            }
        }
        return result;
    }

    /**
     * Send an {@link #ERROR} log message.
     *
     * @param msg The message you would like logged.
     * @author byron
     */
    public int error(String msg) {
        return doError(null, msg, null);
    }

    /**
     * Send an {@link #ERROR} log message.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @author byron
     */
    public int error(LogTag tag, String msg) {
        return doError(tag, msg, null);
    }

    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     *
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author Roy
     */
    public int error(String msg, Throwable tr) {
        return doError(null, msg, tr);
    }

    /**
     * Send a {@link #ERROR} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually
     *            identifies the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     * @author byron
     */
    public int error(LogTag tag, String msg, Throwable tr) {
    	return doError(tag, msg, tr);
    }

    private int doError(LogTag tag, String msg, Throwable tr) {
    	int result = -1;
        if (level <= LOG_LEVEL_ERROR) {
            if (null == tr) {
                result = Log.e(fixTag(tag), fixMsg(msg));
            } else {
                result = Log.e(fixTag(tag), fixMsg(msg), tr);
            }
        }
        return result;
    }

}
