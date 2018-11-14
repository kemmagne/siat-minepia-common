package org.guce.epayment.rest.controllers.jwt;

import java.util.List;
import org.guce.epayment.core.entities.ReceiptInterval;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.rest.dto.ReceiptIntervalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("jwt/admin/receipt-interval")
public class ReceiptIntervalController {

    @Autowired
    private CoreService coreService;

    @ResponseBody
    @RequestMapping(path = "all", method = RequestMethod.GET)
    ResponseEntity<List<ReceiptIntervalDto>> findAll() {

        final List<ReceiptInterval> allSittingDates = coreService.findAll(ReceiptInterval.class);

//        return ResponseEntity.ok(allSittingDates.stream().map(
//                sittingDate -> SittingDateDto.of(sittingDate.getId(),
//                        sittingDate.getInsertionDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)),
//                        sittingDate.getMinTime().format(DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR)),
//                        sittingDate.getMaxTime().format(DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR))
//                )).collect(Collectors.toList()));
        return null;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity save(@RequestBody ReceiptIntervalDto sittingDateDto) {

//        final ReceiptInterval sittingDate = new ReceiptInterval();
//        final LocalTime minTime = LocalTime
//                .parse(sittingDateDto.getMinTime(), DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR));
//        final LocalTime maxTime = LocalTime
//                .parse(sittingDateDto.getMaxTime(), DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR));
//
//        if (minTime.isAfter(maxTime)) {
//            return ResponseEntity.ok(DefaultDto.of("0"));
//        }
//        sittingDate.setMinTime(minTime);
//        sittingDate.setMaxTime(maxTime);
//
//        coreService.save(sittingDate, ReceiptInterval.class);
//
//        sittingDateDto.setId(sittingDate.getId());
//        sittingDateDto.setInsertionDate(sittingDate.getInsertionDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)));
//
//        return ResponseEntity.ok(sittingDateDto);
        return null;
    }

}

