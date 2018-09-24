package com.zoulshell.optional;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

public class OptionalTest {

    @Test
    public void create() {
        Optional<String> o1 = Optional.of("a");
        Optional<Object> o2 = Optional.empty();

        o1.ifPresent(System.out::println);
        // do nothing
        o2.ifPresent(System.out::println);
        // value
        System.out.println(o2.orElse("b"));
        // supplier
        System.out.println(o2.orElseGet("abc"::toUpperCase));
    }

    @Test
    public void midAutumnFestival() {
        Festival festival = null;
        if (Math.random() > 0.5) {
            festival = new Festival(LocalDate.of(2018, 9, 24),
                    "MidAutumn", "Happy MidAutumn!");
        }

        // orElse
        Festival midAutumn = Optional.ofNullable(festival)
                .orElse(new Festival(LocalDate.of(2018, 9, 24),
                        "MidAutumn", "Happy MidAutumn too!"));
        System.out.println(midAutumn.getMsg());

        // map
        Optional.ofNullable(festival).map(e -> "happy too~").ifPresent(System.out::println);
    }
}
