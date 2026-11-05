import java.util.Scanner;
public class Main1 {
    public static void main(String[] args) {
        // ===== PASO 6: arreglo de objetos (superclase + subclase) =====
        Personaje[] personajes = {
                new Viajero("Rick Sanchez", "Human", "Vivo", 51, "C-137", new int[]{80, 65, 90, 70}),
                new Viajero("Morty Smith", "Human", "Vivo", 51, "C-137", new int[]{55, 60, 45, 75}),
                new Personaje("Summer Smith", "Human", "Vivo", 42),
                new Personaje("Beth Smith", "Human", "Vivo", 40),
                new Personaje("Jerry Smith", "Human", "Vivo", 38),
                new Personaje("Birdperson", "Alien", "Muerto", 9),
                new Viajero("Evil Morty", "Human", "Vivo", 7, "35-C", new int[]{95, 85, 100, 90}),
                new Personaje("Mr. Meeseeks", "Alien", "Desconocido", 5)
        };
        System.out.println("=== DISPOSITIVO INTERDIMENSIONAL | Taller Semana 6 ===");
        System.out.println("Personajes registrados: " + personajes.length);
        // ===== PASO 7: mostrar el registro =====
        System.out.println("\n--- REGISTRO INTERDIMENSIONAL ---");
        System.out.printf("%-4s %-15s %-9s %-12s %-9s %s%n", "#", "Nombre", "Especie", "Estado", "Tipo", "Episodios");
        for (int i = 0; i < personajes.length; i++) {
            Personaje p = personajes[i];
            System.out.printf("%-4d %-15s %-9s %-12s %-9s %d%n",
                    i + 1, p.getNombre(), p.getEspecie(), p.getEstado(), p.getTipo(), p.getEpisodios());
        }
        // ===== PASO 8: acceso por indice y validacion =====
        System.out.println("\n--- ACCESO POR INDICE ---");
        System.out.println("Primero (indice 0): " + personajes[0].getNombre());
        System.out.println("Ultimo (indice length - 1): " + personajes[personajes.length - 1].getNombre());

        int indice = 8; // prueba con 3 y luego con 8
        if (indice >= 0 && indice < personajes.length) {
            System.out.println("personajes[" + indice + "] = " + personajes[indice].getNombre());
        } else {
            System.out.println("Indice " + indice + " fuera de rango (valido: 0 a " + (personajes.length - 1) + ")");
        }

        // Que pasa si NO validamos el indice:
        try {
            System.out.println(personajes[8].getNombre());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Portal cerrado! -> " + e.getMessage());
        }
        // ===== PASO 9: estadisticas y conteo por estado =====
        int suma = 0;
        int posMayor = 0;
        int posMenor = 0;
        int vivos = 0, muertos = 0, desconocidos = 0;

        for (int i = 0; i < personajes.length; i++) {
            int ep = personajes[i].getEpisodios();
            suma = suma + ep;
            if (ep > personajes[posMayor].getEpisodios()) {
                posMayor = i;
            }
            if (ep < personajes[posMenor].getEpisodios()) {
                posMenor = i;
            }
        }
        for (Personaje p : personajes) {
            if (p.getEstado().equals("Vivo")) {
                vivos++;
            } else if (p.getEstado().equals("Muerto")) {
                muertos++;
            } else {
                desconocidos++;
            }
        }
        double promedio = suma / (double) personajes.length;

        System.out.println("\n--- ESTADISTICAS DE APARICIONES ---");
        System.out.println("Total de apariciones: " + suma);
        System.out.printf("Promedio por personaje: %.2f%n", promedio);
        System.out.println("Mas apariciones: " + personajes[posMayor].getNombre()
                + " (" + personajes[posMayor].getEpisodios() + ")");
        System.out.println("Menos apariciones: " + personajes[posMenor].getNombre()
                + " (" + personajes[posMenor].getEpisodios() + ")");
        System.out.println("Vivos: " + vivos + " | Muertos: " + muertos + " | Desconocidos: " + desconocidos);
        // ===== PASO 10: busqueda lineal con Scanner =====
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEscribe parte del nombre a buscar: ");
        String busqueda = sc.nextLine().trim().toLowerCase();

        boolean encontrado = false;
        for (int i = 0; i < personajes.length; i++) {
            if (personajes[i].getNombre().toLowerCase().contains(busqueda)) {
                System.out.println("  [+] #" + (i + 1) + " " + personajes[i].resumen());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("  [x] Sin coincidencias en esta dimension.");
        }
        // ===== PASO 11: matriz = arreglo de arreglos (y herencia con instanceof) =====
        // 1) Contar y separar los Viajero que hay dentro del arreglo de Personaje
        int cantidad = 0;
        for (Personaje p : personajes) {
            if (p instanceof Viajero) {
                cantidad++;
            }
        }
        Viajero[] viajeros = new Viajero[cantidad];
        int k = 0;
        for (Personaje p : personajes) {
            if (p instanceof Viajero) {
                viajeros[k] = (Viajero) p;   // casting: de Personaje a Viajero
                k++;
            }
        }

        // 2) Cada energia (int[]) es una fila de la matriz
        int[][] matriz = new int[viajeros.length][];
        for (int i = 0; i < viajeros.length; i++) {
            matriz[i] = viajeros[i].getEnergia();
        }

        System.out.println("\n--- ENERGIA DE LA PISTOLA DE PORTALES (%) ---");
        System.out.printf("%-12s %-11s %5s %5s %5s %5s %8s%n", "Viajero", "Dimension", "V1", "V2", "V3", "V4", "Promedio");
        int mejor = 0;
        double mejorPromedio = 0;
        for (int fila = 0; fila < matriz.length; fila++) {
            int sumaFila = 0;
            System.out.printf("%-12s %-11s", viajeros[fila].getNombre(), viajeros[fila].getDimension());
            for (int col = 0; col < matriz[fila].length; col++) {
                System.out.printf(" %5d", matriz[fila][col]);
                sumaFila += matriz[fila][col];
            }
            double prom = sumaFila / (double) matriz[fila].length;
            System.out.printf(" %8.2f%n", prom);
            if (prom > mejorPromedio) {
                mejorPromedio = prom;
                mejor = fila;
            }
        }
        System.out.println("Mejor viajero: " + viajeros[mejor].getNombre()
                + " (dimension " + viajeros[mejor].getDimension() + ")");

        sc.close();
        System.out.println("\n=== Fin del escaneo. Wubba lubba dub dub! ===");
    }
}