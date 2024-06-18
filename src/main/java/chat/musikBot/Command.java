package org.example;

public interface Command
{
    void execute(String argument);

    String getDescription();
}
