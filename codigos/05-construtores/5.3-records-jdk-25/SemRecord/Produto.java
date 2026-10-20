// Arquivo: Produto.java

class Produto {
    private final String nome;
    private final double preco;
    private final int estoque;

    Produto(String nome, double preco, int estoque) {
        this.nome    = nome;
        this.preco   = preco;
        this.estoque = estoque;
    }

    String getNome()   { return nome; }
    double getPreco()  { return preco; }
    int    getEstoque(){ return estoque; }

    @Override
    public String toString() {
        return "Produto[nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Produto)) return false;
        Produto p = (Produto) obj;
        return nome.equals(p.nome) && preco == p.preco && estoque == p.estoque;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(nome, preco, estoque);
    }
}
