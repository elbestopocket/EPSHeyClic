package actividadArrayList;
import java.util.*;

class Paciente {
    String nombre;
    String id;
    String motivoIngreso;
    int nivelPrioridad; 

    public Paciente(String nombre, String id, String motivoIngreso, int nivelPrioridad) {
        this.nombre = nombre;
        this.id = id;
        this.motivoIngreso = motivoIngreso;
        this.nivelPrioridad = nivelPrioridad;
    }

    @Override
    public String toString() {
        String emoji = "";
        if (nivelPrioridad == 1) emoji = "🔴";
        else if (nivelPrioridad == 2) emoji = "🟡";
        else if (nivelPrioridad == 3) emoji = "🟢";
        
        return emoji + " " + nombre + " | ID: " + id + " | Atención: " + motivoIngreso;
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

public class MenueEps {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        Queue<Paciente> Miguelillo = new LinkedList<>();
        
        
        Stack<Paciente> LuisPiloso = new Stack<>();
        
        
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
               
                    String nombre;
                    String id;
                    String motivo = "";
                    int nivel = 3; 

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
                        System.out.print("Motivo de ingreso: ");
                        motivo = sc.nextLine().trim();
                        if (!motivo.isEmpty()) break;
                        System.out.println("❌ El motivo no puede estar vacío.");
                    }
                    Miguelillo.add(new Paciente(nombre, id, motivo, nivel));
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
                    break;1
1
                case 7:
                    if (!DanielSuperRapido.isEmpty()) {477
                        Paciente atendidoPrioritario = DanielSuperRapido.removeFirst();
                        LuisPiloso.push(atendidoPrioritario);
                        System.out.println("🩺 Atendiendo a (prioritario): " + atendidoPrioritario);
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
                    }
                    break;

                case 8:
   
                    System.out.println("\n📊 RESUMEN DE PACI0ENTES");
                    System.out.println("═══════════════════════════════════════");
                    System.out.println("👥 En espera general: " + Miguelillo.size());
                    System.out.println("🚨 En atención rápida: " + DanielSuperRapido.size());
                    System.out.println("✅ Total atendidos: " + LuisPiloso.size());
                    System.out.println("═══════════════════════════════════════");
                    
                
                    int normales = 0;
                    
                    for (Paciente p : Miguelillo) {
                        if (p.nivelPrioridad == 3) normales++;
                        
                    }
                    
                    if (Miguelillo.size() > 0) {
                        System.out.println("\nEn cola general:");
                        System.out.println("🟢 Normales: " + normales);
                    
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
70
