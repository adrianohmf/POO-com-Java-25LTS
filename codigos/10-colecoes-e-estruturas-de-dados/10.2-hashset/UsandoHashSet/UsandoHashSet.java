// Arquivo: UsandoHashSet.java

import java.util.ArrayList;
import java.util.HashSet;

void main() {
    var cidades = new HashSet<String>();

    IO.println(cidades.add("Recife"));    // true: foi inserido
    IO.println(cidades.add("Olinda"));    // true
    IO.println(cidades.add("Recife"));    // false: já existia, ignorado

    IO.println("Total: " + cidades.size());                     // 2
    IO.println("Tem Olinda? " + cidades.contains("Olinda"));    // true

    // Uso prático: remover duplicados de uma lista
    var comRepetidos = new ArrayList<String>();
    comRepetidos.add("Renda");
    comRepetidos.add("Cerâmica");
    comRepetidos.add("Renda");
    comRepetidos.add("Renda");

    var unicos = new HashSet<String>(comRepetidos);
    IO.println("Antes: " + comRepetidos.size() + " itens");
    IO.println("Depois: " + unicos.size() + " itens");
}
