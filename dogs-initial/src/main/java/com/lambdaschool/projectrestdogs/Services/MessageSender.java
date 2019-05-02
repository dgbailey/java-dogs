package com.lambdaschool.projectrestdogs.Services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.Dog;
import com.lambdaschool.projectrestdogs.MessageDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageSender
{
    private RabbitTemplate rt; // send
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);




    public MessageSender(RabbitTemplate rt)
    {
        this.rt = rt; // send

    }



//    @Scheduled(fixedDelay = 3000L)
    public void SendMessage()
    {
        for (Dog d: ProjectrestdogsApplication.ourDogList.dogList)
        {

            MessageDetail message = new MessageDetail(d.toString()); // send


            logger.info("Sending message Dog Created");
            rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_DOGS, message); // send

        }
    }


    public void SendMessageEndpoint(String message)
    {


            MessageDetail newMessage = new MessageDetail(message); // send


            logger.info("Sending message EndpointHit");
            rt.convertAndSend(ProjectrestdogsApplication.QUEUE_NAME_EP_DOGS, newMessage); // send


    }
}

