/*
 *
 * This is a test class for DataLayer class 
 * NOTE: In this test class flush_data() method of
 * DataLayer class is called in tearDown().
 * This will clear all data from the database, 
 * this is done to perform rigorous testing with 
 * different inputs.
 */
package javaexce;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author setu
 */
public class DataLayerTest {

    ArrayList<Order> orders;
    ArrayList<Person> persons;

    public DataLayerTest() {
    }

    @Before
    public void setUp() {
        DataLayer.initConnection();
    }

    @After
    public void tearDown() {
        DataLayer.flush_data();
        DataLayer.size = 0;
    }

    /**
     * Test of initConnection method, of class DataLayer.
     */
    @Test
    public void testInitConnection() {
        System.out.println("initConnection");
        try {
            assertTrue(DataLayer.initConnection());
        } catch (Exception e) {
            fail("initConnection test failed");
        }
    }

    /**
     * Test of create_order method, of class DataLayer.
     */
    @Test
    public void testCreate_order() {
        System.out.println("create_order");

        //Testing with null
        assertFalse(DataLayer.create_order(null));

        //testing with empty list
        orders = new ArrayList<>();
        assertFalse(DataLayer.create_order(orders));

        //testing with randomly populated list
        for (int i = 0; i < 3; i++) {
            orders.add(new Order(i, i, i));
        }
        assertTrue(DataLayer.create_order(orders));

    }

    /**
     * Test of create_person method, of class DataLayer.
     */
    @Test
    public void testCreate_person() {
        System.out.println("create_person");

        //testing with null
        assertFalse(DataLayer.create_person(null));

        //testing with empty list
        persons = new ArrayList<>();
        assertFalse(DataLayer.create_person(persons));

        //testing with randomly poplulated list
        for (int i = 0; i < 3; i++) {
            persons.add(new Person(i, i + "", i + "", i + "", i + ""));
        }
        assertTrue(DataLayer.create_person(persons));
    }

    /**
     * Test of get_atleast_1_order method, of class DataLayer.
     */
    @Test
    public void testGet_atleast_1_order() {
        System.out.println("get_atleast_1_order");

        //testing for the variable size at the method entry point
        assertEquals(0, DataLayer.size);

        //the database will be empty at this point so 
        //checking for number of output
        DataLayer.get_atleast_1_order();
        assertEquals(0, DataLayer.size);

        //Here main method is invoked so that the database is poplulated with original data
        // and then checking for appropriate number of rows fetched by this method.
        JavaExce.main(new String[]{});
        assertEquals(3, DataLayer.size);

    }

    /**
     * Test of get_orders_with_fname method, of class DataLayer.
     */
    @Test
    public void testGet_orders_with_fname() {
        System.out.println("get_orders_with_fname");

        //testing for variable size at the method entry point
        assertEquals(0, DataLayer.size);

        //the database will be empty at this point so
        //checking number of ouput
        DataLayer.get_orders_with_fname();
        assertEquals(0, DataLayer.size);

        //Here main method is invoked so that the database is poplulated with original data
        // and then checking for appropriate number of rows fetched by this method.
        JavaExce.main(new String[]{});
        assertEquals(3, DataLayer.size);
    }

    @Test
    public void Testflush_data() {
        System.out.println("flush_data");
        try {
            DataLayer.flush_data();
        } catch (Exception e) {
            fail("flush_data() test failed");
        }
    }
}
