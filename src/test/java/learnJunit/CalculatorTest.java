package learnJunit;

import com.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CalculatorTest {

    /**
     * 一般用来初始化资源
     */
    @Before
    public void init() {
        System.out.println("init...");
    }

    /**
     * 一般用来释放资源
     */
    @After
    public void close() {
        System.out.println("close...");
    }

    /**
     * 通过断言来判断程序对错, 而不是通过sout
     */
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(1, 2);
        // 断言, 判断这个结果是多少, 断言通过显示√, 失败显示×
        Assert.assertEquals(4, result);
    }
}
