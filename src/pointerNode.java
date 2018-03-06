import java.util.*;

public class pointerNode {
    private double cost;
    private int type;
    private int x;
    private int y;
    private ArrayList<pointerNode> neighbors = new ArrayList<>();
    private pointerNode parent = null;
    private boolean inPath = false;

    public pointerNode(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() { return this.x; }

    public void setX(int n) { this.x = n; }

    public int getY() { return this.y; }

    public void setY(int n) { this.y = n; }

    public double getCost() { return this.cost; }

    public void setCost(double n) { this.cost= n; }

    public void setType(int n) { this.type = n; }

    public int getType() { return this.type; }

    public void addNext(pointerNode n) {
        if(n.getType() != 3) {
            neighbors.add(n);
        }
    }
    public boolean isEqual(pointerNode n2)
    {
        return this.type == n2.getType();
    }
    public ArrayList<pointerNode> getNeighbors() { return neighbors; }

    public pointerNode getParent(){ return parent; }

    public void setParent(pointerNode n) { parent = n; }

    public boolean getPath(){ return inPath; }
    public void setPath(){ inPath = true; }
}
