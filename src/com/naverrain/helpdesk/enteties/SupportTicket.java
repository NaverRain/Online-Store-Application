package com.naverrain.helpdesk.enteties;

public interface SupportTicket {

    Priority getPriority();

    int getSequentialNumber();

    RequestType getRequestType();
}
