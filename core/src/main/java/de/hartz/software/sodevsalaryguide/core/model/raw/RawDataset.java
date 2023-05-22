package de.hartz.software.sodevsalaryguide.core.model.raw;

import java.util.ArrayList;
import java.util.Collection;

public class RawDataset extends ArrayList<RawRow> {

    public RawDataset(Collection<RawRow> collection) {
        super(collection);
    }
    
}
