package edu.ufp.inf.sd.rabbitmqservices.topics.producer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import edu.ufp.inf.sd.rabbitmqservices.util.RabbitUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogTopic {

    public static final String EXCHANGE_NAME = "exchange_topic_logs";

    /**
     * This producer sends logging messages organised by topics.
     * <p>
     * Let us assume that routing keys have 2 words: <facility>.<severity>.
     * <p>
     * For example,  to emit a log we could use:
     * $ runproducer "kern.critical" "some kern critical message" //to send "some critical kernel message"
     * $ runproducer "cron.info" "some cron info message" //to send "some info cron message"
     * <p>
     * (NB: CANNOT use wildchars, such as '*' or '#', when publishing messages)
     *
     * <p>
     * Another example, used on a chat service parameterized by <chat_room>.<msg_type>.
     * <p>
     * For example, to emit messages for a chat room we could use
     * $ runproducer room1.info "room1 info message" //for sending "some info message" to room1.info topic
     * $ runproducer room2.laugh "room1 laugh message" //for sending "some laugh message" to room2.laugh topic
     *
     * @param argv
     */
    public static void main(String[] argv) {

        if (argv.length < 4) {
            System.err.println("Usage: ReceiveLogsTopic [HOST] [PORT] [EXCHANGE] [RoutingKey] [Message]...");
            System.exit(1);
        }

        //try-with-resources
        try (Connection connection = RabbitUtils.newConnection2Server("localhost", "guest", "guest4rabbitmq");
             Channel channel = RabbitUtils.createChannel2Server(connection)) {

            //Bind exchange to channel
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            //Routing keys will have two words: <facility.severity> e.g. "kern.critical"
            String routingKey = RabbitUtils.getRouting(argv, 3);
            String message = RabbitUtils.getMessage(argv, 4);
            //Messages are not persisted (will be lost if no queue is bound to exchange yet)
            BasicProperties props = null;//=MessageProperties.PERSISTENT_TEXT_PLAIN

            //Publish message on exchange with routing keys
            channel.basicPublish(EXCHANGE_NAME, routingKey, props, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
