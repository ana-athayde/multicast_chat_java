/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Aninh
 */
public class ReadThread implements Runnable{
    private MulticastSocket socketMulticast;
    private InetAddress ipGroup;
    private int portGroup;

    public ReadThread(MulticastSocket socketMulticast, InetAddress ipGroup, int portGroup) {
        this.socketMulticast = socketMulticast;
        this.ipGroup = ipGroup;
        this.portGroup = portGroup;
    }
    
    @Override
    public void run(){
        //Enquanto o grupo multicast (Classee MulticastChat) não for finalizado
        while(!MulticastChat.finished){
            byte[] receivedData = new byte [65507];
            DatagramPacket receivedPacket = new DatagramPacket(receivedData,receivedData.length,ipGroup,portGroup);
            String mensagem;
            
            try{
                socketMulticast.receive(receivedPacket);
                mensagem = new String(receivedData, 0 , receivedPacket.getLength(),"UTF-8");
                if(!mensagem.startsWith(MulticastChat.nome)){
                    System.out.println(mensagem);
                }
            } catch (IOException e){
                System.out.println("O socket está fechado!");
            }
        }
    }
}
