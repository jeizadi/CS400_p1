/*
 *Assignment: Project 1 
 *Author: Jenna Eizadi
 *CS 400 Lecture 001
 *Email: eizadi@wisc.edu
 *Due Date: 02/07/2019 
 *Source Credits: http://stackoverflow.com for helping me resolve a few issues I was having with my remove method
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	
	//checks to make sure the data structure is empty when it is supposed to be
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0) //check to make sure that the size of an empty list is 0
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	//insert one key value pair into the data structure and then confirm that size() is 1
	@Test
	void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert(new String ("key"), new String ("value")); //insert a key value pair
		if(dataStructureInstance.size() != 1) //check to see if the size reflects this insert
			fail("data structure should have one key, value pair, with a size=1, but size="+dataStructureInstance.size());
	}
	
	// insert one key,value pair and remove it, then confirm size is 0
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert(new String("key"), new String("value")); //insert a value
		dataStructureInstance.remove("key"); //remove that value
		if (dataStructureInstance.size() != 0) //see if the removal was successful by checking the size
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	//insert a few key,value pairs such that one of them has the same key as an earlier one.  Confirm that a RuntimeException is thrown
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert(new String("key"), new String("value")); //insert one key value pair
			dataStructureInstance.insert(new String("key1"), new String("value1")); //insert another key value pair
			dataStructureInstance.insert(new String("key"), new String("value")); //attempt to insert a duplicate key value pair
			fail("inserted a duplicate key value and no runtime exception was thrown as expected");
		} catch(RuntimeException exception) {} //see if an exception is thrown
		
	}
	
	//insert some key,value pairs, then try removing a key that was not inserted.  Confirm that the return value is false
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		dataStructureInstance.insert(new String("key1"), new String("value1")); //insert one key value pair
		dataStructureInstance.insert(new String("key2"), new String("value2")); //insert another key value pair
		if(dataStructureInstance.remove("key3") == true) { //try to remove a key that was not inserted and see if the test fails
			fail("attempted to remove a key that was not inserted from data structure and instead of returning false, returned true");
		}
	}
	
	//insert 50 values, remove 25, get the size
	@Test
	void test05_insert_50_remove_25_size_25() {
		for(int i = 0; i < 50; i++) { //insert 50 key value pairs
			dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
		}
		for(int i = 10; i < 35; i++) { //remove 25 of the middle key value pairs
			dataStructureInstance.remove(Integer.toString(i));
		}
		if(dataStructureInstance.size() != 25) fail("inserted 50 items and removed 25 but the size was not equal to 25"); //check to see if the size reflects the amount inserted and then removed
	}
	
	//attempt to insert a null key and check to see if an exception is thrown
	@Test 
	void test06_null_key_exception_is_thrown() {
		try {
			dataStructureInstance.insert(null, new String("value")); //attempt to insert a new key value pair with a null key
			fail("attempted to insert a null key into the list but no exception was thrown as it should have been");
		} catch(IllegalArgumentException exception) {} //check if an exception is thrown
	}
	
	//attempt to remove a null key and check to see if an exception is thrown
	@Test 
	void test07_attempt_to_remove_null_key_exception_is_thrown() {
		try {
			dataStructureInstance.remove(null); //attempt to remove a null value
			fail("no null key excpetion is thrown when attempting to remove a null key");
		} catch(IllegalArgumentException exception) {} //check to see if an exception is thrown
	}
	
	//insert a number of items and then call the get method to see if it returns the correct value associated with a given key 
	@Test 
	void test08_get_returns_correct_value() {
		dataStructureInstance.insert(new String("key"), new String("value")); //insert a few key value pairs
		dataStructureInstance.insert(new String("key1"), new String("value1"));
		dataStructureInstance.insert(new String("key2"), new String("value2"));
		dataStructureInstance.insert(new String("key3"), new String("value3"));
		dataStructureInstance.insert(new String("key4"), new String("value4"));
		if(dataStructureInstance.get("key3").compareTo("value3") != 0) //get the correct value associated with the key value pair
			fail("get method was excepted to take a key and return the associated value but didn't get the correct one");
	}
	
	//call the contains method on a list to check and see if it recognizes when a list has a certain key
	@Test
	void test09_check_if_a_list_contains_a_value_that_was_inserted() {
		for(int i = 0; i < 50; i++) { //insert 50 key value pairs
			dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
		}
		if(dataStructureInstance.contains("5") == false) fail("expected the key to be found within the list but it was not"); //check to see if list contains one of the inserted key value pairs 
	}
	
	//insert 500 items and remove 500 items to check if list is capable of this quantity and ensure they are all removed
	@Test
	void test10_insert_500_remove_500_size_0() {
		for(int i = 0; i < 500; i++) { //insert 500 key value pairs
			dataStructureInstance.insert(Integer.toString(i), Integer.toString(i));
		}
		for(int i = 0; i < 500; i++) { //remove 500 key value pairs
			dataStructureInstance.remove(Integer.toString(i));
		}
		if(dataStructureInstance.size() != 0) fail("inserted 500 items and removed 500 but the size was not equal to 0");
	}
	
	//check to see if a key can be re-added after being removed from a list and not be counted as a duplicate
	@Test
	void test_11_insert_key_then_remove_same_key_and_attempt_to_insert_same_key_again() {
		try {
			dataStructureInstance.insert(new String("key"), new String("value")); //insert a key value pair
			dataStructureInstance.remove(new String("key")); //remove the key value pair
			dataStructureInstance.insert(new String("key"), new String("value")); //attempt to insert the same key value pair
		} catch(RuntimeException exception) {
			fail("attempted to insert a key and value pair after removing the same key and value pair from a list and falsely marked it as a duplicate");
			}
	}

}
