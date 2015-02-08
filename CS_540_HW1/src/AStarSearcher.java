/*Author: Jake Oney
 *Course: CS 540 - Introduction to Artificial Intelligence
 *Instructor: Chuck Dyer
 *Homework 1 Problem 3
 */

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.      
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() 
	{
		ArrayList<State> successors;
		StateFValuePair currPosition;
		int depth = 0; 
		int gVal = 0;
		
		// CLOSED list is a Boolean array that indicates if a state associated 
		//with a given position in the maze has already been expanded. 
		boolean[][] closed =new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// OPEN list (aka Frontier list)
		PriorityQueue<StateFValuePair> open = 
										new PriorityQueue<StateFValuePair>();

		/*startSquare is our player's starting location*/
		Square startSquare = 
				new Square(maze.getPlayerSquare().X, maze.getPlayerSquare().Y);
		Square goalSquare = 
				new Square(maze.getGoalSquare().X, maze.getGoalSquare().Y);
		/*startState is our player's starting state*/
		State startState = new State(startSquare, null, gVal, depth);
		
		//check for "blocked" paths around current node and mark them as blocked
		examineBlockedPaths(startState, closed);
		successors = startState.getSuccessors(closed, maze);
		noOfNodesExpanded++;
		
		//This will compute the heuristic values for all nodes in the successors
		//list and add them to the priority queue
		computeHeuristic(successors, goalSquare, open);
		
		while (!open.isEmpty()) 
		{
			// use open.poll() to extract the minimum stateFValuePair.
			currPosition = open.poll();
			noOfNodesExpanded++;
			// We found the goal!
			if(currPosition.getState().isGoal(maze))
			{
				// so display the path
				displayPath(currPosition.getState(), startState);
				return true;
			}
			// Mark the walls as "a closed path"
			examineBlockedPaths(currPosition.getState(), closed);
			// Get the successors of the newly explored node
			successors = currPosition.getState().getSuccessors(closed, maze);
			// Compute the heuristic value and add the successors to the frontier
			computeHeuristic(successors, goalSquare, open);
		}
		return false;
	}

	/**
	 * The purpose of the method is to "examine the surroundings" to mark the
	 * boundary walls as explored so we will not consider those as possible 
	 * paths in the "getSuccessors" method. 
	 * 
	 * @param currState is the state we are currently at
	 * @param closed the 2D maze that shows what has been "explored"
	 */
	private void examineBlockedPaths(State currState, boolean[][] closed)
	{
		//If path to the left is '%' set it to false -> blocked
		if(maze.getSquareValue(currState.getX(), currState.getY() - 1) == '%')
		{
			closed[currState.getX()][currState.getY() - 1] = true;
		}
		//RIGHT
		if(maze.getSquareValue(currState.getX(), currState.getY() + 1) == '%')
		{
			closed[currState.getX()][currState.getY() + 1] = true;
		}
		//TOP
		if(maze.getSquareValue(currState.getX() - 1, currState.getY()) == '%')
		{
			closed[currState.getX() - 1][currState.getY()] = true;
		}
		//BOTTOM
		if(maze.getSquareValue(currState.getX() + 1, currState.getY()) == '%')
		{
			closed[currState.getX() + 1][currState.getY()] = true;
		}
	}
	
	/**
	 * computeHeuristic examines each successor. Computes an "H-value" that is
	 * the "city-block" distance between the current node and the goal node. 
	 * This value is added to the "G-Value" which is the distance from the start
	 * node. This "F-Value" will be prioritized in the priority queue (ie the 
	 * lowest "F-Value" will be at the top of the priority queue.
	 * 
	 * @param successors list of states to have their heuristic values computed
	 * 					 and to be added to the priority queue
	 * @param goal		 Used to obtain the goal state x & y coordinates 
	 * @param frontier	 The priority queue. The successors will be added to it
	 */
	private void computeHeuristic(
						ArrayList<State> successors, 
						Square goal, 
						PriorityQueue<StateFValuePair> frontier)
	{
		//current node being examined
		State curr;
		//hValue stores the City-Block distance to the goal position
		int hValue;	
		//fValue is hValue + gValue
		int fValue; 
		//Heuristic value and state value in an object
		StateFValuePair currentStateHeurstic;
		for(int i = successors.size() - 1; i >= 0; i--)
		{
			curr = successors.remove(i);
			/*Computes the distance between the current state and the goal state*/
			hValue = Math.abs(curr.getX() - goal.X);
			hValue += Math.abs(curr.getY() - goal.Y);

			//fValue = hValue + gValue
			fValue = hValue + curr.getGValue();
			currentStateHeurstic = new StateFValuePair(curr, fValue);
			frontier.add(currentStateHeurstic);
		}
	}
	
	/**
	 * The purpose of this is to display the solution path found. This will only
	 * be ran once the goal has been found. At this point, the goal state will
	 * be traced back to the start state and a '.' will be placed along the path
	 * 
	 * @param goal the ending goal state
	 * @param start the starting state
	 */
	private void displayPath(State goal, State start)
	{
		State parent = goal.getParent();
		cost++;
		while(parent.getParent() != null)
		{
			maze.setOneSquare(parent.getSquare(), '.');
			parent = parent.getParent();
			cost++;
		}
	}
}
