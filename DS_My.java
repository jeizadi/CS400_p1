
public class DS_My implements DataStructureADT {

	private KeyPair head; //reference to the head element in the list
	int size;
	
    public DS_My() {
    	head = null;
    	size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
    	if(k == null) throw new IllegalArgumentException("null key"); //check if key is null
    	if(this.contains(k)) throw new RuntimeException("duplicate key");
    	KeyPair current = new KeyPair(k, v);
    	current.prev = null;
    	current.next = head;
    	if(head != null) {
    		head.prev = current;
    	}
    	head = current;
    	size++;
    }

    @Override
    public boolean remove(Comparable k) {
    	if(k == null) throw new IllegalArgumentException("null key");
    	if(this.contains(k) == false) return false; //checks whether the key is in the list and returns false if it isn't
    	KeyPair current = head;
    	if(head == null) return false;
    	while(current != null) {
    		if(current.key.compareTo(k) == 0 ) {
    			if(head == current) {
    				head = current.next;
    			}
    			if(current.next != null) {
    				current.next.prev = current.prev;
    			}
    			if(current.prev != null) {
    				current.prev.next = current.next;
    			}
    			size--;
    			return true;
    		}
    		current = current.next;
    	} 
    	return false;
    }

    @Override
    public boolean contains(Comparable k) {
    	//iterate through the list checking if the keys match with the one given  
    	//return true if one does otherwise return false
    	KeyPair current = head;
    	if(k == null) return false; //check to see if the key given is null
    	while(current != null) { //iterate through list 
    		if(current.key.compareTo(k) == 0) { //check keys to see if they are equal to k
    			return true;
    		}
    		current = current.next; 
    	}
        return false;
    }

    @Override
    public Object get(Comparable k) {
    	KeyPair current = head;
    	if(k == null) throw new IllegalArgumentException("null key"); //check to see if the key given is null
    	while(current.next != null) {
    		if(current.key.compareTo(k) == 0) { //check keys to see if they are equal to k
    			return current.value;
    		}
    		current = current.next; 
    	}
    	if(current.key.compareTo(k) == 0) return current.value; //check end cases
        return null;
    }

    @Override
    public int size() {
    	return size;
    }
    
    //private class KeyPair that holds key and value KeyPairs as well as reference to next
    class KeyPair { 
    	private Comparable key;
    	private Object value;
    	private KeyPair next;
    	private KeyPair prev;
    	
    	private KeyPair(Comparable key, Object value, KeyPair next, KeyPair previous) {
    		this.key = key;
    		this.value = value;
    		this.next = next;
    		this.prev = previous;
    	}
    	
    	private KeyPair(Comparable key, Object value) {
    		this.key = key;
    		this.value = value;
    	}
    	
    	private void setNext(KeyPair n) {
    		next = n;
    	}
    	
    	private void setPrev(KeyPair p) {
    		prev = p;
    	}
    	
    }

}
