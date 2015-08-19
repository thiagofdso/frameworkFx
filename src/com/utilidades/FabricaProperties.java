package com.utilidades;

import com.modelos.EntidadeBase;
import com.modelos.properties.PropertyBase;
import com.modelos.properties.factory.PropertyFactory;
import java.util.ArrayList;
import java.util.List;

public class FabricaProperties <F extends PropertyFactory<P,T>,P extends PropertyBase,T extends EntidadeBase> {
    F factory;
    public FabricaProperties(F factory){
        this.factory = factory;
    }
    public P getProperty(T entidade) {
        if(factory == null)
            return null;
        if(entidade == null)
            return factory.createProperty();
        return factory.createProperty(entidade);
    }
    public List<P> getPropertyList(List<T> entidades){
        if(factory == null)
            return null;
        if(entidades == null)
            return new ArrayList<>();
        List<P> propriedades = new ArrayList<>();
        entidades.stream().forEach((T entidade) -> {
            propriedades.add(factory.createProperty(entidade));
        });
        return propriedades;
    }
}
