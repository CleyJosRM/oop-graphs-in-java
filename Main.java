// Gabriel Carraro Salzedas - 16827905
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GrafoListaAdjacencia gL = new GrafoListaAdjacencia();
        GrafoMatrizAdjacencia gM = new GrafoMatrizAdjacencia();
        GrafoPonderadoMatrizAdjacencia gP = new GrafoPonderadoMatrizAdjacencia();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String[] cmd = sc.nextLine().split(" ");
            if (cmd[0].equals("i")) {
                String v1 = cmd[1], v2 = cmd[2];
                int peso = Integer.parseInt(cmd[3]);
                for (Grafo g : new Grafo[]{gL, gM, gP}) g.adicionarVertice(v1);
                for (Grafo g : new Grafo[]{gL, gM, gP}) g.adicionarVertice(v2);
                gL.adicionarAresta(v1, v2);
                gM.adicionarAresta(v1, v2);
                gP.adicionarAresta(v1, v2, peso);
            } else if (cmd[0].equals("d")) {
                if (cmd.length == 3) {
                    gL.removerAresta(cmd[1], cmd[2]);
                    gM.removerAresta(cmd[1], cmd[2]);
                    gP.removerAresta(cmd[1], cmd[2]);
                } else {
                    gL.removerVertice(cmd[1]);
                    gM.removerVertice(cmd[1]);
                    gP.removerVertice(cmd[1]);
                }
            } else if (cmd[0].equals("p")) {
                System.out.println("Lista de Adjacencia\n" + gL.toString());
                System.out.println("Matriz de Adjacencia\n" + gM.toString());
                System.out.println("Ponderado - Matriz de Adjacencia\n" + gP.toString());
            }
        }
        sc.close();
    }
}