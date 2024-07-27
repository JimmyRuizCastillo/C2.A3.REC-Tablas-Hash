import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static HashTable hashTable;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        hashTable = new HashTable(200000); // Tabla con suficiente espacio
        boolean shouldExit = false;

        while (!shouldExit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    loadDataWithMultiplicationHash(true);
                    break;
                case 2:
                    loadDataWithDivisionHash(true);
                    break;
                case 3:
                    searchWithMultiplicationHash(true);
                    break;
                case 4:
                    searchWithDivisionHash(true);
                    break;
                case 5:
                    loadDataWithMultiplicationHash(false);
                    break;
                case 6:
                    loadDataWithDivisionHash(false);
                    break;
                case 7:
                    searchWithMultiplicationHash(false);
                    break;
                case 8:
                    searchWithDivisionHash(false);
                    break;
                case 9:
                    shouldExit = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Cargar datos usando hash de multiplicación con listas de adyacencia");
        System.out.println("2. Cargar datos usando hash de división con listas de adyacencia");
        System.out.println("3. Buscar por ID usando hash de multiplicación con listas de adyacencia");
        System.out.println("4. Buscar por ID usando hash de división con listas de adyacencia");
        System.out.println("5. Cargar datos usando hash de multiplicación con listas de Java");
        System.out.println("6. Cargar datos usando hash de división con listas de Java");
        System.out.println("7. Buscar por ID usando hash de multiplicación con listas de Java");
        System.out.println("8. Buscar por ID usando hash de división con listas de Java");
        System.out.println("9. Salir");
    }

    private static void loadDataWithMultiplicationHash(boolean useLinkedList) {
        loadData(true, useLinkedList);
    }

    private static void loadDataWithDivisionHash(boolean useLinkedList) {
        loadData(false, useLinkedList);
    }

    private static void searchWithMultiplicationHash(boolean useLinkedList) {
        searchData(true, useLinkedList);
    }

    private static void searchWithDivisionHash(boolean useLinkedList) {
        searchData(false, useLinkedList);
    }

    private static void loadData(boolean useMultiplication, boolean useLinkedList) {
        String line = "";
        String splitBy = ",";
        long startTime, endTime;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/resources/bussines.csv"))) {
            startTime = System.nanoTime();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);
                String id = data[0];
                String name = data[1];
                String address = data[2];
                String city = data[3];
                String state = data[4];

                Business business = new Business(id, name, address, city, state);
                hashTable.insert(business, useMultiplication, useLinkedList);
            }
            endTime = System.nanoTime();
            System.out.println("Datos cargados en " + (endTime - startTime) / 1000000.0 + " ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchData(boolean useMultiplication, boolean useLinkedList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ID a buscar:");
        String searchId = scanner.nextLine();

        long startTime = System.nanoTime();
        Business foundBusiness = hashTable.search(searchId, useMultiplication, useLinkedList);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (endTime - startTime) / 1000000.0 + " ms");

        if (foundBusiness != null) {
            System.out.println("Objeto de datos encontrado: \n" + foundBusiness);
        } else {
            System.out.println("No se han encontrado datos.");
        }
    }
}