package chat.WorkThogether.Server;

import chat.WorkThogether.Nachricht.ChangeMessage;

public class ChangeRequestsMemory
{
    private ClientProxy clientProxy;
    private ChangeMessage changeMessage;

    public ChangeRequestsMemory(ClientProxy clientProxy, ChangeMessage changeMessage) {
        this.clientProxy = clientProxy;
        this.changeMessage = changeMessage;
    }

    public ClientProxy getClientProxy() {
        return clientProxy;
    }

    public void setClientProxy(ClientProxy clientProxy) {
        this.clientProxy = clientProxy;
    }

    public ChangeMessage getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(ChangeMessage changeMessage) {
        this.changeMessage = changeMessage;
    }
}
