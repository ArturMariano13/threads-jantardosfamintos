package ifsul.bcc.so2.jantarfamintos;

public class Garfo {

    private boolean ocupado;

    public Garfo() {
        this.ocupado = false;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public synchronized void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized void pegar() throws InterruptedException {
        while (true) {
            if (!this.isOcupado()) {
                this.setOcupado(true);
                break;
            } else {
                wait();
            }
        }
    }

    public synchronized void soltar() {
        this.setOcupado(false);
        notifyAll();
    }
}
