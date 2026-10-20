// Arquivo: ClassesWrapper.java

void main() {

    // PARSING: String -> tipo numerico
    int     idade     = Integer.parseInt("22");
    double  nota      = Double.parseDouble("8.75");
    float   taxa      = Float.parseFloat("0.12");
    long    populacao = Long.parseLong("215000000");
    boolean ativo     = Boolean.parseBoolean("true");

    IO.println("Idade: "     + idade);
    IO.println("Nota: "      + nota);
    IO.println("Taxa: "      + taxa);
    IO.println("Populacao: " + populacao);
    IO.println("Ativo: "     + ativo);

    // WRAPPER: tipo -> String
    String sIdade = Integer.toString(idade);
    String sNota  = Double.toString(nota);
    String sAtivo = Boolean.toString(ativo);

    // Alternativa universal: String.valueOf() aceita qualquer tipo
    String s1 = String.valueOf(idade);     // "22"
    String s2 = String.valueOf(nota);      // "8.75"
    String s3 = String.valueOf(ativo);     // "true"

    IO.println("Como texto: " + s1 + " | " + s2 + " | " + s3);

    // CONSTANTES ÚTEIS das classes wrapper
    IO.println("Maior int:    " + Integer.MAX_VALUE);   // 2147483647
    IO.println("Menor int:    " + Integer.MIN_VALUE);   // -2147483648
    IO.println("Maior double: " + Double.MAX_VALUE);    // 1.7976931348623157E308
    IO.println("Menor double: " + Double.MIN_VALUE);    // 4.9E-324

    // AUTOBOXING: Java converte automaticamente int <-> Integer
    Integer wrapperIdade = idade;          // int -> Integer (autoboxing)
    int     primitivo    = wrapperIdade;   // Integer -> int (unboxing)
    IO.println("Wrapper: " + wrapperIdade);
    IO.println("Primitivo: " + primitivo);

    // CUIDADO: parseBoolean aceita qualquer String
    // só "true" (case insensitive) vira true, qualquer outra coisa vira false
    IO.println(Boolean.parseBoolean("true"));   // true
    IO.println(Boolean.parseBoolean("TRUE"));   // true
    IO.println(Boolean.parseBoolean("sim"));    // false (não e "true")
    IO.println(Boolean.parseBoolean("1"));      // false (não e "true")
}
