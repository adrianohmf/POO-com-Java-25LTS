// Arquivo: Produto.java

class Produto {
    String nome;
    double preco;
    int    estoque;

    void exibir() {
        IO.println(nome + " — R$ " + preco + " (estoque: " + estoque + ")");
    }
}
