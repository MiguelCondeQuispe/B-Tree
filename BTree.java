package btree;

import java.io.*;
import java.util.*;

import Aplicacion.RegistroEstudiante;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E cl) {
        up = false;
        E mediana = push(this.root, cl);
        if (up) {
            BNode<E> pnew = new BNode<>(orden);
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
            boolean found = current.searchNode(cl, pos);
            if (found) {
                System.out.println("Item duplicado\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(orden - 1)) {
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
        int posMdna = (k <= orden / 2) ? orden / 2 : orden / 2 + 1;
        nDes = new BNode<>(orden);

        for (int i = posMdna; i < orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= orden / 2) {
        	putNode(current, cl, rd, k);
        } else {
        	putNode(nDes, cl, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        up = true;
        return median;
    }

    public String toString() {
    	return isEmpty() ? "BTree is empty..." : writeTree(this.root);
    }

    private String writeTree(BNode<E> current) {
    	if (current == null) return "";
    	StringBuilder sb = new StringBuilder(current.toString() + "\n");
    	for (int i = 0; i <= current.count; i++) {
    		sb.append(writeTree(current.childs.get(i)));
    	}
    	return sb.toString();
    }

    public boolean search(E c1) { 
    	return searchRecursive(this.root, c1); 
    } 
    
    private boolean searchRecursive(BNode<E>current, E c1) { 
    	if (current==null) { 
    		return false; 
    	} 
    	int[]pos=new int[1]; 
    	boolean found = current.searchNode(c1, pos); 
    	if(found) { 
    		System.out.println(c1 + "se encuentra en el nodo " + current.idNode+" en la posicion "+ pos[0]); 
    		return true; 
    	}else {
    		return searchRecursive(current.childs.get(pos[0]), c1); 
    	} 
    }
    
    public void remove(E key) {
    	if(this.root ==null) {
    		System.out.println("El arbol esta vacio");
    		return;
    	}
    	removeInternal(this.root, key);
    	
    	if(this.root.count ==0 && !this.root.childs.isEmpty()) {
    		this.root = this.root.childs.get(0);
    	}
    	System .out.println("Eliminacion completada");
    }
    
    private void removeInternal(BNode<E> node, E key){
    	if (node == null) {
            System.out.println("Subárbol vacio no hay nodo que eliminar");
            return;
        }
    	
    	int pos = findKeyPosition(node,key);
    	if(pos < node.count && node.keys.get(pos).compareTo(key)==0){
    		if (node.childs.get(0)==null) {
    			for (int i= pos;i<node.count -1; i++){
    				node.keys.set(i,  node.keys.get(i+1));
    			}
    			node.keys.set(node.count - 1,  null);
    			node.count--;
    		}else {
    			System.out.println("No se completo la eliminacion del nodo interno ");
    		}
    	}else {
    		if(node.childs.get(pos)==null) {
    			System.out.println("La clave no se encuentra en el arbol");
    			return;
    		}
    	}
    	removeInternal(node.childs.get(pos),key);
    }
    
    private int findKeyPosition(BNode<E> node,E key) {
    	int i = 0;
    	while (i<node.count && key.compareTo(node.keys.get(i))>0) {
    		i++;
    	}
    	return i;
    }
    
   

}
