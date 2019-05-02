package com.lambdaschool.projectrestdogs;

import com.lambdaschool.projectrestdogs.Dog;
import com.lambdaschool.projectrestdogs.Services.MessageSender;

import java.util.ArrayList;

public class DogList
{
    public ArrayList<Dog> dogList = new ArrayList<Dog>();



    public DogList()
    {
        dogList.add(Dog.createDog("Springer", 50, false));
        dogList.add(Dog.createDog("Bulldog", 50, true));
        dogList.add(Dog.createDog("Collie", 50, false));
        dogList.add(Dog.createDog("Boston Terrie", 35, true));
        dogList.add(Dog.createDog("Corgie", 35, true));
    }

    public Dog findDog(CheckDog tester)
    {
        for (Dog d : dogList)
        {
            if (tester.test(d))
            {
                return d;
            }
        }
        return null;
    }



    public ArrayList<Dog> findDogs(CheckDog tester)
    {
        ArrayList<Dog> tempDogList = new ArrayList<>();

        for (Dog d : dogList)
        {
            if (tester.test(d))
            {
                tempDogList.add(d);
            }
        }

        return tempDogList;
    }
}
