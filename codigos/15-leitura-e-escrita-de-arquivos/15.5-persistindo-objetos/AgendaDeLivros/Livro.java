// Arquivo: Livro.java

record Livro(String titulo, String autor, int ano) {

    // Objeto -> linha de texto no formato CSV
    String paraCsv() {
        return titulo + ";" + autor + ";" + ano;
    }

    // Linha de texto -> objeto
    static Livro deCsv(String linha) {
        String[] partes = linha.split(";");
        return new Livro(partes[0], partes[1], Integer.parseInt(partes[2]));
    }
}
