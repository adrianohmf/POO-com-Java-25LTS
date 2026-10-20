[Índice](../../README.md) | [← Anterior: 14. Erros Comuns e Como Resolver](../14-erros-comuns/README.md) | [Próximo: Referências e Recursos →](../referencias/README.md)

# 15. Glossário

> **Referência rápida de termos** — use durante as aulas, exercícios e provas.

Programação Orientada a Objetos tem uma terminologia própria que pode parecer intimidadora no inicio. Termos como "instância", "polimorfismo", "encapsulamento" e "sobrescrita" aparecem o tempo todo — e entende-los com precisão faz diferença tanto nas provas quanto na comunicação com outros desenvolvedores.

Este glossário reune todos os termos técnicos usados no guia com definições claras e diretas. Não tente memorizar tudo de uma vez. Use como referência: sempre que encontrar um termo desconhecido nas aulas ou nos exercícios, consulte aqui antes de buscar na internet.

Os termos estão em ordem alfabetica para facilitar a consulta rápida.


| Termo | Definição |
|---|---|
| **Abstrato** | Classe ou método que não tem implementação completa. Obriga subclasses a completar. |
| **Atributo** | Variável declarada dentro de uma classe. Representa uma característica do objeto. |
| **Bytecode** | Código intermediário gerado pelo compilador Java. Executado pela JVM. |
| **Cast** | Conversao forcada de um tipo para outro. Ex: `(double) inteiro` |
| **Classe** | Molde ou template que define atributos e métodos de um objeto. |
| **Construtor** | Método especial com o mesmo nome da classe, executado automaticamente com `new`. |
| **Encapsulamento** | Pilar de POO que protege os dados internos do objeto, expondo apenas o necessário. |
| **Extends** | Palavra-chave usada para herança de classe. `class Aluno extends Pessoa` |
| **Final** | Modificador que impede alteracao. `final` em variável = constante, em classe = não pode ser herdada. |
| **Getter** | Método de leitura de atributo privado. Convenção: `getNome()`, `isAtivo()`. |
| **Herança** | Pilar de POO onde uma classe filha herda atributos e métodos da classe pai. |
| **Implements** | Palavra-chave usada para implementar interface. `class Doc implements Imprimivel` |
| **Instância** | Um objeto criado a partir de uma classe. `new Aluno()` cria uma instância de Aluno. |
| **Interface** | Contrato que define quais métodos uma classe deve implementar. Sem atributos de instância. |
| **JDK** | Java Development Kit. Pacote completo para desenvolver em Java (inclui compilador e JVM). |
| **JRE** | Java Runtime Environment. Só executa programas Java (sem compilador). |
| **JVM** | Java Virtual Machine. Executa o bytecode em qualquer sistema operacional. |
| **Método** | Função declarada dentro de uma classe. Define o comportamento do objeto. |
| **Modificador de acesso** | Palavra que controla visibilidade: `private`, `protected`, `public` ou padrao. |
| **new** | Operador que cria um objeto na memória e chama o construtor. |
| **Objeto** | Instância concreta de uma classe. Ocupa espaco na memória e tem estado próprio. |
| **Override** | Sobrescrita de método herdado. Anotacao `@Override` garante que a sobrescrita e válida. |
| **Overloading** | Sobrecarga: mesmo nome de método com parâmetros diferentes na mesma classe. |
| **Pacote (package)** | Agrupamento lógico de classes relacionadas. Equivale a uma pasta. |
| **Parâmetro** | Variável recebida por um método. Diferente de argumento (valor passado na chamada). |
| **Pilar** | Princípio fundamental da POO: Abstração, Encapsulamento, Herança, Polimorfismo. |
| **Polimorfismo** | Pilar de POO onde o mesmo método se comporta diferente dependendo do objeto. |
| **Protected** | Modificador que permite acesso na mesma classe, mesmo pacote e subclasses. |
| **Private** | Modificador que permite acesso somente dentro da própria classe. |
| **Public** | Modificador que permite acesso em qualquer lugar. |
| **Record** | Tipo especial de classe (JDK 25) para dados imutaveis, com código gerado automaticamente. |
| **Referência** | Variável que aponta para um objeto na memória. Não é o objeto em si. |
| **Return** | Palavra-chave que devolve um valor de um método para quem o chamou. |
| **Setter** | Método de escrita de atributo privado com validação. Convenção: `setNome(String nome)`. |
| **Static** | Pertence a classe, não ao objeto. Compartilhado por todas as instâncias. |
| **Subclasse** | Classe filha que herda de outra classe (superclasse). |
| **Super** | Referência a superclasse. `super()` chama o construtor do pai. `super.metodo()` chama método do pai. |
| **Superclasse** | Classe pai de uma hierarquia de herança. |
| **This** | Referência ao próprio objeto dentro de um método ou construtor. |
| **Throws** | Declara que um método pode lançar uma exceção. `void sacar() throws SaldoInsuficienteException` |
| **UML** | Unified Modeling Language. Linguagem visual para modelar sistemas orientados a objetos. |
| **Var** | Palavra-chave (JDK 10+) que permite ao Java inferir o tipo da variável automaticamente. |
| **Void** | Indica que um método não retorna nenhum valor. |

---

[Índice](../../README.md) | [← Anterior: 14. Erros Comuns e Como Resolver](../14-erros-comuns/README.md) | [Próximo: Referências e Recursos →](../referencias/README.md)
