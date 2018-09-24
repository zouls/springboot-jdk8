package com.zoulshell.optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Festival {
    private LocalDate date;
    private String name;
    private String msg;
}
