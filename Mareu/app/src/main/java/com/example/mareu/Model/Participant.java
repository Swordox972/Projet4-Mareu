package com.example.mareu.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

public class Participant implements Parcelable {


    //Constructeur par défaut
    public Participant() {
    }

    //Parcelable creator
    public static final Participant.Creator CREATOR = new Parcelable.Creator() {
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    private String nomParticipant;

    //Constructeur avec paramètre
    public Participant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }


    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }

    public Participant(Parcel in) {
        this.nomParticipant = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(nomParticipant, that.nomParticipant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomParticipant);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nomParticipant);
    }

    @NonNull
    @Override
    public String toString() {
        return nomParticipant;
    }
}
