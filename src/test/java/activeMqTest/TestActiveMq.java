package activeMqTest;

import com.Application;
import com.how2java.activeMq.producer.QueueProducer;
import com.how2java.activeMq.producer.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMq {
    @Autowired
    private QueueProducer queueProducer;

    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void testQueueProducer() {
        queueProducer.produceMsg();
    }

    @Test
    public void testTopicProducer() {
        topicProducer.produceMsg();
    }

}
