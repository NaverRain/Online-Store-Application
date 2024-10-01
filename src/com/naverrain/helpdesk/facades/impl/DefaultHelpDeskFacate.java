package com.naverrain.helpdesk.facades.impl;

import com.naverrain.helpdesk.enteties.SupportTicket;
import com.naverrain.helpdesk.utils.CustomSupportTicketsComparator;
import com.naverrain.helpdesk.facades.HelpDeskFacade;

import java.util.PriorityQueue;
import java.util.Queue;

public class DefaultHelpDeskFacate implements HelpDeskFacade {

    private Queue<SupportTicket> tickets;

    {
        tickets = new PriorityQueue<>(new CustomSupportTicketsComparator());
    }

    @Override
    public void addNewSupportTicket(SupportTicket supportTicket) {
        tickets.offer(supportTicket);
    }

    @Override
    public SupportTicket getNextSupportTicket() {
        return tickets.poll();
    }

    @Override
    public int getNumberOfTickets() {
        return tickets.size();
    }
}
