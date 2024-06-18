package chat.chatBot;

import java.io.IOException;

public interface Processor
{
    String process(String inputMsg) throws IOException;
}
