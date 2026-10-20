// Arquivo: Cachorro.java

class Cachorro extends Mamifero {
    private String raca;
    Cachorro(String nome, String pelagem, String raca) {
        super(nome, pelagem);
        this.raca = raca;
    }
    void latir()        { IO.println(nome + ": Au au!"); }
    void buscarObjeto() { IO.println(nome + " buscou o objeto!"); }
}
