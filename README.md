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

### [1. Introdução ao Java e ao JDK 25](capitulos/01-introducao-java-jdk25/README.md)

- [1.1 O que é Java?](capitulos/01-introducao-java-jdk25/README.md#11-o-que-é-java)
- [1.2 Como o Java funciona?](capitulos/01-introducao-java-jdk25/README.md#12-como-o-java-funciona)
- [1.3 Novidades do JDK 25 LTS](capitulos/01-introducao-java-jdk25/README.md#13-novidades-do-jdk-25-lts)
- [1.4 Entendendo o `void main()`](capitulos/01-introducao-java-jdk25/README.md#14-entendendo-o-void-main)
- [1.5 Configurando o ambiente (VS Code)](capitulos/01-introducao-java-jdk25/README.md#15-configurando-o-ambiente-vs-code)
- [1.6 Primeiro programa](capitulos/01-introducao-java-jdk25/README.md#16-primeiro-programa)
- [1.7 Tipos de dados em Java](capitulos/01-introducao-java-jdk25/README.md#17-tipos-de-dados-em-java)
- [1.8 Estruturas de controle](capitulos/01-introducao-java-jdk25/README.md#18-estruturas-de-controle)
- [1.9 Arrays](capitulos/01-introducao-java-jdk25/README.md#19-arrays)

### [2. Conceitos Fundamentais de POO](capitulos/02-conceitos-fundamentais-poo/README.md)

- [2.1 O que é Programação Orientada a Objetos?](capitulos/02-conceitos-fundamentais-poo/README.md#21-o-que-é-programação-orientada-a-objetos)
- [2.2 Os 4 Pilares](capitulos/02-conceitos-fundamentais-poo/README.md#22-os-4-pilares)
- [2.3 Classe vs Objeto — A analogia da forma de bolo](capitulos/02-conceitos-fundamentais-poo/README.md#23-classe-vs-objeto--a-analogia-da-forma-de-bolo)

### [3. Classes e Objetos](capitulos/03-classes-e-objetos/README.md)

- [3.1 Visualizando uma Classe — Diagrama UML](capitulos/03-classes-e-objetos/README.md#31-visualizando-uma-classe--diagrama-uml)
- [3.2 Criando uma classe](capitulos/03-classes-e-objetos/README.md#32-criando-uma-classe)
- [3.3 Criando objetos (instanciando)](capitulos/03-classes-e-objetos/README.md#33-criando-objetos-instanciando)
- [3.4 Múltiplos objetos — exemplo prático](capitulos/03-classes-e-objetos/README.md#34-múltiplos-objetos--exemplo-prático)

### [4. Atributos e Métodos](capitulos/04-atributos-e-metodos/README.md)

- [4.1 Tipos de atributos — diagrama](capitulos/04-atributos-e-metodos/README.md#41-tipos-de-atributos--diagrama)
- [4.2 Tipos de métodos](capitulos/04-atributos-e-metodos/README.md#42-tipos-de-métodos)
- [4.3 Sobrecarga de métodos (Overloading)](capitulos/04-atributos-e-metodos/README.md#43-sobrecarga-de-métodos-overloading)

### [5. Construtores](capitulos/05-construtores/README.md)

- [5.1 O problema sem construtor](capitulos/05-construtores/README.md#51-o-problema-sem-construtor)
- [5.2 Construtor resolve o problema](capitulos/05-construtores/README.md#52-construtor-resolve-o-problema)
- [5.3 Records (JDK 25)](capitulos/05-construtores/README.md#53-records-jdk-25)

### [6. Encapsulamento](capitulos/06-encapsulamento/README.md)

- [6.1 Começando pelo mais simples: nome e idade](capitulos/06-encapsulamento/README.md#61-começando-pelo-mais-simples-nome-e-idade)
- [6.2 O problema sem encapsulamento](capitulos/06-encapsulamento/README.md#62-o-problema-sem-encapsulamento)
- [6.3 Diferença entre private e protected](capitulos/06-encapsulamento/README.md#63-diferença-entre-private-e-protected)
- [6.4 Tabela de visibilidade](capitulos/06-encapsulamento/README.md#64-tabela-de-visibilidade)

### [7. Herança](capitulos/07-heranca/README.md)

- [7.1 Visualizando a herança](capitulos/07-heranca/README.md#71-visualizando-a-herança)
- [7.2 Hierarquia de múltiplos níveis](capitulos/07-heranca/README.md#72-hierarquia-de-múltiplos-níveis)

### [8. Polimorfismo](capitulos/08-polimorfismo/README.md)

- [8.1 Visualizando o polimorfismo](capitulos/08-polimorfismo/README.md#81-visualizando-o-polimorfismo)

### [9. Classes Abstratas e Interfaces](capitulos/09-classes-abstratas-e-interfaces/README.md)

- [9.1 Classes Abstratas — quando usar](capitulos/09-classes-abstratas-e-interfaces/README.md#91-classes-abstratas--quando-usar)
- [9.2 Interfaces](capitulos/09-classes-abstratas-e-interfaces/README.md#92-interfaces)
- [9.3 Classe Abstrata vs Interface](capitulos/09-classes-abstratas-e-interfaces/README.md#93-classe-abstrata-vs-interface)

### [10. Coleções: ArrayList e HashSet](capitulos/10-colecoes-e-estruturas-de-dados/README.md)

- [10.1 Array vs ArrayList](capitulos/10-colecoes-e-estruturas-de-dados/README.md#101-array-vs-arraylist)
- [10.2 HashSet: conjuntos sem repetição](capitulos/10-colecoes-e-estruturas-de-dados/README.md#102-hashset-conjuntos-sem-repetição)

### [11. Mapas com HashMap](capitulos/11-mapas-com-hashmap/README.md)

- [11.1 HashMap: chave e valor](capitulos/11-mapas-com-hashmap/README.md#111-hashmap-chave-e-valor)
- [11.2 Outros métodos úteis do HashMap](capitulos/11-mapas-com-hashmap/README.md#112-outros-métodos-úteis-do-hashmap)
- [11.3 Padrão clássico: contar ocorrências](capitulos/11-mapas-com-hashmap/README.md#113-padrão-clássico-contar-ocorrências)
- [11.4 Mapa com lista como valor](capitulos/11-mapas-com-hashmap/README.md#114-mapa-com-lista-como-valor)
- [11.5 Variações do HashMap](capitulos/11-mapas-com-hashmap/README.md#115-variações-do-hashmap)
- [11.6 Qual coleção escolher?](capitulos/11-mapas-com-hashmap/README.md#116-qual-coleção-escolher)

### [12. Tratamento de Exceções](capitulos/12-tratamento-de-excecoes/README.md)

- [12.1 Hierarquia de exceções](capitulos/12-tratamento-de-excecoes/README.md#121-hierarquia-de-exceções)
- [12.2 Exceções personalizadas](capitulos/12-tratamento-de-excecoes/README.md#122-exceções-personalizadas)

### [13. Pacotes e Imports](capitulos/13-pacotes-e-imports/README.md)

- [13.1 O que é um pacote](capitulos/13-pacotes-e-imports/README.md#131-o-que-é-um-pacote)
- [13.2 Declarando e organizando pacotes](capitulos/13-pacotes-e-imports/README.md#132-declarando-e-organizando-pacotes)
- [13.3 Importando classes](capitulos/13-pacotes-e-imports/README.md#133-importando-classes)
- [13.4 Modificadores de acesso e pacotes](capitulos/13-pacotes-e-imports/README.md#134-modificadores-de-acesso-e-pacotes)
- [13.5 Compilando e executando um projeto com pacotes](capitulos/13-pacotes-e-imports/README.md#135-compilando-e-executando-um-projeto-com-pacotes)

### [14. Datas com java.time](capitulos/14-datas-com-java-time/README.md)

- [14.1 As principais classes](capitulos/14-datas-com-java-time/README.md#141-as-principais-classes)
- [14.2 LocalDate: trabalhando com datas](capitulos/14-datas-com-java-time/README.md#142-localdate-trabalhando-com-datas)
- [14.3 LocalTime e LocalDateTime](capitulos/14-datas-com-java-time/README.md#143-localtime-e-localdatetime)
- [14.4 Períodos e durações](capitulos/14-datas-com-java-time/README.md#144-períodos-e-durações)
- [14.5 Formatando e lendo datas](capitulos/14-datas-com-java-time/README.md#145-formatando-e-lendo-datas)
- [14.6 Datas dentro de objetos](capitulos/14-datas-com-java-time/README.md#146-datas-dentro-de-objetos)

### [15. Leitura e Escrita de Arquivos](capitulos/15-leitura-e-escrita-de-arquivos/README.md)

- [15.1 Path e Files](capitulos/15-leitura-e-escrita-de-arquivos/README.md#151-path-e-files)
- [15.2 Escrevendo arquivos](capitulos/15-leitura-e-escrita-de-arquivos/README.md#152-escrevendo-arquivos)
- [15.3 Lendo arquivos](capitulos/15-leitura-e-escrita-de-arquivos/README.md#153-lendo-arquivos)
- [15.4 Leitura segura com try-with-resources](capitulos/15-leitura-e-escrita-de-arquivos/README.md#154-leitura-segura-com-try-with-resources)
- [15.5 Persistindo objetos em CSV](capitulos/15-leitura-e-escrita-de-arquivos/README.md#155-persistindo-objetos-em-csv)
- [15.6 Codificação de caracteres (UTF-8)](capitulos/15-leitura-e-escrita-de-arquivos/README.md#156-codificação-de-caracteres-utf-8)

### [16. Projeto Final — Aplicação Completa](capitulos/16-projeto-final/README.md)


### [17. Boas Práticas e Convenções](capitulos/17-boas-praticas-e-convencoes/README.md)

- [17.1 Nomenclatura Java](capitulos/17-boas-praticas-e-convencoes/README.md#171-nomenclatura-java)
- [17.2 Boas práticas gerais](capitulos/17-boas-praticas-e-convencoes/README.md#172-boas-práticas-gerais)
- [17.3 Estrutura de um projeto Java profissional](capitulos/17-boas-praticas-e-convencoes/README.md#173-estrutura-de-um-projeto-java-profissional)

### [18. Erros Comuns e Como Resolver](capitulos/18-erros-comuns/README.md)


### [19. Glossário](capitulos/19-glossario/README.md)


### [Referências e Recursos](capitulos/referencias/README.md)

### [Códigos de exemplo](codigos/README.md)

---

<div align="center">

**Prof. Adriano França**
Instituto Federal de Pernambuco — IFPE
*Programação Orientada a Objetos — Java JDK 25 LTS*

*IDE: Visual Studio Code + Extension Pack for Java*

*Este material e livre para uso educacional.*

</div>
