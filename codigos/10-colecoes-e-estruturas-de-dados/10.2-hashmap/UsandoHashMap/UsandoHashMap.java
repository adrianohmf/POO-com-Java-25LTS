// Arquivo: UsandoHashMap.java

import java.util.HashMap;

void main() {

    // HashMap: associa chave -> valor
    var notas = new HashMap<String, Double>();

    notas.put("Maria", 8.5);
    notas.put("Joao",  7.0);
    notas.put("Ana",   9.5);
    notas.put("Pedro", 6.0);

    IO.println("Nota da Maria: " + notas.get("Maria"));
    IO.println("Tem Carlos? "   + notas.containsKey("Carlos"));

    notas.put("Joao", 7.5); // atualiza o valor

    IO.println("Boletim:");
    for (var entry : notas.entrySet()) {
        var sit = entry.getValue() >= 7.0 ? "Aprovado" : "Reprovado";
        IO.println("  " + entry.getKey() + ": " + entry.getValue() + " — " + sit);
    }

    notas.remove("Pedro");
    IO.println("Total: " + notas.size());
}
