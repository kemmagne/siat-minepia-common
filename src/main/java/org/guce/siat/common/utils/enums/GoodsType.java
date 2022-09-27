/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.guce.siat.common.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tadzotsa
 */
public enum GoodsType {

    GRUMES,
    DEBITES,
    CACAO_CAFE,
    PLANTES_MEDICINALES,
    COTON,
    AUTRES;

    public static List<GoodsType> getAll() {
        return new ArrayList<>(Arrays.asList(GoodsType.values()));
    }

    public static List<String> getAllNames() {

        List<GoodsType> all = getAll();

        List<String> allNames = new ArrayList<>();

        for (GoodsType gt : all) {
            allNames.add(gt.name());
        }

        return allNames;
    }

    public static List<String> getAirPortProduct() {
        return new ArrayList<>(Arrays.asList(GoodsType.AUTRES.name(), GoodsType.PLANTES_MEDICINALES.name()));
    }
}
