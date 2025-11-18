import java.util.Scanner;

class TexTok {
    public static void main(String[] args){
        Usuarios usuarios = new Usuarios();
        TextosMasVistos textosMasVistos = new TextosMasVistos();
        
        usuarios.cargarUsuarios();
        textosMasVistos.cargarTextos();
        
        menu(usuarios,textosMasVistos);
    }
    
    private static void menu(Usuarios usuarios,TextosMasVistos textosMasVistos) {
        int op = 0;
        
    
        while(op != 6) {
    
            System.out.println("=== MENU ===");
            System.out.println("1) Identificarse");
            System.out.println("2) Crear un usuario");
            System.out.println("3) Dar de baja a un usuario");
            System.out.println("4) Devolver los datos del usuario que más textos creó");
            System.out.println("5) Devolver los datos del usuario que creó los textos más visto");
            System.out.println("6) Salir");
            System.out.print("Opción: ");
    
            op = leerEntero();
    
            if (op == 1) {
                // Identificarse
            } 
            else if (op == 2) {
                // Crear un usuario
            } 
            else if (op == 3) {
                // Dar de baja a un usuario
            } 
            else if (op == 4) {
                // Usuario que más textos creó
            } 
            else if (op == 5) {
                // Usuario que creó los textos más vistos
            } 
            else if (op == 6) {
                System.out.println("Saliendo...");
            } 
            else {
                System.out.println("Opción incorrecta");
            }
    
            limpiar(); // salto de línea
        }
    }
    private static void limpiar() {
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
    }
    
    private static int leerEntero() {
        Scanner sc = new Scanner(System.in);
        int valor = sc.nextInt();
        return valor;
    }

    private static String leerTexto() {
        Scanner sc = new Scanner(System.in);
        String cadena = sc.nextLine();
        return cadena;
    }

}