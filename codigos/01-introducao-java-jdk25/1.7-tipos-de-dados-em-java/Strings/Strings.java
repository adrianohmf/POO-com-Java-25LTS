// Arquivo: Strings.java

void main() {
    String nome = "Maria Silva";

    // INFORMAÇÕES sobre a String
    IO.println(nome.length());           // 11  — número de caracteres
    IO.println(nome.charAt(0));          // M   — caractere na posição 0
    IO.println(nome.indexOf("Silva"));   // 6   — posição onde "Silva" começa
    IO.println(nome.isEmpty());          // false — vazia?
    IO.println(nome.isBlank());          // false — vazia ou só espaços?

    // TRANSFORMAÇÕES (retornam nova String, não mudam a original)
    IO.println(nome.toUpperCase());      // MARIA SILVA
    IO.println(nome.toLowerCase());      // maria silva
    IO.println(nome.trim());             // remove espaços das bordas
    IO.println(nome.strip());            // igual ao trim, mais moderno

    // PARTES da String
    IO.println(nome.substring(0, 5));    // Maria   — do índice 0 até 4
    IO.println(nome.substring(6));       // Silva   — do índice 6 até o fim
    IO.println(nome.replace("Silva", "Santos")); // Maria Santos

    // VERIFICAÇÕES
    IO.println(nome.contains("Silva"));  // true  — contém o texto?
    IO.println(nome.startsWith("Mar"));  // true  — começa com?
    IO.println(nome.endsWith("lva"));    // true  — termina com?

    // DIVISÃO
    String frase = "Java,Python,C++";
    String[] partes = frase.split(",");  // divide pela vírgula
    IO.println(partes[0]);               // Java
    IO.println(partes[1]);               // Python
    IO.println(partes[2]);               // C++

    // COMPARAÇÃO: SEMPRE use .equals(), nunca ==
    String s1 = "Java";
    String s2 = "Java";
    IO.println(s1 == s2);               // pode dar true ou false — NÃO confie!
    IO.println(s1.equals(s2));          // true — sempre confiável
    IO.println(s1.equalsIgnoreCase("java")); // true — ignora maiúsculas

    // CONCATENAÇÃO
    String primeiro = "João";
    String ultimo   = "Silva";
    String completo = primeiro + " " + ultimo;         // "João Silva"
    String completo2 = primeiro.concat(" ").concat(ultimo); // mesmo resultado

    // CONVERSÃO de número para String
    int    numero = 42;
    String texto  = String.valueOf(numero);   // "42"
    String texto2 = Integer.toString(numero); // "42" — alternativa

    // VERIFICAR se String representa um número antes de converter
    String entrada = "123";
    try {
        int valor = Integer.parseInt(entrada);
        IO.println("Número válido: " + valor);
    } catch (NumberFormatException e) {
        IO.println("Não é um número!");
    }

    // Forma moderna com formatted (JDK 15+)
    String msg = "Aluno: %s | Nota: %.1f".formatted(primeiro, 8.5);
    IO.println(msg);  // Aluno: João | Nota: 8.5
}
