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

            //arbolProductos.InOrder(new ElementsToConsole<>());

            int opcion = 0;
            Scanner scanner = new Scanner(System.in);


            while (opcion != 5) { 
                System.out.println("1. Buscar producto por SKU");
                System.out.println("2. Mostrar productos ordenados por precio ascendentemente");
                System.out.println("3. Mostrar productos ordenados por precio descendentemente");
                System.out.println("4. Insertar nuevo producto");

                try {
                    opcion = Integer.parseInt(scanner.nextLine());
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
