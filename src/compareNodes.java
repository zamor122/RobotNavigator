import java.util.*;

public class compareNodes implements Comparator<pointerNode> {
    @Override
    public int compare(pointerNode n1, pointerNode n2)
    {
        return Double.compare(n1.getCost(), n2.getCost());
    }

}