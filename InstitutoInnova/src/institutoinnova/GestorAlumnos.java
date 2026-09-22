package institutoinnova;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador que administra la lista de alumnos registrados.
 * Concentra la logica de negocio para no mezclarla con la
 * entrada de datos por consola (clase Main).
 */
public class GestorAlumnos {

    private final List<Alumno> listaAlumnos = new ArrayList<>();

    /**
     * Agrega un alumno a la lista. Rechaza el registro si ya existe
     * un alumno con el mismo tipo y numero de documento, para evitar
     * duplicados por homonimia.
     */
    public void agregarAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo");
        }
        if (buscarPorDocumento(alumno.getNumeroDocumento()).isPresent()) {
            throw new IllegalArgumentException(
                "Ya existe un alumno registrado con el documento " + alumno.getNumeroDocumento());
        }
        listaAlumnos.add(alumno);
    }

    /**
     * Muestra por consola todos los alumnos registrados.
     */
    public void listarAlumnos() {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados todavia.");
            return;
        }
        System.out.println("----- Lista de alumnos (" + listaAlumnos.size() + ") -----");
        int contador = 1;
        for (Alumno alumno : listaAlumnos) {
            System.out.println(contador + ". " + alumno);
            contador++;
        }
    }

    public Optional<Alumno> buscarPorDocumento(String numeroDocumento) {
        return listaAlumnos.stream()
            .filter(a -> a.getNumeroDocumento().equals(numeroDocumento))
            .findFirst();
    }

    public int totalAlumnos() {
        return listaAlumnos.size();
    }

    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }
}
