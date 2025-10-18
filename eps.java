import java.util.*;

class Paciente {
    String nombre;
    String id;
    String tipoAtencion;

    public Paciente(String nombre, String id, String tipoAtencion) {
        this.nombre = nombre;
        this.id = id;
        this.tipoAtencion = tipoAtencion;
    }

    @Override
    public String toString() {
        return nombre + " (ID: " + id + ", Tipo: " + tipoAtencion + ")";
    }
}

public class MenuEPS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🟦 Cola de espera normal
        Queue<Paciente> colaEspera = new LinkedList<>();

        // 🟨 Pila para historial de pacientes atendidos
        Stack<Paciente> historial = new Stack<>();

        // 🟥 ArrayDeque para atención rápida o prioritaria
        ArrayDeque<Paciente> atencionRapida = new ArrayDeque<>();

        int opcion;
        do {
            System.out.println("\n===== MENÚ EPS =====");
            System.out.println("1. Registrar nuevo paciente (Cola)");
            System.out.println("2. Atender siguiente paciente (Cola ➜ Pila)");
            System.out.println("3. Ver siguiente paciente (Cola)");
            System.out.println("4. Mostrar historial de pacientes atendidos (Pila)");
            System.out.println("5. Registrar paciente de atención rápida (ArrayDeque)");
            System.out.println("6. Mostrar pacientes en atención rápida (ArrayDeque)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    // 🟦 Agregar paciente a la cola
                    System.out.print("Nombre del paciente: ");
                    String nombre = sc.nextLine();
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Tipo de atención (general / urgencia): ");
                    String tipo = sc.nextLine();

                    colaEspera.add(new Paciente(nombre, id, tipo));
                    System.out.println("✅ Paciente agregado a la cola de espera.");
                    break;

                case 2:
                    // 🟦 Sacar de la cola y 🟨 guardar en historial
                    if (!colaEspera.isEmpty()) {
                        Paciente atendido = colaEspera.poll();
                        historial.push(atendido);
                        System.out.println("🩺 Atendiendo a: " + atendido);
                    } else {
                        System.out.println("⚠️ No hay pacientes en espera.");
                    }
                    break;

                case 3:
                    // 🟦 Ver el próximo paciente
                    if (!colaEspera.isEmpty()) {
                        System.out.println("👀 Siguiente en cola: " + colaEspera.peek());
                    } else {
                        System.out.println("⚠️ No hay pacientes en la cola.");
                    }
                    break;

                case 4:
                    // 🟨 Mostrar pila (historial)
                    if (!historial.isEmpty()) {
                        System.out.println("📜 Historial de pacientes atendidos:");
                        for (Paciente p : historial) {
                            System.out.println(" - " + p);
                        }
                    } else {
                        System.out.println("⚠️ Aún no se ha atendido a nadie.");
                    }
                    break;

                case 5:
                    // 🟥 Registrar paciente en atención rápida
                    System.out.print("Nombre del paciente prioritario: ");
                    String n = sc.nextLine();
                    System.out.print("ID: ");
                    String i = sc.nextLine();
                    System.out.print("Motivo (prioritario): ");
                    String m = sc.nextLine();

                    atencionRapida.addFirst(new Paciente(n, i, m));
                    System.out.println("🚨 Paciente agregado a atención rápida.");
                    break;

                case 6:
                    // 🟥 Mostrar pacientes en atención rápida
                    if (!atencionRapida.isEmpty()) {
                        System.out.println("🚑 Pacientes en atención rápida:");
                        for (Paciente p : atencionRapida) {
                            System.out.println(" - " + p);
                        }
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
                    }
                    break;

                case 7:
                    System.out.println("👋 Saliendo del sistema...");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 7);

                                        HOLA HOLA HOLA HOLA HOLA

        sc.close();
    }
}

