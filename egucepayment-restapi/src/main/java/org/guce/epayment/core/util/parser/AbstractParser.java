package org.guce.epayment.core.util.parser;

public abstract class AbstractParser<T> implements Parser<T> {

    protected final Class<T> documentClass;

    public AbstractParser(Class<T> entityClass) {
        this.documentClass = entityClass;
    }

}
