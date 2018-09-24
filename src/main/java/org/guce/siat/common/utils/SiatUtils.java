package org.guce.siat.common.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.guce.siat.common.model.AbstractModel;
import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.Entity;
import org.guce.siat.common.model.Ministry;
import org.guce.siat.common.model.Organism;
import org.guce.siat.common.model.Service;
import org.guce.siat.common.model.SubDepartment;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.BureauType;
import org.xml.sax.InputSource;

/**
 * The Class SiatUtils.
 */
public final class SiatUtils {

    private static final String UNSUPPORTED_ADMINISTRATION_TYPE = "unsupported administration type";

    /**
     * Instantiates a new siat utils.
     */
    private SiatUtils() {
    }

    /**
     * Gets the user ids.
     *
     * @param userList the user list
     * @return the user ids
     */
    @SuppressWarnings("unchecked")
    public static List<Long> getUserIds(final List<User> userList) {
        return (List<Long>) CollectionUtils.collect(userList,
                new Transformer() {
            @Override
            public Object transform(final Object user) {
                return ((User) user).getId();
            }
        });
    }

    public static List<Long> getEntitiesIds(final List<? extends AbstractModel> entities) {
        return (List<Long>) CollectionUtils.collect(entities,
                new Transformer() {
            @Override
            public Object transform(final Object entity) {
                return ((AbstractModel) entity).getId();
            }
        });
    }

    /**
     * Find combined bureaus ids by administration list.
     *
     * @param administrationList the administration list
     * @return the list
     */
    public static List<Bureau> findCombinedBureausByAdministrationList(
            final List<? extends Administration> administrationList) {
        final List<Bureau> extractedEntitiesList = new ArrayList<>();
        for (final Object administration : administrationList) {
            if (administration instanceof Entity) {

                if (administration instanceof Bureau
                        && BureauType.BUREAU_REGIONAL
                                .equals(((Bureau) administration)
                                        .getBureauType())) {
                    extractedEntitiesList.add((Bureau) administration);
                } else {
                    extractedEntitiesList
                            .addAll(findAssociateRegionalOffices((Entity) administration));
                }
            } else {
                switch (administration.getClass().getSimpleName()) {
                    case "Ministry":
                        extractedEntitiesList
                                .addAll(findCombinedBureausByAdministrationList(((Ministry) administration)
                                        .getOrganismsList()));
                        break;
                    case "Organism":
                        extractedEntitiesList
                                .addAll(findCombinedBureausByAdministrationList(((Organism) administration)
                                        .getSubDepartmentsList()));
                        break;
                    case "SubDepartment":
                        extractedEntitiesList
                                .addAll(findCombinedBureausByAdministrationList(((SubDepartment) administration)
                                        .getServicesList()));
                        break;
                    case "Service":
                        extractedEntitiesList
                                .addAll(findCombinedBureausByAdministrationList(((Service) administration)
                                        .getEntityList()));
                        break;
                    default:
                        break;
                }
            }
        }
        return extractedEntitiesList;
    }

    /**
     * Find associate regional offices.
     *
     * @param entity the entity
     * @return the list
     */
    @SuppressWarnings("unchecked")
    private static List<Bureau> findAssociateRegionalOffices(final Entity entity) {
        if (entity.getClass().getSimpleName().equalsIgnoreCase("Bureau")
                || entity.getClass().getSimpleName().equals("Laboratory")
                || entity.getClass().getSimpleName().equals("TreatmentCompany")) {
            final List<Entity> entitiesList = entity.getService()
                    .getEntityList();
            return (List<Bureau>) CollectionUtils.select(entitiesList,
                    new Predicate() {
                @Override
                public boolean evaluate(final Object object) {
                    return object instanceof Bureau;
                }
            });
        }
        throw new UnsupportedOperationException(UNSUPPORTED_ADMINISTRATION_TYPE);
    }

    /**
     * Gets the recursive sub administations.
     *
     * @param administrations the administrations
     * @return the recursive sub administations
     */
    public static List<Administration> getRecursiveSubAdministations(List<? extends Administration> administrations) {

        List<Administration> subAdministrationsList = new ArrayList<Administration>();

        for (final Object administration : administrations) {

            if (administration instanceof Entity) {
                subAdministrationsList.add((Administration) administration);

            } else {

                switch (administration.getClass().getSimpleName()) {
                    case "Ministry":
                        subAdministrationsList
                                .addAll(getRecursiveSubAdministations(((Ministry) administration)
                                        .getOrganismsList()));
                        break;
                    case "Organism":
                        subAdministrationsList
                                .addAll(getRecursiveSubAdministations(((Organism) administration)
                                        .getSubDepartmentsList()));
                        break;
                    case "SubDepartment":
                        subAdministrationsList
                                .addAll(getRecursiveSubAdministations(((SubDepartment) administration)
                                        .getServicesList()));
                        break;
                    case "Service":
                        subAdministrationsList
                                .addAll(getRecursiveSubAdministations(((Service) administration)
                                        .getEntityList()));
                        break;
                    default:
                        break;
                }
            }
        }
        return subAdministrationsList;
    }

    /**
     * Gets the value from xml.
     *
     * @param xmlMessage the xml message
     * @param balise the balise
     * @return the value from xml
     */
    public static String getValueFromXml(final byte[] xmlMessage, final String balise) {

        final XPath xPath = XPathFactory.newInstance().newXPath();
        final InputSource source = new InputSource(new ByteArrayInputStream(xmlMessage));

        try {

            return xPath.compile(balise).evaluate(source);

        } catch (final XPathExpressionException e) {
            Logger.getLogger(SiatUtils.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }

    }

}

