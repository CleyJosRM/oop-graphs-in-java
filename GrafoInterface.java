public interface GrafoInterface {
    public void adicionarVertice(String vertice);
    public void removerVertice(String vertice);
    public void adicionarAresta(String origem, String destino);
    public void removerAresta(String origem, String destino);
    public boolean existeVertice(String vertice);
    public boolean existeAresta(String origem, String destino);
    public int grau(String vertice);
    public int ordem();
    public int tamanho();
    public String toString();
}