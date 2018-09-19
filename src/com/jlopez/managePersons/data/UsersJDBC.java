package com.jlopez.managePersons.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jlopez.managePersons.domain.Person;
import com.jlopez.managePersons.infrastructure.Connect;

public class UsersJDBC {

    private final String SQL_INSERT = "INSERT INTO user(username, email, password) VALUES (?,?, ?)";
    private final String SQL_UPDATE = "UPDATE user set username=?, email=? WHERE id=?";
    private final String SQL_DELETE = "DELETE FROM user WHERE id=?";
    private final String SQL_SELECT = "SELECT id, username, email FROM user order by id";


    public int insert(String username, String email) {
        Connection conn = null;
        PreparedStatement stmt = null;
        //ResultSet rs = null;
        int rows = 0;

        try {

            conn = Connect.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            int index = 1;
            stmt.setString(index++, username);
            stmt.setString(index++, email);
            stmt.setString(index++, "1234");

            System.out.println("Running sql:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Records affected:" + rows);

        }catch (SQLException sqle) {
            System.out.println("Something goes wrong in the SQL");
            sqle.printStackTrace();
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }
        return rows;
    }

    public int update(int id, String username, String email){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Connect.getConnection();
            System.out.println("Running sql:" + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;

            stmt.setString(index++, username);
            stmt.setString(index++, email);
            stmt.setInt(index++, id);

            rows = stmt.executeUpdate();
            System.out.println("Records updated:" + rows);

        }catch (SQLException sqle) {
            System.out.println("Something goes wrong in the SQL in update");
            sqle.printStackTrace();
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }

        return rows;
    }

    public int delete(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Connect.getConnection();
            System.out.println("Running sql:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);

            rows = stmt.executeUpdate();
            System.out.println("Records updated:" + rows);

        }catch (SQLException sqle) {
            System.out.println("Something goes wrong in the SQL in Delete");
            sqle.printStackTrace();
        } finally {
            Connect.close(stmt);
            Connect.close(conn);
        }

        return rows;
    }

    public List<Person> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Person person= null;
        List<Person> personList = new ArrayList<>();

        try {
            conn = Connect.getConnection();
            System.out.println("Running sql:" + SQL_SELECT);
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt(1);
                String username = rs.getString(2);
                String email = rs.getString(3);

                person = new Person();
                person.setId(id);
                person.setUsername(username);
                person.setEmail(email);
                personList.add(person);

            }

        } catch (SQLException sqle) {
            System.out.println("Something goes wrong in the SQL in Delete");
            sqle.printStackTrace();
        } finally {
            Connect.close(rs);
            Connect.close(stmt);
            Connect.close(conn);
        }

        return personList;
    }

}
