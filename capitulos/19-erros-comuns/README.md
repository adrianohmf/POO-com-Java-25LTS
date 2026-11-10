[Índice](../../README.md) | [← Anterior: 18. Boas Práticas e Convenções](../18-boas-praticas-e-convencoes/README.md) | [Próximo: 20. Glossário →](../20-glossario/README.md)

# 19. Erros Comuns e Como Resolver

> **Códigos deste capítulo:** [codigos/19-erros-comuns](../../codigos/19-erros-comuns/)

> **O que você vai aprender neste capítulo:**
> Reconhecer os erros mais frequentes em Java, entender sua causa raiz e saber como corrigi-los rapidamente.

Errar faz parte de aprender a programar. Todo desenvolvedor experiente já enfrentou cada um dos erros listados aqui — muitos deles dezenas de vezes. A diferença entre um iniciante e um desenvolvedor experiente não é não cometer erros, e reconhecer e corrigir erros com rapidez.

Este capítulo funciona como um guia de referência rápida. Quando seu programa travar com uma mensagem de erro que você não reconhece, venha aqui primeiro. Cada erro tem uma causa mais comum e uma solução clara.

Mantenha este capítulo marcado. Você vai consultá-lo com frequencia nas proximas semanas — e isso é perfeitamente normal. Com o tempo, você vai internalizar esses padroes e começar a evitar os erros antes mesmo de comete-los.


## Guia rápido de erros frequentes

| Erro | Causa mais comum | Como resolver |
|---|---|---|
| `NullPointerException` | Usar objeto sem inicializar | Verifique se fez `new` antes de usar |
| `ArrayIndexOutOfBoundsException` | Índice fora do tamanho | Verifique o tamanho com `.length` |
| `ClassCastException` | Cast para tipo errado | Use `instanceof` antes do cast |
| `StackOverflowError` | Recursao infinita | Verifique a condicao de parada |
| `ConcurrentModificationException` | Modificar lista enquanto percorre | Use `removeIf()` ou iterator |
| `NumberFormatException` | Converter texto não numerico | Use try-catch ao converter |
| `Cannot instantiate abstract class` | `new` em classe abstrata | Use uma subclasse concreta |
| `does not override abstract method` | Faltou implementar método | Implemente todos os métodos abstratos |
| `method not found` | Erro de digitacao ou tipo errado | Verifique o nome e os parâmetros |
| `incompatible types` | Atribuir tipo errado | Verifique os tipos ou faca cast |
| `NoSuchFileException` | Arquivo ou pasta não existe no caminho informado | Confira o caminho, use `Files.exists()` ou `Files.createDirectories()` |
| `unreported exception IOException` | Operação de arquivo sem tratamento | Declare `throws IOException` ou use try-catch |
| `DateTimeParseException` | Texto da data fora do padrão do formatador | Confira o padrão (`dd/MM/yyyy`) e trate com try-catch |
| `DateTimeException: Invalid date` | Data que não existe (30 de fevereiro) | Valide dia e mês, ou use `ResolverStyle.STRICT` ao ler |
| `cannot find symbol` (classe) | Faltou o `import` ou houve erro de digitação | Importe a classe do pacote correto |
| `package ... does not exist` | `import` com pacote errado ou fora do classpath | Confira o `package` das classes e o `-cp` |
| `... is not public in ...; cannot be accessed from outside package` | Classe ou método sem `public` usado em outro pacote | Torne `public` apenas o que outros pacotes precisam |
| `class X is public, should be declared in a file named X.java` | Nome da classe diferente do nome do arquivo | Renomeie o arquivo ou a classe |
| `Could not find or load main class` | Executou sem o nome completo do pacote | `java -cp out pacote.Classe` |
| `java.net.BindException: Address already in use` | A porta 8080 já está em uso por outro servidor | Pare o programa anterior ou troque a porta |
| `Connection refused` (`curl` ou navegador) | O servidor não está rodando | Execute o programa e espere a mensagem "no ar" |
| `blocked by CORS policy` (console do navegador) | Frontend em outra origem sem os cabeçalhos CORS | Sirva a página pelo servidor Java ou envie `Access-Control-Allow-Origin` |
| Resposta `400`, `404`, `405` ou `500` | Dados inválidos, rota inexistente, método não aceito ou erro no servidor | Confira a rota, o método e o corpo enviado; veja o console do servidor no caso do `500` |

## Erros de lógica mais comuns

```java
// 1. = em vez de == na comparação
if (nota = 7.0) { }    // ERRADO: atribuição, não comparação
if (nota == 7.0) { }   // CORRETO

// 2. == em vez de .equals() para String
String s = "Maria";
if (s == "Maria") { }      // ERRADO: compara referência
if (s.equals("Maria")) { } // CORRETO: compara conteúdo

// 3. Lógica invertida no if/else
if (nota < 7.0) {
    return "Aprovado";  // ERRADO: abaixo de 7 não e aprovado!
}

// 4. Off-by-one (erro de um a mais ou a menos)
for (int i = 0; i <= lista.size(); i++) { // ERRADO: i vai até size(), fora do índice!
    lista.get(i);
}
for (int i = 0; i < lista.size(); i++) {  // CORRETO
    lista.get(i);
}

// 5. Divisão inteira inesperada
int total = 10, quantidade = 3;
double media = total / quantidade;     // resultado: 3.0 (não 3.33!)
double media = (double) total / quantidade; // resultado: 3.33

// 6. switch antigo sem break (fall-through): executa os casos seguintes
switch (opcao) {
    case 1: IO.println("Cadastrar");     // ERRADO: sem break, continua no case 2
    case 2: IO.println("Listar"); break;
}
// CORRETO: use break em cada case, ou a forma com seta: case 1 -> IO.println("Cadastrar");

// 7. Esperar que uma data (imutável) mude sozinha
data.plusDays(7);          // ERRADO: resultado descartado
data = data.plusDays(7);   // CORRETO: guarda a nova data
```

---

[Índice](../../README.md) | [← Anterior: 18. Boas Práticas e Convenções](../18-boas-praticas-e-convencoes/README.md) | [Próximo: 20. Glossário →](../20-glossario/README.md)
