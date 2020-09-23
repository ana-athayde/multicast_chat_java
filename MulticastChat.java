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
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author Aninh
 */
public class MulticastChat {

    private static final String TERMINATE = "Exit";
    static String nome;
    static volatile boolean finished = false;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        InetAddress ipGrupo = null; //Ip do multicast com o qual vai se comunicar
        MulticastSocket socketServidor = null; //Socket do servidor
        DatagramPacket sendPacket = null;
        
        if(args.length != 2){
            System.out.print("\nInvalid parameters!!\nUse MulticastChat <multicast-host> <server_port>");
            System.exit(1);
        }
        
        try {
            System.out.print("Starting MulticastChar.... configured to send data to "+ args[0]+" at por "+args[1]);
            
            ipGrupo = InetAddress.getByName(args[0]); //Pega o endereco ip do host multicast
            int portaServidor = Integer.parseInt(args[1]); //Passa a porta para int
            Scanner input = new Scanner(System.in); //Cria um scanner
            byte[] sendData = new byte [65507];
            
            System.out.print("\nDigite seu nome: ");
            nome = input.nextLine();
            
            socketServidor = new MulticastSocket(portaServidor);//Inicializa nosso socket Multicast e passa como argumento a porta do servidor Multicast
            socketServidor.setTimeToLive(0); //Seta um tempo de vide para  enviar mesagem para o socket Multicast
            
            socketServidor.joinGroup(ipGrupo);//Se conecta ao grupo multicast

            Thread thread = new Thread(new ReadThread(socketServidor,ipGrupo,portaServidor));//Cria um novo thread para receber as mensagens
            thread.start();
            
            System.out.print("Escreva suas mensagens...\n");
            while (true) {                
                String mensagem;
                mensagem = input.nextLine();
                
                //Se a mensagem for igual "Terminate" ele finaliza a comunicação 
                if(mensagem.equalsIgnoreCase(MulticastChat.TERMINATE)){
                    finished = true;
                    socketServidor.leaveGroup(ipGrupo);//Sai do grupo
                    socketServidor.close();//Fecha o socket de comunicação
                    break;
                }
                
                mensagem = nome + " : " + mensagem;
                sendData = mensagem.getBytes(); //Transforma a mensagem em bytes
                sendPacket = new DatagramPacket(sendData,sendData.length,ipGrupo,portaServidor);
                
                socketServidor.send(sendPacket);
            }
        } catch (SocketException se) {
            System.out.println("Erro ao criar o socket. ");
            se.printStackTrace();
        } catch (IOException ie){
            System.out.println("Erro ao ler ou escrever no socket. ");
            ie.printStackTrace();
        
        }
        
    }
    
}
