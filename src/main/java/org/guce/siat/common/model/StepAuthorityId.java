package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ht
 */
@Embeddable
public class StepAuthorityId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEP_ID", referencedColumnName = "ID", updatable = false)
    private Step step;

    /**
     * The authority granted.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
    private Authority authorityGranted;

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public Authority getAuthorityGranted() {
        return authorityGranted;
    }

    public void setAuthorityGranted(Authority authorityGranted) {
        this.authorityGranted = authorityGranted;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.step);
        hash = 13 * hash + Objects.hashCode(this.authorityGranted);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StepAuthorityId other = (StepAuthorityId) obj;
        if (!Objects.equals(this.step, other.step)) {
            return false;
        }
        return Objects.equals(this.authorityGranted, other.authorityGranted);
    }

}
