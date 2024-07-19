package ifsul.bcc.so2.jantarfamintos;

public class Faminto extends Thread {

    private int id;
    private Garfo garfoEsquerdo;
    private Garfo garfoDireito;
    private boolean tenhoDireito, tenhoEsquerdo;

    public Faminto(int id, Garfo garfoEsquerdo, Garfo garfoDireito) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.tenhoDireito = false;
        this.tenhoEsquerdo = false;
    }

    private synchronized void pensar() throws InterruptedException {
        System.out.println("Faminto " + id + " está pensando.");
        Thread.sleep((int) (Math.random() * 1000));
    }

    private synchronized void comer() throws InterruptedException {
        while (true) {
            if (this.tenhoDireito && this.tenhoEsquerdo) {
                System.out.println("Faminto " + id + " está comendo.");
                Thread.sleep((int) (Math.random() * 1000));
                garfoEsquerdo.soltar();
                garfoDireito.soltar();
                this.tenhoDireito = false;
                this.tenhoEsquerdo = false;
                break;
            } else if (!garfoDireito.isOcupado() || !garfoEsquerdo.isOcupado()) {
                if (!garfoDireito.isOcupado()) {
                    garfoDireito.pegar();
                    tenhoDireito = true;
                    if (!garfoEsquerdo.isOcupado()) {
                        garfoEsquerdo.pegar();
                        tenhoEsquerdo = true;
                    } else {
                        garfoDireito.soltar();
                        tenhoDireito = false;
                    }
                } else if (!garfoEsquerdo.isOcupado()) {
                    garfoEsquerdo.pegar();
                    tenhoEsquerdo = true;
                    if (!garfoDireito.isOcupado()) {
                        garfoDireito.pegar();
                        tenhoDireito = true;
                    } else {
                        garfoEsquerdo.soltar();
                        tenhoEsquerdo = false;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                comer();
                pensar();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public Garfo getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    public Garfo getGarfoDireito() {
        return garfoDireito;
    }
}
