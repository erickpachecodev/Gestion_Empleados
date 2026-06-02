package mi_proyecto;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Empresa {
    // Inicialización de la lista para almacenar los objetos Empleado
    private List<Empleado> listaEmpleados;

    // Constructor de la clase Empresa
    public Empresa() {
        this.listaEmpleados = new ArrayList<>();
    }

    /**
     * Método Agregar: Inserta un registro de tipo Empleado en la lista.
    
     */
    public void agregarEmpleado(Empleado emp) {
        if (emp != null) {
            listaEmpleados.add(emp);
            System.out.println("Empleado agregado exitosamente: " + emp.getNombre());
        } else {
            System.out.println("No se puede agregar un empleado nulo.");
        }
    }

    /**
     * Método Eliminar: Busca un empleado por su DUI.
    */
    public void eliminarEmpleado(String dui) throws EmpleadoNoEncontradoException {
        // Usamos un Iterator para evitar errores de concurrencia al eliminar elementos durante la iteración
        Iterator<Empleado> iterator = listaEmpleados.iterator();
        boolean encontrado = false;

        while (iterator.hasNext()) {
            Empleado emp = iterator.next();
            // Comparamos el DUI ignorando mayúsculas/minúsculas o espacios por seguridad
            if (emp.getDui().trim().equalsIgnoreCase(dui.trim())) {
                iterator.remove(); // Elimina el elemento de la lista de forma segura
                encontrado = true;
                System.out.println("Empleado con DUI " + dui + " eliminado correctamente.");
                break; // Terminamos el bucle ya que encontramos al empleado
            }
        }

        // Si terminó la búsqueda y no existe, se lanza la excepción
        if (!encontrado) {
            throw new EmpleadoNoEncontradoException("Error: No se encontró ningún empleado con el DUI: " + dui);
        }
    }

    // Método opcional para obtener la lista (útil para pruebas)
    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
}