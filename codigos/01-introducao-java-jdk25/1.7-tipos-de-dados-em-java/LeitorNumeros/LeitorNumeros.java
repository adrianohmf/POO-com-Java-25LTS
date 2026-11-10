// Arquivo: LeitorNumeros.java

// Lendo número com IO.readln() + conversão
void main() {
    IO.println("Qual a sua idade?");
    var idade = Integer.parseInt(IO.readln());  // lê texto e converte

    IO.println("Qual a sua nota?");
    var nota = Double.parseDouble(IO.readln()); // lê texto e converte

    IO.println("Idade: " + idade + " | Nota: " + nota);
}
