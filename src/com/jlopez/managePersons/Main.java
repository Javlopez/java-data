package com.jlopez.managePersons;

import com.jlopez.managePersons.data.UsersJDBC;
import com.jlopez.managePersons.domain.Person;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        UsersJDBC usersJDBC = new UsersJDBC();

        usersJDBC.insert("Javier", "javier@javier.com");
        usersJDBC.insert("Javier 2", "javier@javier2.com");
        usersJDBC.insert("Javier 3", "javier@javier3.com");


        List<Person> personList = usersJDBC.select();

        for(Person person: personList) {
            System.out.print(person);
            System.out.println("");
        }

    }
}
