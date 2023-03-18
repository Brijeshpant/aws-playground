package com.brij;

import com.brij.dto.Customer;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class NotificationService {
    public void notify(Customer customer) {
        log.info("Customer creation notification");
    }
}
