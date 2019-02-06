
public class DS_My implements DataStructureADT {

	private Pair first; //marks the first Pair in the list
	
    public DS_My() {
    	first = null;
    }

    @Override
    public void insert(Comparable k, Object v) {
    	Pair current = first;
    	if(k == null) throw new IllegalArgumentException("null key"); //check if key is null
    	if(current == null) {
    		first = new Pair(k, v);
    	}
    	else {
    		while(current.getNext() != null) {
    			if(current.getKey().compareTo(k) == 0) throw new RuntimeException("duplicate key"); //checks for duplicate keys
    			current = current.getNext();
    		}
    		if(current.getKey().compareTo(k) == 0) throw new RuntimeException("duplicate key");
    		else current.setNext(new Pair(k,v));
    	}
    }

    @Override
    public boolean remove(Comparable k) {
    	if(k == null) throw new IllegalArgumentException("null key");
    	Pair current = first;
    	while(current.getNext() != null && current.getNext().getKey().compareTo(k) != 0) {
    			current = current.getNext();
    	}
    	if(current.getNext() == null && current.getKey().compareTo(k) == 0) {
    		first = null;
    		return true;
    	}
    	else if(current.getNext() == null && current.getKey().compareTo(k) != 0) {
    		return false;
    	}
    	else if(current.getNext().getKey().compareTo(k) == 0) {
    		Pair after = current.getNext().getNext();
    		current.setNext(after);
    		return true;
    	}
    	return false;
    }

    @Override
    public boolean contains(Comparable k) {
    	//iterate through the list checking if the keys match with the one given  
    	//return true if one does otherwise return false
    	Pair current = first;
    	if(k == null) throw new IllegalArgumentException("null key"); //check to see if the key given is null
    	while(current.getNext() != null) { //iterate through list 
    		if(current.getKey().compareTo(k) == 0) { //check keys to see if they are equal to k
    			return true;
    		}
    		current = current.getNext(); 
    	}
    	if(current.getKey().compareTo(k) == 0) return true; //check end cases
        return false;
    }

    @Override
    public Object get(Comparable k) {
    	Pair current = first;
    	if(k == null) throw new IllegalArgumentException("null key"); //check to see if the key given is null
    	while(current.getNext() != null) {
    		if(current.getKey().compareTo(k) == 0) { //check keys to see if they are equal to k
    			return current.getValue();
    		}
    		current = current.getNext(); 
    	}
    	if(current.getKey().compareTo(k) == 0) return current.getValue(); //check end cases
        return null;
    }

    @Override
    public int size() {
    	Pair current = first;
    	int size = 0;
        while(current != null) {
        	size++;
        	current = current.getNext();
        }
        return size;
    }
    
    //private class Pair that holds key and value pairs as well as reference to next
    class Pair { 
    	private Comparable key;
    	private Object value;
    	private Pair next;
    	
    	private Pair(Comparable key, Object value, Pair next) {
    		this.key = key;
    		this.value = value;
    		this.next = next;
    	}
    	
    	private Pair(Comparable key, Object value) {
    		this.key = key;
    		this.value = value;
    	}
    	
    	private Comparable getKey() {
    		return key;
    	}
    	
    	private Object getValue() {
    		return value;
    	}
    	
    	private Pair getNext() {
    		return next;
    	}
    	
    	private void setNext(Pair n) {
    		next = n;
    	}
    }

}
