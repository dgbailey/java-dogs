package com.lambdaschool.projectrestdogs.Listeners;

import com.lambdaschool.projectrestdogs.MessageDetail;
import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerDogs {




        private static final Logger logger = LoggerFactory.getLogger(MessageListenerDogs.class);

        @RabbitListener(queues = ProjectrestdogsApplication.QUEUE_NAME_DOGS)
        public void receiveDogMessage(MessageDetail msg)
        {
            // process the message
            logger.info("Received message {}", msg.toString());
        }

}
