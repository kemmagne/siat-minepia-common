package org.guce.epayment.rest.controllers.jwt;

import java.math.BigDecimal;
import java.util.List;
import org.guce.epayment.core.entities.BankAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.BankAccountService;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.dto.BankAccountDto;
import org.guce.epayment.rest.dto.DefaultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt")
public class BankController {

    @Autowired
    private CoreService coreService;
    @Autowired
    private BankAccountService accountService;

    @ResponseBody
    @RequestMapping(path = "cashier/banks/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<BankAccountDto>> getBankAccounts(@RequestHeader("login") String userLogin) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
        final Partner userPartner = connectedUser.getPartner();
        final Partner parentPartner = userPartner.getParent();
        final List<Partner> children = null != parentPartner ? parentPartner.getChildren() : userPartner.getChildren();

//        return ResponseEntity.ok(children.stream().map(child -> {
//
//            final BankAccountDto accountDto = new BankAccountDto();
//
//            accountDto.setId(Constants.BIG_DECIMAL_MINUS_ONE);
//            // bank
//            accountDto.setBankCode(null != parentPartner ? parentPartner.getCode() : userPartner.getCode());
//            accountDto.setBankLabel(null != parentPartner ? parentPartner.getName() : userPartner.getName());
//            // agency
//            final String agencyCode = child.getCode();
//            accountDto.setAgencyCode(agencyCode.split(Constants.GLOBAL_SEPERATOR)[1]);
//            accountDto.setAgencyLabel(child.getName());
//
//            return accountDto;
//        }).collect(Collectors.toList()));
        return null;
    }

    @ResponseBody
    @RequestMapping(path = "banks/accounts/{accountId}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultDto> deleteBankAccount(@PathVariable BigDecimal accountId) {

        coreService.deleteById(accountId, BankAccount.class, BigDecimal.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "banks/accounts/by-owner/{ownerCode}", method = RequestMethod.GET)
    public ResponseEntity<List<BankAccountDto>> getBankAccountsByOwner(@RequestHeader("login") String userLogin,
            @PathVariable String ownerCode) {

//        final Partner partner;
//
//        if (ownerCode == null || "null".equalsIgnoreCase(ownerCode)) {
//
//            final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
//            partner = connectedUser.getPartner();
//        } else {
//            partner = coreService.findByUniqueKey("code", ownerCode, Partner.class).get();
//        }
//
//        final List<BankAccount> partnerBankAccounts = accountService.findByOwner(partner.getCode());
//
//        return ResponseEntity.ok(partnerBankAccounts.stream().map(account
//                -> RestUtils.getBankAccountDto(account)
//        ).collect(Collectors.toList()));
        return null;
    }

    @ResponseBody
    @RequestMapping(path = "banks/accounts/by-owner/{ownerCode}", method = RequestMethod.POST)
    public ResponseEntity saveBankAccount(@PathVariable String ownerCode, @RequestBody BankAccountDto accountDto) {

//        final BankAccount account = coreService.findById(accountDto.getId(), BankAccount.class).orElse(new BankAccount());
//
//        account.setOwner(coreService.findByUniqueKey("code", ownerCode, Partner.class).get());
//
//        final String bankCode = accountDto.getBankCode();
//        final Partner bank = coreService.findByUniqueKey("code", bankCode, Partner.class).orElse(null);
//
//        if (bank == null) {
//            return ResponseEntity.ok(DefaultDto.of("0"));
//        }
//        account.setBank(bank);
//
//        final String agencyCode = bankCode + Constants.GLOBAL_SEPERATOR + accountDto.getAgencyCode();
//        final Partner agency = coreService.findByUniqueKey("code", agencyCode, Partner.class).orElse(null);
//
//        if (agency == null) {
//            return ResponseEntity.ok(DefaultDto.of("1"));
//        }
//        account.setAgency(agency);
//
//        account.setNumber(accountDto.getAccountNumber());
//        account.setKey(accountDto.getAccountKey());
//        account.setLabel(accountDto.getAccountLabel());
//
//        coreService.save(accountDto, BankAccount.class);
        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

}

