package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Refund_table")
public class Refund {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private Long remainPoint;
    private String memberStatus;
    private Long refundPoint;

    @PostPersist
    public void onPostPersist(){

        /*
        if(this.refundPoint <= 0){

            RefundCancelled refundCancelled = new RefundCancelled();
            BeanUtils.copyProperties(this, refundCancelled);

            refundCancelled.setRemainPoint(remainPoint - refundPoint);

            refundCancelled.publishAfterCommit();

        }
        */

        //else{
            System.out.println("\n$$$onPostPersist" + this.refundPoint);
            RefundProcessed refundProcessed = new RefundProcessed();
            BeanUtils.copyProperties(this, refundProcessed);

            if("NORMAL".equals(refundProcessed.getMemberStatus())){

                if(remainPoint > refundPoint){

                    refundProcessed.setRemainPoint(remainPoint - refundPoint);
                }

            }

            refundProcessed.publishAfterCommit();

        //}



        System.out.println("##### MESSAGE SENT");

        //RefundCancelled refundCancelled = new RefundCancelled();
        //BeanUtils.copyProperties(this, refundCancelled);
        //refundCancelled.publishAfterCommit();


    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getRemainPoint() {
        return remainPoint;
    }

    public void setRemainPoint(Long remainPoint) {
        this.remainPoint = remainPoint;
    }
    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
    public Long getRefundPoint() {
        return refundPoint;
    }

    public void setRefundPoint(Long refundPoint) {
        this.refundPoint = refundPoint;
    }




}
