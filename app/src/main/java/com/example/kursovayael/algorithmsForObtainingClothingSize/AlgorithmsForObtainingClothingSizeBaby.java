package com.example.kursovayael.algorithmsForObtainingClothingSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlgorithmsForObtainingClothingSizeBaby {

    Map<Integer, List<String>> tableTypeSize;

    public Map<Integer, List<String>> initialization() {
        tableTypeSize = new HashMap<>();
        tableTypeSize.put(18, Arrays.asList("2M", "50-56", "0", "2"));
        tableTypeSize.put(20, Arrays.asList("6M", "62", "0-3", "2"));
        tableTypeSize.put(22, Arrays.asList("9M", "68-74", "3-9", "2"));
        tableTypeSize.put(24, Arrays.asList("12M", "80-86", "12-18", "2"));
        tableTypeSize.put(26, Arrays.asList("18M", "92-98", "2T-3T", "3"));
        tableTypeSize.put(28, Arrays.asList("2Y", "104-110", "4T-5", "3"));
        tableTypeSize.put(30, Arrays.asList("3Y", "116-122", "6-6X", "4"));
        tableTypeSize.put(32, Arrays.asList("5Y", "128-134", "7", "4"));
        tableTypeSize.put(34, Arrays.asList("6Y", "140", "8", "4-6"));
        tableTypeSize.put(36, Arrays.asList("8Y", "146", "10", "6"));
        tableTypeSize.put(38, Arrays.asList("9L", "152", "12", "8"));
        return tableTypeSize;
    }

    public Integer getSizeWeight(int startSize, int userSize) {
//        https://rebenokmama.ru/razmery-detskoj-odezhdy/
        int size = 18;
        int countSize = 18;
        int raznica = 1;
        int slevaGap = startSize;
        int spravaGap = startSize + raznica;


        for (int i = 0; i < countSize; i++) {
            if (i < 2) {
                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size;
                }
            } else {
                if (i == 3) {
                    raznica = 3;
                }
                if (i == 4) {
                    raznica = 2;
                }
//            if (i == 5) {
//                raznica = 3;
//            }
                if (i == 6) {
                    raznica = 5;
                }

                if (i == 7) {
                    raznica = 6;
                }

                if (i == 9) {
                    raznica = 3;
                }

                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size + (i - 3) * 2;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size + (i - 3) * 2;
                }
                if (userSize >= spravaGap && userSize <= (slevaGap + raznica)) {
                    return size + (i - 3) * 2;
                }
                slevaGap = slevaGap + raznica;
                spravaGap = spravaGap + raznica;

            }
        }
        return -1;
    }


    public Integer getSizeHeight(int startSize, int userSize) {
        int size = 18;
        int countSize = 18;
        int raznica = 6;
        int slevaGap = startSize;
        int spravaGap = startSize + raznica;


        for (int i = 0; i < countSize; i++) {
            if (i < 2) {
                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size;
                }
            } else {
                if (i == 3) {
                    raznica = 12;
                }
                if (i == 9) {
                    raznica = 6;
                }

                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size + (i - 2) * 2;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size + (i - 2) * 2;
                }

                slevaGap = slevaGap + raznica;
                spravaGap = spravaGap + raznica;

            }
        }
        return -1;
    }

    public Integer getSizeBreast(int startSize, int userSize) {
        int size = 18;
        int countSize = 18;
        int raznica = 4;
        int slevaGap = startSize;
        int spravaGap = startSize + raznica;


        for (int i = 0; i < countSize; i++) {
            if (i == 2) {
                slevaGap = spravaGap - 4;
            }
            if (i == 0) {
                raznica = 2;

                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size + (i - 1) * 2;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size + (i - 1) * 2;
                }

                slevaGap = slevaGap + raznica + 2;
                spravaGap = spravaGap + raznica;

            } else {
                raznica = 4;

                if (i == 3) {
                    raznica = 3;
                }

                if (i == 6) {
                    raznica = 5;
                }

                if (i == 7) {
                    raznica = 6;
                }


                if (userSize < slevaGap && userSize > spravaGap - raznica + 1) {
                    return size + (i - 1) * 2;
                }

                if (userSize >= slevaGap && userSize <= spravaGap) {
                    return size + (i - 1) * 2;
                }
                slevaGap = slevaGap + raznica;
                spravaGap = spravaGap + raznica;
            }
        }
        return -1;
    }

    public int getSizeShoes(double sizeUser) {
//        https://razmery.qoon.ru/articles/razmery-obuvi-sootvetstvie-tablitsa/
        int startSize = 15;
        double[] arraySizeShoes = {8.5, 9.5, 10.5, 11, 12, 12.5, 13, 14, 14.5, 15.5, 16, 16.5, 17, 17.5, 18, 19,
                                    20, 20.5, 21.5, 22, 22.5, 23.5, 24.5,
                                    25,25.5,26.5,27,27.5,28.5,29,29.5,30,30.5,31,31.5,32};
        double slevaGap;
        double spravaGap;
        for (int i = 0; i < arraySizeShoes.length; i++) {
            slevaGap = arraySizeShoes[i];
            spravaGap = arraySizeShoes[i + 1];

            System.out.println("slevaGap -> " + slevaGap + "\nspravaGap -> " + spravaGap);
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
    public int getSizeHead(double sizeUser) {
        int[] arraySizeHead ={
                35,40,44,46,47,48,49,50,51,52,53,54,55,56,57,58
        };
        int slevaGap;
        int spravaGap;
        for (int i = 0; i < arraySizeHead.length; i++) {
            slevaGap = arraySizeHead[i];
            spravaGap = arraySizeHead[i+1];
            if (slevaGap == sizeUser)
                if(i==0)
                    return slevaGap;

            if (spravaGap == sizeUser)
                if(i==0)
                    return slevaGap;
                else
                    return slevaGap;

            if (slevaGap < sizeUser && spravaGap > sizeUser)
                return slevaGap;
        }
        return 0;
    }

    static Map<Integer, List<String>> tableTypeSizeBay;
    public static Map<Integer, List<String>> tableTypeSizeHeadCircumference() {
        tableTypeSizeBay = new HashMap<>();
        tableTypeSizeBay.put(35, Arrays.asList("--", "--","--","--"));
        tableTypeSizeBay.put(40, Arrays.asList("--", "1","--","--"));
        tableTypeSizeBay.put(44, Arrays.asList("--", "1","--","--"));
        tableTypeSizeBay.put(46, Arrays.asList("--", "2","--","--"));
        tableTypeSizeBay.put(47, Arrays.asList("--", "2","5 7/8","--"));
        tableTypeSizeBay.put(48, Arrays.asList("--", "2","6 1/8","--"));
        tableTypeSizeBay.put(49, Arrays.asList("Детский S/M", "3","6 1/4","1-4 лет"));
        tableTypeSizeBay.put(50, Arrays.asList("Детский L/XL", "3","6 1/2","1-4 лет"));
        tableTypeSizeBay.put(51, Arrays.asList("XXS", "3","6 1/2","1-4 лет"));
        tableTypeSizeBay.put(52, Arrays.asList("XXS", "4","6","4-8 лет"));
        tableTypeSizeBay.put(53, Arrays.asList("XS", "4","6 5/8","4-8 лет"));
        tableTypeSizeBay.put(54, Arrays.asList("XS", "4","6 3/4","4-8 лет"));

        tableTypeSizeBay.put(55, Arrays.asList("S", "5","7","8-12 лет"));
        tableTypeSizeBay.put(56, Arrays.asList("M", "5","7","8-12 лет"));
        tableTypeSizeBay.put(57, Arrays.asList("L", "6","7 1/4","12-16 лет"));
        tableTypeSizeBay.put(58, Arrays.asList("L", "6","7 1/4","12-16 лет"));
        return tableTypeSizeBay;
    }
}
