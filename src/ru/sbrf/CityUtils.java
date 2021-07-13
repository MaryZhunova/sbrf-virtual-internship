package ru.sbrf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.sbrf.City.districtAndNameComparator;
import static ru.sbrf.City.nameComparator;

public class CityUtils {
    /**
     * Загрузка данных о городах в массив
     *
     * @return список с данными о городах
     */
    public static List<City> parse() {
        List<City> listOfCities = new ArrayList<>();
        File file = new File("city_ru.csv");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            String current;
            while (scanner.hasNextLine()) {
                current = scanner.nextLine();
                String[] res = current.split(";");
                City city = new City();
                city.setName(res[1]);
                city.setRegion(res[2]);
                city.setDistrict(res[3]);
                city.setPopulation(Integer.parseInt(res[4]));
                city.setFoundation(res.length == 6 ? res[5] : "");
                listOfCities.add(city);
            }
            scanner.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return listOfCities;
    }

    /**
     * Вывод в консоль списка городов
     *
     * @param listOfCities список городов
     */
    public static void print(List<City> listOfCities) {
        listOfCities.forEach(System.out::println);
    }

    /**
     * Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра;
     *
     * @param listOfCities список городов
     * @return отсортированный список городов
     */
    public static List<City> sortByName (List<City> listOfCities) {
        listOfCities.sort(nameComparator);
        return listOfCities;
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города
     * внутри каждого федерального округа в алфавитном порядке по убыванию с учетом регистра;
     *
     * @param listOfCities список городов
     * @return отсортированный список городов
     */
    public static List<City> sortByDistrictAndName (List<City> listOfCities) {
        listOfCities.sort(districtAndNameComparator);
        return listOfCities;
    }

    /**
     * Поиск города с наибольшим количеством жителей путем простого перебора
     *
     * @param listOfCities список городов
     */
    public static void findBiggestPopulation(List<City> listOfCities) {
        City[] cityArray = listOfCities.toArray(new City[0]);
        int biggest = cityArray[0].getPopulation();
        int index = 0;
        for (int i = 1; i < cityArray.length; i++) {
            if (cityArray[i].getPopulation() > biggest) {
                biggest = cityArray[i].getPopulation();
                index = i;
            }
        }
        System.out.println("[" + index + "] = " + biggest);
    }

    /**
     * Поиск города с наибольшим количеством жителей путем быстрой сортировки
     *
     * @param listOfCities список городов
     */
    public static void findBiggestPopulationQuickSort(List<City> listOfCities) {
        List<City> list = quickSort(listOfCities);
        int index = list.size() - 1;
        System.out.println("[" + index + "] = " + list.get(list.size() - 1).getPopulation());
    }

    public static List<City> quickSort(List<City> listOfCities) {
        if (listOfCities.size() < 2) {
            return listOfCities;
        }
        int random = (int) Math.floor(Math.random() * listOfCities.size());
        City cur = listOfCities.get(random);
        listOfCities.remove(listOfCities.get(random));

        List<City> l1 = listOfCities.stream().filter(x -> x.getPopulation() < cur.getPopulation()).collect(Collectors.toList());
        List<City> l2 = listOfCities.stream().filter(x -> x.getPopulation() >= cur.getPopulation()).collect(Collectors.toList());

        l1 = quickSort(l1);
        l1.add(cur);
        l2 = quickSort(l2);

        return Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList());
    }

    /**
     * Поиск количества городов в разрезе регионов.
     *
     * @param listOfCities список городов
     */
    public  static void numberOfCities (List<City> listOfCities) {
        Map<String, Integer> map = new HashMap<>();
        listOfCities.forEach(city -> map.merge(city.getRegion(), 1, Integer::sum));
        map.forEach((k, v) -> System.out.println(k + " - " + v));
    }
}
