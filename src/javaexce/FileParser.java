
/*
 * This class does not provide any setters as it might create unnecessary exception 
 * if user explicitly tries to set unknown file using the setter.
 * The files can only be set by using one of the two constructors provided. 
 * This class also does not provide publc access to any getters as this 
 * functionality is not requried outside the class.
 */
package javaexce;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author setu
 */
public class FileParser {

    private File order_file;
    private File person_file;
    private File ordinary_file;
    protected Boolean isFile = false;

    /**
     * this is a default constructor it takes two files and parameters it checks
     * these files for validity and assigns the private variables
     *
     * @param order_file
     * @param person_file
     */
    public FileParser(File order_file, File person_file) {
        if (order_file != null && person_file != null) {
            if (order_file.exists() && order_file.isFile()) {
                this.order_file = order_file;
                if (person_file.exists() && person_file.isFile()) {
                    this.person_file = person_file;
                    isFile = true;
                } else {
                    this.person_file = null;
                    isFile = false;
                }
            } else {
                this.order_file = null;
                this.person_file = null;
                isFile = false;
            }

        } else {
            this.order_file = null;
            this.person_file = null;
            isFile = false;
        }
    }

    /**
     * This constructor is for test purposes it can be used with other types of
     * file, it will check the validity of the file and then assign it to the
     * private variable
     *
     * @param ordinary_file
     */
    public FileParser(File ordinary_file) {
        if (ordinary_file != null) {
            if (ordinary_file.exists() && ordinary_file.isFile()) {
                this.ordinary_file = ordinary_file;
                isFile = true;
            }
        } else {
            this.ordinary_file = null;
            isFile = false;
        }
    }

    /**
     * Order_file getter
     *
     * @return the order_file
     */
    private File getOrder_file() {
        return order_file;
    }

    /**
     * @return the Person_file
     */
    private File getPerson_file() {
        return person_file;
    }

    /**
     * @return the ordinary_file
     */
    private File getOrdinary_file() {
        return ordinary_file;
    }

    /**
     *
     * @return true if the content of the file is parsed successfully, false if
     * file is not valid or empty file or in case of SQLException
     */
    public boolean order_parser() {
        ArrayList<Order> orders = new ArrayList<>();
        if (isFile) {
            try {
                Scanner scanner = new Scanner(getOrder_file());
                //Header line from the CSV file which is not used in execution
                if (scanner.hasNext()) {
                    @SuppressWarnings("UnusedAssignment")
                    String line = scanner.nextLine();
                    while (scanner.hasNext()) {

                        line = scanner.nextLine();
                        if (!line.isEmpty()) {
                            String[] row = line.split("\\|");
                            Order order = new Order(Integer.parseInt(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]));
                            System.out.println(order.toString());
                            orders.add(order);
                        }
                    }
                    DataLayer.create_order(orders);
                    return true;
                } else {
                    return false;
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("Order is not valid file");
            return false;
        }
    }

    /**
     *
     * @return true if the content of the file is parsed successfully, false if
     * file is not valid or empty file or in case of SQLException
     */
    public boolean person_parser() {
        ArrayList<Person> persons = new ArrayList<>();
        if (isFile) {
            try {
                Scanner scanner = new Scanner(getPerson_file());
                //Header line from the CSV file which is not used in the execution
                if (scanner.hasNext()) {
                    @SuppressWarnings("UnusedAssignment")
                    String line = scanner.nextLine();
                    while (scanner.hasNext()) {
                        line = scanner.nextLine();
                        if (!line.isEmpty()) {
                            String[] row = line.split(",");
                            Person person = new Person(Integer.parseInt(row[0]), row[1], row[2], row[3], row[4]);
                            System.out.println(person.toString());
                            persons.add(person);
                        }
                    }
                    DataLayer.create_person(persons);
                    return true;
                } else {
                    return false;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            System.out.println("person is not valid file");
            return false;
        }
    }

    /**
     * This method is for testing purpose only It reads any file and prints it's
     * contents in the output consol. It uses buffered-reader and string-builder
     * classes to achieve the functionality.
     *
     * @return true if the content of the file is successfully parsed into
     * StringBuilder, false if invalid/empty file or incase of
     * FileNotFoundException of IOException
     */
    public boolean ordinary_parser() {
        if (isFile) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(getOrdinary_file().toString()));
                StringBuilder sb = new StringBuilder();
                if (reader.ready()) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                    }
                    System.out.println(sb.toString());
                    return true;
                } else {
                    System.out.println("file is empty");
                    return false;
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }

}
