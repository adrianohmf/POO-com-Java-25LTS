// Arquivo: ScannerSemImport.java

// JDK 25 compact source file — import e OPCIONAL
// Scanner disponível automaticamente via módulo java.base
void main() {
    var sc = new Scanner(System.in);  // funciona sem import!
    IO.println("Nome:");
    var nome = sc.nextLine();
    IO.println("Ola, " + nome);
    sc.close();
}
