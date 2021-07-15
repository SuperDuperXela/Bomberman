package viewcontroller;

public interface ObserverIf {

    /**
     * Ruft {@code zeichenfeld.repaint();} auf.
     * 
     * @author Alex
     */
    void aktualisieren();

    void startCountdown();
}