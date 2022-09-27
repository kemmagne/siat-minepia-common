package org.guce.siat.common.service;

import java.util.List;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.utils.enums.BureauType;

/**
 * The Interface BureauService.
 */
public interface BureauService extends AbstractService<Bureau> {

    /**
     * Find bureau by type and organism.
     *
     * @param type the type
     * @param organism the organism
     * @return the list
     */
    List<Bureau> findBureauByTypeAndOrganism(BureauType type, Organism organism);
    
    List<Bureau> findBureauByOrganism(final Organism organism); 
}
