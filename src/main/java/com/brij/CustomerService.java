package com.brij;

import com.brij.dto.Customer;
import dagger.Component;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//import javax.inject.Inject;
import javax.inject.Inject;
import javax.management.Notification;

//@Service
@Slf4j
public class CustomerService {
    @Inject
    public CustomerService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    //    @Autowired
//    @Inject

    NotificationService notificationService;

    public void saveCustomer(Customer customer) {
        //some db call
        log.info("Saving customer {}", customer);
        notificationService.notify(customer);
    }
}
