// Arquivo: Gato.java

class Gato extends Mamifero {
    Gato(String nome, String pelagem) { super(nome, pelagem); }
    void miar() { IO.println(nome + ": Miau!"); }
}
