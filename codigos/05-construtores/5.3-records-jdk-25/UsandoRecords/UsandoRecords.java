// Arquivo: UsandoRecords.java
// Record: gera automaticamente construtor, getters,
// toString(), equals() e hashCode()

void main() {
    var p1 = new Produto("Mel de Abelha", 25.00, 100);

    // Getters: nome do atributo + ()
    IO.println(p1.nome());    // Mel de Abelha
    IO.println(p1.preco());   // 25.0
    IO.println(p1);           // Produto[nome=Mel de Abelha, preco=25.0, estoque=100]

    var m1 = new Municipio("Caruaru", "PE", 365000, 920.6);
    IO.println(m1.nome() + ": " + String.format("%.1f", m1.densidadeDemografica()) + " hab/km2");
}
