package jogodaforca.dio;

import java.util.Scanner;
import java.util.stream.Stream;

import jogodaforca.dio.exceptions.JogoFinalizadoException;
import jogodaforca.dio.exceptions.LetraJaEscolhidaException;
import jogodaforca.dio.models.JogoDaForca;
import jogodaforca.dio.models.JogoDaForcaCaractere;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\n");        
        var letra = Stream.of(args)
                    .map(a -> a.toLowerCase().charAt(0))
                    .map(JogoDaForcaCaractere::new).toList();
        var jogoDaForca = new JogoDaForca(letra);
        System.out.println("Bem-vindo ao Jogo Da Forca, tente adivinhar a palavra, boa sorte.");
        var option = -1;
        do {
            System.out.println(jogoDaForca);
            System.out.println("Selecione uma das opções:");
            System.out.println("1 - Verificar uma letra.");
            System.out.println("2 - Verificar o status do jogo.");
            System.out.println("3 - Sair do jogo");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> addCaractere(jogoDaForca);
                case 2 -> System.out.println(jogoDaForca.getStatusDoJogo());
                case 3 -> System.exit(0);
                default-> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static void addCaractere(JogoDaForca jogoDaForca) {
        System.out.println("Informe uma letra");
        var caractere = scanner.next().toLowerCase().charAt(0);
        try {
            jogoDaForca.adicionarCaractere(caractere);
        } catch (LetraJaEscolhidaException ex) {
            System.out.println(ex.getMessage());
        } catch (JogoFinalizadoException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
