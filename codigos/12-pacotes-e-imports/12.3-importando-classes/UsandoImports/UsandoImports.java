// Arquivo: UsandoImports.java

import java.util.ArrayList;
import static java.lang.Math.max;    // import estático: permite escrever max(...) sem "Math."

void main() {
    // ArrayList foi importado na primeira linha do arquivo
    var nomes = new ArrayList<String>();
    nomes.add("Ana");
    nomes.add("Bruno");

    // Nome totalmente qualificado: dispensa o import (útil em conflitos de nome)
    java.util.ArrayList<String> copia = new java.util.ArrayList<>(nomes);
    copia.add("Carla");

    IO.println(nomes);
    IO.println(copia);

    // Classes de java.lang (String, Math, Integer...) nunca precisam de import
    IO.println(Math.abs(-7));
    IO.println(max(3, 9));
}
