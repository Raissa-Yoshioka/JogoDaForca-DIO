package jogodaforca.dio.models;

import static jogodaforca.dio.models.JogoDaForcaStatus.DERROTA;
import static jogodaforca.dio.models.JogoDaForcaStatus.PENDENTE;
import static jogodaforca.dio.models.JogoDaForcaStatus.VITORIA;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jogodaforca.dio.exceptions.JogoFinalizadoException;

public class JogoDaForca {

    private final int TAMANHO_LINHA_INICIAL = 9;
    private final int TAMANHO_LINHA_INICIAL_COM_SEPARADOR = 10;

    private final int tamanhoDaLinha;
    private final int tamanhoInicialDaForca;
    private final List<JogoDaForcaCaractere> bonecoDaForca;
    private final List<JogoDaForcaCaractere> caracteres;
    private final List<Character> tentativasFalhas = new ArrayList<>();

    private String forca;
    private JogoDaForcaStatus statusDoJogo;

    public JogoDaForca(final List<JogoDaForcaCaractere> caracteres) {
        var espacoEmBranco = " ".repeat(caracteres.size());
        var caractereDeEspaco = "-".repeat(caracteres.size());
        this.statusDoJogo = PENDENTE;
        this.bonecoDaForca = construirPosicoesBonecoDaForca();
        this.tamanhoDaLinha = TAMANHO_LINHA_INICIAL_COM_SEPARADOR + espacoEmBranco.length();
        criarDesignForca(espacoEmBranco, caractereDeEspaco);
        this.caracteres = colocarEspacosDasLetrasNoJogo(caracteres, espacoEmBranco.length());
        this.tamanhoInicialDaForca = forca.length();
    }

    public void adicionarCaractere(final char caractere) {
        if (this.statusDoJogo != PENDENTE) {
            var mensagem = this.statusDoJogo == VITORIA ?
            "Parabéns, você ganhou!" : "Você perdeu, tente novamente";
            throw new JogoFinalizadoException(mensagem);
        }
        var found = this.caracteres.stream().filter(c -> c.getCaractere() == caractere).toList();
        if (found.isEmpty()) {
            tentativasFalhas.add(caractere);
            if (tentativasFalhas.size() >= 6) {
                this.statusDoJogo = DERROTA;
            }
            reconstruirForca(this.bonecoDaForca.removeFirst());
            return;
        }

        this.caracteres.forEach(c -> {
            if (c.getCaractere() == found.getFirst().getCaractere()) {
                c.habilitarVisibilidade();
            }
        });

        if (this.caracteres.stream().noneMatch(JogoDaForcaCaractere::isInvisivel)) {
            this.statusDoJogo = VITORIA;
        }
        reconstruirForca(found.toArray(JogoDaForcaCaractere[]::new));
    }

    @Override
    public String toString() {
        return forca;
    }

    private List<JogoDaForcaCaractere> construirPosicoesBonecoDaForca() {
        final var LINHA_DA_CABECA = 3;
        final var LINHA_DO_CORPO = 4;
        final var LINHA_DAS_PERNAS = 5;
        return new ArrayList<>(
            List.of(
                new JogoDaForcaCaractere('O', this.tamanhoDaLinha * LINHA_DA_CABECA + 6),
                new JogoDaForcaCaractere('|', this.tamanhoDaLinha * LINHA_DO_CORPO + 6),
                new JogoDaForcaCaractere('/', this.tamanhoDaLinha * LINHA_DO_CORPO + 5),
                new JogoDaForcaCaractere('\\', this.tamanhoDaLinha * LINHA_DO_CORPO + 7),
                new JogoDaForcaCaractere('/', this.tamanhoDaLinha * LINHA_DAS_PERNAS + 5),
                new JogoDaForcaCaractere('\\', this.tamanhoDaLinha * LINHA_DAS_PERNAS + 7)
            )
        );
    }

    private List<JogoDaForcaCaractere> colocarEspacosDasLetrasNoJogo(final List<JogoDaForcaCaractere> caracteres, final int QttEspacoEmBranco) {
        final var LINHA_DE_LETRAS = 6;

        for(int i = 0; i < caracteres.size(); i++) {
            caracteres.get(i).setPosicao(this.tamanhoDaLinha * LINHA_DE_LETRAS + TAMANHO_LINHA_INICIAL + i);
        }

        return caracteres;
    }

    private void reconstruirForca(final JogoDaForcaCaractere... caracteresForca) {
        var construtorForca = new StringBuilder(this.forca);
        Stream.of(caracteresForca).forEach(h -> construtorForca.setCharAt(h.getPosicao(), h.getCaractere()));

        var mensagemFalha = this.tentativasFalhas.isEmpty() ? "" : "Tentativas " + this.tentativasFalhas;
        this.forca = construtorForca.substring(0, tamanhoInicialDaForca) + mensagemFalha;
    }

    private void criarDesignForca(final String espacoEmBranco, final String caractereDeEspaco) {
        this.forca = "  _____  " + espacoEmBranco + System.lineSeparator() + 
                     "  |   |  " + espacoEmBranco + System.lineSeparator() +
                     "  |   |  " + espacoEmBranco + System.lineSeparator() +
                     "  |      " + espacoEmBranco + System.lineSeparator() +
                     "  |      " + espacoEmBranco + System.lineSeparator() +
                     "  |      " + espacoEmBranco + System.lineSeparator() +
                     "  |      " + espacoEmBranco + System.lineSeparator() +
                     "  |      " + espacoEmBranco + System.lineSeparator() +
                     "=========" + caractereDeEspaco + System.lineSeparator();
    }
}
