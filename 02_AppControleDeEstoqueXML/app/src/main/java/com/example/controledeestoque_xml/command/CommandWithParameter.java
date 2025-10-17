package com.example.controledeestoque_xml.command;
@FunctionalInterface
public interface CommandWithParameter <T>{
    void execute(T parameter);
}
