/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jantar_vorazes;

/**
 *
 * @author 20221PF.CC0015
 */
public class Garfo {
    private boolean ocupado;

    public Garfo() {
        this.ocupado = false;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    public synchronized void pegar() throws InterruptedException {
        
        if(!this.isOcupado()) {
            this.setOcupado(true);
        } else {
            while(this.ocupado){
                wait();
            }
            this.setOcupado(true);
        }
    }
    
    public synchronized void soltar() {
      
        this.setOcupado(false);
        notifyAll();

    
    }
}

