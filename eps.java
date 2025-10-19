import java.util.*;

class Paciente {
    String nombre;
    String id;
    String tipoAtencion;
    int nivelPrioridad; // 1=Crítico, 2=Urgente, 3=Normall

    public Paciente(String nombre, String id, String tipoAtencion, int nivelPrioridad) {
        this.nombre = nombre;
        this.id = id;
        this.tipoAtencion = tipoAtencion;
        this.nivelPrioridad = nivelPrioridad;
    }

    @Override
    public String toString() {
        String emoji = "";
        if (nivelPrioridad == 1) emoji = "🔴";
        else if (nivelPrioridad == 2) emoji = "🟡";
        else if (nivelPrioridad == 3) emoji = "🟢";
        
        return emoji + " " + nombre + " | ID: " + id + " | Atención: " + tipoAtencion;
    }

    public static int leerEntero(Scanner sc) {
        int opcion = -1;
        System.out.print("Seleccione una opción: ");

        if (sc.hasNextInt()) {
            opcion = sc.nextInt();
        } else {
            System.out.println("❌ Entrada inválida. Por favor, ingrese un número.");
            sc.nextLine();
        }
        return opcion;
    }
}

public class eps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cola normal para pacientes generales (FIFO)
        Queue<Paciente> Miguelillo = new LinkedList<>();
        
        // Pila para historial de atendidos (LIFO)
        Stack<Paciente> LuisPiloso = new Stack<>();
        
        // ArrayDeque para pacientes prioritarios (FIFO también)
        ArrayDeque<Paciente> DanielSuperRapido = new ArrayDeque<>();

        int opcion;

        do {
            System.out.println("\n══════════════════════════════════════════════════════════");
            System.out.println("           🏥 EPS HEYCLIC - SISTEMA DE ATENCIÓN      ");
            System.out.println("══════════════════════════════════════════════════════════");

            System.out.println("\n> PACIENTES GENERALES");
            System.out.println("  [1] Registrar nuevo paciente");
            System.out.println("  [2] Atender siguiente paciente");
            System.out.println("  [3] Ver siguiente paciente");
            System.out.println("  [4] Mostrar historial de pacientes atendidos");

            System.out.println("\n> PACIENTES ATENCIÓN RÁPIDA (UCI)");
            System.out.println("  [5] Registrar paciente UCI");
            System.out.println("  [6] Mostrar pacientes en UCI");
            System.out.println("  [7] Atender paciente UCI");
            
            System.out.println("\n> ESTADÍSTICAS");
            System.out.println("  [8] Ver resumen de pacientes");

            System.out.println("\n[0] Apagar sistema");
            System.out.println("══════════════════════════════════════════════════════════");

            opcion = Paciente.leerEntero(sc);
            sc.nextLine();

            switch (opcion) {
                case 1:
                    // Registrar paciente general
                    String nombre;
                    String id;
                    String tipo = "";
                    int nivel = 3; // Por defecto nivel normal

                    while (true) {
                        System.out.print("Nombre del paciente: ");
                        nombre = sc.nextLine();
                        if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                            break;
                        } else {
                            System.out.println("❌ Nombres solo corresponden a letras, inténtalo nuevamente.");
                        }
                    }

                    while (true) {
                        System.out.print("ID (solo números): ");
                        id = sc.nextLine();
                        if (id.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("❌ ID solo corresponde a números, inténtalo nuevamente.");
                        }
                    }

                    while (true) {
                        System.out.print("Tipo de atención (0. general / 1. urgencia): ");
                        String tipoOpcion = sc.nextLine();
                        if (tipoOpcion.equals("0")) {
                            tipo = "general";
                            nivel = 3; // 🟢 Normal
                            break;
                        } else if (tipoOpcion.equals("1")) {
                            tipo = "urgencia";
                            nivel = 2; // 🟡 Urgente
                            break;
                        } else {
                            System.out.println("❌ Opción inexistente, intenta nuevamente.");
                        }
                    }

                    Miguelillo.add(new Paciente(nombre, id, tipo, nivel));
                    System.out.println("✅ Paciente agregado a la cola de espera.");
                    break;

                case 2:
                    if (!Miguelillo.isEmpty()) {
                        Paciente atendido = Miguelillo.poll();
                        LuisPiloso.push(atendido);
                        System.out.println("🩺 Atendiendo a: " + atendido);
                    } else {
                        System.out.println("⚠️ No hay pacientes en espera.");
                    }
                    break;

                case 3:
                    if (!Miguelillo.isEmpty()) {
                        System.out.println("👀 Siguiente en cola: " + Miguelillo.peek());
                    } else {
                        System.out.println("⚠️ No hay pacientes en la cola.");
                    }
                    break;

                case 4:
                    if (!LuisPiloso.isEmpty()) {
                        System.out.println("📜 Historial de pacientes atendidos:");
                        System.out.println("(Último atendido primero)\n");
                        for (int i = LuisPiloso.size() - 1; i >= 0; i--) {
                            System.out.println(" " + (LuisPiloso.size() - i) + ". " + LuisPiloso.get(i));
                        }
                    } else {
                        System.out.println("⚠️ Aún no se ha atendido a nadie.");
                    }
                    break;

                case 5:
                    // Registrar paciente UCI (prioritario)
                    String n, i;
                    
                    while (true) {
                        System.out.print("Nombre del paciente prioritario: ");
                        n = sc.nextLine();
                        if (n.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) break;
                        System.out.println("❌ Nombres solo corresponden a letras, inténtalo nuevamente.");
                    }

                    while (true) {
                        System.out.print("ID (Solo números): ");
                        i = sc.nextLine();
                        if (i.matches("\\d+")) {
                            break;
                        }
                        System.out.println("❌ ID solo corresponde a números, inténtalo nuevamente.");
                    }

                    // CAMBIO: ahora usa addLast para mantener orden FIFO
                    DanielSuperRapido.addLast(new Paciente(n, i, "prioritario", 1));
                    System.out.println("🚨 Paciente agregado a atención rápida.");
                    break;

                case 6:
                    if (!DanielSuperRapido.isEmpty()) {
                        System.out.println("🚑 Pacientes en atención rápida:");
                        System.out.println("(En orden de llegada)\n");
                        int contador = 1;
                        for (Paciente p : DanielSuperRapido) {
                            System.out.println(" " + contador + ". " + p);
                            contador++;
                        }
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
                    }
                    break;

                case 7:
                    if (!DanielSuperRapido.isEmpty()) {
                        Paciente atendidoPrioritario = DanielSuperRapido.removeFirst();
                        LuisPiloso.push(atendidoPrioritario);
                        System.out.println("🩺 Atendiendo a (prioritario): " + atendidoPrioritario);
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
                    }
                    break;

                case 8:
                    // Estadísticas simples
                    System.out.println("\n📊 RESUMEN DE PACIENTES");
                    System.out.println("═══════════════════════════════════════");
                    System.out.println("👥 En espera general: " + Miguelillo.size());
                    System.out.println("🚨 En atención rápida: " + DanielSuperRapido.size());
                    System.out.println("✅ Total atendidos: " + LuisPiloso.size());
                    System.out.println("═══════════════════════════════════════");
                    
                    // Contar pacientes por nivel en cola general
                    int normales = 0;
                    int urgentes = 0;
                    for (Paciente p : Miguelillo) {
                        if (p.nivelPrioridad == 3) normales++;
                        else if (p.nivelPrioridad == 2) urgentes++;
                    }
                    
                    if (Miguelillo.size() > 0) {
                        System.out.println("\nEn cola general:");
                        System.out.println("🟢 Normales: " + normales);
                        System.out.println("🟡 Urgentes: " + urgentes);
                    }
                    
                    if (DanielSuperRapido.size() > 0) {
                        System.out.println("\nEn UCI:");
                        System.out.println("🔴 Críticos: " + DanielSuperRapido.size());
                    }
                    break;

                case 0:
                    System.out.println("👋 Saliendo del sistema...");
                    break;

                default:
                    System.out.println("❌ Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 0);

        sc.close();
    }
}

