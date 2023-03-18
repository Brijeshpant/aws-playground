package com.brij;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.brij.dto.Customer;
import com.brij.dto.CustomerType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.ApplicationContext;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AWSApiGatewayHandler implements RequestHandler<Customer, String> {
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
    public String handleRequest(Customer input, Context context) {
        context.getLogger().log("Customer input " + input);
        return "Processed lambda customer request " + input;
    }
}
