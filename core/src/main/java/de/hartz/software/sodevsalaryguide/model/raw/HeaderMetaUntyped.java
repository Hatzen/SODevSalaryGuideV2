package de.hartz.software.sodevsalaryguide.model.raw;

import java.util.Optional;

public class HeaderMetaUntyped extends HeaderMeta<Void>{

    public HeaderMetaUntyped(String name) {
        super(name, Optional.empty());
    }
}
