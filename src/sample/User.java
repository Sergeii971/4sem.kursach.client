package sample;

public class User implements Comparable<User>{
    private String name;
    private String surname;
    private String login;
    private String password;
    private int userNumber;
    public User(){}
    public User (String surname,String name, String login){
        this.surname=surname;
        this.name=name;
        this.login=login;
    }
    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}
    public void setSurname(String surname){this.surname=surname;}
    public String getSurname(){return this.surname;}
    public void setLogin(String login){this.login=login;}
    public String getLogin(){return this.login;}
    public void setPassword(String password){this.password=password;}
    public String getPassword(){return this.password;}
    public int getUserNumber(){ return this.userNumber; }
    public void setUserNumber(int userNumber){this.userNumber=userNumber;}

    @Override
    public int compareTo(User o) {
        if(this.getSurname().compareTo(o.getSurname())>0)
            return 1;
        if(this.getSurname().compareTo(o.getSurname())<0)
            return -1;
        if(this.getSurname().equals(o.getSurname()))
            return 0;
        return 0;
    }
}
