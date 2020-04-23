package org.guce.siat.common.utils;

import java.util.Arrays;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileField;
import org.guce.siat.common.model.FileFieldValue;
import org.guce.siat.common.model.FileType;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;

/**
 *
 * @author tadzotsa
 */
public class FileUtilsTest {

    @Ignore
    @Test
    public void testApplyModifications() {
        File source = new File();
        File dest = new File();
        FileType fileType = new FileType();
        fileType.setId(Long.MIN_VALUE);
        source.setFileType(fileType);
        dest.setFileType(fileType);

        String code = "CODE";
        FileField ff = new FileField();
        ff.setCode(code);
        ff.setUpdatable(Boolean.TRUE);

        String value = "FINALVALUE";

        FileFieldValue ffv;

        ffv = new FileFieldValue();
        ffv.setFileField(ff);
        ffv.setValue(value);
        source.setFileFieldValueList(Arrays.asList(ffv));

        ffv = new FileFieldValue();
        ffv.setFileField(ff);
        ffv.setValue("FIRSTVALUE");
        dest.setFileFieldValueList(Arrays.asList(ffv));

        FileUtils.applyModifications(source, dest);

        Assert.assertEquals(value, dest.getFileFieldValueList().get(0).getValue());
    }

    @Ignore
    @Test
    public void testGetRootFile() {

        long id = 1l;

        File root = new File();
        root.setId(id++);

        File current = new File();
        current.setId(id++);
        current.setParent(root);

        Assert.assertEquals(root, FileUtils.getRootFile(current));
    }

}
