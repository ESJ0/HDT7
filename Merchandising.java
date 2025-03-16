import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que representa un producto de merchandising.
 */
public class Merchandising {
    String SKU, Nombre, Descripcion;
    Map<String, Integer> TallasDisponibles;

    /**
     * Constructor de la clase Merchandising.
     * @param SKU
     * @param Nombre
     * @param Descripcion
     * @param Tallas
     */
    public Merchandising(String SKU, String Nombre, String Descripcion, String Tallas) {
        this.SKU = SKU;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.TallasDisponibles = parseTallas(Tallas);
    }

    /**
     * Metodo para obtener el SKU del producto.
     * @return
     */
    public Map<String, Integer> parseTallas(String data) {
        Map<String, Integer> tallasMap = new HashMap<>();
        String[] parts = data.split("\\|");
        for (String part : parts) {
            String[] kv = part.split(":");
            tallasMap.put(kv[0], Integer.parseInt(kv[1]));
        }
        return tallasMap;
    }

    /**
     * Metodo para obtener el SKU del producto.
     * @return
     */
    @Override
    public String toString() {
        return "SKU: " + SKU + ", Nombre: " + Nombre + ", Descripcion: " + Descripcion + ", TallasDisponibles: " + TallasDisponibles;
    }

    /**
     * Metodo para convertir el producto a formato CSV.
     * @return
     */
    public String toCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(SKU).append(",").append(Nombre).append(",").append(Descripcion).append(",");
        List<String> tallas = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : TallasDisponibles.entrySet()) {
            tallas.add(entry.getKey() + ":" + entry.getValue());
        }
        sb.append(String.join("|", tallas));
        return sb.toString();
    }
}