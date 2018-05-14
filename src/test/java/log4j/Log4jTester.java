package log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Log4jContextHolder;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 1234qwer on 2018/5/14.
 */
public class Log4jTester {

    private Log log = LogFactory.getLog(Log4jTester.class);
    private Log log2 = LogFactory.getLog("aaaaaaaaa");
    private Log log3 = LogFactory.getLog("bbbbbbbb");


    @Test
    public void test() throws Exception {

       final CountDownLatch c = new CountDownLatch(50);

       final Random r = new Random();

        for(int i=0; i<50; i++) {
            Thread t = new Thread(){
                @Override
                public void run() {
                    Log4jContextHolder.setFileSplitKey(String.valueOf(r.nextInt(5)));
                    log.info("=========info==========");
                    log2.info("=========info==========");
                    log3.info("=========info==========");
                    c.countDown();
                }
            };
            t.start();
        }

        c.await();

    }


    @Test
    public void test2() throws Exception {

        Map<String,String> a  = new HashMap<>();
        a.put("0","b");
        a.put("1","b");
        a.put("2","b");
        a.put("3","b");
        a.put("4","b");
        a.put("5","b");
        System.out.println(a);

        new HashSet<>(a.keySet()).removeAll(Arrays.asList("0","1"));

        System.out.println(a);

    }

}
