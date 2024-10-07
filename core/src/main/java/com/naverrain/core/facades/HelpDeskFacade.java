package com.naverrain.core.facades;

import com.naverrain.persistence.enteties.SupportTicket;

public interface HelpDeskFacade {

    void addNewSupportTicket(SupportTicket supportTicket);

    SupportTicket getNextSupportTicket();

    /**
     * @return amount of tickets that are not processed
     */
    int getNumberOfTickets();
}
