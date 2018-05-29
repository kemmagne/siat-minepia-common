package org.guce.epayment.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tadzotsa
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class SittingDateDto {

    private Integer id;
    private String insertionDate;
    private String minTime;
    private String maxTime;

    public SittingDateDto() {
    }

    private SittingDateDto(Integer id, String insertionDate, String minTime, String maxTime) {
        this.id = id;
        this.insertionDate = insertionDate;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public static SittingDateDto of(Integer id, String insertionDate, String minTime, String maxTime) {

        return new SittingDateDto(id, insertionDate, minTime, maxTime);
    }

}
