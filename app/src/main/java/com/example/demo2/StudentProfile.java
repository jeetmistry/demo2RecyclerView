package com.example.demo2;

public class StudentProfile {
    String Username;
    String Name;

    public StudentProfile(String Username, String Name) {
        this.Username = Username;
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }
}
