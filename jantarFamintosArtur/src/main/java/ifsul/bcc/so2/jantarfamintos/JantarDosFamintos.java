package ifsul.bcc.so2.jantarfamintos;

public class JantarDosFamintos {
    public static void main(String[] args) {
        int numFamintos = 5;
        Garfo[] garfos = new Garfo[numFamintos];
        Faminto[] famintos = new Faminto[numFamintos];

        // Inicializa os garfos
        for (int i = 0; i < numFamintos; i++) {
            garfos[i] = new Garfo();
        }

        // Inicializa os famintos
        for (int i = 0; i < numFamintos; i++) {
            Garfo garfoEsquerdo = garfos[i];
            Garfo garfoDireito = garfos[(i + 1) % numFamintos];
            famintos[i] = new Faminto(i, garfoEsquerdo, garfoDireito);
            famintos[i].start();
        }
    }
}

