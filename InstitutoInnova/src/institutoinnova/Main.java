package institutoinnova;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Punto de entrada del sistema. Muestra un menu por consola que
 * permite registrar y listar alumnos. Todo el ingreso de datos
 * se controla con try/catch para que ningun dato invalido
 * detenga el programa de forma inesperada.
 */
public class Main {

    private final Scanner sc = new Scanner(System.in);
    private final GestorAlumnos gestor = new GestorAlumnos();

    public static void main(String[] args) {
        new Main().iniciar();
    }

    public void iniciar() {
        boolean salir = false;
        System.out.println("=== Sistema de Matricula - Instituto Innova ===");
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    registrarAlumno();
                    break;
                case 2:
                    gestor.listarAlumnos();
                    break;
                case 3:
                    salir = true;
                    System.out.println("Saliendo del sistema. Hasta pronto, Regina.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
        sc.close();
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("1. Registrar alumno");
        System.out.println("2. Listar alumnos");
        System.out.println("3. Salir");
        System.out.print("Elija una opcion: ");
    }

    /**
     * Lee la opcion del menu de forma segura: si el usuario escribe
     * texto en vez de un numero, no se cae el programa.
     */
    private int leerOpcion() {
        try {
            int opcion = sc.nextInt();
            sc.nextLine();
            return opcion;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }

    private void registrarAlumno() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellido: ");
            String apellido = sc.nextLine();

            TipoDocumento tipoDocumento = leerTipoDocumento();
            String numeroDocumento = leerNumeroDocumento(tipoDocumento);
            NivelSocioeconomico nivel = leerNivelSocioeconomico();
            TipoBeca beca = leerTipoBeca();

            Alumno alumno = new Alumno(nombre, apellido, tipoDocumento, numeroDocumento, nivel, beca);
            gestor.agregarAlumno(alumno);
            System.out.println("Alumno registrado correctamente:");
            System.out.println(alumno);

        } catch (DocumentoInvalidoException e) {
            System.out.println("No se pudo registrar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrio un error inesperado al registrar al alumno: " + e.getMessage());
        }
    }

    private TipoDocumento leerTipoDocumento() {
        while (true) {
            System.out.print("Tipo de documento (1=DNI, 2=Carne de Residencia Temporal): ");
            String entrada = sc.nextLine().trim();
            if (entrada.equals("1")) return TipoDocumento.DNI;
            if (entrada.equals("2")) return TipoDocumento.CARNE_RESIDENCIA;
            System.out.println("Opcion no valida, ingrese 1 o 2.");
        }
    }

    private String leerNumeroDocumento(TipoDocumento tipoDocumento) {
        System.out.print("Numero de documento (" + tipoDocumento.getLongitudRequerida() + " digitos): ");
        return sc.nextLine().trim();
    }

    private NivelSocioeconomico leerNivelSocioeconomico() {
        while (true) {
            System.out.print("Nivel socioeconomico (A, B, C): ");
            String entrada = sc.nextLine().trim().toUpperCase();
            try {
                return NivelSocioeconomico.valueOf(entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Opcion no valida, ingrese A, B o C.");
            }
        }
    }

    private TipoBeca leerTipoBeca() {
        while (true) {
            System.out.print("Beca (1=Ninguna, 2=Parcial 50%, 3=Total 100%): ");
            String entrada = sc.nextLine().trim();
            switch (entrada) {
                case "1": return TipoBeca.NINGUNA;
                case "2": return TipoBeca.PARCIAL;
                case "3": return TipoBeca.TOTAL;
                default: System.out.println("Opcion no valida, ingrese 1, 2 o 3.");
            }
        }
    }
}
