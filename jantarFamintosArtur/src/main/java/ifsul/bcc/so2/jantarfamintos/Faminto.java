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
        Thread.sleep(2000);
    }

    private synchronized void comer() throws InterruptedException {
        while (true) {
            if (this.tenhoDireito && this.tenhoEsquerdo) {
                System.out.println("Faminto " + id + " está comendo.");
                Thread.sleep(2000);
                System.out.println("Faminto " + id + " terminou de comer.");
                garfoEsquerdo.soltar();
                garfoDireito.soltar();
                this.tenhoDireito = false;
                this.tenhoEsquerdo = false;
                
                break;
            } 
            
            // filósofos com id par tentam pegar o garfo esquerdo primeiro
            if (id % 2 == 0){
                if (!garfoEsquerdo.isOcupado()) {
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
                // ímpares tentam o direito primeiro
            } else {
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
