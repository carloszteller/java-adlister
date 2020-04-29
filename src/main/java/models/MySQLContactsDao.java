package models;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactsDao implements Contacts {
    private Connection connection;

    public MySQLContactsDao() throws SQLException {
        // setup database drive and make a connection
        Config config = new Config();
        DriverManager.registerDriver(new Driver());
        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> output = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from contacts");

            while(rs.next()) {
                output.add(
                    new Contact(
                        rs.getLong("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number")
                    )
                );
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return output;
    }

    @Override
    public long createContact(Contact contact) {
        long newContactId = 0;

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(
                String.format("insert into contact (first_name, last_name, phone_number) values (%s, %s, %s)",
                    contact.getFirstName(),
                    contact.getLastName(),
                    contact.getPhoneNumber()
                ), statement.RETURN_GENERATED_KEYS
            );

            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next()) {
                newContactId = rs.getLong(1);
            }

            if(newContactId != 0) {
                contact.setId(newContactId);
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return newContactId;
    }

    @Override
    public void deleteContact(long id) {
        try {
            Statement statement = connection.createStatement();

            statement.execute(String.format("delete from contacts where id = %d", id));
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @Override
    public Contact getContact(long id) {
        Contact output = new Contact();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("select * from contacts where id = %d", id));

            if(rs.next()) {
                output.setId(id);
                output.setFirstName(rs.getString("first_name"));
                output.setLastName(rs.getString("last_name"));
                output.setPhoneNumber(rs.getString("phone_number"));
            } else {
                System.out.println("No user found by that ID...");
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }

        return output;
    }
}
