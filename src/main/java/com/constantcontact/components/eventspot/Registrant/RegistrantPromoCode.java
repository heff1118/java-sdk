package com.constantcontact.components.eventspot.Registrant;

import com.constantcontact.components.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * RegistrantPromoCode for the Events in Constant Contact.
 *
 * @author ConstantContact
 * @see EventSpotService
 */
public class RegistrantPromoCode extends Component implements Serializable {

    private static final long serialVersionUID = -572514180630845836L;

    @JsonIgnore
    private Double totalDiscount;

    @JsonIgnore
    private PromoCodeInfo promoCodeInfo;


    /**
     * Get the tota discount.
     *
     * @return The {@link #totalDiscount}
     */
    @JsonProperty("total_discount")
    public Double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Get the promocode info.
     *
     * @return The {@link #promoCodeInfo}
     */
    @JsonProperty("promo_code_info")
    public PromoCodeInfo getPromoCodeInfo() {
        return promoCodeInfo;
    }

    /**
     * Set the total discount.
     *
     * @param totalDiscount The total discount.
     */
    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    /**
     * Set the promocode info.
     *
     * @param promoCodeInfo The promocode info.
     */
    public void setPromoCodeInfo(PromoCodeInfo promoCodeInfo) {
        this.promoCodeInfo = promoCodeInfo;
    }



    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RegistrantPromoCode [total_discount=");
        builder.append(totalDiscount);
        builder.append(", promo_code_info=");
        builder.append(promoCodeInfo);
        builder.append("]");

        return builder.toString();
    }
}
