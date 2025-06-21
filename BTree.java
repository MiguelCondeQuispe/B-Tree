package Aplicacion;

public class BTree<E extends Comparable<E>> {
    protected BNode<E> root;
    protected int orden;
    protected boolean up;
    protected BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }

    private E push(BNode<E> current, E cl) {
        int[] pos = new int[1];
        E mediana;
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }

    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<>(this.orden);
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2) {
            putNode(current, cl, rd, k);
        } else {
            putNode(nDes, cl, rd, k - posMdna);
        }
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }

    // ========== EJERCICIO 4 ==========
    public String buscarNombre(int codigo) {
        RegistroEstudiante dummy = new RegistroEstudiante(codigo, "");
        return buscarNombreRec((BNode<RegistroEstudiante>) this.root, dummy);
    }

    private String buscarNombreRec(BNode<RegistroEstudiante> node, RegistroEstudiante clave) {
        if (node == null) return "No encontrado";

        for (int i = 0; i < node.count; i++) {
            RegistroEstudiante actual = (RegistroEstudiante) node.keys.get(i);
            if (clave.compareTo(actual) == 0) {
                return actual.getNombre();
            } else if (clave.compareTo(actual) < 0) {
                return buscarNombreRec((BNode<RegistroEstudiante>) node.childs.get(i), clave);
            }
        }
        return buscarNombreRec((BNode<RegistroEstudiante>) node.childs.get(node.count), clave);
    }

    @Override
    public String toString() {
        return isEmpty() ? "Árbol vacío" : writeTree(this.root, "");
    }

    private String writeTree(BNode<E> node, String indent) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder(indent + node + "\n");
        for (int i = 0; i <= node.count; i++) {
            sb.append(writeTree(node.childs.get(i), indent + "  "));
        }
        return sb.toString();
    }
}
