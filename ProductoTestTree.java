import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductoTestTree {

    private BinarySearchTree<String, Producto> arbol;

    @BeforeEach
    public void setUp() {
        arbol = new BinarySearchTree<>();
    }

    @Test
    public void testInsertarProducto() {
        Producto p = new Producto("A100", "1000", "800", "Refrigeradora LG", "Refrigerators");
        arbol.insert(p.getSku(), p);

        assertEquals(1, arbol.count(), "El árbol debería tener un elemento insertado.");
    }

    @Test
    public void testBuscarProductoExistente() {
        Producto p = new Producto("B200", "1200", "950", "Estufa Whirlpool", "Ranges");
        arbol.insert(p.getSku(), p);

        Producto resultado = arbol.search("B200");

        assertNotNull(resultado, "El producto debería encontrarse.");
        assertEquals("Estufa Whirlpool", resultado.getProductName(), "El nombre del producto debería coincidir.");
    }

    @Test
    public void testBuscarProductoInexistente() {
        Producto resultado = arbol.search("NO_EXISTE");
        assertNull(resultado, "La búsqueda de un SKU inexistente debería retornar null.");
    }
}
