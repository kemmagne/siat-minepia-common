package org.guce.siat.common.model;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ht
 */
@Entity
@Table(name = "STEP_AUTHORITY")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.step", joinColumns = @JoinColumn(name = "STEP_ID")),
    @AssociationOverride(name = "primaryKey.authorityGranted", joinColumns = @JoinColumn(name = "AUTHORITY_ID"))
})
public class StepAuthority implements Serializable {

    @EmbeddedId
    private StepAuthorityId primaryKey = new StepAuthorityId();

    public StepAuthorityId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(StepAuthorityId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Step getStep() {
        return primaryKey.getStep();
    }

    public void setStep(Step step) {
        primaryKey.setStep(step);
    }

    @Transient
    public Authority getAuthorityGranted() {
        return primaryKey.getAuthorityGranted();
    }

    public void setAuthorityGranted(Authority authorityGranted) {
        primaryKey.setAuthorityGranted(authorityGranted);
    }

}
