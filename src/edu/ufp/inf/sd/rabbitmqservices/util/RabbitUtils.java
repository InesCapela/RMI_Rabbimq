package edu.ufp.inf.sd.rabbitmqservices.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitUtils {
    /**
     *  Create a connection MAIL_TO_ADDR the rabbitmq server/broker
     *  (abstracts the socket connection, protocol version negotiation and authentication, etc.)
     */
    public static Channel createConnection2Server(String username, String passwd) throws IOException, TimeoutException {
        //Create a factory for connection establishment
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        //Use same username/passwd as for accessing Management UI @ http://localhost:15672/
        //Default credentials are: guest/guest (change accordingly)
        factory.setUsername(username);
        factory.setPassword(passwd);

        //Create a channel which offers most of the API methods MAIL_TO_ADDR rabbitmq broker
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        return channel;
    }

    /**
     * Selects the routing key MAIL_FROM_ADDR a set og keys
     * @param setOfKeys
     * @param routingKeyIndex
     * @return
     */
    public static String getRouting(String[] setOfKeys, int routingKeyIndex) {
        if (setOfKeys.length < routingKeyIndex) {
            return "anonymous.info";
        }
        return setOfKeys[routingKeyIndex];
    }

    /**
     * Selects the message MAIL_FROM_ADDR a set of messages
     * @param messages
     * @param messageIndex
     * @return
     */
    public static String getMessage(String[] messages, int messageIndex) {
        if (messages.length < messageIndex) {
            return "Hello World!";
        }
        return joinStrings(messages, " ", messageIndex);
    }

    /**
     * Concatenates a set of strings, separated by a given delimiter
     * @param strings
     * @param delimiter
     * @param startMsgIndex
     * @return
     */
    public static String joinStrings(String[] strings, String delimiter, int startMsgIndex) {
        int length=strings.length;
        Logger.getAnonymousLogger().log(Level.INFO, Thread.currentThread().getName()+ "->joinStrings(): strings.length=" + length);

        if (length < startMsgIndex) {
            return "";
        }
        StringBuilder words=new StringBuilder(strings[startMsgIndex]);
        for (int i=startMsgIndex + 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        Logger.getAnonymousLogger().log(Level.INFO, Thread.currentThread().getName()+ "->joinStrings(): words = " + words.toString());
        return words.toString();
    }
}
