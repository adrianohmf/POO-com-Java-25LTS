// Arquivo: Animal.java

class Animal {
    protected String nome;
    Animal(String nome) { this.nome = nome; }
    void respirar() { IO.println(nome + " esta respirando."); }
    void dormir()   { IO.println(nome + " esta dormindo."); }
}
