package com.brij.dagger;

import com.brij.CustomerService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = DependencyModule.class)
public interface DependencyComponent {
     CustomerService buildCustomer();
}