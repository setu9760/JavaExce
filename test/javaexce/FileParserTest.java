/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexce;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author setu
 */
public class FileParserTest {

    FileParser parser;

    public FileParserTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        parser = null;
    }

    /**
     *
     */
    @Test
    public void testFileParser_File_File() {
        //testing with both null params
        parser = new FileParser(null, null);
        assertFalse(parser.isFile);

        //testing with one null params;
        parser = new FileParser(new File("raw/Order.data"), null);
        assertFalse(parser.isFile);
        parser = new FileParser(null, new File("raw/Person.data"));
        assertFalse(parser.isFile);

        //testing for incorrect file paths and directory paths 
        parser = new FileParser(new File(""), new File(""));
        assertFalse(parser.isFile);
        parser = new FileParser(new File("raw/Order.data"), new File("src"));
        assertFalse(parser.isFile);
        parser = new FileParser(new File("raw"), new File("raw.Person.data"));
        assertFalse(parser.isFile);

        //testing for correct file paths
        parser = new FileParser(new File("raw/Order.data"), new File("raw/Person.data"));
        assertTrue(parser.isFile);
    }

    /**
     *
     */
    @Test
    public void testFileParser_File() {
        //testing for null params
        parser = new FileParser(null);
        assertFalse(parser.isFile);

        //testing for incorrect file paths and directories
        parser = new FileParser(new File(""));
        assertFalse(parser.isFile);
        parser = new FileParser(new File("src"));
        assertFalse(parser.isFile);
        parser = new FileParser(new File("raw/order.java"));
        assertFalse(parser.isFile);

        //testing for correct file paths
        parser = new FileParser(new File("raw/Order.data"));
        assertTrue(parser.isFile);
        parser = new FileParser(new File("raw/Person.data"));
        assertTrue(parser.isFile);
        parser = new FileParser(new File("raw/ordinary_file.txt"));
        assertTrue(parser.isFile);

    }

    /**
     * Test of order_parser method, of class FileParser.
     */
    @Test
    public void testOrder_parser() {
        System.out.println("order_parser");
        //testing with null params
        parser = new FileParser(null, null);
        assertFalse(parser.order_parser());

        //testing with incorrect file paths or directory paths
        parser = new FileParser(new File("raw"), new File("src"));
        assertFalse(parser.order_parser());

        //testing with vlid but non existant file path
        parser = new FileParser(new File("raw/abcd.txt"), new File("raw.xyz.data"));
        assertFalse(parser.order_parser());

        //testing with valid but empty file
        parser = new FileParser(new File("raw/empty_file.txt"), new File("raw/Person.data"));
        assertFalse(parser.order_parser());

    }

    /**
     * Test of person_parser method, of class FileParser.
     */
    @Test
    public void testPerson_parser() {
        System.out.println("person_parser");
        //testing with null params
        parser = new FileParser(null, null);
        assertFalse(parser.person_parser());

        //testing with incorrect file paths or directory paths
        parser = new FileParser(new File("raw"), new File("src"));
        assertFalse(parser.person_parser());

        //testing with vlid but non existant file path
        parser = new FileParser(new File("raw/abcd.txt"), new File("raw.xyz.data"));
        assertFalse(parser.person_parser());

        //testing with valid but empty file
        parser = new FileParser(new File("raw/empty_file.txt"), new File("raw/empty_file.txt"));
        assertFalse(parser.person_parser());

    }

    /**
     * Test of ordinary_parser method, of class FileParser.
     */
    @Test
    public void testOrdinary_parser() {
        System.out.println("ordinary_parser");
        //testing with null params
        parser = new FileParser(null);
        assertFalse(parser.ordinary_parser());

        //testing with incorrect file path or directory
        parser = new FileParser(new File(""));
        assertFalse(parser.ordinary_parser());
        parser = new FileParser(new File("raw"));
        assertFalse(parser.ordinary_parser());
        parser = new FileParser(new File("src/abcd.java"));
        assertFalse(parser.ordinary_parser());

        //testing with correct but empty file
        parser = new FileParser(new File("raw/empty_file.txt"));
        assertFalse(parser.ordinary_parser());

        //testing with correct file paths with nonempty files
        parser = new FileParser(new File("raw/ordinary_file.txt"));
        assertTrue(parser.ordinary_parser());
        parser = new FileParser(new File("raw/Order.data"));
        assertTrue(parser.ordinary_parser());

    }

}
