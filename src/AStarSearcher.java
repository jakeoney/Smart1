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
	public boolean search() {

		// FILL THIS METHOD

		// CLOSED list is a Boolean array that indicates if a state associated with a given position in the maze has already been expanded. 
		boolean[][] closed = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		// OPEN list (aka Frontier list)
		PriorityQueue<StateFValuePair> open = new PriorityQueue<StateFValuePair>();

		// TODO initialize the root state and add
		// to OPEN list
		// ...

		while (!open.isEmpty()) {
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded,
			// TODO update the maze if a solution found

			// use open.poll() to extract the minimum stateFValuePair.
			// use open.add(...) to add stateFValue pairs
		}

		// TODO return false if no solution
		return false;
	}

}
