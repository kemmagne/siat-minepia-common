package org.guce.epayment.core.repositories;

import org.guce.epayment.core.entities.InvoiceTypeAsset;
import org.guce.epayment.core.entities.InvoiceTypeAsset.InvoiceTypeAssetID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tadzotsa
 */
public interface InvoiceTypeAssetRepository extends JpaRepository<InvoiceTypeAsset, InvoiceTypeAssetID> {

}
