package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.campost.entities.CampostAccount;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.CampostAccountDto;
import org.guce.epayment.rest.dto.DefaultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("jwt/admin/campost/accounts")
public class CampostAccountController {

    @Autowired
    private CoreService coreService;

    @ResponseBody
    @RequestMapping(path = "all", method = RequestMethod.GET)
    public ResponseEntity<List<CampostAccountDto>> findAllAccounts() {

        final List<CampostAccount> allAccounts = coreService.findAll(CampostAccount.class);

        return ResponseEntity.ok(allAccounts.stream().map(
                account -> getCampostAccountDto(account)
        ).collect(Collectors.toList()));
    }

    private static CampostAccountDto getCampostAccountDto(final CampostAccount account) {

        final CampostAccountDto accountDto = new CampostAccountDto();

        accountDto.setId(account.getId());
        accountDto.setBeneficiary(RestUtils.getPartnerDto(account.getBeneficiary(), false));
        accountDto.setInitVector(account.getInitVector());
        accountDto.setIzenid(account.getIzenid());
        accountDto.setMerchantid(account.getMerchantid());
        accountDto.setSecretKey(account.getSecretKey());

        return accountDto;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CampostAccountDto> saveAccount(@RequestBody CampostAccountDto accountDto) {

        final CampostAccount account = new CampostAccount();

        account.setId(accountDto.getId());
        account.setBeneficiary(coreService.findById(accountDto.getBeneficiary().getId(), Partner.class).get());
        account.setInitVector(accountDto.getInitVector());
        account.setIzenid(accountDto.getIzenid());
        account.setMerchantid(accountDto.getMerchantid());
        account.setSecretKey(accountDto.getSecretKey());

        coreService.save(account, CampostAccount.class);

        return ResponseEntity.ok(getCampostAccountDto(account));
    }

    @ResponseBody
    @RequestMapping(path = "{accountId}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultDto> deleteAccount(@PathVariable Integer accountId) {

        coreService.deleteById(accountId, CampostAccount.class, Integer.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

}
