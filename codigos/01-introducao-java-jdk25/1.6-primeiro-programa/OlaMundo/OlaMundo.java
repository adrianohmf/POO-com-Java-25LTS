// Arquivo: OlaMundo.java

void main() {
    // IO.println: forma moderna (JDK 25)
    IO.println("Olá, mundo!");

    // System.out.println: forma clássica
    System.out.println("Olá pelo modo clássico!");

    // var: Java infere o tipo automaticamente
    var nome   = "Adriano";   // String
    var idade  = 35;           // int
    var altura = 1.75;         // double

    IO.println("Nome: " + nome + " | Idade: " + idade);
}
