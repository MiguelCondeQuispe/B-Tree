package btree;

import java.util.ArrayList;
import java.util.Collections;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected int idNode;
    private static int nextId = 0;

    public BNode(int n) {
        this.keys = new ArrayList<>(Collections.nCopies(n, null));
        this.childs = new ArrayList<>(Collections.nCopies(n, null));
        this.count = 0;
        this.idNode = nextId++;
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(E key, int[] pos) {
        for (int i = 0; i < count; i++) {
            if (keys.get(i) == null) continue;
            if (keys.get(i).equals(key)) {
                pos[0] = i;
                return true;
            } else if (keys.get(i).compareTo(key) > 0) {
                pos[0] = i;
                return false;
            }
        }
        pos[0] = count;
        return false;
    }

    public String toString() {
        return "Nodo " + idNode + ": " + keys.subList(0, count);
    }
}
