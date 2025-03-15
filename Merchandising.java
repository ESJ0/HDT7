import java.util.HashMap;
import java.util.Map;

public class Merchandising {
    String SKU, Nombre, Descripcion;
    Map<String, Integer> TallasDisponibles;

    public Merchandising(String SKU, String Nombre, String Descripcion, String Tallas) {
        this.SKU = SKU;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.TallasDisponibles = parseTallas(Tallas);
    }

    public Map<String, Integer> parseTallas(String data) {
        Map<String, Integer> tallasMap = new HashMap<>();
        String[] parts = data.split("\\|");
        for (String part : parts) {
            String[] kv = part.split(":");
            tallasMap.put(kv[0], Integer.parseInt(kv[1]));
        }
        return tallasMap;
    }

    @Override
    public String toString() {
        return "SKU: " + SKU + ", Nombre: " + Nombre + ", Descripcion: " + Descripcion + ", TallasDisponibles: " + TallasDisponibles;
    }
}