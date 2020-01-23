package com.example.mareu.Model;

public class Participant {
    private String nomParticipant;

    //Constructeur par défaut
    public Participant() {}

    public Participant (String nomParticipant) {
        this.nomParticipant= nomParticipant;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }

    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }
}
