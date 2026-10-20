// Arquivo: Casting.java

void main() {

    // CONVERSÃO IMPLÍCITA: de menor para maior (Java faz automaticamente)
    int    inteiro = 10;
    double decimal = inteiro;   // int -> double, sem perda de dado
    IO.println("Double: " + decimal);  // 10.0

    // CONVERSÃO EXPLÍCITA (cast): de maior para menor (você decide)
    double altura  = 1.758;
    int    arredondado = (int) altura;  // corta os decimais, não arredonda!
    IO.println("Int: " + arredondado); // 1 (não 2!)

    // PROBLEMA CLÁSSICO: divisão de inteiros
    int a = 7, b = 2;
    double errado  = a / b;           // resultado: 3.0 (divisão inteira!)
    double correto = (double) a / b;  // resultado: 3.5
    IO.println("Errado:  " + errado);
    IO.println("Correto: " + correto);

    // CONVERTER String para número
    String textoIdade = "22";
    int    idadeNum   = Integer.parseInt(textoIdade);
    double notaNum    = Double.parseDouble("8.5");
    IO.println("Idade como numero: " + (idadeNum + 1)); // 23

    // CONVERTER número para String
    int    numero = 100;
    String texto  = String.valueOf(numero);  // "100"
    String texto2 = "" + numero;             // forma rápida: "100"
    IO.println("Numero como texto: " + texto);
}
