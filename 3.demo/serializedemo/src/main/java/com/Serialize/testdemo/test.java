package com.Serialize.testdemo;

import java.io.*;

public class test {
    private static void SerializePersion() throws IllegalAccessException, InstantiationException, IOException {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.Serialize.testdemo.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Person person = (Person) aClass.newInstance();
        person.setAge(12);
        System.out.println(person);
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("D:\\JAVA_Study\\test.txt")));
        oo.writeObject(person);
        System.out.println("序列化成功");
    }
    private static Person DeserializePerson() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("D:\\JAVA_Study\\test.txt")));
        Person person =(Person) ois.readObject();
        System.out.println("反序列化成功");
        return person;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        SerializePersion();
        Person person = DeserializePerson();
        System.out.println(person);
    }
}
