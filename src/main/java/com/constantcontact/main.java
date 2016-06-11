package com.constantcontact;

import com.constantcontact.ConstantContactFactory;
import com.constantcontact.components.emailcampaigns.EmailCampaignResponse;
import com.constantcontact.components.emailcampaigns.tracking.clicks.EmailCampaignTrackingClick;
import com.constantcontact.components.emailcampaigns.tracking.opens.EmailCampaignTrackingOpen;
import com.constantcontact.components.generic.response.ResultSet;
import com.constantcontact.exceptions.service.ConstantContactServiceException;
import com.constantcontact.services.contactlists.IContactListService;
import com.constantcontact.services.emailcampaigns.IEmailCampaignService;
import com.constantcontact.services.emailcampaigns.tracking.IEmailCampaignTrackingService;
import com.constantcontact.util.RawApiRequestError;

import java.util.*;


/**
 * Created by aheffernan on 6/7/16.
 */
public class main {



    public static void main(String[] args){
        try {
            ConstantContactFactory constantContact = new ConstantContactFactory("3bb60361-f328-4e1c-81f0-472191be0d9e","xaqkn2gwyvyyk4wrthzgxwaj");

            IEmailCampaignTrackingService Tracking = constantContact.createEmailCampaignTrackingService();

            ResultSet<EmailCampaignTrackingClick> Clicks = Tracking.getClicksByLinkId("1124977684869", "465668312939", 100, "2016-06-08");

            Clicks.getMeta();
            List<EmailCampaignTrackingClick> Results = Clicks.getResults();

            String CLickedContact = Results.get(0).getEmailAddress();

            //ClickedContact
        }
        catch (ConstantContactServiceException e)
        {
            ConstantContactServiceException c = (ConstantContactServiceException)e.getCause();
            for (Iterator<RawApiRequestError> i = c.getErrorInfo().iterator(); i.hasNext(); ) {
                RawApiRequestError item = i.next();
                System.out.println(item.getErrorKey() + " : " + item.getErrorMessage());
            }
            e.printStackTrace();
        }
    }


}

