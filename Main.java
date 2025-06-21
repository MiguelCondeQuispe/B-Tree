package Aplicacion;

public class Main {
    public static void main(String[] args) {
        BTree<RegistroEstudiante> arbol = new BTree<>(4);

        RegistroEstudiante[] estudiantes = {
            new RegistroEstudiante(103, "Ana"),
            new RegistroEstudiante(110, "Luis"),
            new RegistroEstudiante(101, "Carlos"),
            new RegistroEstudiante(120, "Lucía"),
            new RegistroEstudiante(115, "David"),
            new RegistroEstudiante(125, "Jorge"),
            new RegistroEstudiante(140, "Camila"),
            new RegistroEstudiante(108, "Rosa"),
            new RegistroEstudiante(132, "Ernesto"),
            new RegistroEstudiante(128, "Denis"),
            new RegistroEstudiante(145, "Enrique"),
            new RegistroEstudiante(122, "Karina"),
            new RegistroEstudiante(108, "Juan") 
        };

        for (RegistroEstudiante est : estudiantes) {
            System.out.println("Insertando: " + est);
            arbol.insert(est);
        }

        System.out.println("\n>>> Estructura del árbol:");
        System.out.println(arbol);

        System.out.println("\nBuscando código 115 → " + arbol.buscarNombre(115)); 
        System.out.println("Buscando código 132 → " + arbol.buscarNombre(132)); 
        System.out.println("Buscando código 999 → " + arbol.buscarNombre(999)); 
    }
}
