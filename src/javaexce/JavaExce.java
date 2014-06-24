
/*
 * 
 */
package javaexce;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author setu
 *
 */
public class JavaExce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DataLayer.initConnection();
            FileParser parser = new FileParser(new File("raw/Order.data"), new File("raw/Person.data"));
            parser.order_parser();
            parser.person_parser();

            System.out.println("******Orders with first name*********");
            System.out.println("[First name, Last name, Order ID]");
            DataLayer.get_orders_with_fname();

            System.out.println(System.lineSeparator());
            System.out.println("--------------------------------------");
            System.out.println("******Atleast one order list*********");
            System.out.println("[Order ID, Order Number, Person ID, First name]");
            DataLayer.get_atleast_1_order();

            /**
             * following method will flush all data from the data. Use when
             * required while testing.
             */
            //DataLayer.flush_data();
        } catch (Exception ex) {
            Logger.getLogger(JavaExce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
