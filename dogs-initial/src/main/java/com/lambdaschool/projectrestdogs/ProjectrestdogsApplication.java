package com.lambdaschool.projectrestdogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;


@EnableScheduling
@EnableWebMvc
@SpringBootApplication
public class ProjectrestdogsApplication
{

    public static final String EXCHANGE_NAME = "LambdaServer";
    public static final String QUEUE_NAME_DOGS = "DogsEndPointQueue";
    public static final String QUEUE_NAME_HIGH = "HighPriorityQueue";

    public static DogList ourDogList;
    public static void main(String[] args)
    {
        ourDogList = new DogList();
        ApplicationContext ctx = SpringApplication.run(ProjectrestdogsApplication.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

    @Bean
    public TopicExchange appExchange()
    {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueDogs()
    {
        return new Queue(QUEUE_NAME_DOGS);
    }

    @Bean
    public Binding declareBindingDogs()
    {
        return BindingBuilder.bind(appQueueDogs()).to(appExchange()).with(QUEUE_NAME_DOGS);
    }

}
