// Arquivo: LeituraSegura.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

void main() {
    Path log = Path.of("log.txt");

    // try-with-resources: o arquivo é fechado automaticamente ao sair do bloco
    try (BufferedWriter escritor = Files.newBufferedWriter(log)) {
        escritor.write("Sistema iniciado");
        escritor.newLine();
        escritor.write("Usuario conectado");
        escritor.newLine();
    } catch (IOException e) {
        IO.println("Erro ao gravar: " + e.getMessage());
        return;
    }

    // Lendo linha a linha, sem carregar o arquivo todo na memória
    try (BufferedReader leitor = Files.newBufferedReader(log)) {
        String linha;
        int numero = 1;
        while ((linha = leitor.readLine()) != null) {   // null indica o fim do arquivo
            IO.println(numero + ": " + linha);
            numero++;
        }
    } catch (IOException e) {
        IO.println("Erro ao ler: " + e.getMessage());
    }
}
