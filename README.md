# Jogo Da Forca - DIO
## Desafio de Projeto 2 do bootcamp NTT DATA da DIO

![Java](https://img.shields.io/badge/Java-21-royalblue?style=for-the-badge&logo=openjdk)
![Gradle](https://img.shields.io/badge/Gradle-8.8-green?style=for-the-badge&logo=gradle)

O projeto consiste na criação de um jogo da forca utilizando Java como linguagem de programação, com foco no uso de conceitos de Programação Orientada a Objetos (POO). 

     ------------------------------------------
      Bem-vindo ao Jogo Da Forca, tente adivinhar a palavra, boa sorte.
        _____
        |   |
        |   |
        |
        |
        |
        |
      ========= -----
    
      Selecione uma das opções:
      1 - Verificar uma letra.
      2 - Verificar o status do jogo.
      3 - Sair do jogo
     
---
 ## Organização do Projeto
 
Desenvolvido em ambiente console, o jogo inclui a estrutura completa de um jogo tradicional da forca, desde a definição da palavra oculta até a montagem gráfica simplificada da forca e da interação com o usuário por meio de menus e entradas de dados.

O projeto está separado da seguinte forma:

- `Main.java`: arquivo que lida com o menu da aplicação e processamento das entradas do usuário.
    
- Pasta `exceptions`:  
    * Classes que lidam com exceções específicas para diferentes cenários, como por exemplo `LetraJaEscolhidaException`, que lida com o caso de uma letra já ter sido informada anteriormente.

- Pasta `models`:
    * Classes que contém os modelos de domínio principais da aplicação, como por exemplo `JogoDaForca`, que apresenta a lógica para o funcionamento do jogo.

---

## Execução do Projeto

### Pré-requisitos
- JDK 21 ou superior

### Passos Iniciais
  1. Clone o repositório:
     ```sh
     https://github.com/Raissa-Yoshioka/JogoDaForca-DIO.git
     ```
  2. Há duas opções para começar o jogo:
     
     2.1. Pela linha de comando:
       ```sh
       # 1 - Primeiro é preciso compilar todos os arquivos do projeto
       javac -d out --source-path app/src/main/java app/src/main/java/jogodaforca/dio/Main.java

       # 2 - Executar o arquivo Main.java com a palavra secreta, separando as letras por espaços
       java -cp out jogodaforca.dio.Main t e s t e
       ```

     2.2. Pela IDE
       - Por IntelliJ IDEA ou Eclipse:
           1. Abra o projeto na sua IDE e localize o arquivo `Main.java`.
           2. Edite as configurações de execução da classe principal `Main`.
           3. No campo _Program arguments_, inclua a palavra secreta, separando as letras por espaços como em `t e s t e`. 
           4. Execute o arquivo `Main.java`.
        
      - Pelo VS Code:
          1. Abra o projeto na IDE e localize o arquivo `launch.json`, encontrado na pasta `.vscode`.
          2. No arquivo `launch.json` encontre a linha de código contendo `"args"` e o modifique com sua palavra secreta de preferência, separando cada letra como em `["t", "e", "s", "t", "e"]`.
          3. Execute o arquivo `Main.java`.

  3. Executando o código, um menu será apresentado com as opções:
     * _Verificar uma letra_, para incluir uma tentativa em busca de descobrir a palavra secreta, mas o jogador tem apenas 6 tentativas, ou então perde o jogo;
     *  _Verificar o status do jogo_, que apresentará o status atual, entre `PENDENTE`, `VITORIA` ou `DERROTA`; e
     *  _Sair do jogo_, que encerrará a execução do programa.
     
  4.  E pronto! Você pode testar as operações e funcionamento da aplicação. Bom jogo!
