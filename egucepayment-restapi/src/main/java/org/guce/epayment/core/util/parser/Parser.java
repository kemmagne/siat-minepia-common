package org.guce.epayment.core.util.parser;

public interface Parser<T> {

    T parse(String string);

}
