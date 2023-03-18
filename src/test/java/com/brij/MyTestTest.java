package com.brij;

import com.brij.dagger.DaggerDependencyComponent;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MyTestTest {
    @Test
    void shouldBuildDaggerComponent() {
        CustomerService customerService = DaggerDependencyComponent.create().buildCustomer();
        assertFalse(Objects.isNull(customerService));
    }
}