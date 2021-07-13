package ru.sbrf;

import java.util.Comparator;

/**
 * Модель города
 */
public class City {
    /**
     * Наименование города
     */
    private String name;
    /**
     * Регион
     */
    private String region;
    /**
     * Федеральный округ
     */
    private String district;
    /**
     * Количество жителей города
     */
    private Integer population;
    /**
     * Дата основания или первое упоминание
     */
    private String foundation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "ru.sbrf.City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation=" + foundation +
                '}';
    }

    public static Comparator<City> nameComparator = (o1, o2) -> o2.getName().compareToIgnoreCase(o1.getName());

    public static Comparator<City> districtAndNameComparator = (o1, o2) -> {
        int res;
        res = o2.getDistrict().compareTo(o1.getDistrict());
        if (res == 0) {
            res = o2.getName().compareTo(o1.getName());
        }
        return res;
    };
}
