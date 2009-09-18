/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.common.dao.GenericDAO;
import com.fivesticks.time.common.dao.GenericDAOFactory;
import com.fivesticks.time.common.util.SharedSystemProperties;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2LookupMap;
import com.fivesticks.time.contactv2.CustomerContactV2Map;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.settings.SettingTypeEnum;
import com.fivesticks.time.settings.SystemSettingsServiceDelegate;
import com.fivesticks.time.settings.SystemSettingsServiceDelegateFactory;
import com.fivesticks.time.system.fivesticks.CleanUpHiddenCustomersCommand;
import com.fivesticks.time.system.update.august.August_BuildDefaultSystemOwnerCommand;
import com.fivesticks.time.system.update.august.August_ConvertSystemSettingsCommand;
import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.DefaultSystemOwnerBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class DatabaseContentUpdateChecker {

    public static final String DEFAULT_VERSION_DATE = "05/01/2004";

    private static Log log = LogFactory
            .getLog(DatabaseContentUpdateChecker.class);

    /**
     * So this really takes care of the versions...
     */
    public void update() throws Exception {

        log.info("here I am in update.  Boy this IS a thrill!");

        String currentVersion = SystemSettingsServiceDelegateFactory.factory
                .build(DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .getSettingAsStringAllowNull(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName());

        if (currentVersion == null || currentVersion.trim().length() == 0) {
            log
                    .info("i'm still in update, but an older version -- the settings aren't owner specific");
            // SystemOwner systemOwner = DefaultSystemOwnerBroker
            // .getDefaultSystemOwner();
            // systemOwner.setKey(null);
            currentVersion = SystemSettingsServiceDelegateFactory.factory
                    .build(new SystemOwner()).getSettingAsStringAllowNull(
                            SettingTypeEnum.CONFIG_DATABASE_VERSION.getName());
        }

        log.info("current version is ... " + currentVersion);

        /*
         * this means this is our first use...
         */
        if (currentVersion == null) {
            currentVersion = DEFAULT_VERSION_DATE;
        }

        SimpleDate dateVersion = SimpleDate.factory
                .buildMidnight(currentVersion);

        handleUpdates(dateVersion);

    }

    /**
     * @param dateVersion
     */
    private void handleUpdates(SimpleDate dateVersion) throws Exception {

        log.info("here I am in the handleUpdates");

        /*
         * don't really need since we're no distributing.
         */
        // try {
        // updateJune2004_01(dateVersion);
        // } catch (Exception e) {
        // throw e;
        // }
        try {
            updateAugust2004_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        // try {
        // updateNovember2004_01(dateVersion);
        // } catch (Exception e) {
        // throw e;
        // }

        try {
            updateDecember2004_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateJanuary2005_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateApril2005_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateJune2005_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateNovember2005_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateMay2006_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateJuly2006_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateJuly2006_02(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateAug2006_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }

        try {
            updateAug2006_02(dateVersion);
        } catch (Exception e) {
            throw e;
        }
        
        try {
            updateJan2007_01(dateVersion);
        } catch (Exception e) {
            throw e;
        }       
        
        try {
            updateJan2007_02(dateVersion);
        } catch (Exception e) {
            throw e;
        }             
    }



    private void updateNovember2005_01(SimpleDate dateVersion)
            throws SystemOwnerServiceDelegateException {
        log.info("here I am in updateNovember2005_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("11/01/2005");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        SharedSystemProperties p = SharedSystemProperties.getSingleton();

        if (p.getDefaultOwnerKey() == null
                || p.getDefaultOwnerKey().trim().length() == 0) {
            throw new RuntimeException("Somehow default owner key is empty");
        }

        SystemOwner defaultOwner = SystemOwnerServiceDelegateFactory.factory
                .build().get(p.getDefaultOwnerKey());

        SystemSettingsServiceDelegate sd = SystemSettingsServiceDelegateFactory.factory
                .build(defaultOwner);

        sd.updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_NAME,
                "Reid Carlberg");
        sd.updateSetting(SettingTypeEnum.SETTING_MAIL_FROM_ADDRESS,
                "reid@fastwebupdates.com");
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_HOST,
                "smtp.emailsrvr.com");
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_PORT, "587");
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_USERNAME,
                "rsc1@fivesticks.com");
        sd.updateSetting(SettingTypeEnum.SETTING_SMTP_PASSWORD, "mark1969");

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateAugust2004_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateAugust2004-01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("08/01/2004");

        // basically, if the version of this software is less than 8/1/2004, we
        // should
        // do this update.
        if (dateVersion.getDate().compareTo(requires.getDate()) < 0) {

            SystemOwner updatedOwner = null;

            // try {
            // August_AddDefaultUserGroupsAndRights command = new
            // August_AddDefaultUserGroupsAndRights();
            // command.execute();
            // } catch (Exception e) {
            // throw e;
            // }

            // build a default system owner.
            try {
                August_BuildDefaultSystemOwnerCommand command = new August_BuildDefaultSystemOwnerCommand();
                command.execute();
                updatedOwner = command.getSystemOwner();
            } catch (Exception e) {
                throw e;
            }

            // mark everything as owned by that system.
            // try {
            // August_MarkAllCurrentContentAsOwnedCommand command =
            // (August_MarkAllCurrentContentAsOwnedCommand) SpringBeanBroker
            // .getBeanFactory()
            // .getBean(
            // August_MarkAllCurrentContentAsOwnedCommand.SPRING_BEAN_NAME);
            //
            // command.setNewOwner(updatedOwner);
            // command.execute();
            // } catch (Exception e) {
            // throw e;
            // }

            // convert system settings.
            try {
                August_ConvertSystemSettingsCommand command = new August_ConvertSystemSettingsCommand(
                        updatedOwner);
                command.execute();
            } catch (Exception e) {
                throw e;
            }

            SystemSettingsServiceDelegateFactory.factory.build(
                    DefaultSystemOwnerBroker.getDefaultSystemOwner())
                    .updateSetting(
                            SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                            requires);

            dateVersion = requires;
        }
    }



    private void updateDecember2004_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateDecember2004_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("12/30/2004");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        /*
         * 2005-07-18 shouldn't be here during regular run.
         */
        // throw new RuntimeException("shouldn't be here...");
        // new Update2004_December_UserTypeUpdateCommand().execute();
        //
        // SystemSettingsServiceDelegate.factory.build(
        // DefaultSystemOwnerBroker.getDefaultSystemOwner())
        // .updateSetting(
        // SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
        // requires);
        //
        dateVersion = requires;

    }

    private void updateJanuary2005_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJanuary2005_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("01/01/2005");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        new Update2005_January_SystemSettingsUpdateCommand().execute();

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateApril2005_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateApril2005_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("04/01/2005");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        new Update2005_April_SystemSettingsUpdateCommand().execute();

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateJune2005_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJune2005_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("06/20/2005");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        new Update2005_June_AccountTransactionUpdateCommand().execute();

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateMay2006_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateMay20065_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("05/10/2006");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        new Update2006_May_ActivityElapsedChargeableUpdateCommand().execute();

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateJuly2006_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJuly2006_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("07/01/2006");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        StringBuffer sd = new StringBuffer();

        sd.append("UPDATE " + SystemOwner.class.getName()
                + " set expirationDate = '2006-08-31 23:59:59'");

        GenericDAOFactory.factory.build().execute(sd.toString());

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateJuly2006_02(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJuly2006_02");
        SimpleDate requires = SimpleDate.factory.buildMidnight("07/02/2006");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        StringBuffer sd = new StringBuffer();

        sd.append("from " + Project.class.getName()
                + " where shortName like '% %'");

        log.info("project selection: " + sd.toString());

        GenericDAO dao = GenericDAOFactory.factory.build();

        Collection projectsWithSpaces = dao.find(sd.toString(), null);

        for (Iterator iter = projectsWithSpaces.iterator(); iter.hasNext();) {
            Project element = (Project) iter.next();
            log.info("updating element " + element.getShortName());

            element.setShortName(element.getShortName().replaceAll(" ", "_"));

            dao.save(element);
        }

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateAug2006_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateAug2006_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("08/01/2006");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...");

        StringBuffer sd = new StringBuffer();

        sd.append("from " + Activity.class.getName()
                + " where timeDate not like '%00:00:00'");

        GenericDAO dao = GenericDAOFactory.factory.build();

        Collection activities = dao.find(sd.toString(), null);

        for (Iterator iter = activities.iterator(); iter.hasNext();) {
            Activity element = (Activity) iter.next();
            log.info("updating element " + element.getDate());

            element.setDate(SimpleDate.factory.buildMidnight(element.getDate())
                    .getDate());

            dao.save(element);
        }

        GenericDAOFactory.factory.build().execute(
                "UPDATE " + SystemOwner.class.getName() + " set account = '"
                        + AccountType.DEMO.getName() + "'");

        GenericDAOFactory.factory.build().execute(
                "UPDATE " + SystemOwner.class.getName()
                        + " set requiresAccountUpdate = false");

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }

    private void updateAug2006_02(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateAug2006_02 ");
        SimpleDate requires = SimpleDate.factory.buildMidnight("08/02/2006");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        /*
         * 2006-09-02 Reid this is just to get rid of the customers we have that
         * we don't need the old eBay customers.
         */
        log.debug("just before...cleanup fivesticks hidden customers");
        {
            SystemOwner fivesticks = SystemOwnerServiceDelegateFactory.factory
                    .build().get("CXZASTPKGU");
            if (fivesticks == null) {
                throw new RuntimeException("couldn't find FiveSticks");
            }
            new CleanUpHiddenCustomersCommand().execute(fivesticks);

        }

        log.debug("just before...convert contacts");

        String hql = " from " + Contact.class.getName();

        Collection c = GenericDAOFactory.factory.build().find(hql);

        GenericDAO dao = GenericDAOFactory.factory.build();

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            Contact element = (Contact) iter.next();

            Customer customer = (Customer) dao.get(Customer.class, element
                    .getCustId());

            ContactV2 newElement = new ContactV2();

            newElement.setNameFirst(element.getNameFirst());
            newElement.setNameLast(element.getNameLast());

            if (StringUtils.hasText(element.getNameFirst())
                    && StringUtils.hasText(element.getNameLast())) {
                newElement.setNameFormatted(element.getNameFirst() + " "
                        + element.getNameLast());
            }
            if (StringUtils.hasText(element.getPrimaryPhone()))
                newElement.setOrganizationPhone(element.getPrimaryPhone());

            if (StringUtils.hasText(element.getAlternatePhone()))
                newElement.setMiscPhoneMobile(element.getAlternatePhone());

            if (StringUtils.hasText(element.getPrimaryEmail()))
                newElement.setOrganizationEmail(element.getPrimaryEmail());

            if (StringUtils.hasText(element.getAlternateEmail1()))
                newElement.setHomeEmail(element.getAlternateEmail1());

            StringBuffer sb = new StringBuffer();
            if (StringUtils.hasText(element.getAlternateEmail2())) {
                sb.append("Alt2: " + element.getAlternateEmail2());
            }

            if (StringUtils.hasText(element.getAlternateEmail3())) {
                sb.append("Alt3: " + element.getAlternateEmail3());
            }

            if (StringUtils.hasText(element.getAlternateEmail4())) {
                sb.append("Alt3: " + element.getAlternateEmail4());
            }

            if (sb.length() > 0) {
                newElement.setMiscDescription(sb.toString());
            }

            if (customer != null) {

                newElement.setOrganizationName(customer.getName());
                if (StringUtils.hasText(customer.getStreet1()))
                    newElement.setOrganizationAddress1(customer.getStreet1());

                if (StringUtils.hasText(customer.getStreet2()))
                    newElement.setOrganizationAddress2(customer.getStreet2());

                if (StringUtils.hasText(customer.getCity()))
                    newElement.setOrganizationCity(customer.getCity());

                if (StringUtils.hasText(customer.getState()))
                    newElement.setOrganizationRegion(customer.getState());

                if (StringUtils.hasText(customer.getZip()))
                    newElement.setOrganizationPostalCode(customer.getZip());

                if (StringUtils.hasText(customer.getCountry()))
                    newElement.setOrganizationCountry(customer.getCountry());

            } else {
                log.info("found contact without customer "
                        + element.getNameFirst() + " " + element.getNameLast());
            }

            newElement.setOwnerKey(element.getOwnerKey());

            dao.save(newElement);

            CustomerContactV2Map m = new CustomerContactV2Map();
            m.setOwnerKey(element.getOwnerKey());
            m.setContactV2Id(newElement.getId());
            m.setCustomerId(element.getCustId());
            dao.save(m);

        }

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }
    
    private void updateJan2007_01(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJan2007_01");
        SimpleDate requires = SimpleDate.factory.buildMidnight("01/05/2007");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        /*
         * 2006-09-02 Reid this is just to get rid of the customers we have that
         * we don't need the old eBay customers.
         */
        log.debug("just before...cleanup fivesticks hidden customers");

        
        String hql = " from " + ContactV2LookupMap.class.getName();
      
        GenericDAO dao = GenericDAOFactory.factory.build();

        Collection c = dao.find(hql);
        
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            ContactV2LookupMap element = (ContactV2LookupMap) iter.next();
            
            ContactV2 v2 = (ContactV2) dao.get(ContactV2.class, element.getContactV2Id());
            
            Lookup lu = (Lookup) dao.get(Lookup.class, element.getLuId());
            
            Set<Lookup> newLu = new TreeSet<Lookup>();
            newLu.add(lu);
            
            if (element.getLuType().equals(LookupType.CONTACT_CLASS.getName())) {
                if (v2.getClasses() != null) {
                    v2.getClasses().addAll(newLu);
                } else {
                    v2.setClasses(newLu);
                }
            }
            
            if (element.getLuType().equals(LookupType.CONTACT_INTEREST.getName())) {
                if (v2.getInterests() != null) {
                    v2.getInterests().addAll(newLu);
                } else {
                    v2.setInterests(newLu);
                }
            }    
            
            if (element.getLuType().equals(LookupType.CONTACT_SOURCE.getName())) {
                if (v2.getSources() != null) {
                    v2.getSources().addAll(newLu);
                } else {
                    v2.setSources(newLu);
                }
            }           
            
            dao.save(v2);
            
        }
        
        

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }    

    private void updateJan2007_02(SimpleDate dateVersion) throws Exception {
        log.info("here I am in updateJan2007_02");
        SimpleDate requires = SimpleDate.factory.buildMidnight("01/09/2007");

        if (dateVersion.getDate().compareTo(requires.getDate()) >= 0) {
            return;
        }

        log.debug("just before...cleanup fivesticks hidden customers");
        
        String hql = " from " + AccountTransaction.class.getName() + " where acctActivityId is not null";
      
        GenericDAO dao = GenericDAOFactory.factory.build();

        Collection c = dao.find(hql);
        
        int i = 0;
        
        for (Iterator iter = c.iterator(); iter.hasNext();) {
            AccountTransaction element = (AccountTransaction) iter.next();
            
            String tHql = " update " + Activity.class.getName() + " set timeAcctId = " + element.getId() + " where timeNewId = " + element.getActivityId();
            
            dao.execute(tHql);
        
            i++;
            
            if (i % 100 == 0) {
                log.info("Updated " + i);
            }
        }
        

        log.debug("just after...");

        SystemSettingsServiceDelegateFactory.factory.build(
                DefaultSystemOwnerBroker.getDefaultSystemOwner())
                .updateSetting(
                        SettingTypeEnum.CONFIG_DATABASE_VERSION.getName(),
                        requires);

        dateVersion = requires;

    }    
}