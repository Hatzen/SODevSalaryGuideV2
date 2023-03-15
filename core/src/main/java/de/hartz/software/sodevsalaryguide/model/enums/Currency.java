package de.hartz.software.sodevsalaryguide.model.enums;

import lombok.RequiredArgsConstructor;

// https://bankenverband.de/service/waehrungsrechner-bdb-link/wahrungen-und-abkurzungen/
@RequiredArgsConstructor
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    JPY("JPY"),
    MXN("MXN"),
    GBP("GBP")
    // TODO: Add all currency codes.
    ;

    private final String code;
}
