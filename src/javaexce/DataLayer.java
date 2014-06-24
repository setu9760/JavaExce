/**
 * This is a DataLayer class which is used to perform all actions to the
 * database. The methods used in this class are static.
 */
package javaexce;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author setu
 */
public class DataLayer {

    private static Connection conn;

    /*
     * Below are the database credential to make connection to the database.
     * This credential differ on each machine as per the set up by the machine user.
     * While running the programm on different machine all or some of this 
     * credentials might have to be modified for the program to execute correctly.
     */
    /* ********DATABASE CREDENTIAL DECLERATION****************** */
    /**
     * This is the url to where the database is found. While developing the
     * program WAMP localhost service was used.
     */
    private static final String dburl = "jdbc:mysql://localhost/Javaexec";

    /**
     * This is the phpMyAdmin user name.
     */
    private static final String user = "root";

    /**
     * This is the password to access the database for above defined user.
     */
    private static final String password = "sdesai";

    /* ***********END OF DATABASE CREDENTIAL DECLERATION.********** */
    /**
     * size is a static variable used to determine the number of rows returned
     * as result from the database. This variable is set to 0 initially and s
     * increased by one in each iteration while fetching result from the
     * resultSet.
     *
     * @see DataLayer#get_atleast_1_order()
     * @see DataLayer#get_orders_with_fname()
     */
    protected static int size = 0;

    /**
     *
     */
    public DataLayer() {
    }

    /**
     * Static method which initialises the connection to the database or catches
     * SQLException if not successful
     *
     * @return true if connection is successful, false otherwise.
     */
    public static boolean initConnection() {
        try {

            conn = DriverManager.getConnection(dburl, user, password);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * This method is called from order_file() method of FileParser class, the
     * list of Order is dynamically created from appropriate file read by parser
     * class and is passed to this method. this method then checks the list
     * against null or isEmpty method, if not the data is inserted into the
     * database table
     *
     * @param orders list of Order objects
     * @return true if the execution of SQL query is successful, false otherwise
     * @see javaexce.FileParser#order_parser()
     */
    public static boolean create_order(ArrayList<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return false;
        } else {
            try {
                Statement s = conn.createStatement();
                for (Order order : orders) {
                    String query = String.format("INSERT INTO `order` (Order_ID, Order_Number, Person_ID) VALUES ('%d', '%d', '%d');",
                            order.getOrder_ID(), order.getOrder_Number(), order.getPerson_ID());
                    s.execute(query);

                }
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }

    /**
     * This method is called from person_parser() method of FileParser class,
     * The list of Person is dynamically created from appropriate file read by
     * parser class and is passed to this method. This method then checks the
     * list against null or isEmpty method, if not the data is inserted into the
     * database table Person
     *
     * @param persons list of Person objects
     * @return true if the execution of SQL query is successful, false otherwise
     * @see javaexce.FileParser#person_parser()
     */
    public static boolean create_person(ArrayList<Person> persons) {
        if (persons == null || persons.isEmpty()) {
            return false;
        } else {
            try {
                Statement s = conn.createStatement();
                for (Person person : persons) {
                    String query = String.format("INSERT INTO `Person` (Person_ID, First_name, Last_name, Street, City) VALUES "
                            + "('%d', '%s', '%s', '%s', '%s')",
                            person.getPerson_ID(), person.getFirst_name(), person.getLast_name(), person.getStreet(), person.getCity());
                    s.execute(query);
                }
                return true;
            } catch (SQLException e) {
                Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, e);
                return false;
            }
        }
    }

    /**
     * This method fetches all the details of a person from person_id where
     * there is at-least one order placed by the person. This method fetches
     * four fields from two tables; Order_ID, Order_number, Person_ID and
     * First_name.
     *
     * @return true if the request from database is successful, false otherwise
     */
    public static boolean get_atleast_1_order() {
        try {
            size = 0;
            Statement s = conn.createStatement();
            String query = String.format("SELECT order.Order_ID, order.Order_number, Person.Person_ID, Person.First_name\n"
                    + "FROM  `order` "
                    + "INNER JOIN  `Person` ON order.Person_ID = Person.Person_ID "
                    + "ORDER BY order.Order_ID");
            s.executeQuery(query);
            ResultSet result = s.getResultSet();
            List<String[]> list = new ArrayList<>();
            while (result.next()) {
                list.add(new String[]{result.getString(1), result.getString(2), result.getString(3), result.getString(4)});
                size++;
            }
            convert(list);
            System.out.println(size + " people have atleast one order");

            return true;
        } catch (SQLException e) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * This method returns names of the person with any orders placed the data
     * is sorted by the first names of the persons. This method fetches three
     * fields from two tables; First_name, Last_name and Order_ID.
     *
     * @return true if the request from database is successful, false otherwise.
     */
    public static boolean get_orders_with_fname() {
        try {
            size = 0;
            Statement s = conn.createStatement();
            String query = String.format("SELECT Person.First_name, Person.Last_name, order.Order_ID "
                    + "FROM `Person` INNER JOIN `order` "
                    + "ON Person.Person_ID=order.Person_ID "
                    + "ORDER BY Person.First_name;");

            s.executeQuery(query);
            ResultSet result = s.getResultSet();
            List<String[]> list = new ArrayList<>();
            while (result.next()) {
                list.add(new String[]{result.getString(1), result.getString(2), result.getString(3)});
                size++;
            }
            convert(list);
            System.out.println("Orders with first name of " + size + " people");

            return true;
        } catch (SQLException e) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * This method is mostly used in test cases as during testing the data in
     * database needs to be modified repeatedly. This method can be used in main
     * method as well. **Important** This method will erase all data from the
     * database therefore it is not recommended to use it in the main method
     *
     * @return true if both queries executed successfully, false when
     * SQLException occurs.
     */
    public static boolean flush_data() {
        try {
            Statement s = conn.createStatement();
            String query1 = String.format("DELETE FROM `Person`;");
            String query2 = String.format("DELETE FROM `order`;");
            s.execute(query1);
            s.execute(query2);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DataLayer.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * This method returns the content of the file in two dimensional array of
     * String which can be easily read or printed in tabular format.
     *
     * @param list of database result row in form of array of String
     * @return two dimensional array of result
     */
    private static String[][] convert(List<String[]> list) {
        if (list == null || list.isEmpty()) {
            return new String[][]{};
        }
        String[][] output = new String[list.size()][list.get(0).length];
        for (int i = 0; i < list.size(); i++) {
            output[i] = list.get(i);
        }
        for (String[] strings : output) {
            System.out.println(Arrays.toString(strings));
        }
        return output;
    }
}
