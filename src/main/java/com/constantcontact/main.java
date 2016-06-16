package com.constantcontact;

import com.constantcontact.components.contacts.Contact;
import com.constantcontact.components.contacts.ContactList;
import com.constantcontact.components.emailcampaigns.tracking.clicks.EmailCampaignTrackingClick;
import com.constantcontact.components.generic.response.ResultSet;
import com.constantcontact.exceptions.service.ConstantContactServiceException;
import com.constantcontact.services.contactlists.IContactListService;
import com.constantcontact.services.contacts.IContactService;
import com.constantcontact.services.emailcampaigns.tracking.IEmailCampaignTrackingService;
import com.constantcontact.util.RawApiRequestError;

import java.util.Iterator;
import java.util.List;


/**
 * Created by aheffernan on 6/7/16.
 */
public class main {



    public static void main(String[] args) {

        for (int t = 0; t < 60; t++) {

            try {

                ConstantContactFactory constantContact = new ConstantContactFactory("3bb60361-f328-4e1c-81f0-472191be0d9e", "xaqkn2gwyvyyk4wrthzgxwaj");

                //Get tracks for specific Campaigns
                IEmailCampaignTrackingService Tracking = constantContact.createEmailCampaignTrackingService();
                ResultSet<EmailCampaignTrackingClick> Clicks = Tracking.getClicksByLinkId("1124977684869", "465668312939", 100, "2016-06-08");

                //set Contacts services
                IContactListService Lists = constantContact.createContactListService();
                List<ContactList> AddtoThisList = Lists.getLists("2016-01-08");
                IContactService IcontactService = constantContact.createContactService();
                List<EmailCampaignTrackingClick> Results = Clicks.getResults();

                for (EmailCampaignTrackingClick e : Results) {

                    String CLickedContact = e.getEmailAddress();
                    ResultSet<Contact> ContactSet = IcontactService.getContactByEmail(CLickedContact);

                    for (Contact x : ContactSet.getResults()) {

                        x.setLists(AddtoThisList);

                        IcontactService.updateContact(x, false);
                    }

                }

            } catch (ConstantContactServiceException e) {
                ConstantContactServiceException c = (ConstantContactServiceException) e.getCause();
                for (Iterator<RawApiRequestError> i = c.getErrorInfo().iterator(); i.hasNext(); ) {
                    RawApiRequestError item = i.next();
                    System.out.println(item.getErrorKey() + " : " + item.getErrorMessage());
                }
                e.printStackTrace();
            }

            try {
                Thread.sleep(15000);

                System.out.println("Sleeping");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

