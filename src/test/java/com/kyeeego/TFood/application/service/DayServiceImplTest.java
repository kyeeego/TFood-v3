package com.kyeeego.TFood.application.service;

import com.kyeeego.TFood.application.repository.DayRepository;
import com.kyeeego.TFood.application.port.DayService;
import com.kyeeego.TFood.domain.models.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.security.Principal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DayServiceImplTest {

    private static final Principal user = new PrincipalMock();
    private static final LocalDate LOCAL_DATE = LocalDate.of(2021, 4, 17);

    @Autowired
    private DayService service;

    @MockBean
    private DayRepository dayRepository;

    @MockBean
    private Clock clock;

    @BeforeEach
    void setUp() {
        Day day1 = new Day(user.getName(), LocalDate.of(2021, 4, 12 ));
        Day day2 = new Day(user.getName(), LocalDate.of(2021, 4, 14 ));
        Day day3 = new Day(user.getName(), LocalDate.of(2021, 4, 17 ));
        Day day4 = new Day(user.getName(), LocalDate.of(2020, 5, 5 ));

        Mockito.doReturn(List.of(day1, day2, day3, day4)).when(dayRepository).findByUser(user.getName());

        Clock fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        Mockito.doReturn(fixedClock.instant()).when(clock).instant();
        Mockito.doReturn(fixedClock.getZone()).when(clock).getZone();

    }

    @Test
    void pastWeek() {
       List<Day> week = service.pastWeek(user);
       assertEquals(3, week.size());
    }

    private static class PrincipalMock implements Principal {
        @Override
        public String getName() {
            return "a@a.a";
        }
    }
}