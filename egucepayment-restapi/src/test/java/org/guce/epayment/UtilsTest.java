package org.guce.epayment;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.Table;
import org.apache.commons.lang3.StringUtils;
import org.guce.epayment.core.entities.InvoiceType;
import org.guce.epayment.core.entities.InvoiceVersion;
import org.guce.epayment.core.utils.Constants;
import org.guce.epayment.core.utils.CoreUtils;
import static org.guce.epayment.core.utils.CoreUtils.findNext;
import org.guce.epayment.core.utils.DateUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author tadzotsa
 */
public class UtilsTest {

    @Ignore
    @Test
    public void testFindNext() {
        Assert.assertTrue(CoreUtils.findNext("T11", "T11,T21,T31", ",").isPresent());
        Assert.assertFalse(findNext("T31", "T11,T21,T31", ",").isPresent());
    }

    @Ignore
    @Test
    public void testGenerateUniqueValue() {
        System.out.println(generateUniqueValue("", "TO"));
    }

    private String generateUniqueValue(String sequence, String prefix) {

        final long i = 1;

        return new DecimalFormat(prefix + StringUtils.repeat("0", 10)).format(i);
    }

    @Ignore
    @Test
    public void testCompare() {

        final String flow = "T11,T21,T31";

        Assert.assertTrue(0 < CoreUtils.compare(flow, "T31", "T21", ","));
        Assert.assertTrue(0 > CoreUtils.compare(flow, "T21", "T31", ","));

    }

    @Ignore
    @Test
    public void testMapToString() {

        final Map<String, String> map = new HashMap<>();

        map.put("1", "un");
        map.put("2", "deux");
        map.put("3", "trois");

        System.out.println(CoreUtils.mapToString(map, ","));
    }

    @Ignore
    @Test
    public void testStringBuilder() {

        final StringBuilder whereBuilder = new StringBuilder("(");

        for (int index = 0; index < 10; index++) {
            whereBuilder.append("piv.invoiceVersion.invoice.type.code = :invoiceTypeCode")
                    .append(index).append(" OR ");
        }

        whereBuilder.append("1 <> 1)");
        System.out.println(whereBuilder);
        whereBuilder.delete(0, whereBuilder.length());
        whereBuilder.append("(");
        System.out.println(whereBuilder);
    }

    @Ignore
    @Test
    public void testJavaoDate() {

        LocalDateTime dateTime = DateUtils.resetTime(LocalDateTime.now());

        dateTime = dateTime.withHour(Constants.ZERO);
        dateTime = dateTime.withMinute(Constants.ZERO);
        dateTime = dateTime.withSecond(Constants.ZERO);
        dateTime = dateTime.withNano(Constants.ZERO);

        System.out.println();
        System.out.println(dateTime);
        System.out.println();

        dateTime = dateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        dateTime = dateTime.minusWeeks(Constants.ONE);
        System.out.println(dateTime);
        System.out.println();

        dateTime = dateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(dateTime);
        System.out.println();

        dateTime = dateTime.with(TemporalAdjusters.firstDayOfYear());
        System.out.println(dateTime);
        System.out.println();

        Map<String, Object> result = new HashMap<>();
        System.out.println((List<InvoiceType>) result.get("0"));
    }

    @Ignore
    @Test
    public void testSittingDate() {

        final LocalTime minTime = LocalTime.of(7, 30);
        final LocalTime maxTime = LocalTime.of(17, 0);

        System.out.println(DateUtils.getSittingDate(minTime, maxTime));
    }

    @Ignore
    @Test
    public void testUpdate() throws Exception {

        final Map<String, Object> ids = new HashMap<>();
        ids.put("ID", "");

        final Map<String, Object> map = new HashMap<>();
        map.put("VERSION_NUMBER", "");
        map.put("VERSION_DATE", "");
        map.put("VERSION_AMOUNT", "");
        map.put("BALANCE_AMOUNT", "");
        map.put("PAYMENT_DATE", "");

        final Table table = InvoiceVersion.class.getAnnotation(Table.class);
        final StringBuilder builder = new StringBuilder("UPDATE ");
        final List<String> update = map.keySet().stream().map(key -> String.format("%s = :%s", key, key))
                .collect(Collectors.toList());
        final List<String> where = ids.keySet().stream().map(key -> String.format("%s = :%s", key, key))
                .collect(Collectors.toList());

        builder.append(table.name()).append(" SET ")
                .append(org.springframework.util.StringUtils.collectionToDelimitedString(update, " AND "))
                .append(" WHERE ")
                .append(org.springframework.util.StringUtils.collectionToDelimitedString(where, " AND "));

        System.out.println(builder);
    }

}
