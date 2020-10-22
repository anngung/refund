package mileage;

import mileage.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    RefundRepository refundRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRefundApplied_RefundProcess(@Payload RefundApplied refundApplied){

        if(refundApplied.isMe()){
            System.out.println("##### listener RefundProcess : " + refundApplied.toJson());

            Refund refund = new Refund();

            refund.setMemberId(refundApplied.getMemberId());
            refund.setRemainPoint(refundApplied.getRemainPoint());
            refund.setMemberStatus(refundApplied.getMemberStatus());
            refund.setRefundPoint(refundApplied.getRefundPoint());

            refundRepository.save(refund);

        }
    }

}
