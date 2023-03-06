package org.guce.siat.common.utils.filter;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author tadzotsa
 */
public class ContentTypeFileFilter implements FileFilter {

    private final String contentType;

    public ContentTypeFileFilter(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean accept(File pathname) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
