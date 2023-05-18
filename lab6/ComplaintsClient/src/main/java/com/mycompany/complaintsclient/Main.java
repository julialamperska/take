/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.complaintsclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.GenericType;
import java.util.List;

/**
 *
 * @author Julia Lamperska
 */
public class Main {
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String status
                = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints/1250/status")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);

        System.out.println("Status: " + status);
        
        List<Complaint> allComplaints = client
                .target("http://localhost:8080/Complaints/resources/complaints")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Complaint>>() {});
        
        allComplaints.forEach(System.out::println);
        
        Long openId = allComplaints
                .stream()
                .filter(c -> "open".equals(c.getStatus()))
                .findAny()
                .get()
                .getId();
        
        Complaint complaint = client
                .target("http://localhost:8080/Complaints/resources/complaints")
                .path(String.valueOf(openId))
                .request(MediaType.APPLICATION_JSON)
                .get(Complaint.class);
        
        System.out.println(complaint.toString());
        
        complaint.setStatus("closed");
        
        client
                .target("http://localhost:8080/Complaints/resources/complaints")
                .path(String.valueOf(complaint.getId()))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
        
        List<Complaint> allOpen = client
                .target("http://localhost:8080/Complaints/resources/complaints")
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Complaint>>() {});
        
        allOpen.forEach(System.out::println);

        client.close();
    }
    
}
