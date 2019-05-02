package com.lambdaschool.projectrestdogs;

import com.lambdaschool.projectrestdogs.exceptions.ResourceNotFoundException;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lambdaschool.projectrestdogs.Services.MessageSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;

@RestController
@RequestMapping("/dogs")
public class DogController
{
//    @Autowired
    MessageSender msgSender;

    DogController(MessageSender msgSender)
    {
        this.msgSender = msgSender;
    }


    private static final Logger logger = LoggerFactory.getLogger(DogController.class);
    // localhost:8080/dogs/dogs

    @GetMapping(value = "/dogs", produces = {"application/json"})
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("We requested /dogs resource");
        String endPtMsg = "Hit Dogs endpoint on DATE = TODAY DDMMYYYY";

        this.msgSender.SendMessageEndpoint(endPtMsg);

        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);

    }

    // localhost:8080/dogs/{id}
    @GetMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;
        logger.info("We requested /dogs/{id}");


        if ((ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id)) == null))
        {
            logger.info("We requested /dogs/{id} threw exception");
            throw new ResourceNotFoundException("Employee with id " + id + " not found");

        } else
        {
            logger.info("We requested /dogs/{id} success");
            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(d -> (d.getId() == id));
        }

        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed)
    {
        logger.info("We requested /dogs/{breed} success");
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));
        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);
    }
}
