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
 * @author boris.tomfeu
 */
public enum PackageType {
    CONVENTIONNEL,
    CONTENEURISE,
    VRAC;

    public static List<PackageType> getAll() {
        return new ArrayList<>(Arrays.asList(PackageType.values()));
    }

    public static List<String> getAllNames() {

        List<PackageType> all = getAll();

        List<String> allNames = new ArrayList<>();

        for (PackageType pt : all) {
            allNames.add(pt.name());
        }

        return allNames;
    }
}
