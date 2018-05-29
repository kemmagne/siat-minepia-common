package org.guce.epayment.transfer.dao;

import java.util.List;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.transfer.entities.TransferOrder;
import org.guce.epayment.transfer.models.FilterTransferOrder;

/**
 *
 * @author tadzotsa
 */
public interface TransferOrderDao {

    List<TransferOrder> findPartnerTransferOrders(User connectedUser, int start, int end, boolean count);

    List<TransferOrder> findPartnerTransferOrders(User connectedUser, boolean toValidate, int start, int end, boolean count);

    Object filterTransferOrders(FilterTransferOrder filter, String code);

    Object findTransferOrdersPeriodically(int type, int period, String code, int start, int end, boolean count);

    List<TransferOrder> findLastTransferOrders(int type, String code, int number);

    Object findByTosUser(String userLogin, int start, int end, boolean count);

    List getTosStats(int type, boolean bank);

    Object getAcknowledTransferOrders(String benefCode, int start, int end, boolean count);

}
