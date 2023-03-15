package de.hartz.software.sodevsalaryguide.mapper;

import de.hartz.software.sodevsalaryguide.mapper.raw.ColumnList;
import de.hartz.software.sodevsalaryguide.model.Range;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.enums.Currency;
import de.hartz.software.sodevsalaryguide.model.enums.Gender;
import de.hartz.software.sodevsalaryguide.model.raw.RawRow;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractRawRowMapper {
    static String COLUMN_DONT_EXIST = "COLUMN_DONT_EXIST";
    // TODO: do we still need this? As it only appears with frontend csv parser. Maybe use as domain object..
    static String UNNAMED_COLUMN_PREFIX = "columnIndex-";

    // Sets to distinct values and map to filter values with single response.
    static Map<String, Integer> educations = new HashMap<>();
    static  Map<String, Integer> countries = new HashMap<>();
    static  Set<String> genders = new HashSet<>();
    static Set<String> years = new HashSet<>();
    static  Map<String, Integer> abilities = new HashMap<>();

    abstract String SALARY_KEY();
    abstract String CURRENCY_KEY();
    abstract String GENDER_KEY();
    abstract String YEARS_OF_EXPIERIENCE();
    abstract String ABILITIES_KEY();
    abstract ColumnList ABILITIES_KEYs();
    abstract String DEGREE();
    abstract String COMPANY_SIZE();
    abstract String COUNTRY();

    abstract Integer MAPPER_FOR_YEAR();

    public SurveyEntry map (RawRow csvRow) {
        val result = new SurveyEntry();
        this.setSalary(csvRow, result);
        this.setGender(csvRow, result);
        this.setYearsOfExpirience(csvRow, result);
        this.setAbilities(csvRow, result);
        this.setCompanySize(csvRow, result);
        this.setCountry(csvRow, result);
        this.setDegree(csvRow, result);
        return result;
    }

    void setAbilities(RawRow csvRow, SurveyEntry result) {
        List<String> abilities = new ArrayList<>(result.getAbilities());
        if (ABILITIES_KEY() != null) {
            String abilitiesSeperatedBySemicolon = csvRow.get((String) ABILITIES_KEY());
            if (abilitiesSeperatedBySemicolon != null) {
                for (String abi : abilitiesSeperatedBySemicolon.split(";")) {
                    addKeyAndUpdateKeyCount(abi, abilities);
                }
            }
        } else {
            ColumnList columnList = ABILITIES_KEYs();
            String ability = csvRow.get(columnList.getInitial());
            addKeyAndUpdateKeyCount(ability, abilities);
            if (abilities == null) {
                return;
            }
            for (int i = columnList.getFrom(); i <= columnList.getTo(); i++) {
                ability = csvRow.get(UNNAMED_COLUMN_PREFIX + i);
                addKeyAndUpdateKeyCount(ability, abilities);
            }
        }
        result.setAbilities(new HashSet<>(abilities));
    }

     void addKeyAndUpdateKeyCount(String key, List<String> targetList) {
        if (key == null) {
            return;
        }
        String id = valueAsId(key);
         List<String> invalidValues = List.of("response", "", "none", "other", "others", "otherpleasespecify");
        if (invalidValues.contains(id)) {
            return;
        }
        int newValue = abilities.getOrDefault(id, 0) + 1;
        abilities.put(id, newValue);
        targetList.add(id);
    }

     String valueAsId(String dirtyString) {
        return dirtyString.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }

     void setDegree(RawRow csvRow, SurveyEntry result) {
        String degree = csvRow.get(DEGREE());
        if (degree == null) {
            return;
        }
        String id = valueAsId(degree);
         List<String> invalidValues = List.of("response", "", "none", "other", "others", "otherpleasespecify", "na");
        if (invalidValues.contains(id)) {
            return;
        }
        id = id.contains("selftaught") ? "selftaught" : id;
        id = id.contains("onthejob") ? "onthejob" : id;
        int newValue = educations.getOrDefault(id, 0) + 1;
        educations.put(id, newValue);
        result.setHighestDegree(id);
    }





    protected void setCountry(RawRow csvRow, SurveyEntry result) {
        String country = csvRow.get(this.COUNTRY());
        if (country == null) {
            return;
        }
        String id = valueAsId(country);
        List<String> invalidValues = Arrays.asList("response", "", "none", "other", "others", "otherpleasespecify");
        if (invalidValues.contains(id)) {
            return;
        }
        int newValue = AbstractRawRowMapper.countries.getOrDefault(id, 0) + 1;
        AbstractRawRowMapper.countries.put(id, newValue);
        result.setCountry(id);
    }

    protected void setYearsOfExpirience(RawRow csvRow, SurveyEntry result) {
        String yearsOfExpirience = csvRow.get(this.YEARS_OF_EXPIERIENCE());
        if (yearsOfExpirience == null) {
            return;
        }
        AbstractRawRowMapper.years.add(yearsOfExpirience);
        int min = 0;
        int max = 0;
        Matcher matcher = Pattern.compile("\\d+").matcher(yearsOfExpirience);
        if (yearsOfExpirience.contains("-") || yearsOfExpirience.contains("to")) {
            if (matcher.find()) {
                min = Integer.parseInt(matcher.group());
            }
            if (matcher.find()) {
                max = Integer.parseInt(matcher.group());
            }
        } else if (matcher.find()) {
            min = Integer.parseInt(matcher.group());
            max = min;
        } else {
            return;
        }
        result.setExpirienceInYears(new Range(min, max));
    }

    protected void setCompanySize(RawRow csvRow, SurveyEntry result) {
        String companySize = csvRow.get(this.COMPANY_SIZE());
        if (companySize == null) {
            return;
        }
        int min = 0;
        int max = 0;
        Matcher matcher = Pattern.compile("\\d+").matcher(companySize);
        if (companySize.contains("-") || companySize.contains("to")) {
            if (matcher.find()) {
                min = Integer.parseInt(matcher.group());
            }
            if (matcher.find()) {
                max = Integer.parseInt(matcher.group());
            }
        } else if (matcher.find()) {
            min = Integer.parseInt(matcher.group());
            max = min;
        } else {
            return;
        }
        result.setCompanySize(new Range(min, max));
    }

    protected void setGender(RawRow csvRow, SurveyEntry result) {
        String gender = csvRow.get(this.GENDER_KEY());
        if (gender == null) {
            return;
        }
        AbstractRawRowMapper.genders.add(gender);
        Gender mappedGender = null;
        if (gender.contains("Female") || gender.contains("Woman")) {
            mappedGender = Gender.FEMALE;
        }
        if (gender.contains("Male") || gender.contains("Man")) {
            mappedGender = Gender.MALE;
        }
        if (gender.contains("Non-binary") || gender.contains("gender")) {
            mappedGender = Gender.OTHER;
        }
        result.setGender(mappedGender);
    }

    protected void setSalary(RawRow csvRow, SurveyEntry result) {
        String salary = csvRow.get(SALARY_KEY());
        if (salary != null) {
            double salaryValue = getSalaryValue(salary);
            if (salaryValue < 10000) {
                return;
            }
            result.setSalary(salaryValue);
        }

        String currency = csvRow.get(CURRENCY_KEY());
        if (currency != null) {
            result.setCurrency(getCurrency(currency));
        }

        if (result.getSalary() > 250000) {
            // System.err.println("Bad ratio: " + result.currency + " with value " + result._salary);
            result.setSalary(-1.);
        }
    }

    protected Currency getCurrency(String value) {
        if (containsValue(value, "EUR")) {
            return Currency.EUR;
        }
        if (containsValue(value, "YEN")) {
            return Currency.JPY;
        }
        if (containsValue(value, "POUNDS")) {
            return Currency.GBP;
        }
        if (containsValue(value, "US DOLLAR")) {
            return Currency.USD;
        }

        // TODO: Add all currencies

        // System.err.println("Cannot map " + value + " to currency. Setting to default USD.");
        return Currency.USD;
    }


    /*
    // TODO: Consider activation

    protected String valueAsId(String value) {
        String[] invalidChars = {" ", ",", "."};
        String id = value.toLowerCase();
        for (String invalidChar : invalidChars) {
            id = id.replace(invalidChar, "");
        }
        return id;
    }

    protected void setId(RawRow csvRow, SurveyEntry result) {
        result.id = csvRow.get(ID_KEY);
    }

    protected void setEducation(RawRow csvRow, SurveyEntry result) {
        String education = csvRow.get(EDUCATION_KEY);
        if (education == null) {
            // When column is not defined it is null.
            return;
        }

        Education mappedEducation;
        if (education.contains("PhD")) {
            mappedEducation = Education.PHD;
        } else if (education.contains("Master")) {
            mappedEducation = Education.MASTER;
        } else if (education.contains("Bachelor")) {
            mappedEducation = Education.BACHELOR;
        } else if (education.contains("no degree") || education.contains("none")) {
            mappedEducation = Education.NONE;
        } else {
            mappedEducation = Education.OTHER;
        }
        result.education = mappedEducation;
    }

    protected void setAge(RawRow csvRow, SurveyEntry result) {
        String age = csvRow.get(AGE_KEY);
        if (age == null) {
            // When column is not defined it is null.
            return;
        }
        int ageValue = Integer.parseInt(age);
        if (ageValue < 18 || ageValue > 120) {
            // System.err.println("Age value is out of bounds: " + ageValue);
            return;
        }
        result.age = ageValue;
    }

    protected void setRemote(RawRow csvRow, SurveyEntry result) {
        String remote = csvRow.get(REMOTE_KEY);
        if (remote == null) {
            // When column is not defined it is null.
            return;
        }
        if (remote.toUpperCase().equals("YES")) {
            result.isRemote = true;
        }
    }

     */


    protected boolean containsValue(String value, String find) {
        return value.toUpperCase().indexOf(find.toUpperCase()) != -1;
    }

    protected double getSalaryValue(String value) {
        if (value == null || value.isEmpty()) {
            return -1;
        }
        if (value.contains("<")) {
            return 10000;
        } else if (value.contains("$") && value.contains("-")) {
            String firstValue = value
                    .replaceAll("\\$", "")
                    .replaceAll(",", "")
                    .substring(0, value.indexOf("-"));
            return Integer.parseInt(firstValue) + 10000;
        }
        try {
            int result = Integer.parseInt(value);
            if (result > 500000 && result % 10000 == 0) {
                result /= 100;
            }
            if (result > 1000000) {
                result = -1;
            }
            return result;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
