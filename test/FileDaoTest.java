package org.guce.siat.common.dao;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.guce.siat.common.model.File;
import org.guce.siat.common.model.FileType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 *
 * @author ht
 */
//@Transactional
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(classes = {H2DataSourceConfig.class})
public class FileDaoTest extends TestCase {

    private static final int NB_FILES = 10;

    private static final String NUMERO_DEMANDE = "NUMERO_DEMANDE";

    @Mock
    private TestDataBuilder builder;

    @Ignore
    @Test
    public void test_findByNumeroDemandeAndFileType() {
        List<File> files = getFiles();
        List<File> filesList = builder.getFileDao().findByNumeroDemandeAndFileType(NUMERO_DEMANDE, files.get(0).getFileType());
        Assert.assertTrue(filesList.isEmpty());
        int nb = 3;
        for (int i = 0; i < nb; i++) {
            files.get(i).setNumeroDemande(NUMERO_DEMANDE);
            builder.getFileDao().update(files.get(i));
        }
        filesList = builder.getFileDao().findByNumeroDemandeAndFileType(NUMERO_DEMANDE, files.get(0).getFileType());
        Assert.assertFalse(filesList.isEmpty());
        Assert.assertEquals(nb, filesList.size());
        Assert.assertEquals(files.get(0).getId(), filesList.get(0).getId());
        builder.getFileDao().deleteList(files);
    }

    @Ignore
    @Test
    public void test_findByNumeroDemande() {
        List<File> files = getFiles();
        List<File> filesList = builder.getFileDao().findByNumeroDemande(NUMERO_DEMANDE);
        Assert.assertTrue(filesList.isEmpty());
        int nb = 4;
        for (int i = 0; i < nb; i++) {
            files.get(i).setNumeroDemande(NUMERO_DEMANDE);
            builder.getFileDao().update(files.get(i));
        }
        filesList = builder.getFileDao().findByNumeroDemande(NUMERO_DEMANDE);
        Assert.assertFalse(filesList.isEmpty());
        Assert.assertEquals(nb, filesList.size());
        Assert.assertEquals(files.get(0).getId(), filesList.get(0).getId());
        builder.getFileDao().deleteList(files);
    }

    private List<File> getFiles() {
        List<File> files = new ArrayList<>(NB_FILES);
        FileType fileType = builder.getFileType();
        for (int i = 0; i < NB_FILES; i++) {
            File file = builder.getFile();
            file.setFileType(fileType);
            builder.getFileDao().update(file);
            files.add(file);
        }
        return files;
    }

}
