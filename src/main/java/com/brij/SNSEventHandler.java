package com.brij;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.amazonaws.util.CollectionUtils;

public class SNSEventHandler implements RequestHandler<SNSEvent, String> {

    @Override
    public String handleRequest(SNSEvent input, Context context) {
        context.getLogger().log(String.format("Sns event %s", input.getRecords()));
        if (CollectionUtils.isNullOrEmpty(input.getRecords())) {
            context.getLogger().log("No record found");
        } else {
            SNSEvent.SNSRecord snsRecord = input.getRecords().get(0);
            SNSEvent.SNS sns = snsRecord.getSNS();
            context.getLogger().log(String.format("Sns event record %s", sns.getMessage()));
        }
        return "Processed";
    }
}

