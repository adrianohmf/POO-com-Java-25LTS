// Arquivo: ProblemaBuffer.java
// JDK 25 compact source file: sem import necessário

void main() {
    var sc = new Scanner(System.in);

    IO.println("Digite sua idade:");
    var idade = sc.nextInt();

    // SEM sc.nextLine() de limpeza:
    IO.println("Digite seu nome:");
    var nome = sc.nextLine(); // lê o Enter que ficou do nextInt!
    // nome fica vazio ""

    // SOLUCAO: chamar sc.nextLine() logo após nextInt/nextDouble
    // para descartar o Enter que ficou no buffer
    IO.println("Digite sua cidade:");
    sc.nextLine();            // descarta o Enter residual
    var cidade = sc.nextLine(); // agora lê corretamente

    System.out.format("Idade: %d | Nome: '%s' | Cidade: %s%n",
                      idade, nome, cidade);
    sc.close();
}
