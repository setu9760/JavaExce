
/*
 * 
 */
package javaexce;

import java.util.Objects;

/**
 *
 * @author setu
 */
public class Person {

    private int Person_ID;
    private String First_name;
    private String Last_name;
    private String Street;
    private String City;

    /**
     *
     * @param Person_ID
     * @param First_name
     * @param Last_name
     * @param Street
     * @param City
     */
    public Person(int Person_ID, String First_name, String Last_name, String Street, String City) {
        this.Person_ID = Person_ID;
        this.First_name = First_name;
        this.Last_name = Last_name;
        this.Street = Street;
        this.City = City;
    }

    /**
     * @return the Person_ID
     */
    public int getPerson_ID() {
        return Person_ID;
    }

    /**
     * @param Person_ID the Person_ID to set
     */
    public void setPerson_ID(int Person_ID) {
        this.Person_ID = Person_ID;
    }

    /**
     * @return the First_name
     */
    public String getFirst_name() {
        return First_name;
    }

    /**
     * @param First_name the First_name to set
     */
    public void setFirst_name(String First_name) {
        this.First_name = First_name;
    }

    /**
     * @return the Last_name
     */
    public String getLast_name() {
        return Last_name;
    }

    /**
     * @param Last_name the Last_name to set
     */
    public void setLast_name(String Last_name) {
        this.Last_name = Last_name;
    }

    /**
     * @return the Street
     */
    public String getStreet() {
        return Street;
    }

    /**
     * @param Street the Street to set
     */
    public void setStreet(String Street) {
        this.Street = Street;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * This method Overrides the toString method of Object class. It creates
     * visual representation as String object of Person class.
     *
     * @return String object with the complete details of the object.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("---------------------")
                .append(System.lineSeparator())
                .append("Person ID: ").append(Person_ID)
                .append(System.lineSeparator())
                .append("First name: ").append(First_name)
                .append(System.lineSeparator())
                .append("Last Name: ").append(Last_name)
                .append(System.lineSeparator())
                .append("Street: ").append(Street)
                .append(System.lineSeparator())
                .append("City: ").append(City);
        return sb.toString();
    }

    /**
     * This method Overrides equals method of Object class. This method checks
     * the parameter 'obj' against null or instance or Person class then checks the
     * object for their elements. This method uses Overridden hashCode method
     * for this functionality.
     *
     * @param obj
     * @return true if same or similar object with same elements, false if null,
     * non-similar/same or similar object with different elements.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Person)) {
            return false;
        } else {
            if (obj == this) {
                return true;
            } else {
                return obj.hashCode() == this.hashCode();
            }
        }
    }

    /**
     * This method Overrides hashCode method of Object class. It calculates the
     * hash value from the elements of the object.
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.Person_ID;
        hash = 23 * hash + Objects.hashCode(this.First_name);
        hash = 23 * hash + Objects.hashCode(this.Last_name);
        hash = 23 * hash + Objects.hashCode(this.Street);
        hash = 23 * hash + Objects.hashCode(this.City);
        return hash;
    }

}
