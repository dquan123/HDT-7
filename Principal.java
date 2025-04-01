import java.io.*;
import java.util.*;

public class Principal {
    public static void main(String[] args) {

        String ruta = "C:\\Users\\dquan\\OneDrive\\Documentos\\Diego Quan\\UVG\\Ciclo 3\\Algoritmos y Estructura de datos\\Hoja de trabajo 7\\HDT-7\\home appliance skus lowes.csv";

        BinarySearchTree<String, Producto> arbolProductos = new BinarySearchTree<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String encabezado = br.readLine();
            if (encabezado == null) {
                System.out.println("Archivo vacío.");
                return;
            }

            String[] columnas = encabezado.split(",");
            Map<String, Integer> indices = new HashMap<>();

            for (int i = 0; i < columnas.length; i++) {
                switch (columnas[i].trim().toLowerCase()) {
                    case "sku": indices.put("SKU", i); break;
                    case "price_retail": indices.put("Price_Retail", i); break;
                    case "price_current": indices.put("Price_Current", i); break;
                    case "product_name": indices.put("Product_Name", i); break;
                    case "category": indices.put("Category", i); break;
                }
            }

            if (indices.size() < 5) {
                System.out.println("Faltan campos necesarios en el archivo.");
                return;
            }

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",", -1);

                if (datos.length <= Collections.max(indices.values())) continue; // previene IndexOutOfBounds

                String sku = datos[indices.get("SKU")];
                String retail = datos[indices.get("Price_Retail")];
                String current = datos[indices.get("Price_Current")];
                String name = datos[indices.get("Product_Name")];
                String category = datos[indices.get("Category")];

                Producto p = new Producto(sku, retail, current, name, category);
                arbolProductos.insert(sku, p);
            }

            int opcion = 0;
            Scanner scanner = new Scanner(System.in);


            while (opcion != 5) { 
                System.out.println("");
                System.out.println("1. Buscar producto por SKU");
                System.out.println("2. Mostrar productos ordenados por SKU ascendentemente");
                System.out.println("3. Mostrar productos ordenados por SKU descendentemente");
                System.out.println("4. Insertar nuevo producto");
                System.out.println("5. Salir");

                try {
                    opcion = Integer.parseInt(scanner.nextLine());

                    switch (opcion){
                        case 1:
                            System.out.print("Ingresa el SKU: ");
                            break;
                        case 2:
                            arbolProductos.InOrder(new ElementsToConsole<>());
                            break;
                        case 3:
                            arbolProductos.InOrderReverso(new ElementsToConsole<>());
                            break;
                        case 4:
                            System.out.println("\n-- Creación de un nuevo producto --");
                        
                            try {
                                System.out.print("SKU: ");
                                String sku = scanner.nextLine();
                        
                                System.out.print("Precio original (Price_Retail): ");
                                String retail = scanner.nextLine();
                        
                                System.out.print("Precio actual (Price_Current): ");
                                String current = scanner.nextLine();
                        
                                System.out.print("Nombre del producto: ");
                                String name = scanner.nextLine();
                        
                                System.out.print("Categoría: ");
                                String category = scanner.nextLine();
                        
                                // Validación de campos vacíos
                                if (sku.isEmpty() || retail.isEmpty() || current.isEmpty() || name.isEmpty() || category.isEmpty()) {
                                    System.out.println("Todos los campos son obligatorios.");
                                    break;
                                }
                        
                                // Verificar si el SKU ya existe
                                if (arbolProductos.search(sku) != null) {
                                    System.out.println("El SKU ya existe. Intenta con uno diferente.");
                                    break;
                                }
                        
                                // Crear e insertar nuevo producto
                                Producto nuevo = new Producto(sku, retail, current, name, category);
                                arbolProductos.insert(sku, nuevo);
                                System.out.println("Producto agregado correctamente.");
                        
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        
                        case 5:
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Ingresa un número válido.");
                    continue; // Vuelve al inicio del while
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
