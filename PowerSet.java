
public class PowerSet<T> {
	/**
	 * This class is the Power Set of a given set
	 */
	private Set<T>[] set; 
	
	// constructor
	public PowerSet(T[] elements) {
		int num = (int) Math.pow(2, elements.length); 
		int i; 
		// array of Sets with size 2 to the power of the length of the input array 
		set = new Set[num]; 
		for (i = 0; i < num; i++) {
			String S = Integer.toBinaryString(i);
			while (S.length() < elements.length) {
				S = "0" + S; 
			}
			Set<T> newSet = new Set<>(); // new empty Set of type T
			// loop through the binary string
			for (int j = 0; j < S.length(); j++) {
				if (S.charAt(j) == '1') {
					newSet.add(elements[j]); 
				}
			}
			set[i] = newSet; // new Set assigned to index i of array of Sets
		}
		
	}
	
	// returns the number of items in the array of sets in the array
	public int getLength() {
		return set.length; 
	}
	
	// returns the Set stored at index i of array 
	public Set<T> getSet(int i) {
		return set[i]; 
	}

}
