
public class Counter {
	/***
	 * This class calculates the number of points from a Cribbage hand  
	 * using the Power Set of the cards
	 */
	private PowerSet<Card> cardps; 
	private Card starter; 
	
	// constructor 
	public Counter(Card[] hand, Card starter) {
		this.cardps = new PowerSet<Card>(hand); // initialize cardps with a PowerSet<Card> object 
		this.starter = starter; // sets the starter field to the Card object 
	}
	
	// calculates the total number of points for the hand by adding each scores from the methods below
	public int countPoints() {
		int score = 0; // initialize score to 0 
		// add total points counted by each method
		score += countPairs(); 
		score += countRuns();
		score += countFifteens(); 
		score += countFlush(); 
		score += countHisKnobs(); 
		return score; // return total score 
	}
	
	// Scoring for Pairs 
	private int countPairs() {
		int score = 0; 
		// iterate through subsets of the hand generated by PowerSet 
		for (int i = 0; i < cardps.getLength(); i++) { 
			Set<Card> set = cardps.getSet(i); 
			// check if each subset has two cards of the same rank
			if (set.getLength() == 2 && set.getElement(0).getLabel() == set.getElement(1).getLabel()) {
				score += 2; // add 2 points to score
			}
		}
		return score; 
	}
	
	
	// Runs
	public int countRuns() {
		int score = 0; 
		int longestRun = 0; // initialize the length of the longest run  
		for (int i = 0; i < cardps.getLength(); i++) {  // iterates through subsets of the hand
			Set<Card> set = cardps.getSet(i); 
			if (isRun(set)) {
				int runLength = set.getLength(); 
				// keep track of the longest run length 
				if (runLength > longestRun) { 
					longestRun = runLength; 
					score += runLength; 
				} else if (runLength == longestRun) { 
					// if the length of the run is equal to the longest run found: 
					score += runLength; // add the run length to the score
				}
			}
		}
		return score; 
	}
	
	
	// scoring for Fifteens 
	private int countFifteens() {
		int score = 0; 
		for (int i = 0; i < cardps.getLength(); i++) {
			Set<Card> set = cardps.getSet(i); 
			int sum = 0; 
			for (int j = 0; j < set.getLength(); j++) {
				sum += set.getElement(j).getRunRank(); // finds the length of the set
			}
			if (sum == 15) { // if the sum of the ranks in the subset is 15: 
				score += 2; // add 2 points to score
			}
		}
		return score; 
	}

	
	// scoring for flush 
	private int countFlush() {
		int score = 0; 
		boolean isFlush = true; 
		String firstSuit = starter.getSuit(); 
		
		for (int i = 0; i < cardps.getLength(); i++) {
			Set<Card> set = cardps.getSet(i); 
			for (int j = 0; j < set.getLength(); j++) {			
				// check if the current card suit is the same as the suit of the first card in hand
				if (!set.getElement(j).getSuit().equals(firstSuit)) { // if not
					isFlush = false; // return false 
					break; // end the loop
				}
			}	
		}
		// if the hand is a flash
		if (isFlush) { 
			score += 5; // add 5 points to score
		}
		return score; 	
	}
	
	// scoring for His Knobs: check if the hand has a Jack of the same suit as the starter card
	private int countHisKnobs() {
	    int score = 0;
	    String starterSuit = starter.getSuit();
	    String starterRank = starter.getLabel(); 
	    for (int i = 0; i < cardps.getLength(); i++) {
	        Set<Card> set = cardps.getSet(i);
	        // check if the hand has a Jack of the same suit as the starter card
	        if (set.contains(new Card(starterRank, starterSuit))) {
	            score += 1; // add 1 point to score 
	        }
	    }
	    return score;
	}
	
	
	private boolean isRun (Set<Card> set) {
		// In this method, we are going through the given set to check if it constitutes a run of 3 or more
		// consecutive cards. To do this, we are going to create an array of 13 cells to represent the
		// range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
		// each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
		// An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
		// contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
		// Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.
		
		int n = set.getLength();
		
		if (n <= 2) return false; // Run must be at least 3 in length.
		
		int[] rankArr = new int[13];
		for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.
		
		for (int i = 0; i < n; i++) {
			rankArr[set.getElement(i).getRunRank()-1] += 1;
		}

		// Now search in the array for a sequence of n consecutive 1's.
		int streak = 0;
		int maxStreak = 0;
		for (int i = 0; i < 13; i++) {
			if (rankArr[i] == 1) {
				streak++;
				if (streak > maxStreak) maxStreak = streak;
			} else {
				streak = 0;
			}
		}
		if (maxStreak == n) { // Check if this is the maximum streak.
			return true;
		} else {
			return false;
		}

	}
	
}
