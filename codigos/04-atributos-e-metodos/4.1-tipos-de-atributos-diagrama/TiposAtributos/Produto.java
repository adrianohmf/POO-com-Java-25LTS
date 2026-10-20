// Arquivo: Produto.java

class Produto {

    // ATRIBUTOS DE INSTÂNCIA: cada objeto tem sua própria cópia
    String nome;
    double preco;

    // ATRIBUTO ESTÁTICO: compartilhado por TODOS os objetos. Pertence à
    // classe, compartilhado por todos os objetos, mas o valor pode ser
    // modificado ao longo do tempo.
    static int totalProdutos = 0;

    // CONSTANTE: pertence à classe (não muda por objeto) e o valor não
    // pode ser alterado (é uma constante real). (por convenção: MAIÚSCULAS)
    static final double TAXA_IMPOSTO = 0.12;

    // ATRIBUTO COM VALOR PADRÃO
    boolean ativo  = true;
    String  origem = "Nordeste";
}
