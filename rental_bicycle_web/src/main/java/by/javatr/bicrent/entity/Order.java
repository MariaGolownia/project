package by.javatr.bicrent.entity;
import java.time.LocalDateTime;
import java.util.List;

public class Order extends Entity {
    private Integer userId;
    private List<Integer> bicyclesId;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private Integer finishLocationId;
    private Integer startLocationId;
    private Integer paymentId;
    String startTimeStr;
    String finishTimeStr;

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public String getFinishTimeStr() {
        return finishTimeStr;
    }

    public void setFinishTimeStr(String finishTimeStr) {
        this.finishTimeStr = finishTimeStr;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer idUser) {
        this.userId = idUser;
    }

    public List<Integer> getBicyclesId() {
        return bicyclesId;
    }

    public void setBicyclesId(List<Integer> idBicycles) {
        this.bicyclesId = idBicycles;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        if (startTime != null)
            this.startTimeStr = startTime.toString();
        else
            this.startTimeStr = "";
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
        if (finishTime != null)
            this.finishTimeStr = finishTime.toString();
        else
            this.finishTimeStr = "";
    }

    public Integer getFinishLocationId() {
        return finishLocationId;
    }

    public void setFinishLocationId(Integer finishLocationId) {
        this.finishLocationId = finishLocationId;
    }

    public Integer getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(Integer startLocationId) {
        this.startLocationId = startLocationId;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPayment(Integer payment) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", bicyclesId=" + bicyclesId +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", finishLocationId=" + finishLocationId +
                ", startLocationId=" + startLocationId +
                ", paymentId=" + paymentId +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", finishTimeStr='" + finishTimeStr + '\'' +
                '}';
    }
}
