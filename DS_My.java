
public class DS_My implements DataStructureADT {

	private KeyPair head; //reference to the head element in the list
	int size; //holds value to the size of the list 
	
    public DS_My() {
    	head = null;
    	size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) { //method to insert a new keypair into the list
    	if(k == null) throw new IllegalArgumentException("null key"); //check if key is null
    	if(this.contains(k)) throw new RuntimeException("duplicate key"); //checks to make sure there isn't already a keypair with the inputed key
    	KeyPair current = new KeyPair(k, v); //creates a new keypair with the key and value passed in
    	current.prev = null;
    	current.next = head; //adds this new keypair to the front of the list by saying that what comes after it is the head
    	if(head != null) {
    		head.prev = current; //changes the head's previous pointer to the new keypair 
    	}
    	head = current; //mark the new keypair as the head because it is now at the front of the list
    	size++; //add one to the size
    }

    @Override
    public boolean remove(Comparable k) { //method to remove a keypair with the specified key from the list
    	if(k == null) throw new IllegalArgumentException("null key"); //check if key is null
    	if(this.contains(k) == false) return false; //checks whether the key is in the list and returns false if it isn't
    	KeyPair current = head; //creates new variable pointing to the head
    	if(head == null) return false; //if list is empty remove cannot be performed
    	while(current != null) { //iterate through the list
    		if(current.key.compareTo(k) == 0 ) { //if the correct key is found
    			if(head == current) { //if key is in keypair at the head
    				head = current.next; //change reference to head to next keypair
    			}
    			if(current.next != null) { //if the next keypair is not null
    				current.next.prev = current.prev; //that keypair's prev pointer will be to the keypair before the one to be removed
    			}
    			if(current.prev != null) { //if the previous keypair is not null
    				current.prev.next = current.next; //that keypair's next pointer will be to the keypair after the one to be removed 
    			}
    			size--; //remove one from the size
    			return true;
    		}
    		current = current.next;
    	} 
    	return false;
    }

    @Override
    public boolean contains(Comparable k) { //iterate through the list checking if the keys match with the one given  
    	KeyPair current = head; //creates new variable pointing to the head
    	if(k == null) return false; //check to see if the key given is null
    	while(current != null) { //iterate through list 
    		if(current.key.compareTo(k) == 0) { //check keys to see if they are equal to k
    			return true; //if key is found in the list return true
    		}
    		current = current.next; 
    	}
        return false; //otherwise return false
    }

    @Override
    public Object get(Comparable k) { //return value associated with the key passed in 
    	KeyPair current = head; //creates new variable pointing to the head
    	if(k == null) throw new IllegalArgumentException("null key"); //check to see if the key given is null
    	while(current.next != null) { //iterate through list
    		if(current.key.compareTo(k) == 0) { //check keys to see if they are equal to k
    			return current.value; //if keypair with associated key is in list, return the value
    		}
    		current = current.next; 
    	}
    	if(current.key.compareTo(k) == 0) return current.value; //checks end cases
        return null; //if key not found return null
    }

    @Override
    public int size() { //returns the size of the list
    	return size;
    }
    
    class KeyPair { //private class KeyPair that holds key and value KeyPairs as well as reference to next
    	private Comparable key; //key for the KeyPair	
    	private Object value; //value of the KeyPair
    	private KeyPair next; //reference to the next KeyPair in the list
    	private KeyPair prev; //reference to the previous KeyPair in the list
    	
    	private KeyPair(Comparable key, Object value, KeyPair next, KeyPair previous) { //create a KeyPair object is prev and next are known
    		this.key = key;
    		this.value = value;
    		this.next = next;
    		this.prev = previous;
    	}
    	
    	private KeyPair(Comparable key, Object value) { //create a KeyPair object with unknown prev and next values
    		this.key = key;
    		this.value = value;
    	}
    	
    	
    }

}
