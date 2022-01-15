package gamemodel;

public class Node {

	private int x;
	private int y;
	private int cost;
	private int distance;
	private boolean closed;
	private Node parent;

	public Node(int x, int y, int movementCost, int distanceGuess, Node parent) {
		this.x = x;
		this.y = y;
		this.cost = movementCost;
		this.distance = distanceGuess;
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getFullCost() {
		return cost + distance;
	}

}
