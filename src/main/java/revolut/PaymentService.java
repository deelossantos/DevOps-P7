package revolut;

public class PaymentService {
    private String serviceName;
    private boolean status;
    public PaymentService(String name){
        this.serviceName = name;
        this.status = true;
    }

    public String getType() {
        return serviceName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {this.status = status;}

}
