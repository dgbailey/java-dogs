package com.lambdaschool.projectrestdogs;

import com.lambdaschool.projectrestdogs.Services.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


public class Dog
{
    private static final AtomicLong counter = new AtomicLong();
    private long id;
    private String breed;
    private int weight;
    private boolean apartmentSuitable;


    @Autowired
    private  MessageSender msgSender;





    private Dog() {
    }


    public Dog(String breed, int weight, boolean apartmentSuitable)
    {
        this.id = counter.incrementAndGet();
        this.breed = breed;
        this.weight = weight;
        this.apartmentSuitable = apartmentSuitable;




        //send message each time dog created
    }

    public Dog(Dog toClone)
    {
        this.id = toClone.getId();
        this.breed = toClone.getBreed();
        this.weight = toClone.weight;
        this.apartmentSuitable = toClone.isApartmentSuitable();

    }


    public static Dog createDog(String breed, int weight, boolean apartmentSuitable){

        Dog d = new Dog(breed,weight,apartmentSuitable);
//        msgSender.SendMessage();
        System.out.println("Dog created message");
        return d;

    }

////    setter based DI
//    @Autowired
//    public void setMessageSender(MessageSender messageSender) {
//        this.messageSender = messageSender;
//
//    }

//    private void sendEndPointMsg()
//    {
//        this.msgSender.SendMessage();
//    }

    public long getId()
    {
        return id;
    }

    @Override
    public String toString() {

        return "Dog{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", apartmentSuitable=" + apartmentSuitable +
                '}';
    }

    public String getBreed()
    {
        return breed;
    }

    public void setBreed(String breed)
    {
        this.breed = breed;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public boolean isApartmentSuitable()
    {
        return apartmentSuitable;
    }

    public void setApartmentSuitable(boolean apartmentSuitable)
    {
        this.apartmentSuitable = apartmentSuitable;
    }
}