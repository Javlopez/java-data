package com.jlopez.managePersons;

import com.jlopez.managePersons.data.UsersJDBC;
import com.jlopez.managePersons.domain.Person;
import com.jlopez.managePersons.infrastructure.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Connection conn = null;
        UsersJDBC usersJDBC = new UsersJDBC();

        try {
            conn = Connect.getConnection();

            if(conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            usersJDBC = new UsersJDBC(conn);
            usersJDBC.update(2, "pepepepep", "javier@javier.com");
            
            conn.commit();

            List<Person> personList = usersJDBC.select();

            for(Person person: personList) {
                System.out.print(person);
                System.out.println("");
            }


        } catch (SQLException sqle) {
            try {
                System.out.println("Doing rollback");
                sqle.printStackTrace(System.out);
                conn.rollback();
            } catch (SQLException ex1) {
                sqle.printStackTrace(System.out);
            }
        }





    }
}
