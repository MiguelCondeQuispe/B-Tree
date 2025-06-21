package Main;

import btree.BTree;

public class Main {
    public static void main(String[] args) {
    	
        System.out.println(">>> Inserción en Árbol B de orden 5");
        BTree<Integer> arbolOrden5 = new BTree<>(5);
        int[] clavesAInsertar = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25,15, 5, 65, 35, 60, 18, 93, 94};

        for (int clave : clavesAInsertar) {
            System.out.println("\nInsertando clave: " + clave);
            arbolOrden5.insert(clave);
            System.out.println(arbolOrden5);
        }

        System.out.println("\n>>> Eliminación desde hojas en el Árbol B de orden 5");

        int[] clavesAEliminar = {5, 15, 25, 94}; 
        for (int clave : clavesAEliminar) {
            System.out.println("\nEliminando clave: " + clave);
            arbolOrden5.remove(clave);
            System.out.println(arbolOrden5);
        }
    }
}
