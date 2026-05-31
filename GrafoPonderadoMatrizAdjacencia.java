import java.util.ArrayList;
import java.util.List;

public class GrafoPonderadoMatrizAdjacencia extends Grafo {
    private List<List<Integer>> matriz;

    public GrafoPonderadoMatrizAdjacencia() {
        super();
        this.matriz = new ArrayList<>();
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        int i = obterIndiceVertice(origem);
        int j = obterIndiceVertice(destino);
        if (i != -1 && j != -1) {
            matriz.get(i).set(j, peso);
            matriz.get(j).set(i, peso);
        }
    }

    @Override
    public void adicionarVertice(String vertice) {
        if (!existeVertice(vertice)) {
            super.adicionarVertice(vertice);
            for (List<Integer> linha : matriz) linha.add(0);
            List<Integer> novaLinha = new ArrayList<>();
            for (int i = 0; i < vertices.size(); i++) novaLinha.add(0);
            matriz.add(novaLinha);
        }
    }

    @Override
    public void removerVertice(String vertice) {
        int index = obterIndiceVertice(vertice);
        if (index != -1) {
            vertices.remove(index);
            matriz.remove(index);
            for (List<Integer> linha : matriz) linha.remove(index);
        }
    }

    @Override
    public void adicionarAresta(String origem, String destino) { /* Não usado conforme requisitos */ }

    @Override
    public void removerAresta(String origem, String destino) {
        int i = obterIndiceVertice(origem);
        int j = obterIndiceVertice(destino);
        if (i != -1 && j != -1) {
            matriz.get(i).set(j, 0);
            matriz.get(j).set(i, 0);
        }
    }

    @Override
    public boolean existeAresta(String origem, String destino) {
        int i = obterIndiceVertice(origem);
        int j = obterIndiceVertice(destino);
        return (i != -1 && j != -1 && matriz.get(i).get(j) != 0);
    }

    @Override
    public int grau(String vertice) {
        int index = obterIndiceVertice(vertice);
        if (index == -1) return 0;
        int count = 0;
        for (int peso : matriz.get(index)) if (peso != 0) count++;
        return count;
    }

    @Override
    public int tamanho() {
        int count = 0;
        for (int i = 0; i < matriz.size(); i++) 
            for (int j = i + 1; j < matriz.size(); j++) 
                if (matriz.get(i).get(j) != 0) count++;
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("graph {\n");
        List<String> linhas = new ArrayList<>();
        List<String> isolados = new ArrayList<>();

        for (int i = 0; i < matriz.size(); i++) {
            boolean temAresta = false;
            for (int j = 0; j < matriz.size(); j++) {
                if (matriz.get(i).get(j) != 0) {
                    temAresta = true;
                    if (i < j) {
                        String v1 = vertices.get(i);
                        String v2 = vertices.get(j);
                        
                        // Força a ordem alfabética
                        if (v1.compareTo(v2) > 0) {
                            String temp = v1; v1 = v2; v2 = temp;
                        }
                        
                        linhas.add("    \"" + v1 + "\" -- \"" + v2 + "\" [label=\"" + matriz.get(i).get(j) + "\"];\n");
                    }
                }
            }
            if (!temAresta) {
                isolados.add("    \"" + vertices.get(i) + "\";\n");
            }
        }
        
        isolados.sort(String::compareTo);
        for (String isolado : isolados) sb.append(isolado);
        
        linhas.sort(String::compareTo);
        for (String l : linhas) sb.append(l);
        
        return sb.append("}").toString();
    }
}