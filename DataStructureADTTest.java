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

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// test01_after_insert_one_size_is_one
	//insert one key value pair into the data structure and then confirm that size() is 1
	@Test
	void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert(new String ("key"), new String ("value"));
		if(dataStructureInstance.size() != 1)
			fail("data structure should have one key, value pair, with a size=1, but size="+dataStructureInstance.size());
	}
	
	// test02_after_insert_one_remove_one_size_is_0
	// insert one key,value pair and remove it, then confirm size is 0
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert(new String("key"), new String("value"));
		dataStructureInstance.remove("key");
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// test03_duplicate_exception_is_thrown
	//insert a few key,value pairs such that one of them has the same key as an earlier one.  Confirm that a RuntimeException is thrown
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert(new String("key"), new String("value"));
			dataStructureInstance.insert(new String("key1"), new String("value1"));
			dataStructureInstance.insert(new String("key"), new String("value"));
			fail("inserted a duplicate key value and no runtime exception was thrown as expected");
		} catch(RuntimeException exception) {}
		
	}
	
	// test04_remove_returns_false_when_key_not_present
	//insert some key,value pairs, then try removing a key that was not inserted.  Confirm that the return value is false
	@Test
	void test04_remove_returns_false_when_key_not_present() {
		dataStructureInstance.insert(new String("key1"), new String("value1"));
		dataStructureInstance.insert(new String("key2"), new String("value2"));
		if(dataStructureInstance.remove("key3") == true) {
			fail("attempted to remove a key that was not inserted from data structure and instead of returning false, returned true");
		}
	}
	
	// TODO: add tests to ensure that you can detect implementation that fail
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes


}
