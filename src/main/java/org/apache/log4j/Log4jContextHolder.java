package org.apache.log4j;

/**
 * Created by 1234qwer on 2018/5/11.
 */
public class Log4jContextHolder {

    private static final ThreadLocal<String> KEY_CONTEXT = new ThreadLocal<String>();


    public static void setFileSplitKey(String key) {
        KEY_CONTEXT.set(key);
    }

    public static String getFileSplitKey() {
        return KEY_CONTEXT.get();
    }

}
