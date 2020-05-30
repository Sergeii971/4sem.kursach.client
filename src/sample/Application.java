package sample;

public class Application {
    private String name;
    private String surname;
    private String login;
    private String carBrand;
    private String yearRelease;
    private String carModel;
    private String vinNumber;
    private String registrationNumber;
    private int applicationNumber;
Application(){}
Application(String name,String surname,String yearRelease,String carBrand,String carModel,String vinNumber,String registrationNumber){
    this.name=name;
    this.surname=surname;
    this.yearRelease=yearRelease;
    this.carModel=carModel;
    this.carBrand=carBrand;
    this.vinNumber=vinNumber;
    this.registrationNumber=registrationNumber;
}
    Application(String name,String surname,String login,String yearRelease,String carBrand,String carModel,String vinNumber,String registrationNumber){
        this.name=name;
        this.surname=surname;
        this.login=login;
        this.yearRelease=yearRelease;
        this.carModel=carModel;
        this.carBrand=carBrand;
        this.vinNumber=vinNumber;
        this.registrationNumber=registrationNumber;
    }
    public void setLogin(String login){this.login=login;}
    public String getLogin(){return this.login;}
    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}
    public void setSurname(String surname){this.surname=surname;}
    public String getSurname(){return this.surname;}
    public void setCarBrand(String carBrand){this.carBrand=carBrand;}
    public String getCarBrand(){return this.carBrand;}
    public void setYearRelease(String yearRelease ){this.yearRelease=yearRelease;}
    public String getYearRelease(){return this.yearRelease;}
    public void setCarModel(String carModel){this.carModel=carModel;}
    public String getCarModel(){return this.carModel;}
    public void setVinNumber(String vinNumber){this.vinNumber=vinNumber;}
    public String getVinNumber(){return this.vinNumber;}
    public void setRegistrationNumber(String registrationNumber){this.registrationNumber=registrationNumber;}
    public String getRegistrationNumber(){return this.registrationNumber;}
    public int getApplicationNumber(){ return this.applicationNumber; }
    public void setApplicationNumber(int applicationNumber){this.applicationNumber=applicationNumber;}
}
