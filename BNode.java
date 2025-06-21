package Aplicacion;

import java.util.ArrayList;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected static int ID_GEN = 0;
    protected int idNode;

    public BNode(int n) {
        this.idNode = ID_GEN++;
        this.count = 0;
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        for (int i = 0; i < n; i++) this.keys.add(null);
        for (int i = 0; i < n + 1; i++) this.childs.add(null);
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    public boolean searchNode(E key, int[] pos) {
        pos[0] = 0;
        while (pos[0] < count && keys.get(pos[0]) != null && ((Comparable<E>) key).compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        return (pos[0] < count && keys.get(pos[0]) != null && ((Comparable<E>) key).compareTo(keys.get(pos[0])) == 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo ").append(idNode).append(": ");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i)).append(" ");
        }
        return sb.toString().trim();
    }
}
