package sample;

import javafx.collections.ObservableList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client  implements Runnable {
    private int checkNumber;
    private static Socket socket;
    Client(){
        try {
            socket = new Socket("localhost", 1280);
            System.out.println("Client connected to socket");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getCheckNumber(){ return this.checkNumber; }
    public void setCheckNumber(int checkNumber){this.checkNumber=checkNumber;}

    public int checkLoginPasswordServer(String login,String password){
        try {
            DataInputStream ois = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(login);
            out.writeUTF(password);
        setCheckNumber(ois.read());
            if(getCheckNumber()==0)return 0;
         if(getCheckNumber()==1)return 1;
         if(getCheckNumber()==2)return 2;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void inputNewLoginPasswordServer(final User user) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        sendMenuNumberServer(3);
        out.writeUTF(user.getLogin());
        out.writeUTF(user.getPassword());
        sendMenuNumberServer(10);
    }

    public User setUserNumberServer() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        User user=new User();
        user.setUserNumber(ois.read());
        return user;
    }

    public  void sendMenuNumberServer(int a) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(a);
        if(a==10)socket.close();
    }
    public void sendUserInformation(final User user) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(user.getName());
        out.writeUTF(user.getSurname());
        out.writeUTF(user.getLogin());
        out.writeUTF(user.getPassword());
    }
    public User setUserNameSurnameLoginPasswordServer() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        User user=new User();
        user.setName(ois.readUTF());
        user.setSurname(ois.readUTF());
        user.setLogin(ois.readUTF());
        user.setPassword(ois.readUTF());
        return user;
    }
public void sendUsersNumber(int a) throws IOException {
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    out.write(a);
}
public int checkLoginRepeat(String login) throws IOException {
    DataInputStream ois = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
   out.writeUTF(login);
   int number=ois.read();
   sendMenuNumberServer(10);
   return number;

}
    public  void sendApplicationInformation(Application application) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
   out.writeUTF(application.getName());
   out.writeUTF(application.getSurname());
   out.writeUTF(application.getYearRelease());
   out.writeUTF(application.getCarModel());
   out.writeUTF(application.getCarBrand());
   out.writeUTF(application.getVinNumber());
   out.writeUTF(application.getRegistrationNumber());
}
public Application setApplicationData() throws IOException {
    DataInputStream ois = new DataInputStream(socket.getInputStream());
    Application application=new Application();
    application.setName(ois.readUTF());
    application.setSurname(ois.readUTF());
    application.setLogin(ois.readUTF());
    application.setYearRelease(ois.readUTF());
    application.setCarModel(ois.readUTF());
    application.setCarBrand(ois.readUTF());
    application.setVinNumber(ois.readUTF());
    application.setRegistrationNumber(ois.readUTF());
    return application;
}
    public int setApplicationNumberServer() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        int number=ois.read();
        return number;
    }
    public void sendApplicationNumberServer(int a) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.write(a);
    }
    public void sendMethodInformation(String information) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(information);
    }
    public int getMethodInformationSize() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        return ois.read();
    }
    public String getResult() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        return ois.readUTF();
    }
    public void sendLogin(String login) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(login);
    }
    public String getAnswerData() throws IOException {
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        return ois.readUTF();
    }
    @Override
    public void run() {
    }
}
