// Gabriel Carraro Salzedas - 16827905
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GrafoListaAdjacencia grafoLista = new GrafoListaAdjacencia();
        GrafoMatrizAdjacencia grafoMatriz = new GrafoMatrizAdjacencia();
        GrafoPonderadoMatrizAdjacencia grafoPonderado = new GrafoPonderadoMatrizAdjacencia();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) {
                continue;
            }

            String[] partes = linha.split("\\s+");
            String comando = partes[0];

            switch (comando) {
                case "i": {
                    String v1 = partes[1];
                    String v2 = partes[2];
                    int peso = Integer.parseInt(partes[3]);

                    grafoLista.adicionarVertice(v1);
                    grafoLista.adicionarVertice(v2);
                    grafoLista.adicionarAresta(v1, v2);
                    grafoMatriz.adicionarVertice(v1);
                    grafoMatriz.adicionarVertice(v2);
                    grafoMatriz.adicionarAresta(v1, v2);
                    grafoPonderado.adicionarVertice(v1);
                    grafoPonderado.adicionarVertice(v2);
                    grafoPonderado.adicionarAresta(v1, v2, peso);
                    break;
                }
                case "d": {
                    if(partes.length == 2) {
                        String v1 = partes[1];
                        grafoLista.removerVertice(v1);
                        grafoMatriz.removerVertice(v1);
                        grafoPonderado.removerVertice(v1);
                    } else if(partes.length == 3) {
                        String v1 = partes[1];
                        String v2 = partes[2];
                        grafoLista.removerAresta(v1, v2);
                        grafoMatriz.removerAresta(v1, v2);
                        grafoPonderado.removerAresta(v1, v2);
                    }
                    break;
                }
                case "p": {
                    System.out.println("Lista de Adjacencia");
                    System.out.println(grafoLista);
                    System.out.println("Matriz de Adjacencia");
                    System.out.println(grafoMatriz);
                    System.out.println("Ponderado - Matriz de Adjacencia");
                    System.out.println(grafoPonderado);
                    break;
                }
            }
        }

        scanner.close();
    }
}