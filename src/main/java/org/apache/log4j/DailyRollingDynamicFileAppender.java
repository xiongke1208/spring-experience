package org.apache.log4j;

import com.counect.probe.utils.StringUtil;
import org.apache.log4j.spi.LoggingEvent;

import java.util.*;

public class DailyRollingDynamicFileAppender extends AppenderSkeleton {


    private String datePattern = "'.'yyyy-MM-dd";
    private String fileName;
    protected boolean fileAppend = true;

    private static final Map<String,Appender> ALL_APPENDERS = new HashMap<>();
    private static final  Set<String> ALIVES = new HashSet<>();

    private long nextCheckTime = getCheckNextTime(new Date());

    private long getCheckNextTime(Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        //半天检查一次，半天 > t < 1天 之内没有被使用的appender会被清除掉
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour < 12) {
            c.set(Calendar.HOUR_OF_DAY, 12);
        } else {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.add(Calendar.DAY_OF_MONTH, 1);
        }
        //测试时使用的时间间隔
//        c.add(Calendar.MILLISECOND, 10);

        return c.getTime().getTime();
    }


    public void setFile(String file) {
        String val = file.trim();
        fileName = val;
    }
    public void setDatePattern(String pattern) {
        datePattern = pattern;
    }
    public void setAppend(boolean flag) {
        fileAppend = flag;
    }

    public DailyRollingDynamicFileAppender(){
        System.out.println("=========org.apache.log4j.DailyRollingDynamicFileAppender==========");
    }

    /**
     * 因为调用者doAppend方法用synchronized修饰，所以该方法不关心线程同步问题
     * @param event
     */
    @Override
    protected void append(LoggingEvent event) {

        if(System.currentTimeMillis() > nextCheckTime) {
            System.out.println("closeNotAliveAppend start");
            closeNotAliveAppender();
            System.out.println("closeNotAliveAppend end");
            nextCheckTime = getCheckNextTime(new Date());
        }

        String key = Log4jContextHolder.getFileSplitKey();
        Appender appender = ALL_APPENDERS.get(key);
        String appendFile = fileName.replace("_{key}", StringUtil.isEmpty(key) ? "" : "_"+key);
        System.out.println(appendFile);
        if(appender == null) {
            System.out.println("============init append by key:"+ key+"================");
            try {
                appender = new DailyRollingFileAppender(layout,appendFile,datePattern);
            } catch (Exception e){
                e.printStackTrace();
            }
            ALL_APPENDERS.put(key, appender);
        }


        //记录alive的appender
        if(!ALIVES.contains(key)) {
            ALIVES.add(key);
        }

        appender.doAppend(event);
    }

    private void closeNotAliveAppender() {
        HashSet<String> tmp = new HashSet(ALL_APPENDERS.keySet());
        tmp.removeAll(ALIVES);

        System.out.println("=================cleard appender:"+tmp);

        for (String del : tmp) {
            ALL_APPENDERS.remove(del).close();
        }

        ALIVES.clear();
    }

    @Override
    public void close() {

        System.out.println("=========org.apache.log4j.DailyRollingDynamicFileAppender close==========");


        for(Appender appender : ALL_APPENDERS.values()) {
            try {
                appender.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ALL_APPENDERS.clear();

    }


    @Override
    public boolean requiresLayout() {
        return true;
    }
}
