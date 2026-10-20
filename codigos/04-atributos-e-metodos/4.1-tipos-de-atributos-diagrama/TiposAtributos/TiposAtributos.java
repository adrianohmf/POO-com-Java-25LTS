// Arquivo: TiposAtributos.java

void main() {
    var p1 = new Produto();
    p1.nome  = "Mel de Abelha";
    p1.preco = 25.00;

    IO.println(p1.nome + " — ativo: " + p1.ativo + " — origem: " + p1.origem);

    // Atributo estático: acessado PELA CLASSE, não pelo objeto
    IO.println("Taxa de imposto: " + Produto.TAXA_IMPOSTO);

    double precoFinal = p1.preco * (1 + Produto.TAXA_IMPOSTO);
    System.out.format("Preco com imposto: R$ %.2f%n", precoFinal);
}
