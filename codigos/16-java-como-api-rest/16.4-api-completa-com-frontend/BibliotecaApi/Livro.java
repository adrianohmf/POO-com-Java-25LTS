// Arquivo: Livro.java

record Livro(int id, String titulo, String autor, int ano) {

    // Converte o livro em texto JSON: {"id":1,"titulo":"...","autor":"...","ano":1899}
    String paraJson() {
        return "{\"id\":" + id
                + ",\"titulo\":" + Json.texto(titulo)
                + ",\"autor\":" + Json.texto(autor)
                + ",\"ano\":" + ano + "}";
    }
}
