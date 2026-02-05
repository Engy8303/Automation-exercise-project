package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import pojo_class.*;


import java.io.FileNotFoundException;
import java.io.FileReader;


public class Helpers {

    private static final String testDataPath = "src\\main\\resources\\TestData\\";


    //<<<<< Method for reading an element from a json file >>>>>
    public static String readValue(String key, String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        JsonElement element = new JsonParser().parse(reader);
        return element.getAsJsonObject().get(key).getAsString();
    }

    // <<<<< Methods for reading array of user from a json file >>>>>

    public static Register[] registerUsers(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        Register[] ListOfCredentials = gson.fromJson(reader, Register[].class);
        return ListOfCredentials;
    }

    public static Valid_Login[] validLogin(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        Valid_Login[] ListOfCredentials = gson.fromJson(reader, Valid_Login[].class);
        return ListOfCredentials;
    }

    public static Invalid_Login[] invalidLogin(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        Invalid_Login[] ListOfCredentials = gson.fromJson(reader, Invalid_Login[].class);
        return ListOfCredentials;
    }

    public static User_Data[] UserData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        User_Data[] ListOfCredentials = gson.fromJson(reader, User_Data[].class);
        return ListOfCredentials;
    }

    public static Products[] productsData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        Products[] ListOfCredentials = gson.fromJson(reader, Products[].class);
        return ListOfCredentials;
    }

    public static ContactUs[] contactUsData(String fileName) throws FileNotFoundException {
        FileReader reader = new FileReader(testDataPath + fileName + ".json");
        Gson gson = new Gson();
        ContactUs[] ListOfCredentials = gson.fromJson(reader, ContactUs[].class);
        return ListOfCredentials;
    }


    public static LoginBeforeCheckout[] loginBeforeCheckout(String filename) throws FileNotFoundException {
        FileReader reader=new FileReader(testDataPath + filename + ".json");
        Gson gson=new Gson();
        LoginBeforeCheckout[] ListOfCredentials=gson.fromJson(reader,LoginBeforeCheckout[].class);
        return ListOfCredentials;
    }

}

