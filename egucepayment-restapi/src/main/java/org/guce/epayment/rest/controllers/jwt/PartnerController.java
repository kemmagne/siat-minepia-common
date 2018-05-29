package org.guce.epayment.rest.controllers.jwt;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.Partner;
import org.guce.epayment.core.entities.PartnerType;
import org.guce.epayment.core.entities.RepPartnerGroup;
import org.guce.epayment.core.entities.User;
import org.guce.epayment.core.services.ApplicationService;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.services.PartnerService;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.rest.controllers.utils.RestConstants;
import org.guce.epayment.rest.controllers.utils.RestUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.PartnerDto;
import org.guce.epayment.rest.dto.PartnerGroupDto;
import org.guce.epayment.rest.dto.PartnerTypeDto;
import org.guce.epayment.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author tadzotsa
 */
@Transactional
@RestController
@RequestMapping("jwt")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;
    @Autowired
    private CoreService coreService;
    @Autowired
    private ApplicationService appService;

    @ResponseBody
    @RequestMapping(path = "public/partners/all/{start}/{end}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findAllPartners(@PathVariable("start") int start,
            @PathVariable("end") int end) {

        final List<Partner> partners = coreService.findRange(Partner.class, start, end);

        return ResponseEntity.ok(partners.stream().map(
                partner -> RestUtils.getPartnerDto(partner, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "partners/children/{parentCode}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findChildren(@PathVariable String parentCode) {

        final Partner parent = coreService.findByUniqueKey(Constants.UK_CODE, parentCode, Partner.class).get();

        return ResponseEntity.ok(parent.getChildren().stream().map(
                partner -> RestUtils.getPartnerDto(partner, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "partners/types/all", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerTypeDto>> findAllPartnersTypes() {

        final List<PartnerType> allPartnerTypes = coreService.findAll(PartnerType.class);

        return ResponseEntity.ok(allPartnerTypes.stream().map(
                partnerType -> PartnerTypeDto.of(partnerType.getId(), partnerType.getCode(), partnerType.getLabel())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "partners/groups/all", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerGroupDto>> findAllPartnersGroups() {

        final List<RepPartnerGroup> allPartnerGroups = coreService.findAll(RepPartnerGroup.class);

        return ResponseEntity.ok(allPartnerGroups.stream().map(
                partnerGroup -> PartnerGroupDto.of(partnerGroup.getCode(), partnerGroup.getName())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/partners/by-types/{partnerTypes}/{start}/{end}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findPartnersByTypes(@PathVariable("partnerTypes") String partnerTypes,
            @PathVariable("start") int start, @PathVariable("end") int end) {

        final List<Partner> partnersByTypes = partnerService.findByTypes(partnerTypes, start, end);

        return ResponseEntity.ok(partnersByTypes.stream().map(
                partner -> RestUtils.getPartnerDto(partner, true)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "partners/by-groups", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findPartnersByGroups(@RequestHeader("login") String userLogin) {

        final User connectedUser = coreService.findByUniqueKey(Constants.UK_USER_LOGIN, userLogin, User.class).get();
        final Partner userPartner = connectedUser.getPartner();
        final String partnerGroups = StringUtils
                .collectionToDelimitedString(userPartner.getGroups().stream()
                        .map(pg -> pg.getCode()).collect(Collectors.toList()),
                        appService.getColSep());

        final List<Partner> partnersByGroups = partnerService.findByGroups(partnerGroups);

        return ResponseEntity.ok(partnersByGroups.stream().map(
                partner -> RestUtils.getPartnerDto(partner, false)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/partners/by-code/{partnerCode}/{taxPayerNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findByCode(@PathVariable("partnerCode") String partnerCode,
            @PathVariable("taxPayerNumber") String taxPayerNumber) {

        final List<Partner> partnersByGroups = partnerService.findByCodeOrTaxPayerNumber(partnerCode, taxPayerNumber);

        return ResponseEntity.ok(partnersByGroups.stream().map(
                partner -> RestUtils.getPartnerDto(partner, false)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "partners/beneficiaries/{invoiceTypeCode}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findBeneficiaries(@PathVariable String invoiceTypeCode) {

        final List<Partner> partnersByGroups = partnerService.findBeneficiaries(invoiceTypeCode);

        return ResponseEntity.ok(partnersByGroups.stream().map(
                partner -> RestUtils.getPartnerDto(partner, false)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/partners/principals/{start}/{end}", method = RequestMethod.GET)
    public ResponseEntity<List<PartnerDto>> findPrincipals(@PathVariable("start") int start,
            @PathVariable("end") int end) {

        final List<Partner> principals = partnerService.findPrincipals(start, end);

        return ResponseEntity.ok(principals.stream().map(
                partner -> RestUtils.getPartnerDto(partner, false)
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "public/partners/principals/count", method = RequestMethod.GET)
    public ResponseEntity<DefaultDto> countPrincipals() {
        return ResponseEntity.ok(DefaultDto.of(partnerService.countPrincipals() + ""));
    }

    @ResponseBody
    @RequestMapping(path = "admin/partners/users/{partnerId}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findPrincipals(@PathVariable BigDecimal partnerId) {

        final Partner partner = coreService.findById(partnerId, Partner.class).get();
        final List<User> partnerUsers = partner.getUsers();

        return ResponseEntity.ok(partnerUsers.stream().map(
                user -> RestUtils.getUserDto(user, Optional.empty())
        ).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(path = "admin/partners/{partnerId}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultDto> deletePartner(@PathVariable BigDecimal partnerId) {

        coreService.deleteById(partnerId, Partner.class, BigDecimal.class);

        return ResponseEntity.ok(RestConstants.DEFAULT_RESPONSE_BODY);
    }

    @ResponseBody
    @RequestMapping(path = "admin/partners", method = RequestMethod.POST)
    public ResponseEntity savePartner(@RequestBody PartnerDto partnerDto) {

        final BigDecimal partnerId = partnerDto.getId();

        final String taxPayerNumber = partnerDto.getTaxPayerNumber();
        if (null == partnerId && null != taxPayerNumber
                && coreService.findByUniqueKey(Constants.UK_TAX_PAYER_NUMBER, taxPayerNumber, Partner.class).isPresent()) {
            return ResponseEntity.ok(DefaultDto.of("0"));
        }

        final PartnerDto parentDto = partnerDto.getParent();
        String partnerCode = partnerDto.getCode();
        if (null != parentDto) {
            partnerCode = parentDto.getCode() + Constants.GLOBAL_SEPERATOR + partnerCode;
        }
        if (null == partnerId && null != partnerCode
                && coreService.findByUniqueKey(Constants.UK_CODE, partnerCode, Partner.class).isPresent()) {
            return ResponseEntity.ok(DefaultDto.of("0"));
        }

        final Partner partner = new Partner();

        partner.setId(partnerId);
        partner.setCode(partnerCode);
        partner.setTaxPayerNumber(taxPayerNumber);
        partner.setName(partnerDto.getName());
        if (null != parentDto) {
            partner.setParent(coreService.findById(parentDto.getId(), Partner.class).get());
        }

        partner.setTypes(partnerDto.getTypes().stream().map(
                pt -> coreService.findByUniqueKey(Constants.UK_CODE, pt.getCode(), PartnerType.class).get()
        ).collect(Collectors.toList()));

        partner.setGroups(partnerDto.getGroups().stream().map(
                pg -> coreService.findByUniqueKey(Constants.UK_CODE, pg.getCode(), RepPartnerGroup.class).get()
        ).collect(Collectors.toList()));

        coreService.save(partner, Partner.class);

        partnerDto.setId(partner.getId());

        return ResponseEntity.ok(partnerDto);
    }

}
