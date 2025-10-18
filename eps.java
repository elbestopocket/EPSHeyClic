package actividadArrayList;
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
        return nombre + " | ID: " + id + " | Atención: " + tipoAtencion;

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

     
        Queue<Paciente> colaEspera = new LinkedList<>();

  
        Stack<Paciente> historial = new Stack<>();

 
        ArrayDeque<Paciente> DanielSuperRapido = new ArrayDeque<>();

        int opcion;

        do {
            System.out.println("══════════════════════════════════════════════════════════");
            System.out.println("                 🏥 EPS HEYCLIC - SISTEMA DE ATENCIÓN      ");
            System.out.println("══════════════════════════════════════════════════════════");

            System.out.println("\n> PACIENTES GENERALES");
            System.out.println("  [1] Registrar nuevo paciente");
            System.out.println("  [2] Atender siguiente paciente");
            System.out.println("  [3] Ver siguiente paciente");
            System.out.println("  [4] Mostrar historial");

            System.out.println("\n> PACIENTES ATENCIÓN RÁPIDA (UZI)");
            System.out.println("  [5] Registrar paciente UZI");
            System.out.println("  [6] Mostrar pacientes en UZI");
            System.out.println("  [7] Atender paciente UZI");

            System.out.println("\n[0] Apagar sistema");
            System.out.println("══════════════════════════════════════════════════════════");

            opcion = Paciente.leerEntero(sc);
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                   
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
                    
                    if (!colaEspera.isEmpty()) {
                        Paciente atendido = colaEspera.poll();
                        historial.push(atendido);
                        System.out.println("🩺 Atendiendo a: " + atendido);
                    } else {
                        System.out.println("⚠️ No hay pacientes en espera.");
                    }
                    break;

                case 3:
   
                    if (!colaEspera.isEmpty()) {
                        System.out.println("👀 Siguiente en cola: " + colaEspera.peek());
                    } else {
                        System.out.println("⚠️ No hay pacientes en la cola.");
                    }
                    break;

                case 4:
                 
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
          
                    System.out.print("Nombre del paciente prioritario: ");
                    String n = sc.nextLine();
                    System.out.print("ID: ");
                    String i = sc.nextLine();
                    System.out.print("Motivo (prioritario): ");
                    String m = sc.nextLine();

                    DanielSuperRapido.addFirst(new Paciente(n, i, m));
                    System.out.println("🚨 Paciente agregado a atención rápida.");
                    break;

                case 6:
                
                    if (!DanielSuperRapido.isEmpty()) {
                        System.out.println("🚑 Pacientes en atención rápida:");
                        for (Paciente p : DanielSuperRapido) {
                            System.out.println(" - " + p);
                        }
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
                    }
                    break;

                case 7:
               
                    if (!DanielSuperRapido.isEmpty()) {
                        Paciente atendidoPrioritario = DanielSuperRapido.removeFirst();
                        historial.push(atendidoPrioritario);
                        System.out.println("🩺 Atendiendo a (prioritario): " + atendidoPrioritario);
                    } else {
                        System.out.println("⚠️ No hay pacientes en atención rápida.");
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
