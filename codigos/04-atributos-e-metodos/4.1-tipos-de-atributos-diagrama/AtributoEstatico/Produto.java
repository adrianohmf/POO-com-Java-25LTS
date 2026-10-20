// Arquivo: Produto.java

class Produto {
    String nome;

    // static: uma unica copia, compartilhada por TODOS os objetos
    static int totalProdutos = 0;

    Produto(String nome) {
        this.nome = nome;
        totalProdutos++;   // incrementa a copia UNICA a cada objeto criado
    }
}
