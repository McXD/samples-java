package net.corda.samples.supplychain.clients;

import net.corda.core.messaging.CordaRPCOps;
import net.corda.samples.supplychain.accountUtilities.CreateNewAccount;
import net.corda.samples.supplychain.accountUtilities.ShareAccountTo;
import net.corda.samples.supplychain.accountUtilities.ViewInboxByAccount;
import net.corda.samples.supplychain.clients.models.*;
import net.corda.samples.supplychain.flows.SendCargo;
import net.corda.samples.supplychain.flows.SendInvoice;
import net.corda.samples.supplychain.flows.SendPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class Controller implements ApplicationContextAware {
    private CordaRPCOps buyerProxy;
    private CordaRPCOps sellerProxy;
    private CordaRPCOps shipperProxy;
    Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping("createAcct")
    public Status createAcct(@RequestBody Account account){
        String onNode = account.getOnNode();
        Status status = new Status(true);

        logger.info("createAcct(Account) " + account.toString());

        switch (onNode) {
            case "buyer":
                buyerProxy.startFlowDynamic(CreateNewAccount.class, account.getAcctName());
                break;
            case "seller":
                sellerProxy.startFlowDynamic(CreateNewAccount.class, account.getAcctName());
                break;
            case "shipper":
                shipperProxy.startFlowDynamic(CreateNewAccount.class, account.getAcctName());
                break;
            default:
                status.setSuccess(false);
                logger.info("createAcct(Account) received unrecognizable node: " + onNode);
                break;
        }

        return status;
    }

    @PostMapping("shareAcct")
    public Status shareAcct(@RequestBody AccountShare accountShare){
        Status status = new Status(true);
        String receiver = accountShare.getReceiverNode();
        String sender = accountShare.getSenderNode();
        String acct = accountShare.getAcctName();

        CordaRPCOps senderNode = null, receiverNode = null;

        logger.info("shareAcct(AccountShare) " + accountShare.toString());

        switch (sender) {
            case "buyer":
                senderNode = this.buyerProxy;
                break;
            case "seller":
                senderNode = this.sellerProxy;
                break;
            case "shipper":
                senderNode = this.shipperProxy;
                break;
            default:
                status.setSuccess(false);
                logger.info("createAcct(Account) received unrecognizable node: " + sender);
                break;
        }

        switch (receiver) {
            case "buyer":
                receiverNode = this.buyerProxy;
                break;
            case "seller":
                receiverNode = this.sellerProxy;
                break;
            case "shipper":
                receiverNode = this.shipperProxy;
                break;
            default:
                status.setSuccess(false);
                logger.info("createAcct(Account) received unrecognizable node: " + receiver);
                break;
        }

        if (senderNode != null && receiverNode != null){
            String ret = null;
            try {
                ret = senderNode.startFlowDynamic(ShareAccountTo.class, acct, receiverNode.nodeInfo().getLegalIdentities().get(0)).getReturnValue().get();
            } catch (InterruptedException | ExecutionException e) {
                logger.info(e.getMessage());
            }
            logger.info(ret);
        }

        return status;
    }

    @PostMapping("/sendInvoice")
    public Status sendInvoice(@RequestBody Invoice invoice){
        Status status = new Status(true);

        try{
            sellerProxy.startFlowDynamic(SendInvoice.class, invoice.getWhoAmI(), invoice.getWhereTo(), invoice.getAmount());
        }catch(Exception e){
            logger.info(e.getMessage());
            status.setSuccess(false);
        }

        return status;
    }

    @PostMapping("/sendInternalMessage")
    public Status sendInternalMessage(@RequestBody InternalMessage message){
        Status status = new Status(true);

        CordaRPCOps node = findRPCOps(message.getOnNode());
        if (node == null) {
            status.setSuccess(false);
            return status;
        }

        try{
            String ret = node.startFlowDynamic(net.corda.samples.supplychain.flows.InternalMessage.class, message.getWhoAmI(), message.getWhereTo(), message.getMessage()).getReturnValue().get();
        }catch (Exception e){
            logger.info(e.getMessage());
            status.setSuccess(false);
        }

        return status;
    }

    @PostMapping("/sendPayment")
    public Status sendPayment(@RequestBody Payment payment){
        Status status = new Status(true);

        try{
            buyerProxy.startFlowDynamic(SendPayment.class, payment.getWhoAmI(), payment.getWhereTo(), payment.getAmount());
        }catch(Exception e){
            logger.info(e.getMessage());
            status.setSuccess(false);
        }

        return status;
    }

    @PostMapping("/sendShippingRequest")
    public Status sendShippingRequest(@RequestBody ShippingRequest shippingRequest){
        Status status = new Status(true);

        try{
            sellerProxy.startFlowDynamic(SendPayment.class, shippingRequest.getWhoAmI(), shippingRequest.getWhereTo(), shipperProxy.nodeInfo().getLegalIdentities().get(0), shippingRequest.getCargo());
        }catch(Exception e){
            logger.info(e.getMessage());
            status.setSuccess(false);
        }

        return status;
    }

    @PostMapping("/sendCargo")
    public Status sendCargo(@RequestBody Cargo cargo){
        Status status = new Status(true);

        try{
            String ret = shipperProxy.startFlowDynamic(SendCargo.class, cargo.getPickUpFrom(), cargo.getWhereTo(), cargo.getCargo()).getReturnValue().get();
            logger.info(ret);
        }catch(Exception e){
            logger.info(e.getMessage());
            status.setSuccess(false);
        }

        return status;
    }

    @GetMapping("/inbox")
    public Inbox getInbox(@RequestParam("node") String onNode, @RequestParam("acctName") String acctName){
        CordaRPCOps proxy = findRPCOps(onNode);
        if (proxy == null) return null;

        Inbox inbox = new Inbox();
        try{
            List<String> ret = proxy.startFlowDynamic(ViewInboxByAccount.class, acctName).getReturnValue().get();
            inbox.setMessage(ret);
            inbox.setFromNode(onNode);
            inbox.setOfAcct(acctName);
        }catch(Exception e){
            logger.info(e.getMessage());
            return null;
        }

        return inbox;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.buyerProxy = applicationContext.getBean("buyerProxy", CordaRPCOps.class);
        this.sellerProxy = applicationContext.getBean("sellerProxy", CordaRPCOps.class);
        this.shipperProxy = applicationContext.getBean("shipperProxy", CordaRPCOps.class);
    }

    private CordaRPCOps findRPCOps(String nodeName){
        CordaRPCOps ret = null;

        switch (nodeName) {
            case "buyer":
                ret = this.buyerProxy;
                break;
            case "seller":
                ret = this.sellerProxy;
                break;
            case "shipper":
                ret = this.shipperProxy;
                break;
            default:
                logger.info("Unrecognizable node: " + nodeName);
                break;
        }

        return ret;
    }
}
