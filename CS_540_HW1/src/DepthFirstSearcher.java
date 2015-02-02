import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Depth-First Search (DFS)
 * 
 * You should fill the search() method of this class.
 */
public class DepthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public DepthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main depth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD
		int depth; 
		int gVal;
		ArrayList<State> successors;
		depth = 0;
		gVal = 0;	//will need to do for astar
		State currPosition;
		//number of nodes expanded
		/*startSquare is our player's starting location*/
		Square startSquare = 
				new Square(maze.getPlayerSquare().X, maze.getPlayerSquare().Y);
		/*startState is our player's starting state*/
		State startState = new State(startSquare, null, gVal, depth);
		
		// CLOSED list is a 2D Boolean array that indicates if a state 
		//associated with a given position in the maze has already been expanded
		boolean[][] closed =new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		/*Check if we are already in the goal square initially.. I don't think
		 *this is possible, but just in case*/
		if(startState.isGoal(maze))
			return true;
			
		// Stack implementing the Frontier list
		LinkedList<State> stack = new LinkedList<State>();
		
		//check for "blocked" paths around current node and mark them as blocked
		examineBlockedPaths(startState, closed);
		
		//This stores all of successors of current node.
		successors = startState.getSuccessors(closed, maze);
		noOfNodesExpanded++;
		//add successors to the stack
		addToStack(stack, successors);
		
		while (!stack.isEmpty()) 
		{
			currPosition = stack.pop();
			noOfNodesExpanded++;
			// We found the goal!
			if(currPosition.isGoal(maze))
			{
				// so display the path
				displayPath(currPosition, startState);
				return true;
			}
			examineBlockedPaths(currPosition, closed);
			successors = currPosition.getSuccessors(closed, maze);
			addToStack(stack, successors);
		}
		return false;
	}
	
	/*My thinking here is that if it is set to true; it will be ignored*/
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
	
	private void addToStack(LinkedList<State> stack, ArrayList<State>successors)
	{
		State curr;
		for(int i = successors.size() - 1; i >=0; i--)
		{
			curr = successors.remove(i);
			stack.push(curr);
		}
	}
	
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
