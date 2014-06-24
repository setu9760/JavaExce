/*
 * 
 */
package javaexce;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author setu
 */
public class OrderTest {

    public OrderTest() {

    }

    Order order;
    int exp;

    @Before
    public void setUp() {
        order = new Order(1, 1, 1);
        exp = 1;
    }

    @After
    public void tearDown() {

    }

    /**
     * Test of getOrder_ID method, of class Order.
     */
    @Test
    public void testGetOrder_ID() {
        System.out.println("getOrder_ID");
        assertEquals(exp, order.getOrder_ID());
    }

    /**
     * Test of setOrder_ID method, of class Order.
     */
    @Test
    public void testSetOrder_ID() {
        System.out.println("setOrder_ID");
        order.setOrder_ID(2);
        exp = 2;
        assertEquals(exp, order.getOrder_ID());
    }

    /**
     * Test of getOrder_Number method, of class Order.
     */
    @Test
    public void testGetOrder_Number() {
        System.out.println("getOrder_Number");
        assertEquals(exp, order.getOrder_Number());

    }

    /**
     * Test of setOrder_Number method, of class Order.
     */
    @Test
    public void testSetOrder_Number() {
        System.out.println("setOrder_Number");
        order.setOrder_Number(23);
        exp = 23;
        assertEquals(exp, order.getOrder_Number());
    }

    /**
     * Test of getPerson_ID method, of class Order.
     */
    @Test
    public void testGetPerson_ID() {
        System.out.println("getPerson_ID");
        assertEquals(exp, order.getPerson_ID());
    }

    /**
     * Test of setPerson_ID method, of class Order.
     */
    @Test
    public void testSetPerson_ID() {
        System.out.println("setPerson_ID");
        exp = 3;
        order.setPerson_ID(3);
        assertEquals(exp, order.getPerson_ID());
    }

    /**
     * Test of toString method, of class Order.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String exp2 = "---------------------"
                + "\nOrder ID: " + order.getOrder_ID()
                + "\nOrder number: " + order.getOrder_Number()
                + "\nPerson ID: " + order.getPerson_ID();
        assertEquals(exp2, order.toString());

    }

    /**
     * Test of equals and hashCode method, of class Order.
     */
    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testequals_hashcode() {
        //testing equals() to null and hashcode to 0
        assertFalse(order.equals(null));
        assertTrue(order.hashCode() != 0);

        //testing with not null but different type of object(String in this case)
        assertFalse(order.equals("Hello"));
        assertFalse(order.hashCode() == "Hello".hashCode());

        //testing with same object
        assertTrue(order.equals(order));
        assertTrue(order.hashCode() == order.hashCode());

        //testing with not same but similar type of object with same params
        Order order = new Order(1, 1, 1);
        assertTrue(this.order.equals(order));
        assertTrue(this.order.hashCode() == order.hashCode());

        //testing with not same but similar type of object with different params
        order = new Order(1, 1, 2);
        assertFalse(this.order.equals(order));
        assertFalse(this.order.hashCode() == order.hashCode());
    }

}
