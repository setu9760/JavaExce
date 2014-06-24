
/*
 * 
 */
package javaexce;

/**
 *
 * @author setu
 */
public class Order {

    private int Order_ID;
    private int Order_Number;
    private int Person_ID;

    /**
     *
     * @param Order_ID
     * @param Order_Number
     * @param Person_ID
     */
    public Order(int Order_ID, int Order_Number, int Person_ID) {
        this.Order_ID = Order_ID;
        this.Order_Number = Order_Number;
        this.Person_ID = Person_ID;
    }

    /**
     * @return the Order_ID
     */
    public int getOrder_ID() {
        return Order_ID;
    }

    /**
     * @param Order_ID the Order_ID to set
     */
    public void setOrder_ID(int Order_ID) {
        this.Order_ID = Order_ID;
    }

    /**
     * @return the Order_Number
     */
    public int getOrder_Number() {
        return Order_Number;
    }

    /**
     * @param Order_Number the Order_Number to set
     */
    public void setOrder_Number(int Order_Number) {
        this.Order_Number = Order_Number;
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
     * This method Overrides the toString method of Object class. It creates
     * visual representation as String object of Order class.
     *
     * @return String object with the complete details of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------")
                .append(System.lineSeparator())
                .append("Order ID: ").append(Order_ID)
                .append(System.lineSeparator())
                .append("Order number: ").append(Order_Number)
                .append(System.lineSeparator())
                .append("Person ID: ").append(Person_ID);
        return sb.toString();
    }

    /**
     * This method Overrides equals method of Object class. This method checks
     * the params 'obj' against null or instance or Order class then checks the
     * object for their elements. This method uses Overridden hashCode method
     * for this functionality.
     *
     * @param obj
     * @return true if same or similar object with same elements, false if null,
     * non-similar/same or similar object with different elements.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Order)) {
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
        hash = 41 * hash + this.Order_ID;
        hash = 41 * hash + this.Order_Number;
        hash = 41 * hash + this.Person_ID;
        return hash;
    }

}
