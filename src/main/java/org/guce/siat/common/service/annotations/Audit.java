package org.guce.siat.common.service.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.guce.siat.common.utils.enums.AuditConstants;

/**
 * The Interface Audit.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Audit {

    /**
     * Operation type.
     *
     * @return the string
     */
    AuditConstants operationType();

}
