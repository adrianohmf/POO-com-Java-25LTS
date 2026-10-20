// Arquivo: Animal.java

class Animal {
    protected String nome;
    Animal(String nome) { this.nome = nome; }
    void fazerSom() { IO.println(nome + " faz algum som..."); }
    void dormir()   { IO.println(nome + " esta dormindo."); }
}
