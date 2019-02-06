
public class DS_My implements DataStructureADT {

	private Pair first; //marks the first pair in the list
	
    public DS_My() {
    	first = null;
    }

    @Override
    public void insert(Comparable k, Object v) {
    	Pair current = first;
    	if(k == null) throw new IllegalArgumentException("null key"); //check if key is null
    	if(this.contains(k)) throw new RuntimeException("duplicate key");
    	if(current == null) {
    		first = new Pair(k, v);
    	}
    	else {
    		while(current.next != null) {
    			current = current.next;
    		}
    		current.setNext(new Pair(k,v));
    	}
    }

    @Override
    public boolean remove(Comparable k) {
    	if(k == null) throw new IllegalArgumentException("null key");
    	Pair current = first;
    	while(current.next != null && current.next.key.compareTo(k) != 0) {
    			current = current.next;
    	}
    	if(current.next == null && current.key.compareTo(k) == 0) {
    		current = null;
    		return true;
    	}
    	else if(current.next == null && current.key.compareTo(k) != 0) {
    		return false;
    	}
    	else if(current.next.key.compareTo(k) == 0) {
    		Pair after = current.next.next;
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
    	Pair current = first;
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
    	Pair current = first;
    	int size = 0;
        while(current != null) {
        	size++;
        	current = current.next;
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
    	
    	private void setNext(Pair n) {
    		next = n;
    	}
    }

}
