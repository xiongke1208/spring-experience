package targetSource;

import com.x.y.z.annotation.base.AnnotationBaseMain;
import com.x.y.z.xml.base.targetSource.Target;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 1234qwer on 2018/5/28.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:RootContext.xml")
public class SwapTargetSourceTest {

    @Resource(name = "swappable")
    private Target target;
    @Autowired
    private HotSwappableTargetSource swapper;

    @Test
    public void test(){

        assertTrue(target.getName().equals("init"));
        swapper.swap(new Target());


        assertNull(target.getName());

    }

}
