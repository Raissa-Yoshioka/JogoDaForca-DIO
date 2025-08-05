package jogodaforca.dio.models;

import static jogodaforca.dio.models.JogoDaForcaStatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import jogodaforca.dio.exceptions.JogoFinalizadoException;
import jogodaforca.dio.exceptions.LetraJaEscolhidaException;

public class JogoDaForca {

    private final static int TAMANHO_INICIAL_LINHA = 9;

    private final int tamanhoLinha;
    private final int tamanhoInicialForca;
    private final List<JogoDaForcaCaractere> bonecoForca;
    private final List<JogoDaForcaCaractere> caracteres;
    private final List<Character> tentativasFalhas = new ArrayList<>();

    private String forca;
    private JogoDaForcaStatus statusJogo;

    public JogoDaForcaStatus getStatusDoJogo() {
        return statusJogo;
    }

    public JogoDaForca(final List<JogoDaForcaCaractere> caracteres) {
        var espacoEmBranco = " ".repeat(caracteres.size());
        var caracteresDeEspaco = "-".repeat(caracteres.size());
        this.tamanhoLinha = TAMANHO_INICIAL_LINHA + espacoEmBranco.length() + System.lineSeparator().length();
        this.statusJogo = PENDENTE;
        this.bonecoForca = construirBonecoForca();
        construirForca(espacoEmBranco, caracteresDeEspaco);
        this.caracteres = colocarCaracteresEspacoNoJogo(caracteres);
        this.tamanhoInicialForca = forca.length();
    }

    public void adicionarCaractere(final char caractere) {
        if (this.statusJogo != PENDENTE) {
            var mensagem = this.statusJogo == VITORIA ?
                    "Parabéns! Você ganhou o jogo" :
                    "Você perdeu! Tente novamente";
            throw new JogoFinalizadoException(mensagem);
        }

        var found = this.caracteres.stream()
                .filter(c -> c.getCaractere() == caractere)
                .toList();

        if (this.tentativasFalhas.contains(caractere)) {
            throw new LetraJaEscolhidaException("A letra '" + caractere + "' já foi informada.");
        }

        if (found.isEmpty()){
            tentativasFalhas.add(caractere);
            if (tentativasFalhas.size() >= 6){
                this.statusJogo = DERROTA;
            }
            reconstruirForca(this.bonecoForca.removeFirst());
            return;
        }
        if (found.getFirst().isEhVisivel()){
            throw new LetraJaEscolhidaException("A letra '" + caractere + "' já foi informada.");
        }

        this.caracteres.forEach(c -> {
            if (c.getCaractere() == found.getFirst().getCaractere()) {
                c.habilitarVisibilidade();
            }
        });

        if (this.caracteres.stream().noneMatch(JogoDaForcaCaractere::isInvisivel)){
            this.statusJogo = VITORIA;
        }

        reconstruirForca(found.toArray(JogoDaForcaCaractere[]::new));
    }

    @Override
    public String toString() {
        return this.forca;
    }

    private void reconstruirForca(final JogoDaForcaCaractere... JogoDaForcaCaracteres){
        var construtorForca = new StringBuilder(this.forca);
        Stream.of(JogoDaForcaCaracteres).forEach(
                h -> construtorForca.setCharAt(h.getPosicao(), h.getCaractere()
                ));
        var mensagemFalha = this.tentativasFalhas.isEmpty() ? "" : " Tentativas: " + this.tentativasFalhas;
        this.forca = construtorForca.substring(0, tamanhoInicialForca) + mensagemFalha;
    }

    private List<JogoDaForcaCaractere> construirBonecoForca(){
        final var LINHA_DA_CABECA = 3;
        final var LINHA_DO_CORPO = 4;
        final var LINHA_DA_PERNA = 5;
        return new ArrayList<>(
                List.of(
                        new JogoDaForcaCaractere('O', this.tamanhoLinha * LINHA_DA_CABECA + 6),
                        new JogoDaForcaCaractere('|', this.tamanhoLinha * LINHA_DO_CORPO + 6),
                        new JogoDaForcaCaractere('/', this.tamanhoLinha * LINHA_DO_CORPO + 5),
                        new JogoDaForcaCaractere('\\', this.tamanhoLinha * LINHA_DO_CORPO + 7),
                        new JogoDaForcaCaractere('/', this.tamanhoLinha * LINHA_DA_PERNA + 5),
                        new JogoDaForcaCaractere('\\', this.tamanhoLinha * LINHA_DA_PERNA + 7)
                )
        );
    }

    private List<JogoDaForcaCaractere> colocarCaracteresEspacoNoJogo(final List<JogoDaForcaCaractere> caracteres){
        final var LINHA_DAS_LETRAS = 7;
        for (int i = 0; i < caracteres.size(); i++) {
            caracteres.get(i).setPosicao(this.tamanhoLinha * LINHA_DAS_LETRAS + TAMANHO_INICIAL_LINHA + i);
        }
        return caracteres;
    }

    private void construirForca(final String espacoEmBranco, final String caracteresDeEspaco){
        this.forca = "  -----  " + espacoEmBranco + System.lineSeparator() +
                       "  |   |  " + espacoEmBranco + System.lineSeparator() +
                       "  |   |  " + espacoEmBranco + System.lineSeparator() +
                       "  |      " + espacoEmBranco + System.lineSeparator() +
                       "  |      " + espacoEmBranco + System.lineSeparator() +
                       "  |      " + espacoEmBranco + System.lineSeparator() +
                       "  |      " + espacoEmBranco + System.lineSeparator() +
                       "=========" + caracteresDeEspaco + System.lineSeparator();
    }
}
