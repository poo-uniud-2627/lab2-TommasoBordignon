// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

/** Una racchetta: sta a un'altezza fissa in orizzontale e si muove solo in verticale. */
public class Racchetta {
    static final int LARGHEZZA = 12;
    static final int ALTEZZA = 80;
    /** Quanti pixel si sposta la racchetta a ogni fotogramma, quando si muove. */
    static final int VELOCITA = 6;

    private int x;
    private double y;
    private final Bordo bordo;
    private final int larghezza;
    private final int altezza;

    Racchetta(Bordo bordo, Campo campo) {
        if (bordo == null || campo == null) {
            throw new IllegalArgumentException("bordo e campo non possono essere null");
        }
        this.bordo = bordo;
        if (bordo.orizzontale()) {
            larghezza = ALTEZZA;
            altezza = LARGHEZZA;
            x = (campo.getLarghezza() - larghezza) / 2;
            y = bordo == Bordo.ALTO ? 30 : campo.getAltezza() - 30 - altezza;
        } else {
            larghezza = LARGHEZZA;
            altezza = ALTEZZA;
            x = bordo == Bordo.SINISTRA
                    ? 30 : campo.getLarghezza() - 30 - larghezza;
            y = (campo.getAltezza() - altezza) / 2.0;
        }
    }

    // ---- quello che chiunque può chiedere alla racchetta

    public int getX() { return x; }
    /** Il bordo alto della racchetta (le y crescono verso il basso). */
    public double getY() { return y; }
    public double getCentroY() { return y + altezza / 2.0; }
    public int getLarghezza() { return larghezza; }
    public int getAltezza() { return altezza; }
    public Bordo getBordo() { return bordo; }

    // ---- quello che può chiedere solo il codice del pacchetto pong (la Partita)

    /**
     * Esegue una mossa.
     * Pretende: campo non è null ed è alto almeno quanto la racchetta; la racchetta è dentro il campo.
     * Promette: con SU sale di VELOCITA pixel, con GIU scende, con FERMO (o null) resta dov'è;
     *           alla fine sta tutta dentro il campo.
     */
    void muovi(Mossa mossa, Campo campo) {
        if (bordo.orizzontale()) {
            if (mossa == Mossa.SU) x -= VELOCITA;
            if (mossa == Mossa.GIU) x += VELOCITA;
            if (x < 0) x = 0;
            if (x > campo.getLarghezza() - larghezza) {
                x = campo.getLarghezza() - larghezza;
            }
        } else {
            if (mossa == Mossa.SU) y -= VELOCITA;
            if (mossa == Mossa.GIU) y += VELOCITA;
            if (y < 0) y = 0;
            if (y > campo.getAltezza() - altezza) {
                y = campo.getAltezza() - altezza;
            }
        }
    }
}
