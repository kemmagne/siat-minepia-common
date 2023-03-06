package org.guce.siat.common.utils.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author tadzotsa
 */
public class ExtentionFileFilter implements FilenameFilter {

    private final String extention;

    public ExtentionFileFilter(String extention) {
        this.extention = extention;
    }

    @Override
    public boolean accept(File dir, String fileName) {
        return fileName.endsWith(extention);
    }

}
