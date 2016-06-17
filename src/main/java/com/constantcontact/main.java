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
import java.util.ArrayList;


/**
 * Created by aheffernan on 6/7/16.
 */
public class main {

    public static void main(String[] args) {

        ArrayList<String> LinkIds = new ArrayList<String>();
        List<ContactList> ListIds = new ArrayList<ContactList>();

        LinkIds.add("465668312939");
        LinkIds.add("466110148359");
        LinkIds.add("466114052494");

        for (int t = 0; t < 6000; t++) {

            try {

                ConstantContactFactory constantContact = new ConstantContactFactory("3bb60361-f328-4e1c-81f0-472191be0d9e", "xaqkn2gwyvyyk4wrthzgxwaj");
                IContactListService Lists = constantContact.createContactListService();

                int i = 0;
                while (LinkIds.size() > i) {

                    //Get All tracks for specific Campaigns
                    IEmailCampaignTrackingService Tracking = constantContact.createEmailCampaignTrackingService();
                    ResultSet<EmailCampaignTrackingClick> Clicks = Tracking.getClicksByLinkId("1125030959898", LinkIds.get(i), 100, "2016-06-08");

                    //Initialize Contacts service
                    IContactService IcontactService = constantContact.createContactService();

                    //Loop through each click
                    for (EmailCampaignTrackingClick e:Clicks.getResults()) {

                        String CLickedContact = e.getEmailAddress();
                        String selectedLink = e.getLinkId();

                        ResultSet<Contact> ContactSet = IcontactService.getContactByEmail(CLickedContact);

                        for (Contact x : ContactSet.getResults()) {

                            if(selectedLink.equals("465668312939")) {
                                ListIds.add(0, Lists.getList("1092818561"));//Vegetarian
                            }
                            if(selectedLink.equals("466114052494")) {
                                ListIds.add(0, Lists.getList("1840063460"));//Pailo
                            }
                            if(selectedLink.equals("466110148359")) {
                                ListIds.add(0, Lists.getList("1780133628"));//Meat Eaters
                            }

                            x.setLists(ListIds);
                            IcontactService.updateContact(x, false);
                        }

                        ListIds.clear();
                    }

                    i++;

                }

                try {

                    Thread.sleep(15000);
                    System.out.println("Sleeping " + t);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }catch(ConstantContactServiceException e){
                ConstantContactServiceException c = (ConstantContactServiceException) e.getCause();
                for (Iterator<RawApiRequestError> i = c.getErrorInfo().iterator(); i.hasNext(); ) {
                    RawApiRequestError item = i.next();
                    System.out.println(item.getErrorKey() + " : " + item.getErrorMessage());
                }
                e.printStackTrace();
            }
        }
    }
}

