package chat.core.server;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SocketManager
{
    private List<ServerSocket> sockets = new ArrayList<>();

    public void addSocket(ServerSocket socket)
    {
        sockets.add(socket);
    }

    public void closeAllSockets()
    {
        for (ServerSocket socket : sockets)
        {
            try
            {
                if (socket != null && !socket.isClosed())
                {
                    socket.close();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        sockets.clear();
    }
}
