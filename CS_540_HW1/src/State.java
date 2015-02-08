import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State 
{
	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the DFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth)
	{
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            closed[i][j] is true if (i,j) is already expanded
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] closed, Maze maze) 
	{
		Square leftSquare, downSquare, rightSquare, upSquare;
		State left, down, right, up;
		ArrayList<State> successors = new ArrayList<State>();
		
		//this node is getting expanded so set it to false in closed list
		closed[this.getSquare().X][this.getSquare().Y] = true;
		
		//Defining the squares and states
		upSquare = new Square(this.getX() - 1, this.getY());
		if(closed[upSquare.X][upSquare.Y] == false)
		{
			up = new State(upSquare, this, this.getGValue() + 1, this.getDepth() + 1);
			successors.add(up);
		}
		
		rightSquare = new Square(this.getX(), this.getY() + 1);
		if(closed[rightSquare.X][rightSquare.Y] == false)
		{
			right = new State(rightSquare, this, this.getGValue() + 1, this.getDepth() + 1);
			successors.add(right);
		}

		downSquare = new Square(this.getX() + 1, this.getY());
		if(closed[downSquare.X][downSquare.Y] == false)
		{
			down = new State(downSquare, this, this.getGValue() + 1, this.getDepth() + 1);
			successors.add(down);
		}
		
		leftSquare = new Square(this.getX(), this.getY() - 1);
		//if true, it has been explored
		if(closed[leftSquare.X][leftSquare.Y] == false)
		{
			left = new State(leftSquare, this, this.getGValue() + 1, this.getDepth() + 1);
			successors.add(left);
		}
		return successors;
	}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the DFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
}
