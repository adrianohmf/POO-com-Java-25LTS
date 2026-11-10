// Arquivo: Produto.java

class Produto {
    String nome;

    // static: uma única cópia, compartilhada por TODOS os objetos
    static int totalProdutos = 0;

    Produto(String nome) {
        this.nome = nome;
        totalProdutos++;   // incrementa a cópia ÚNICA a cada objeto criado
    }
}
