package com.example.controledeestoque_xml.binding;



import com.example.controledeestoque_xml.command.Command;
import com.example.controledeestoque_xml.viewmodel.Observable;

import java.util.function.Consumer;

public class DataBinder {

    public <T extends Observable> void bind(T observable, String propertyName, Consumer<Object> updateHandler){
          observable.addListener(propertyName,updateHandler);
    }

    public void bindCommand(Object eventFont, String eventName, Command command){

    }

}
