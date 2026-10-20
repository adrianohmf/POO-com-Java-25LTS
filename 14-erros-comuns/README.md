[Índice](../README.md) | [← Anterior: 13. Boas Práticas e Convenções](../13-boas-praticas-e-convencoes/README.md) | [Próximo: 15. Glossário →](../15-glossario/README.md)

# 14. Erros Comuns e Como Resolver

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
```

---

[Índice](../README.md) | [← Anterior: 13. Boas Práticas e Convenções](../13-boas-praticas-e-convencoes/README.md) | [Próximo: 15. Glossário →](../15-glossario/README.md)
