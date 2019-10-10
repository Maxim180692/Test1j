package Lesson_6;

import Lesson_6.Server.ClientHandler;

public class Test1 {

    // эта часть кода взята с класса ClientHandler на отправку личных сообщений
    /*
    я пробовал писать без гибкости кода, а просто обычные условия у меня ничего не получилось
    геттер я сделал как вы и сказали на уроке
     */
     */


    public String getNick() {
        return nick;
    }
    while (true) {
        String str = in.readUTF();
        if (str.startsWith("/")) {
            if (str.equalsIgnoreCase("/end")) {
                sendMsg("/clientClose");
                break;
            }
            if (str.equalsIgnoreCase("/w")) {
                String[] tokens = str.split(" ", 3);
                serv.privatMessage(ClientHandler.this, tokens[1], tokens[2]);
            }
        }else {
            serv.broadcastMsg(nick + ": " + str);
        }
        System.out.println("Client: " + str);
    }

    // эта часть кода взята с класса MainServ для отправки личных сообщений 

    public void privatMessage(ClientHandler fromClient, String clientRead, String message){
        for (ClientHandler o : clients){
            if (o.getNick().equals(clientRead)){
                o.sendMsg("from " + fromClient.getNick() + ": " + message);
                fromClient.sendMsg("to "+ clientRead + ": " + message );
                return;
            }
        }
        fromClient.sendMsg("Клиент " + clientRead +" не найден в чате !");
    }



}
