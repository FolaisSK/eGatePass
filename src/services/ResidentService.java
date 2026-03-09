package services;

import data.models.Resident;

import java.util.List;


public interface ResidentService {
    String registerResident(String name, String phoneNumber, String houseAddress);
    String updateResident(String name, String phoneNumber, String houseAddress);
    Resident findByPhoneNumber(String phoneNumber);
    List<Resident> findAllResidents();
    void deleteResident(String phoneNumber);
    int getResidentsCount();
    void deleteAllResidents();

}
