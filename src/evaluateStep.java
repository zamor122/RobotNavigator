import java.util.*;
public class evaluateStep {
    private pointerNode start;
    private pointerNode goal;
    private pointerNode[][] grid;
    private ArrayList<pointerNode> notAvailable = new ArrayList<>();
    private compareNodes compare = new compareNodes();
    private PriorityQueue<pointerNode> available = new PriorityQueue<>(compare);
    private boolean pathFound = false;

    public evaluateStep(pointerNode start, pointerNode goal, pointerNode[][] grid) {
        this.start = start;
        this.goal = goal;
        this.grid = grid;
        available.add(start);
    }

    private void globalEuclideanDistance(pointerNode current){
        current.setCost((current.getY() - goal.getY()) + (Math.pow((current.getX() - goal.getX()),2) + Math.pow((current.getY() - goal.getY()),2)));
    }

    private void getGlobalEuclideanSuccessors(pointerNode n){
        for (pointerNode neighbor : n.getNeighbors()) {//euclideanDistance cost of all neighbors, set their parent, and add them to the available list
            if(!available.contains(neighbor) && !notAvailable.contains(neighbor)) {
                globalEuclideanDistance(neighbor);
                available.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }


    private void globalManhattanDistance(pointerNode current){
        current.setCost((current.getY() - goal.getY()) + (Math.abs((current.getX() - goal.getX())) + Math.abs((current.getY() - goal.getY()))));
    }

    private void getGlobalManhattanSuccessors(pointerNode n){
        for (pointerNode neighbor : n.getNeighbors()) {//euclideanDistance cost of all neighbors, set their parent, and add them to the available list
            if(!available.contains(neighbor) && !notAvailable.contains(neighbor)) {
                globalManhattanDistance(neighbor);
                available.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }

    private void manhattanDistance(pointerNode current){
        current.setCost(Math.abs((current.getX() - goal.getX())) + Math.abs((current.getY() - goal.getY())));
    }

    private void getManhattanSuccessors(pointerNode n) {
        for (pointerNode neighbor : n.getNeighbors()) {//euclideanDistance cost of all neighbors, set their parent, and add them to the available list
            if(!available.contains(neighbor) && !notAvailable.contains(neighbor)) {
                manhattanDistance(neighbor);
                available.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }

    private void euclideanDistance(pointerNode current){
        current.setCost(Math.sqrt(Math.pow((current.getX() - goal.getX()),2) + Math.pow((current.getY() - goal.getY()),2)));
    }

    private void getEuclideanSuccessors(pointerNode n) {
        for (pointerNode neighbor : n.getNeighbors()) {//euclideanDistance cost of all neighbors, set their parent, and add them to the available list
            if(!available.contains(neighbor) && !notAvailable.contains(neighbor)) {
                euclideanDistance(neighbor);
                available.add(neighbor);
                neighbor.setParent(n);
            }
        }
    }

    private void getPath(pointerNode n) {
        pointerNode current = n;
        while(current.getType() != 1) {//backtrack through parents and use boolean marker to indicate path before reaching the start node
            current.setPath();
            current = current.getParent();
        }
    }

    public void printGrid() {
        int numSteps = 1;
        if(!pathFound) {
            System.out.println("No path found");
        }
        else {
            for (pointerNode[] aGrid : grid) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (aGrid[j].getType() == 0) {
                        if (aGrid[j].getPath()) {
                            System.out.print("o ");
                            ++numSteps;
                        } else {
                            System.out.print(". ");
                        }
                    } else if (aGrid[j].getType() == 1) {//start
                        System.out.print("i ");
                    } else if (aGrid[j].getType() == 2) {//goal
                        System.out.print("g ");
                    } else {
                        System.out.print("+ ");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Number of steps taken: " + numSteps);
    }

    public void searchWithEuclideanDistance() {
        int numNodes = 1;
        while(!available.isEmpty()) {
            pointerNode current = available.poll();
            notAvailable.add(current);
            numNodes++;
            if(goal.isEqual(current)) {
                pathFound = true;
                getPath(current);
                System.out.println("Number of nodes: " + numNodes);
            }
            else {
                getEuclideanSuccessors(current);
            }
        }
    }

    public void searchWithManhattanDistance() {
        int numNodes = 1;
        while(!available.isEmpty()) {
            pointerNode current = available.poll();
            notAvailable.add(current);
            numNodes++;
            if(goal.isEqual(current)) {
                pathFound = true;
                getPath(current);
                System.out.println("Number of nodes: " + numNodes);
            }
            else {
                getManhattanSuccessors(current);
            }
        }
    }

    public void searchWithGlobalManhattanDistance() {
        int numNodes = 1;
        while(!available.isEmpty()) {
            pointerNode current = available.poll();
            notAvailable.add(current);
            numNodes++;
            if(goal.isEqual(current)) {
                pathFound = true;
                getPath(current);
                System.out.println("Number of nodes: " + numNodes);
            }
            else {
                getGlobalManhattanSuccessors(current);
            }
        }
    }

    public void searchWithGlobalEuclideanDistance() {
        int numNodes = 1;
        while(!available.isEmpty()) {
            pointerNode current = available.poll();
            notAvailable.add(current);
            numNodes++;
            if(goal.isEqual(current)) {
                pathFound = true;
                getPath(current);
                System.out.println("Number of nodes: " + numNodes);
            }
            else {
                getGlobalEuclideanSuccessors(current);
            }
        }
    }
}
