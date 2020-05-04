package edu.ufp.inf.sd.rabbitmqservices.pubsub.chatgui;

import com.rabbitmq.client.*;
import edu.ufp.inf.sd.rabbitmqservices.pubsub.producer.EmitLog;
import edu.ufp.inf.sd.rabbitmqservices.util.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static edu.ufp.inf.sd.rabbitmqservices.pubsub.producer.EmitLog.EXCHANGE_NAME;


public class Observer {

    Connection connection;
    Channel channel;
    final String exchangename = EXCHANGE_NAME;
    final String exchangeRoutingkey = "";
    final String exchangeFormate = "UTF-8";
    String message;

    BuiltinExchangeType exchangeType = BuiltinExchangeType.FANOUT;
    ObserverGuiClient guiClient;

    public Observer(ObserverGuiClient guiClient, String host, String user, String password) throws IOException, TimeoutException {
        this.guiClient = guiClient;
        this.connection = RabbitUtils.newConnection2Server("localhost", "guest", "guest");
        this.channel = RabbitUtils.createChannel2Server(connection);
        bindExchangeToChannelRabbitMq(this.channel, exchangename, exchangeType);
        attachConsumerToChannelExchangeWithKey(this.channel, exchangename, exchangeRoutingkey);
    }

    private void attachConsumerToChannelExchangeWithKey(Channel channel, String exchangename, String exchangeRoutingkey) throws IOException {
        String queueName = channel.queueDeclare().getQueue();
        String routingKey = "";
        channel.queueBind(queueName, EmitLog.EXCHANGE_NAME, routingKey);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Consumer Tag [" + consumerTag + "] - Received '" + message + "'");
        };
        CancelCallback cancelCalback = (consumerTag) -> {
            System.out.println(" [x] Consumer Tag [" + consumerTag + "] - Cancel Callback invoked!");
        };
        channel.basicConsume(queueName, true, deliverCallback, cancelCalback);
    }

    private void bindExchangeToChannelRabbitMq(Channel channel, String exchangename, BuiltinExchangeType exchangeType) throws IOException {
        channel.exchangeDeclare(exchangename, exchangeType);
    }

    public void sendMenssage(String msgToSend, String routingKey, BasicProperties ptop) throws IOException {
        channel.basicPublish(this.exchangename, routingKey, null, msgToSend.getBytes("UTF-8"));

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
