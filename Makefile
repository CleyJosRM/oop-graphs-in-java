all: Main.class

Main.class: Main.java Grafo.java GrafoInterface.java GrafoListaAdjacencia.java GrafoMatrizAdjacencia.java GrafoPonderadoMatrizAdjacencia.java
	javac Main.java Grafo.java GrafoInterface.java GrafoListaAdjacencia.java GrafoMatrizAdjacencia.java GrafoPonderadoMatrizAdjacencia.java

run: all
	java Main

clean:
	del /Q *.class