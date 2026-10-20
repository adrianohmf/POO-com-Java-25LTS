// Arquivo: Hierarquia.java

void main() {
    var rex  = new Cachorro("Rex",  "caramelo", "Vira-lata");
    var mimi = new Gato("Mimi", "branca");

    rex.respirar();   // herdado de Animal
    rex.latir();      // próprio de Cachorro
    rex.buscarObjeto();

    mimi.respirar();  // herdado de Animal
    mimi.dormir();    // herdado de Animal
    mimi.miar();      // próprio de Gato
}
