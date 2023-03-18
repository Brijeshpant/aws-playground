package com.brij.dagger;

import com.brij.CustomerService;
import com.brij.NotificationService;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DependencyModule {
    @Provides
    public CustomerService provideCustomerService(NotificationService notificationService) {
        return new CustomerService(notificationService);
    }

    @Provides
    @Singleton
    public NotificationService provideNotificationService() {
        return new NotificationService();
    }
}