package com.naverrain.persistence.utils.comparator;

import com.naverrain.persistence.enteties.SupportTicket;

import java.util.Comparator;

public class CustomSupportTicketsComparator implements Comparator<SupportTicket> {


    @Override
    public int compare(SupportTicket supportTicket1, SupportTicket supportTicket2) {
        if (supportTicket1 == null || supportTicket2 == null
                || supportTicket1.getPriority() == null || supportTicket2.getPriority() == null)
            return 0;

        int result = supportTicket1.getPriority().compareTo(supportTicket2.getPriority());

        if (result == 0){
            result = supportTicket1.getSequentialNumber() - supportTicket2.getSequentialNumber();
        }

        return result;
    }
}
