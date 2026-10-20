// Arquivo: Operadores.java

void main() {

    // ARITMETICOS
    int a = 10, b = 3;
    IO.println(a + b);   // 13  — soma
    IO.println(a - b);   // 7   — subtracao
    IO.println(a * b);   // 30  — multiplicacao
    IO.println(a / b);   // 3   — divisão inteira (cuidado!)
    IO.println(a % b);   // 1   — resto da divisão
    IO.println(a++);     // 10  — usa o valor, depois incrementa
    IO.println(++a);     // 12  — incrementa, depois usa o valor

    // COMPARAÇÃO (resultado sempre boolean)
    IO.println(10 == 10);  // true  — igual
    IO.println(10 != 5);   // true  — diferente
    IO.println(10 >  5);   // true  — maior que
    IO.println(10 <  5);   // false — menor que
    IO.println(10 >= 10);  // true  — maior ou igual
    IO.println(10 <= 9);   // false — menor ou igual

    // LÓGICOS
    boolean x = true, y = false;
    IO.println(x && y);   // false — E (ambos verdadeiros)
    IO.println(x || y);   // true  — OU (pelo menos um verdadeiro)
    IO.println(!x);       // false — NÃO (inverte)

    // OPERADOR TERNARIO: condição ? se_verdadeiro : se_falso
    double nota = 7.5;
    String situacao = (nota >= 7.0) ? "Aprovado" : "Reprovado";
    IO.println(situacao);  // Aprovado
}
