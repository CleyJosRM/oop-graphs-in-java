import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoListaAdjacencia extends Grafo {
    // Lista de listas para armazenar os vizinhos de cada vértice
    private List<List<Integer>> adjacencias;

    public GrafoListaAdjacencia() {
        super();
        this.adjacencias = new ArrayList<>();
    }

    @Override
    public void adicionarVertice(String vertice) {
        if (!existeVertice(vertice)) {
            super.adicionarVertice(vertice);
            adjacencias.add(new ArrayList<>()); // Cria a lista de adjacências para o novo vértice
        }
    }

    @Override
    public void removerVertice(String vertice) {
        int index = obterIndiceVertice(vertice);
        if (index != -1) {
            // Remove o vértice da lista base
            vertices.remove(index);
            
            // Remove todas as referências a esse vértice nas listas dos outros
            for (List<Integer> vizinhos : adjacencias) {
                vizinhos.remove(Integer.valueOf(index));
                
                // Ajusta os índices que ficaram maiores após a remoção
                for (int i = 0; i < vizinhos.size(); i++) {
                    if (vizinhos.get(i) > index) {
                        vizinhos.set(i, vizinhos.get(i) - 1);
                    }
                }
            }
            
            // Remove a lista de adjacências deste vértice
            adjacencias.remove(index);
        }
    }

    @Override
    public void adicionarAresta(String origem, String destino) {
        int u = obterIndiceVertice(origem);
        int v = obterIndiceVertice(destino);
        
        // Registra nos dois sentidos
        if (u != -1 && v != -1 && !adjacencias.get(u).contains(v)) {
            adjacencias.get(u).add(v);
            adjacencias.get(v).add(u);
        }
    }

    @Override
    public void removerAresta(String origem, String destino) {
        int u = obterIndiceVertice(origem);
        int v = obterIndiceVertice(destino);
        
        if (u != -1 && v != -1) {
            adjacencias.get(u).remove(Integer.valueOf(v));
            adjacencias.get(v).remove(Integer.valueOf(u));
        }
    }

    @Override
    public boolean existeAresta(String origem, String destino) {
        int u = obterIndiceVertice(origem);
        int v = obterIndiceVertice(destino);
        return (u != -1 && v != -1 && adjacencias.get(u).contains(v));
    }

    @Override
    public int grau(String vertice) {
        int index = obterIndiceVertice(vertice);
        return (index != -1) ? adjacencias.get(index).size() : 0;
    }

    @Override
    public int tamanho() {
        int total = 0;
        for (List<Integer> vizinhos : adjacencias) {
            total += vizinhos.size();
        }
        return total / 2; // Como é não direcionado, cada aresta conta em dois lugares
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("graph {\n");
        List<String> arestas = new ArrayList<>();
        List<String> isolados = new ArrayList<>();
        
        for (int i = 0; i < vertices.size(); i++) {
            if (adjacencias.get(i).isEmpty()) {
                isolados.add("    \"" + vertices.get(i) + "\";\n");
            } else {
                for (int vizinho : adjacencias.get(i)) {
                    if (i < vizinho) { // Evita duplicatas (A-B e B-A)
                        String v1 = vertices.get(i);
                        String v2 = vertices.get(vizinho);
                        
                        // Força a ordem alfabética entre os vértices da aresta
                        if (v1.compareTo(v2) > 0) {
                            String temp = v1; v1 = v2; v2 = temp;
                        }
                        
                        arestas.add("    \"" + v1 + "\" -- \"" + v2 + "\";\n");
                    }
                }
            }
        }
        
        Collections.sort(isolados);
        for (String isolado : isolados) sb.append(isolado);
        
        Collections.sort(arestas);
        for (String aresta : arestas) sb.append(aresta);
        
        return sb.append("}").toString();
    }
}