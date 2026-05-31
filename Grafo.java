import java.util.ArrayList;
import java.util.List;

public abstract class Grafo implements GrafoInterface {

    protected List<String> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public void adicionarVertice(String vertice) {
        if (!existeVertice(vertice)) {
            vertices.add(vertice);
        }
    }

    public boolean existeVertice(String vertice) {
        return vertices.contains(vertice);
    }

    public int ordem() {
        return vertices.size();
    }

    // método auxiliar para as subclasses
    protected int obterIndiceVertice(String vertice) {
        return vertices.indexOf(vertice);
    }

    // Como cada estrutura gerencia as arestas de uma maneira, as subclasses que implementam:
    
    public abstract void removerVertice(String vertice);
    
    public abstract void adicionarAresta(String origem, String destino);
    
    public abstract void removerAresta(String origem, String destino);
    
    public abstract boolean existeAresta(String origem, String destino);
    
    public abstract int grau(String vertice);
    
    public abstract int tamanho();
    
    @Override
    public abstract String toString();


}