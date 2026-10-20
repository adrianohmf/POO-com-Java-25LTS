// Arquivo: AtributoEstatico.java

void main() {
    IO.println("Total antes: " + Produto.totalProdutos); // 0

    var p1 = new Produto("Mel de Abelha");
    var p2 = new Produto("Castanha de Caju");
    var p3 = new Produto("Rapadura");

    // Acessado PELA CLASSE, nao por p1, p2 ou p3
    IO.println("Total depois: " + Produto.totalProdutos); // 3

    // Mesmo acessando por um objeto, o valor e o MESMO para todos
    IO.println("Via p1: " + p1.totalProdutos); // 3
    IO.println("Via p2: " + p2.totalProdutos); // 3
}
