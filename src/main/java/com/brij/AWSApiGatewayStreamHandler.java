package com.brij;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.util.CollectionUtils;
import com.brij.dagger.DaggerDependencyComponent;
import com.brij.dto.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AWSApiGatewayStreamHandler implements RequestStreamHandler {
//    static ApplicationContext context =
//            SpringApplication.run(AwsApplication.class, new String[]{});

    //    @Autowired
    private CustomerService customerService;
    public AWSApiGatewayStreamHandler() {
        this.customerService = DaggerDependencyComponent.create().buildCustomer();
//        customerService = context.getBean(CustomerService.class);
    }

    /**
     * {
     * "id: 1,
     * "customer": "BP",
     * "customerType"" "NEW"
     * }
     */


    public static Customer buildCustomer(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        byte[] byteArray = buffer.toByteArray();
        String message = new String(byteArray, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(message, Customer.class);
        return customer;
    }


    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        Customer customer = buildCustomer(input);
        context.getLogger().log("Customer request " + customer);
        String test = System.getenv("test");
        context.getLogger().log("Test env variable " + test);

        customerService.saveCustomer(customer);
        output.write("Successfully processed the request".getBytes());
    }

}
