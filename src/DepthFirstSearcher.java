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

		// CLOSED list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been expanded.
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...

		// Stack implementing the Frontier list
		LinkedList<State> stack = new LinkedList<State>();

		while (!stack.isEmpty()) {
			// TODO return true if find a solution
			// TODO maintain the cost, noOfNodesExpanded
			// TODO update the maze if a solution found

			// use stack.pop() to pop the stack.
			// use stack.push(...) to elements to stack
		}

		// TODO return false if no solution
		return false;
	}
}
