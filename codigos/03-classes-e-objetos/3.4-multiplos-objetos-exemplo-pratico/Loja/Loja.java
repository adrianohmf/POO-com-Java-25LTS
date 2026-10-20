// Arquivo: Loja.java

void main() {
    var p1 = new Produto();
    var p2 = new Produto();
    var p3 = new Produto();

    p1.nome = "Mel de Abelha";     p1.preco = 25.00; p1.estoque = 50;
    p2.nome = "Castanha de Caju";  p2.preco = 18.50; p2.estoque = 200;
    p3.nome = "Rapadura";          p3.preco =  5.00; p3.estoque = 100;

    Produto[] catalogo = { p1, p2, p3 };

    IO.println("=== CATALOGO DA LOJA ===");
    for (int i = 0; i < catalogo.length; i++) {
        catalogo[i].exibir();
    }
}
