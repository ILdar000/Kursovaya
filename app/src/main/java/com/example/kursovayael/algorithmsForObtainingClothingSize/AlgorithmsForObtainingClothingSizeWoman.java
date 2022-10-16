package com.example.kursovayael.algorithmsForObtainingClothingSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmsForObtainingClothingSizeWoman {
    Map<Integer, List<String>> tableTypeSizeWoman;

    public Map<Integer, List<String>> initialization() {
        tableTypeSizeWoman = new HashMap<>();
        tableTypeSizeWoman.put(38, Arrays.asList("XXS", "34", "0", "4"));
        tableTypeSizeWoman.put(40, Arrays.asList("XS", "36", "2", "6"));
        tableTypeSizeWoman.put(42, Arrays.asList("S", "38", "4", "8"));
        tableTypeSizeWoman.put(44, Arrays.asList("M", "40", "6", "10"));
        tableTypeSizeWoman.put(46, Arrays.asList("L", "42", "8", "12"));
        tableTypeSizeWoman.put(48, Arrays.asList("XL", "44", "10", "14"));
        tableTypeSizeWoman.put(50, Arrays.asList("XXL", "46", "12", "16"));
        tableTypeSizeWoman.put(52, Arrays.asList("3XL", "48", "14", "18"));

        int ru = 52;
        int countX = 3;
        int eu = 48;
        int us = 14;
        int uk = 18;

        for (int i = 0; i < 7; i++) {
            countX++;
            ru = ru + 2;
            eu = eu + 2;
            us = us + 2;
            uk = uk + 2;
            tableTypeSizeWoman.put(ru, Arrays.asList(countX + "XL", String.valueOf(eu), String.valueOf(us), String.valueOf(uk)));
        }
        return tableTypeSizeWoman;
    }

    public Integer getSizeHipOrWaist(int startSize, int userSize) {
        int size = 40;
        int countSize = 11;
        int slevaGap = startSize;
        int spravaGap = startSize;
        for (int i = 0; i < countSize; i++) {
            if (i >= 1 && i != 10) {
                slevaGap = slevaGap + 4;
            } else if (i == 10) {
                slevaGap = slevaGap + 3;
            }

            if (i < 2) {
                spravaGap = spravaGap + i * 2 + 2;
            } else if (i == 10) {
                spravaGap = spravaGap + 3;
            } else {
                spravaGap = spravaGap + 4;
            }

            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }
            if (userSize < slevaGap && userSize > spravaGap - 4) {
                return size + i * 2;
            }
//            System.out.println(slevaGap +"-"+ spravaGap);
        }
        return -1;
    }

    public Integer getSizeChest(int startSize, int userSize) {
        int size = 40;
        int countSize = 11;

        int slevaGap = startSize;
        int spravaGap = startSize;
        ;
        for (int i = 0; i < countSize; i++) {

            if (i >= 1)
                slevaGap = slevaGap + 4;


            if (i < 2)
                spravaGap = spravaGap + i * 2 + 2;
            else
                spravaGap = spravaGap + 4;

            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }
            if (userSize < slevaGap && userSize > spravaGap - 4) {
                return size + i * 2;
            }
        }
        return -1;
    }

    public Integer getSizeForShirts(int startSize, int userSize) {
        int size = 38;
        int countSize = 17;

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

    public Integer getSizeForSkirts(int startSize, int userSize) {
//        https://ntkani.ru/tablitsy-razmerov-yubok/
        int size = 40;
        int countSize = 30;

        int slevaGap = startSize;
        int spravaGap = startSize + 2;

        for (int i = 0; i < countSize; i++) {
            System.out.println(slevaGap + "-" + spravaGap);

            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }
            if (userSize < slevaGap && userSize > spravaGap - 3) {
                return size + i * 2;
            }
            slevaGap = slevaGap + 3;

            spravaGap = spravaGap + 3;
        }
        return -1;
    }

    public Integer getSizeForDresses(int startSize, int userSize) {
//      https://tkaner.com/odezhda/plate/razmernaya-setka-zhenskih-platev/
//        ??????????????????????????
//        ??????????????????????????
//        ??????????????????????????
//        ??????????????????????????
        int size = 40;
        int countSize = 14;

        int slevaGap = startSize;
        int spravaGap = startSize + 3;


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
        int size = 38;
        int countSize = 14;

        int slevaGap = startSize;
        int spravaGap = startSize + 3;


        for (int i = 0; i < countSize; i++) {
            if (userSize >= slevaGap && userSize <= spravaGap) {
                return size + i * 2;
            }

            if (userSize < slevaGap && userSize > spravaGap - 3) {
                return size + i * 2;
            }

            slevaGap = slevaGap + 3;

            spravaGap = spravaGap + 3;
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
            spravaGap = arraySizeShoes[i+1];
            if (slevaGap == sizeUser)
                if(i==0)
                    return startSize;
                else
                    return startSize + i+1;

            if (spravaGap == sizeUser)
                if(i==0)
                    return startSize;
                else
                    return startSize + i+1;

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


    public Map<Integer, List<String>> tableTypeSizeHeadCircumference() {
        tableTypeSizeWoman = new HashMap<>();
        tableTypeSizeWoman.put(54,Arrays.asList( "XXS","6 3/4"));
        tableTypeSizeWoman.put(55, Arrays.asList("XS","6 7/8"));
        tableTypeSizeWoman.put(56, Arrays.asList("S","7"));
        tableTypeSizeWoman.put(57, Arrays.asList("M","7 1/8"));
        tableTypeSizeWoman.put(58, Arrays.asList("L","7 1/4"));
        tableTypeSizeWoman.put(59, Arrays.asList("XL","7 8/8"));
        tableTypeSizeWoman.put(60, Arrays.asList("XXL","7 1/2"));
        tableTypeSizeWoman.put(61, Arrays.asList("XXL","7 5/8"));
        tableTypeSizeWoman.put(62, Arrays.asList("XXXL","7 3/4"));
        tableTypeSizeWoman.put(63, Arrays.asList("XXXL","7 7/8"));
        tableTypeSizeWoman.put(64, Arrays.asList("XXXXL","8"));
        tableTypeSizeWoman.put(65, Arrays.asList("XXXXL","8 1/8"));
        return tableTypeSizeWoman;
    }
}
