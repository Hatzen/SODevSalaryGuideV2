package de.hartz.software.sodevsalaryguide.model.raw;

import java.util.HashMap;

public class RawRow extends HashMap<HeaderMeta<?>, String> {

    public String get(String columName) {
        return getValueForColumnName(columName);
    }

    public String getValueForColumnName(String columName) {
        return super.get(columName);
    }

    public String getValueForColumnIndex(Integer index) {
        return (String) entrySet().toArray()[index];
    }
}
