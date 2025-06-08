package com.cdac.acts;

public class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String city;

    public User(String username, String password, String name, String email, String city) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCity() { return city; }

    public void setPassword(String password) { this.password = password; }
}
