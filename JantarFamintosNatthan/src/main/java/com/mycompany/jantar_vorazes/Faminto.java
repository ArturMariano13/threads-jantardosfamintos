/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jantar_vorazes;

/**
 *
 * @author 20221PF.CC0015
 */
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
        System.out.println("Faminto " + id + " está comendo.");
        Thread.sleep((int) (Math.random() * 1000));
        System.out.println("Faminto " + id + " PAROU DE COMER.");
    }

    @Override
    public void run() {
        try {
            while (true) {
                
                if(this.tenhoEsquerdo && this.tenhoDireito) {
                    comer();
                    this.garfoEsquerdo.soltar();
                    this.garfoDireito.soltar();
                    this.tenhoEsquerdo = false;
                    this.tenhoDireito = false;
                    continue;
                } else {
                    pensar();
                }
                
                if(!this.garfoEsquerdo.isOcupado() && !this.garfoDireito.isOcupado()){
                    this.garfoEsquerdo.pegar();
                    this.tenhoEsquerdo = true;
                    if(!this.garfoDireito.isOcupado()) {
                        this.garfoDireito.pegar();
                        this.tenhoDireito = true;
                    } else {
                        //this.tenhoDireito = false;
                        this.garfoEsquerdo.soltar();
                        this.tenhoEsquerdo = false;
                        
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Garfo getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    public void setGarfoEsquerdo(Garfo garfoEsquerdo) {
        this.garfoEsquerdo = garfoEsquerdo;
    }

    public Garfo getGarfoDireito() {
        return garfoDireito;
    }

    public void setGarfoDireito(Garfo garfoDireito) {
        this.garfoDireito = garfoDireito;
    }

    public boolean isTenhoDireito() {
        return tenhoDireito;
    }

    public void setTenhoDireito(boolean tenhoDireito) {
        this.tenhoDireito = tenhoDireito;
    }

    public boolean isTenhoEsquerdo() {
        return tenhoEsquerdo;
    }

    public void setTenhoEsquerdo(boolean tenhoEsquerdo) {
        this.tenhoEsquerdo = tenhoEsquerdo;
    }


}