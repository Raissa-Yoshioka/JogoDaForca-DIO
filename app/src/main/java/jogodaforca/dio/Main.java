package jogodaforca.dio;

import java.util.stream.Stream;

import jogodaforca.dio.models.JogoDaForca;
import jogodaforca.dio.models.JogoDaForcaCaractere;

public class Main {

    public static void main(String... args) {
        System.out.println("\n");        
        var letra = Stream.of(args)
                    .map(a -> a.toLowerCase().charAt(0))
                    .map(JogoDaForcaCaractere::new).toList();
        System.out.println(letra);
        System.out.println(new JogoDaForca(letra));
    }
}
