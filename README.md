<div align="center">


# Guia Completo de Programação Orientada a Objetos
## Java JDK 25 LTS

**Prof. Adriano França**
Instituto Federal de Pernambuco — IFPE
Cursos Técnico e Tecnólogo em Informática para Internet / Sistemas para Internet

---

*Este guia cobre do básico ao intermediário de POO com Java 25 LTS,
com teoria, diagramas, exemplos comentados, erros comuns e exercícios práticos.*

*IDE utilizada: Visual Studio Code com Extension Pack for Java*

</div>

---

## Sumário

### [1. Introdução ao Java e ao JDK 25](01-introducao-java-jdk25/README.md)

- [1.1 O que é Java?](01-introducao-java-jdk25/README.md#11-o-que-é-java)
- [1.2 Como o Java funciona?](01-introducao-java-jdk25/README.md#12-como-o-java-funciona)
- [1.3 Novidades do JDK 25 LTS](01-introducao-java-jdk25/README.md#13-novidades-do-jdk-25-lts)
- [1.4 Entendendo o `void main()`](01-introducao-java-jdk25/README.md#14-entendendo-o-void-main)
- [1.5 Configurando o ambiente (VS Code)](01-introducao-java-jdk25/README.md#15-configurando-o-ambiente-vs-code)
- [1.6 Primeiro programa](01-introducao-java-jdk25/README.md#16-primeiro-programa)
- [1.7 Tipos de dados em Java](01-introducao-java-jdk25/README.md#17-tipos-de-dados-em-java)

### [2. Conceitos Fundamentais de POO](02-conceitos-fundamentais-poo/README.md)

- [2.1 O que é Programação Orientada a Objetos?](02-conceitos-fundamentais-poo/README.md#21-o-que-é-programação-orientada-a-objetos)
- [2.2 Os 4 Pilares](02-conceitos-fundamentais-poo/README.md#22-os-4-pilares)
- [2.3 Classe vs Objeto — A analogia da forma de bolo](02-conceitos-fundamentais-poo/README.md#23-classe-vs-objeto--a-analogia-da-forma-de-bolo)

### [3. Classes e Objetos](03-classes-e-objetos/README.md)

- [3.1 Visualizando uma Classe — Diagrama UML](03-classes-e-objetos/README.md#31-visualizando-uma-classe--diagrama-uml)
- [3.2 Criando uma classe](03-classes-e-objetos/README.md#32-criando-uma-classe)
- [3.3 Criando objetos (instanciando)](03-classes-e-objetos/README.md#33-criando-objetos-instanciando)
- [3.4 Múltiplos objetos — exemplo prático](03-classes-e-objetos/README.md#34-múltiplos-objetos--exemplo-prático)

### [4. Atributos e Métodos](04-atributos-e-metodos/README.md)

- [4.1 Tipos de atributos — diagrama](04-atributos-e-metodos/README.md#41-tipos-de-atributos--diagrama)
- [4.2 Tipos de métodos](04-atributos-e-metodos/README.md#42-tipos-de-métodos)
- [4.3 Sobrecarga de métodos (Overloading)](04-atributos-e-metodos/README.md#43-sobrecarga-de-métodos-overloading)

### [5. Construtores](05-construtores/README.md)

- [5.1 O problema sem construtor](05-construtores/README.md#51-o-problema-sem-construtor)
- [5.2 Construtor resolve o problema](05-construtores/README.md#52-construtor-resolve-o-problema)
- [5.3 Records (JDK 25)](05-construtores/README.md#53-records-jdk-25)

### [6. Encapsulamento](06-encapsulamento/README.md)

- [6.1 Começando pelo mais simples: nome e idade](06-encapsulamento/README.md#61-começando-pelo-mais-simples-nome-e-idade)
- [6.2 O problema sem encapsulamento](06-encapsulamento/README.md#62-o-problema-sem-encapsulamento)
- [6.3 Diferença entre private e protected](06-encapsulamento/README.md#63-diferença-entre-private-e-protected)
- [6.4 Tabela de visibilidade](06-encapsulamento/README.md#64-tabela-de-visibilidade)

### [7. Herança](07-heranca/README.md)

- [7.1 Visualizando a herança](07-heranca/README.md#71-visualizando-a-herança)
- [7.2 Hierarquia de múltiplos níveis](07-heranca/README.md#72-hierarquia-de-múltiplos-níveis)

### [8. Polimorfismo](08-polimorfismo/README.md)

- [8.1 Visualizando o polimorfismo](08-polimorfismo/README.md#81-visualizando-o-polimorfismo)

### [9. Classes Abstratas e Interfaces](09-classes-abstratas-e-interfaces/README.md)

- [9.1 Classes Abstratas — quando usar](09-classes-abstratas-e-interfaces/README.md#91-classes-abstratas--quando-usar)
- [9.2 Interfaces](09-classes-abstratas-e-interfaces/README.md#92-interfaces)
- [9.3 Classe Abstrata vs Interface](09-classes-abstratas-e-interfaces/README.md#93-classe-abstrata-vs-interface)

### [10. Coleções e Estruturas de Dados](10-colecoes-e-estruturas-de-dados/README.md)

- [10.1 Array vs ArrayList](10-colecoes-e-estruturas-de-dados/README.md#101-array-vs-arraylist)
- [10.2 HashMap](10-colecoes-e-estruturas-de-dados/README.md#102-hashmap)

### [11. Tratamento de Exceções](11-tratamento-de-excecoes/README.md)

- [11.1 Hierarquia de exceções](11-tratamento-de-excecoes/README.md#111-hierarquia-de-exceções)
- [11.2 Exceções personalizadas](11-tratamento-de-excecoes/README.md#112-exceções-personalizadas)

### [12. Projeto Final — Aplicação Completa](12-projeto-final/README.md)


### [13. Boas Práticas e Convenções](13-boas-praticas-e-convencoes/README.md)

- [13.1 Nomenclatura Java](13-boas-praticas-e-convencoes/README.md#131-nomenclatura-java)
- [13.2 Boas práticas gerais](13-boas-praticas-e-convencoes/README.md#132-boas-práticas-gerais)
- [13.3 Estrutura de um projeto Java profissional](13-boas-praticas-e-convencoes/README.md#133-estrutura-de-um-projeto-java-profissional)

### [14. Erros Comuns e Como Resolver](14-erros-comuns/README.md)


### [15. Glossário](15-glossario/README.md)


### [Referências e Recursos](referencias/README.md)

---

<div align="center">

**Prof. Adriano França**
Instituto Federal de Pernambuco — IFPE
*Programação Orientada a Objetos — Java JDK 25 LTS*

*IDE: Visual Studio Code + Extension Pack for Java*

*Este material e livre para uso educacional.*

</div>
