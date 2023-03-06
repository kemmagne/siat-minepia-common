package org.guce.siat.common.dao.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import org.guce.siat.common.dao.HistoryTraceDao;
import org.guce.siat.common.model.FileItemFieldValueId;
import org.guce.siat.common.model.HistoryTrace;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.DateUtils;
import org.guce.siat.common.utils.FilterTrace;
import org.guce.siat.common.utils.ObjectMapperFacade;
import org.guce.siat.common.utils.TransformObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class HistoryTraceDaoImpl.
 */
@Repository("historyTraceDao")
@Transactional(propagation = Propagation.REQUIRED)
public class HistoryTraceDaoImpl extends AbstractJpaDaoImpl<HistoryTrace> implements HistoryTraceDao {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HistoryTraceDaoImpl.class);
    
    
    private final TransformObject transformObject = new TransformObject();

    /**
     * Instantiates a new bureau dao impl.
     */
    public HistoryTraceDaoImpl() {
        super();
        setClasse(HistoryTrace.class);
    }
/**
 * enregistrement d'une historisation
 * @param <T>
 * @param entity
 * @param operation
 * @param user 
 */
    @Override
    public <T> void makeHistory(T entity, String operation, User user) {
        //To change body of generated methods, choose Tools | Templates.

        makeHistory(entity, operation, user, null);
    }

    /**
     * enregistrement d'une historisation en considérant ses sous classe
     * @param <C>
     * @param <S>
     * @param entity
     * @param operation
     * @param user
     * @param clazz 
     */
    @Override
    public <C, S extends C> void makeHistory(S entity, String operation, User user, Class<C> clazz) {
        //To change body of generated methods, choose Tools | Templates.
        Class classz = clazz != null ? clazz : entity.getClass();
        java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.INFO, " Valeur de l'entité " + entity.getClass(), entity);
       
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateFormated = df.format(new Date());
        Date date = null;
        try {
            date = df.parse(dateFormated);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        HistoryTrace coreHistoryTrace = new HistoryTrace();
        coreHistoryTrace.setHtAction(operation);
        coreHistoryTrace.setHtCreatedDate(date);
        coreHistoryTrace.setHtModel(classz.getCanonicalName());

        // recupérer le champ avec l'annotation @ID 
        // coreHistoryTrace.setHtModelId(entity.getClass());
        for (Field field : FieldUtils.getAllFieldsList(classz)) {
            if (field.isAnnotationPresent(Id.class)) {
                try {
                    field.setAccessible(true);
                    coreHistoryTrace.setHtModelId(String.valueOf(field.get(entity)));
                    break;
                } catch (IllegalArgumentException ex) {
                    java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (field.isAnnotationPresent(EmbeddedId.class)) {
                try {
                    field.setAccessible(true);
                    if(field.get(entity) instanceof FileItemFieldValueId){
                       // String fileItem = field.get(entity).;
                       System.out.println(" ***** *FileItemFieldValueId embeded id *** "+String.valueOf(field.get(entity)));
                    }
                    //coreHistoryTrace.setHtModelId(String.valueOf(field.get(entity)));
                    break;
                } catch (IllegalArgumentException ex) {
                    java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ObjectMapperFacade objectMapperFacade = new ObjectMapperFacade();
        String resultJson = "";

        resultJson = objectMapperFacade.getTraceAsJson(transformObject.transformToDto(entity, classz));

        java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.INFO, " Valeur de HtValue " + resultJson, entity);
        coreHistoryTrace.setHtValue(resultJson.getBytes());
        //}
        // utilisateur connecté
        coreHistoryTrace.setUserAction(user);
        //coreHistoryFacade.create(coreHistoryTrace);
        super.entityManager.persist(coreHistoryTrace);
        super.entityManager.flush();
        java.util.logging.Logger.getLogger(HistoryTraceDaoImpl.class.getName()).log(Level.INFO, " Fin de la persistance ", entity);
        
    }
    
    /**
     * Rechercher les traces d'un utilisateur 
     * @param coreUser
     * @return 
     */

    @Override
    public List<HistoryTrace> findTraceByUser(User coreUser) {
        //To change body of generated methods, choose Tools | Templates.
        String query = "SELECT ht from HistoryTrace ht where 1=1 ";
        if (coreUser != null) {
            query = query + " and ht.userAction = :coreUser ";
        }
        query = query + " ORDER BY ht.htCreatedDate ";
        Query q = super.entityManager.createQuery(query);
        if (coreUser != null) {
            q.setParameter("coreUser", coreUser);
        }
        return q.getResultList();
    }
    
    /**
     * trace suivant un filtre précis
     * @param filterTrace
     * @return 
     */

    @Override
    public List<HistoryTrace> findTraceByFilter(FilterTrace filterTrace) {
        //To change body of generated methods, choose Tools | Templates.

        StringBuilder query = new StringBuilder();
        query.append("SELECT ht from CoreHistoryTrace ht where 1=1 ");
        if (!StringUtils.isBlank(filterTrace.getUserAction())) {
            query.append(" and ht.userAction.userlogin = :coreUser ");
        }
        if (!StringUtils.isBlank(filterTrace.getModelId())) {
            query.append(" and ht.htModelId = :modelId ");
        }
        if (!StringUtils.isBlank(filterTrace.getModel())) {
            query.append(" and ht.htModel= :model ");
        }
        if (filterTrace.getDateStart() != null) {
            query.append(" AND ht.htCreatedDate >= :dateStart ");
        }
        if (filterTrace.getDateEnd() != null) {
            query.append(" AND ht.htCreatedDate < :dateEnd");
        }

        query.append(" ORDER BY ht.htCreatedDate ");
        System.out.println("La requete est " + query.toString());

        TypedQuery<HistoryTrace> q = super.entityManager.createQuery(query.toString(), HistoryTrace.class);

        if (!StringUtils.isBlank(filterTrace.getUserAction())) {
            q.setParameter("coreUser", filterTrace.getUserAction());
        }
        if (!StringUtils.isBlank(filterTrace.getModelId())) {
            q.setParameter("modelId", filterTrace.getModelId());
        }
        if (!StringUtils.isBlank(filterTrace.getModel())) {
            q.setParameter("model", filterTrace.getModel());
        }
        if (filterTrace.getDateStart() != null) {
            q.setParameter("dateStart", filterTrace.getDateStart());

        }
        if (filterTrace.getDateEnd() != null) {
            q.setParameter("dateEnd", DateUtils.addDays(filterTrace.getDateEnd(), 1));
        }

        java.util.logging.Logger.getLogger(getClass().getName()).info(" Requête générée " + q.toString());

        return q.getResultList();
    }

    /**
     * les models d'une historisation
     * @return 
     */
    @Override
    public List<String> findModelTrace() {
        //To change body of generated methods, choose Tools | Templates.
        String query = "SELECT distinct (ht.htModel) from HistoryTrace ht ";

        Query q = super.entityManager.createQuery(query, HistoryTrace.class);

        return q.getResultList();
    }
}
