package players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gamemodel.Bomb;
import gamemodel.Directions;
import gamemodel.GameLogic;
import gamemodel.Node;

public class BotPlayer extends AbstractPlayer implements PlayerIf {

	private String botType;

	public BotPlayer(double x, double y, GameLogic gameLogic, int playerNumber, int team, String botType) {
		super((int) x, (int) y, gameLogic, playerNumber, team);
		this.botType = botType;
	}

	public synchronized void think() {
		// TODO
		if (!botType.equals("standing still")) {
			while (getLives() > -1) {

				placeBomb();

				if (Boolean.FALSE.equals(getSafePlaces()[getX()][getY()])) {
					moveTo(searchSavePlace());

				}

				if (gameLogic.getUpgrades()[getX()][getY()] != null) {
					pickUpUpgrade();
				}

				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	private boolean isInBombRange(Bomb bomb, int x, int y) {
		return (((x >= bomb.getX() - bomb.getBombRadius() && x <= bomb.getX() + bomb.getBombRadius()) // check x-range
				&& bomb.getY() == y) // and check exact y
				|| (x == bomb.getX() // check exact x
						&& (y >= bomb.getY() - bomb.getBombRadius() && y <= bomb.getY() + bomb.getBombRadius())) // and
																													// y-range
		);
	}

	/**
	 * @return 2D Array, false at unsafe places, null at safe places
	 */
	private Boolean[][] getSafePlaces() {
		Boolean[][] safe = new Boolean[gameLogic.getWidth()][gameLogic.getHeight()];
		Bomb[][] allbombs = gameLogic.getBombs();
		for (Bomb[] bombrow : allbombs) {
			for (Bomb bomb : bombrow) {
				if (bomb != null) {

					safe[bomb.getX()][bomb.getY()] = false;
					safe = checkSafePlaces(bomb, safe, -1, 0, Directions.LEFT);
					safe = checkSafePlaces(bomb, safe, 0, -1, Directions.UP);
					safe = checkSafePlaces(bomb, safe, 1, 0, Directions.RIGHT);
					safe = checkSafePlaces(bomb, safe, 0, 1, Directions.DOWN);

				}

			}
		}
		return safe;
	}

	private Boolean[][] checkSafePlaces(Bomb bomb, Boolean[][] safe, int xChange, int yChange, Directions direction) {
		for (int i = 1; i <= bomb.getBombRadius(); i++) {
			if (bomb.calculateExplosionNoEffect(xChange, yChange, direction, i)) {
				return safe;
			} else {
				safe[bomb.getX() + i * xChange][bomb.getY() + i * yChange] = false;
			}
		}
		return safe;
	}

	private List<Node> searchSavePlace() {
		// 2d array, Path ausbreiten solange bis ein sicherer Platz gefunden wurde
		//
		Node[][] nodes = new Node[gameLogic.getWidth()][gameLogic.getHeight()];
		Boolean[][] safe = getSafePlaces();
		Set<Node> openNodes = new HashSet<>();
		Set<Node> closedNodes = new HashSet<>();

		nodes[super.getX()][super.getY()] = new Node(super.getX(), super.getY(), 0, 0, null); // start
		openNodes.add(nodes[super.getX()][super.getY()]);

		while (!openNodes.isEmpty()) {
			Node currentNode = getCheapestNode(nodes, openNodes);

//			System.out.println("end search");
			if (currentNode != null) {

				openNodes.remove(currentNode);
				closedNodes.add(currentNode);

				if (!Boolean.FALSE.equals(safe[currentNode.getX()][currentNode.getY()])) {
					return createPathFromTarget(currentNode);
				}

				int x = currentNode.getX();
				int y = currentNode.getY();
				int f = currentNode.getFullCost();

				for (int i = 1; i < 5; i++) {
					int dx = x;
					int dy = y;
					switch (i) {
					case 1:
						dx = x - 1;
						dy = y;
						break;
					case 2:
						dx = x;
						dy = y - 1;
						break;
					case 3:
						dx = x + 1;
						dy = y;
						break;
					case 4:
						dx = x;
						dy = y + 1;
						break;
					default:
						break;
					}

					if (isWalkable(dx, dy)) {
						if (nodes[dx][dy] == null) {
							// node does not exist yet
							nodes[dx][dy] = new Node(dx, dy, f + 10, 0, currentNode);
							openNodes.add(nodes[dx][dy]);
						} else if (openNodes.contains(nodes[dx][dy]) && f + 10 < nodes[dx][dy].getCost()) {
							// better path to this node was found
							// change cost and parent
							nodes[dx][dy].setCost(f + 10);
							nodes[dx][dy].setParent(currentNode);
						}
					}

				}
			}
		}
		return new ArrayList<>();
		//
	}

	private void moveTo(Point point) {
		moveTo(createPathTo(point));
	}

	private void moveTo(List<Node> path) {
		if (path.isEmpty()) {
			return;
		}
		for (int i = 0; i < path.size(); i++) {
//			System.out.println(
//					"Move to: " + path.get(path.size() - 1 - i).getX() + " " + path.get(path.size() - 1 - i).getY());
			int dx = path.get(path.size() - 1 - i).getX() - super.getX();
			int dy = path.get(path.size() - 1 - i).getY() - super.getY();
//			System.out.println("d x y " + dx + " " + dy);
			if (dx < 0) {
				super.setDirection(1);
			} else if (dx > 0) {
				super.setDirection(3);
			}
			if (dy < 0) {
				super.setDirection(2);
			} else if (dy > 0) {
				super.setDirection(4);
			}

			while (Math.abs(path.get(path.size() - 1 - i).getX() - x) > 0.2
					|| Math.abs(path.get(path.size() - 1 - i).getY() - y) > 0.2) {
				// eventuell verbessern
				try {
					Thread.sleep(1000 / 60);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}

			}
			super.setDirection(0);
		}

	}

	/**
	 * @param target
	 * @return ArrayList of Nodes if a path was found, otherwise an empty List
	 */
	private List<Node> createPathTo(Point target) {
		Node[][] nodes = new Node[gameLogic.getWidth()][gameLogic.getHeight()];
		Set<Node> openNodes = new HashSet<>();
		Set<Node> closedNodes = new HashSet<>();

		nodes[super.getX()][super.getY()] = new Node(super.getX(), super.getY(), 0, 0, null); // start
		openNodes.add(nodes[super.getX()][super.getY()]);

		while (!openNodes.isEmpty()) {
			Node currentNode = getCheapestNode(nodes, openNodes);

			if (currentNode != null) {

				openNodes.remove(currentNode);
				closedNodes.add(currentNode);

				if (currentNode.getX() == target.x && currentNode.getY() == target.y) {
					// current Node is the goal, create path and save it in a List
					return createPathFromTarget(currentNode);
				}

				int x = currentNode.getX();
				int y = currentNode.getY();
				int f = currentNode.getFullCost();

				int dx = x;
				int dy = y;
				for (int i = 1; i < 5; i++) {
					switch (i) {
					case 1:
						dx = x - 1;
						dy = y;
						break;
					case 2:
						dx = x;
						dy = y - 1;
						break;
					case 3:
						dx = x + 1;
						dy = y;
						break;
					case 4:
						dx = x;
						dy = y + 1;
						break;
					default:
						break;
					}

					if (isWalkable(dx, dy)) {
						if (nodes[dx][dy] == null) {
							// node does not exist yet
							nodes[dx][dy] = new Node(dx, dy, f + 10,
									10 * Math.abs(dx - target.x) + 10 * Math.abs(dy - target.y), currentNode);
							openNodes.add(nodes[dx][dy]);
						} else if (openNodes.contains(nodes[dx][dy]) && f + 10 < nodes[dx][dy].getCost()) {
							// better path to this node was found
							// change cost and parent
							nodes[dx][dy].setCost(f + 10);
							nodes[dx][dy].setParent(currentNode);
						}
					}

				}
			}
		}
		return new ArrayList<>();
	}

	/**
	 * @param nodes     2D Array of nodes
	 * @param openNodes Only nodes in this set are checked
	 * @return The node with the lowest full cost
	 */
	private Node getCheapestNode(Node[][] nodes, Set<Node> openNodes) {
		int currentFullCost = Integer.MAX_VALUE;
		Node currentNode = null;
		for (Node[] nodeRow : nodes) {
			for (Node node : nodeRow) {
				if (node != null && node.getFullCost() < currentFullCost && openNodes.contains(node)) {
					currentFullCost = node.getFullCost();
					currentNode = node;
				}
			}
		}
		return currentNode;
	}

	private List<Node> createPathFromTarget(Node currentNode) {
		List<Node> path = new ArrayList<>();
		path.add(currentNode);

		while (currentNode.getParent() != null) {
			currentNode = currentNode.getParent();
			path.add(currentNode);
		}
		return path;
	}

	/**
	 * @param x
	 * @param y
	 * @return True if the square at {@code x} and {@code y} does not contain any
	 *         Block or Bomb, otherwise false
	 */
	private boolean isWalkable(int x, int y) {
		return (gameLogic.getSolidBlocks()[x][y] == null && gameLogic.getBrokenBlocks()[x][y] == null
				&& gameLogic.getBombs()[x][y] == null);
	}

	@Override
	public void render(Graphics2D g, int size, int start) {
		// TODO nicht fertig halt, bzw in AbstractPLayer regeln

		g.setColor(new Color(150, 150, 150));
		g.fillOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);

		g.setColor(new Color(222, 99, 22));
		g.drawOval((int) (start + x * size), (int) (start + y * size), size - 1, size - 1);

		g.drawString("Bot", (int) (start + x * size) + size / 2, (int) (start + y * size) + size / 2);

	}

}
