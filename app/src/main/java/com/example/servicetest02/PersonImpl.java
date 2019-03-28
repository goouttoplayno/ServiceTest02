package com.example.servicetest02;

import android.os.RemoteException;

public class PersonImpl extends IPerson.Stub {
    private String name;
    private String sex;
    private int age;

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void setSex(String sex) throws RemoteException {
        this.sex = sex;
    }

    @Override
    public void setAge(int age) throws RemoteException {
        this.age = age;
    }

    @Override
    public String getPerson() throws RemoteException {
        return "name = " + name + ", sex = " + sex + ", age = " + age;
    }
}
