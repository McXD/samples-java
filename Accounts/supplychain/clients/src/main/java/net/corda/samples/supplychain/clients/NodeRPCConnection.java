package net.corda.samples.supplychain.clients;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NodeRPCConnection {
    @Value("${config.rpc.host}")
    private static String host;
    @Value("${config.rpc.username}")
    private static String username;
    @Value("${config.rpc.password}")
    private static String password;

    @Bean(name = "buyerProxy")
    public static CordaRPCOps buyerProxy(@Value("$config.rpc.buyer.port") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

    @Bean(name = "sellerProxy")
    public static CordaRPCOps sellerProxy(@Value("$config.rpc.seller.port") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

    @Bean(name = "shipperProxy")
    public static CordaRPCOps shipperProxy(@Value("$config.rpc.shipper.port") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

}
