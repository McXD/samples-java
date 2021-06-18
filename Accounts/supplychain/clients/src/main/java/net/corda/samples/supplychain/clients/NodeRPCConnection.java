package net.corda.samples.supplychain.clients;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class NodeRPCConnection {
    @Value("${config.rpc.host}")
    private String host;
    @Value("${config.rpc.username}")
    private String username;
    @Value("${config.rpc.password}")
    private String password;

    @Bean(name = "buyerProxy")
    public CordaRPCOps buyerProxy(@Value("${config.rpc.buyer.port}") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

    @Bean(name = "sellerProxy")
    public CordaRPCOps sellerProxy(@Value("${config.rpc.seller.port}") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

    @Bean(name = "shipperProxy")
    public CordaRPCOps shipperProxy(@Value("${config.rpc.shipper.port}") int port){
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, port);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        CordaRPCConnection connection = rpcClient.start(username, password);
        return connection.getProxy();
    }

}
