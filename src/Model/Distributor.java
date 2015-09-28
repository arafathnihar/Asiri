package Model;

public class Distributor {
    
    private String code;
    private String name;
    private String address;
    private String phoneNo;

    public Distributor(String code, String name, String address, String phoneNo) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }
    
    public Distributor(){
        this.code = "";
        this.name = "";
        this.address = "";
        this.phoneNo = "";
    }
 
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
}
