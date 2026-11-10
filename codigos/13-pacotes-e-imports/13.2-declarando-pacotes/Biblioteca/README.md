# Biblioteca: exemplo com pacotes

Projeto com três pacotes (`modelo`, `servico` e `app`). A estrutura de pastas dentro de `src` repete o nome dos pacotes.

## Compilar e executar pelo terminal

Dentro desta pasta (`Biblioteca`):

**Linux e macOS**

```
javac -d out $(find src -name "*.java")
java -cp out br.edu.ifpe.biblioteca.app.App
```

**Windows (PowerShell)**

```
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out br.edu.ifpe.biblioteca.app.App
```

* `-d out` diz ao `javac` onde salvar os `.class`, criando as pastas dos pacotes.
* `-cp out` (classpath) diz ao `java` onde procurar as classes compiladas.
* O nome da classe no `java` é o **nome completo**: pacote + classe.

## No VS Code

Abra a pasta `Biblioteca` (**File > Open Folder**). A extensão Java reconhece a pasta `src` e mostra o botão **Run** acima do `main` da classe `App`.

## Saída esperada

```
Total no catálogo: 3
Dom Casmurro (Machado de Assis, 1899)
Quincas Borba (Machado de Assis, 1891)
```
