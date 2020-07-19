package com.gabriel.beerservice.web.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if (ts != null) {
            LocalDateTime ldt = ts.toLocalDateTime();
            return OffsetDateTime.of(ldt.getYear(), ldt.getMonthValue(), ldt.getDayOfMonth(),
                    ldt.getHour(), ldt.getMinute(), ldt.getSecond(), ldt.getNano(), ZoneOffset.UTC);
        } else {
            return null;
        }
    }

    public Timestamp asTimeStamp(OffsetDateTime odt) {
        if (odt != null) {
            return Timestamp.valueOf(odt.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
