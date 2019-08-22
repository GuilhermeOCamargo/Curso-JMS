package br.com.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Scanner;

public class ConsumerTest {
    public static void main(String[] args) throws Exception{

        InitialContext context = new InitialContext();

        //Opening connections
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        //Consumer
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("financeiro");
        MessageConsumer consumer = session.createConsumer(fila);
        Message message = consumer.receive();
        System.out.println("Receiving message: " + message);

        new Scanner(System.in).nextLine(); //parar o programa para testar a conexao

        //Closing Connections
        session.close();
        connection.close();
        context.close();
    }
}
