package jogodaforca.dio.models;

import java.util.Objects;

public class JogoDaForcaCaractere {

    private final char caractere;
    private boolean ehVisivel;
    private int posicao;

    public JogoDaForcaCaractere(final char caractere) {
        this.caractere = caractere;
        this.ehVisivel = false;
    }

    public JogoDaForcaCaractere(char caractere, int posicao) {
        this.caractere = caractere;
        this.posicao = posicao;
        this.ehVisivel = true;
    }

    public char getCaractere() {
        return caractere;
    }

    public boolean isEhVisivel() {
        return ehVisivel;
    }

    public boolean isInvisivel() {
        return !ehVisivel;
    }

    public void habilitarVisibilidade() {
        ehVisivel = true;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caractere, ehVisivel, posicao);
    }

    @Override
    public boolean equals(final Object obj) {
        if(!(obj instanceof JogoDaForcaCaractere that)) return false;
        return caractere == that.caractere &&
                ehVisivel == that.ehVisivel &&
                posicao == that.posicao;
    }

    @Override
    public String toString() {
        return "JogoDaForcaCaractere {" +
                "caractere=" + caractere + 
                ", ehVisivel=" + ehVisivel + 
                ", posicao=" + posicao + "}";
    }
}
