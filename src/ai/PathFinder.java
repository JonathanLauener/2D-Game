package ai;
import java.util.ArrayList;
import ai.Node;

import main.GamePanel;
public class PathFinder {

	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<>();
	Node startNode, currentNode, goalNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNodes();
		
		
	}
	public void instantiateNodes() {
		node = new Node[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			node[col][row] = new Node(col,row);
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	public void resetNode() {
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
		resetNode();
		startNode = node[startCol][startRow];
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
		int tileNum = gp.tileM.mapTileNum[gp.mapnum][col][row];
		if(gp.tileM.tile[tileNum].collision == true) {
			node[col][row].solid = true;
		}
		getCost(node[col][row]);
		col++;
		if(col == gp.maxWorldCol) {
			col = 0;
			row++;
		}
		
		
	
	}
	}
	public void getCost(Node node) {
		int xDistance = Math.abs(node.col - goalNode.col);
		int yDistance = Math.abs(node.row - goalNode.row);
		node.gcost = xDistance + yDistance;
		
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hcost = xDistance + yDistance;
		node.fcost = node.gcost + node.hcost;
		
		
	}
	public boolean search() {
		while (goalReached == false && step < 500) {
		int col = currentNode.col;
		int row = currentNode.row;
		
		currentNode.checked = true;
		openList.remove(currentNode);
		
		if(row - 1 >= 0) {
			openNode(node[col][row-1]);
		}
		if(col - 1 >= 0) {
			openNode(node[col-1][row]);
		}
		
		if(row + 1 < gp.maxWorldRow) {
			openNode(node[col][row+1]);
		}
		if(col + 1 < gp.maxWorldRow) {
			openNode(node[col+1][row]);
		}
		
		int bestNodeIndex = 0;
		int bestNodefCost = 999;
		for(int i = 0; i < openList.size(); i++) {
			
			if(openList.get(i).fcost < bestNodefCost) {
				bestNodeIndex = i;
				bestNodefCost = openList.get(i).fcost;
			}
			else if(openList.get(i).fcost == bestNodefCost) {
				if(openList.get(i).gcost < openList.get(bestNodeIndex).gcost) {
					bestNodeIndex = i;
				}
			}
		}
		
		if(openList.size() ==0) {
			break;
		}
		currentNode = openList.get(bestNodeIndex);
		
		if (currentNode == goalNode) {
			goalReached = true;
			trackThePath();
		}
		step++;
		}
		return goalReached;
	}

	public void openNode(Node node) {
		if(node.open == false && node.checked == false && node.solid == false) {
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
			
		}
		public void trackThePath() {
			Node current = goalNode;
			
			while(current != startNode) {
				
				pathList.add(0,current);
				current = current.parent;
			}
			
		
	}
}
