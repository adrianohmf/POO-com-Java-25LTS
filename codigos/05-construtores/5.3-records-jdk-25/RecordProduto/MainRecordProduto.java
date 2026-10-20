// Arquivo: MainRecordProduto.java
// Programa principal adicionado para executar o record Produto desta seção.
// Para executar: java MainRecordProduto.java

void main() {
    var p = new Produto("Mel de Abelha", 25.00, 100);
    IO.println(p.nome());   // acessor gerado automaticamente
    IO.println(p);          // toString gerado automaticamente
}
