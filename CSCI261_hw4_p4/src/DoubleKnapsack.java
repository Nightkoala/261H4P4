/**
 * DoubleKnapsack.java
 * 
 * @author	Derek Brown <djb3718@rit.edu>
 *
 * Purpose	Variation on the knapsack problem,  Find the max cost of items that
 * 			can fit in both of the backpacks.
 */

import java.util.Scanner;

public class DoubleKnapsack {
	
	// Attributes
	
	private int items;
	private int weight;
	private int[] costs;
	private int[] weights;
	private int[][] S;
	
	// Constructor
	
	/**
	 * Constructor for creating an instance of a DoubleKnapsack.
	 * 
	 * @param n	The number of items provided by user.
	 * @param c	The array of costs associated with each item.
	 * @param w	The array of weights associated with each item.
	 * @param W	The total capacity of the knapsacks.
	 */
	public DoubleKnapsack( int n, int[] c, int[] w, int W ) {
		this.items = n;
		this.weight = W;
		this.costs = c;
		this.weights = w;
		S = new int[n+1][W+1];
		for( int i = 0 ; i <= W ; i++ ) {
			S[0][i] = 0;
		}//end for
		for( int i = 0 ; i <= n ; i++ ) {
			S[i][0] = 0;
		}//end for
	}//end DoubleKnapsack constructor
	
	// Methods
	
	/**
	 * The implementation for the double knapsack algorithm, Finds the greatest
	 * cost of items that could fit in both backpacks by pretending that there
	 * is a single knapsack of capacity of both of the knapsacks.
	 * 
	 * @return	The greatest cost that can fit in both knapsacks.
	 */
	public int doubleKnapsackSolver() {
		for( int v = 1 ; v <= weight ; v++ ) {
			for( int k = 1 ; k <= items ; k++ ) {
				S[k][v] = S[k-1][v];
				if( ( weights[k-1] <= v ) && ( S[k-1][v-weights[k-1]] + costs[k-1] > S[k][v] ) ) {
					S[k][v] = S[k-1][v-weights[k-1]] + costs[k-1];
				}//end if
			}//end for k
		}//end for v
		return S[items][weight];
	}//end doubleKnapsackSolver

	/**
	 * The main logic for the program, The program reads in the data from the
	 * user and feeds that data into the algorithm,  Then displays the results.
	 * 
	 * @param args	Command line arguments, unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner( System.in );
		String input = sc.next();
		int numItems = Integer.parseInt( input );
		input = sc.next();
		int weight1 = Integer.parseInt( input );
		input = sc.next();
		int weight2 = Integer.parseInt( input );
		int iWeight;
		int iCost;
		int[] weights = new int[numItems];
		int[] costs = new int[numItems];
		for( int i = 0 ; i < numItems ; i++ ) {
			input = sc.next();
			iWeight = Integer.parseInt( input );
			input = sc.next();
			iCost = Integer.parseInt( input );
			weights[i] = iWeight;
			costs[i] = iCost;
		}//end for
		sc.close();
		DoubleKnapsack D = new DoubleKnapsack( numItems, costs, weights, weight1+weight2 );
		int solution = D.doubleKnapsackSolver();
		System.out.println(solution);
	}//end main
}//end DoubleKnapsack
