package sample;

public class Method {
    private String name;
      private String column1;
    private String column2;
    private String column3;
    private String column4;
    private String column5;
    private String service;
    private String price;
    private String rj;
    private String ck;
    private String DRj;
    private String DCk;
 private String date;
    public void setDate(String date) { this.date = date; }
    public String getDate() { return date; }
    public void setRj(String rj) { this.rj = rj; }
    public String getRj() { return rj; }
    public void setDRj(String DRj) { this.DRj = DRj; }
    public String getDRj() { return DRj; }
    public void setCk(String ck) { this.ck = ck; }
    public String getCk() { return ck; }
    public void setDCk(String DCk) { this.DCk = DCk; }
    public String getDCk() { return DCk; }
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setColumn1(String column1) { this.column1 = column1; }
    public String getColumn1() { return column1; }
    public void setColumn2(String column2) { this.column2 = column2; }
    public String getColumn2() { return column2; }
    public void setColumn3(String column3) { this.column3 = column3; }
    public String getColumn3() { return column3; }
    public void setColumn4(String column4) { this.column4 = column4; }
    public String getColumn4() { return column4; }
    public void setColumn5(String column5) { this.column5 = column5; }
    public String getColumn5() { return column5; }
    public void setService(String service){ this.service=service;}
    public String getService(){return service;}
    public void setPrice(String price) { this.price = price; }
    public String getPrice() { return price; }
    Method(){}
    Method(String service,String ck){
        this.service=service;
        this.ck=ck;
    }
    Method(String name, String column1, String column2, String column3, String column4, String column5){
        this.name = name;
        this.column1=column1;
        this.column2=column2;
        this.column3=column3;
        this.column4=column4;
        this.column5=column5;
    }
    Method(String column1, String column2, String column3, String column4, String column5){
        this.column1=column1;
        this.column2=column2;
        this.column3=column3;
        this.column4=column4;
        this.column5=column5;
    }
    Method(String service, String column1, String column2, String column3, String column4, String column5,String columnPrice){
        this.service = service;
        this.column1=column1;
        this.column2=column2;
        this.column3=column3;
        this.column4=column4;
        this.column5=column5;
        this.price=columnPrice;
    }

    @Override
    public String toString() {
        return getColumn1()+"\n"+getColumn2()+"\n"+getColumn3()+"\n"+getColumn4()+"\n"+getColumn5()+"\n";
    }
}
