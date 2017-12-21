package com.waterhub.waterhub.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    private Long id;
    private String email; //
    private String password; //
    private String firstName; //
    private String lastName; //
    private String birthDate;
    private String phone; //
    private String country; //
    private String city; //
    private String address; //
    private Double latitude;
    private Double longitude;
    private String picture;
    private String createdAt;
    private List<Role> roles = new ArrayList<>();
    private List<Store> stores = new ArrayList<>();

    public User() {

    }

    public User(String email, String firstName, String lastName, String phone, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Role getRole(int index) {
        return roles.get(index);
    }

    public void setRole(Role role) {
        roles.add(role);
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    public Store getStore(int index) {
        return stores.get(index);
    }

    public void setStore(Store store) {
        stores.add(store);
    }

    public static String passwordEncoder(String userPassword) throws NoSuchAlgorithmException {
        StringBuilder userPasswordEncode = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(userPassword.getBytes());

        byte userPasswordByte[] = messageDigest.digest();

        for (byte b : userPasswordByte) {
            userPasswordEncode.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return userPasswordEncode.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User u = (User) obj;

            return id.equals(u.getId());
        } else
            return false;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
