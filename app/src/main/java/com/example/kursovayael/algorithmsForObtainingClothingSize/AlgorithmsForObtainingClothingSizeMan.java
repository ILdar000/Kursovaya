package com.example.kursovayael.algorithmsForObtainingClothingSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmsForObtainingClothingSizeMan {
    Map<Integer, List<String>> tableTypeSize;

    public Map<Integer, List<String>> initialization() {
        tableTypeSize = new HashMap<>();
        tableTypeSize.put(44, Arrays.asList("XXS", "38", "34", "32"));
        tableTypeSize.put(46, Arrays.asList("XS", "40", "36", "34"));
        tableTypeSize.put(48, Arrays.asList("S", "42", "38", "36"));
        tableTypeSize.put(50, Arrays.asList("M", "44", "40", "38"));
        tableTypeSize.put(52, Arrays.asList("L", "46", "42", "40"));
        tableTypeSize.put(54, Arrays.asList("XL", "48", "44", "42"));

        int ru = 54;
        int countX = 1;
        int eu = 48;
        int us = 44;
        int uk = 42;

        for (int i = 0; i < 7; i++) {
            countX++;
            ru = ru + 2;
            eu = eu + 2;
            us = us + 2;
            uk = uk + 2;
            tableTypeSize.put(ru, Arrays.asList(countX + "XL", String.valueOf(eu), String.valueOf(us), String.valueOf(uk)));
        }
        return tableTypeSize;
    }

    public Integer getSizeOuterwear(int startSize, int userSize) {
        int size = 44;
        int countSize = 14;

        int slevaGap = startSize;
        int spravaGap = startSize + 4;


        for (int i = 0; i < countSize; i++) {
            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }

            if (userSize < slevaGap && userSize > spravaGap - 4) {
                return size + i * 2;
            }

            slevaGap = slevaGap + 4;

            spravaGap = spravaGap + 4;
        }
        return -1;
    }

    public Integer getSizeForJeans(int startSize, int userSize) {
//      https://xtkani.ru/razmery-zhenskix-dzhinsov-v-tablice/
        int size = 44;
        int countSize = 14;
        int raznica = 6;
        int slevaGap = startSize;
        int spravaGap = startSize + raznica;


        for (int i = 0; i < countSize; i++) {
            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }

            if (userSize < slevaGap && userSize > spravaGap - raznica) {
                return size + i * 2;
            }

            slevaGap = slevaGap + raznica;

            spravaGap = spravaGap + raznica;
        }
        return -1;
    }

    public int getSizeShoes(double sizeUser) {
        int startSize = 15;
        double[] arraySizeShoes = {8.5, 9.5, 10.5, 11, 12, 12.5, 13, 14, 14.5, 15.5, 16, 16.5, 17, 17.5, 18, 19,
                20, 20.5, 21.5, 22, 22.5, 23.5, 24.5,
                25,25.5,26.5,27,27.5,28.5,29,29.5,30,30.5,31,31.5,32};
        double slevaGap;
        double spravaGap;
        for (int i = 0; i < arraySizeShoes.length; i++) {
            slevaGap = arraySizeShoes[i];
            spravaGap = arraySizeShoes[i + 1];

//            System.out.println("slevaGap -> " + slevaGap + "\nspravaGap -> " + spravaGap);
            if (slevaGap == sizeUser)
                if (i == 0)
                    return startSize;
                else
                    return startSize + i + 1;

            if (spravaGap == sizeUser)
                if (i == 1)
                    return startSize;
                else
                    return startSize + i + 1;

            if (slevaGap < sizeUser && spravaGap > sizeUser)
                return startSize + i;
        }
        return 0;
    }

    public int getSizeHead(int sizeUser) {
        int[] arraySizeHead = {
                54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65
        };
        int slevaGap;
        int spravaGap;
        for (int i = 0; i < arraySizeHead.length; i++) {
            slevaGap = arraySizeHead[i];
            spravaGap = arraySizeHead[i + 1];
            if (slevaGap == sizeUser)
                if (i == 0)
                    return slevaGap;

            if (spravaGap == sizeUser)
                if (i == 0)
                    return slevaGap;
                else
                    return slevaGap;

            if (slevaGap < sizeUser && spravaGap > sizeUser)
                return slevaGap;
        }
        return 0;
    }
}
