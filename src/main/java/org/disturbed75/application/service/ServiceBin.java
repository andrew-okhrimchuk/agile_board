package org.disturbed75.application.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class ServiceBin {

    @Lookup
    public SessionBin getBin() {
        return null;
    }
    public ServiceBin() {
    }
}
