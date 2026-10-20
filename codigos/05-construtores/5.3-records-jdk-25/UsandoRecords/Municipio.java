// Arquivo: Municipio.java

record Municipio(String nome, String estado, long populacao, double area) {
    // Método personalizado dentro do record
    double densidadeDemografica() {
        return populacao / area;
    }
}
