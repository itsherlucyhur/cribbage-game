
public class Set<T>{
	
	private LinearNode<T> setStart; 
	
	// constructor 
	public Set() {
		setStart = null; // empty linked list 
	}
		
	// creating a new node containing the given element 
	public void add(T element) {
		// creates a new node
		LinearNode<T> newNode = new LinearNode<>(element); 
		if (setStart == null) {
			setStart = newNode; // add it to the start of the linked list
		}
		else { // if the list is empty 
			newNode.setNext(setStart);
			setStart = newNode; // new node becomes the start node 
		}
	}
	
	// returns the number of items in linked list 
	public int getLength() {
		int count = 0; 
		LinearNode<T> current = setStart; 
		while (current != null) {
			current = current.getNext(); 
			count++; 
		}
		return count; 
	}
	
	// returns the element in ith node of the linked list 
	public T getElement(int i) {
		LinearNode<T> current = setStart; 
		for (int j = 0; j < i; j++) {
			current = current.getNext(); 
		}
		return current.getElement(); 
	}
	
	// return true if the given element is found within the linked list
	public boolean contains(T element) {
		LinearNode<T> current = setStart; 
		while (current != null) {
			// if the given element is in the linked list
			if (current.getElement().equals(element)) {
				return true; 
			}
			current = current.getNext(); 
		}
		return false; // if it's not found, return false
	}
	
	// return a string representation of the linked list
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		
		LinearNode<T> current = setStart; 
		while (current != null) {
			sb.append(current.getElement());
			sb.append(" "); 
			current = current.getNext(); 			
		}

		return sb.toString(); 
	}
	

}
