package org.guce.epayment.rest.controllers.jwt;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.guce.epayment.core.entities.SittingDate;
import org.guce.epayment.core.services.CoreService;
import org.guce.epayment.core.utils.DateUtils;
import org.guce.epayment.rest.dto.DefaultDto;
import org.guce.epayment.rest.dto.SittingDateDto;
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
@RequestMapping("jwt/admin/sitting/date")
public class SittingDateController {

    @Autowired
    private CoreService coreService;

    @ResponseBody
    @RequestMapping(path = "all", method = RequestMethod.GET)
    ResponseEntity<List<SittingDateDto>> findAll() {

        final List<SittingDate> allSittingDates = coreService.findAll(SittingDate.class);

        return ResponseEntity.ok(allSittingDates.stream().map(
                sittingDate -> SittingDateDto.of(sittingDate.getId(),
                        sittingDate.getInsertionDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)),
                        sittingDate.getMinTime().format(DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR)),
                        sittingDate.getMaxTime().format(DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR))
                )).collect(Collectors.toList()));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity save(@RequestBody SittingDateDto sittingDateDto) {

        final SittingDate sittingDate = new SittingDate();
        final LocalTime minTime = LocalTime
                .parse(sittingDateDto.getMinTime(), DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR));
        final LocalTime maxTime = LocalTime
                .parse(sittingDateDto.getMaxTime(), DateTimeFormatter.ofPattern(DateUtils.TIME_PATTERN_FR));

        if (minTime.isAfter(maxTime)) {
            return ResponseEntity.ok(DefaultDto.of("0"));
        }
        sittingDate.setMinTime(minTime);
        sittingDate.setMaxTime(maxTime);

        coreService.save(sittingDate, SittingDate.class);

        sittingDateDto.setId(sittingDate.getId());
        sittingDateDto.setInsertionDate(sittingDate.getInsertionDate().format(DateTimeFormatter.ofPattern(DateUtils.DATE_TIME_PATTERN_FR)));

        return ResponseEntity.ok(sittingDateDto);
    }

}
