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
public class PersonTest {

    public PersonTest() {
    }
    Person person;
    int exp_integer;

    @Before
    public void setUp() {
        person = new Person(1, "Setu", "Desai", "Oxford Street", "Oxford");
        exp_integer = 1;
    }

    @After
    public void tearDown() {
        person = null;
        exp_integer = -1;
    }

    /**
     * Test of getPerson_ID method, of class Person.
     */
    @Test
    public void testGetPerson_ID() {
        System.out.println("getPerson_ID");
        assertEquals(exp_integer, person.getPerson_ID());
    }

    /**
     * Test of setPerson_ID method, of class Person.
     */
    @Test
    public void testSetPerson_ID() {
        System.out.println("setPerson_ID");
        person.setPerson_ID(2);
        assertEquals(2, person.getPerson_ID());
    }

    /**
     * Test of getFirst_name method, of class Person.
     */
    @Test
    public void testGetFirst_name() {
        System.out.println("getFirst_name");
        assertFalse(person.getFirst_name().equals("setu"));
        assertTrue(person.getFirst_name().equals("Setu"));
    }

    /**
     * Test of setFirst_name method, of class Person.
     */
    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testSetFirst_name() {
        System.out.println("setFirst_name");
        person.setFirst_name("setu");
        assertFalse(person.getFirst_name().equals(null));
        assertTrue(person.getFirst_name().equals("setu"));
    }

    /**
     * Test of getLast_name method, of class Person.
     */
    @Test
    public void testGetLast_name() {
        System.out.println("getLast_name");
        assertFalse(person.getLast_name().equals("desai"));
        assertTrue(person.getLast_name().equals("Desai"));
    }

    /**
     * Test of setLast_name method, of class Person.
     */
    @Test
    public void testSetLast_name() {
        System.out.println("setLast_name");
        person.setLast_name("desai");
        assertTrue(person.getLast_name().equals("desai"));
        assertFalse(person.getLast_name().equals("Desai"));
    }

    /**
     * Test of getStreet method, of class Person.
     */
    @Test
    public void testGetStreet() {
        System.out.println("getStreet");
        assertTrue(person.getStreet().equals("Oxford Street"));
        assertFalse(person.getStreet().equals("oxford street"));
        assertFalse(person.getStreet().equals("OxfordStreet"));
    }

    /**
     * Test of setStreet method, of class Person.
     */
    @Test
    public void testSetStreet() {
        System.out.println("setStreet");
        person.setStreet("New Street");
        assertFalse(person.getStreet().equals("Oxford Street"));
        assertTrue(person.getStreet().equals("New Street"));
    }

    /**
     * Test of getCity method, of class Person.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        assertTrue(person.getCity().equals("Oxford"));
        assertFalse(person.getCity().equals("oxford"));
    }

    /**
     * Test of setCity method, of class Person.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        person.setCity("Cambridge");
        assertFalse(person.getCity().equals("Oxford"));
        assertTrue(person.getCity().equals("Cambridge"));
        assertFalse(person.getCity().equals("cambridge"));
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    @SuppressWarnings("ObjectEqualsNull")
    public void testToString() {
        System.out.println("toString");

        //testing with null reference
        assertFalse(this.person.toString().equals(null));

        //testing toString() method on same object
        assertTrue(this.person.toString().equals(this.person.toString()));

        //testing with not same but similar type of object with same params
        Person person = new Person(1, "Setu", "Desai", "Oxford Street", "Oxford");
        assertEquals(this.person.toString(), person.toString());

        //testing with not same but similar object with different params
        person = new Person(2, "Setu", "Desai", "Oxford Street", "Oxford");
        assertFalse(this.person.toString().equals(person.toString()));

    }

    /**
     * Test of equals and hashCode methods, of class Person.
     */
    @Test
    public void testEquals_hashcode() {
        System.out.println("equals");

        //testing equals() to null reference and hashcode to 0
        assertFalse(person.equals(null));
        assertTrue(person.hashCode() != 0);

        //testing with not null but different type of object(String in this case)
        assertFalse(person.equals("Hello"));
        assertFalse(person.hashCode() == "Hello".hashCode());

        //testing with the same object
        assertTrue(person.equals(person));
        assertTrue(person.hashCode() == person.hashCode());

        //testing with not same but similar type of object with same params
        Person person = new Person(1, "Setu", "Desai", "Oxford Street", "Oxford");
        assertTrue(this.person.equals(person));
        assertTrue(this.person.hashCode() == person.hashCode());

        //testing with not same but similar type of object with different params
        person = new Person(2, "Setu", "Desai", "Oxford Street", "Oxford");
        assertFalse(this.person.equals(person));
        assertFalse(this.person.hashCode() == person.hashCode());
    }
}
