package com.pduda.tourney.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

@Entity
@javax.persistence.Table(name = "PLAYER_DESC")
@Configurable(autowire = Autowire.BY_TYPE)
public class PlayerDescription implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SPACE = " ";
    public static final String NA = "n/a";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLAYER_DESC_ID")
    private long id;
    @Column(name = "FULL_NAME")
    private String fullName;
    @Enumerated
    @Column(name = "GENDER")
    private Gender gender;
    @Column(name = "CITY")
    private String city;
    @Column(name = "CLUB")
    private String club;

    public PlayerDescription(String fullName, Gender gender, String city, String club) {
        this.fullName = fullName;
        this.gender = gender;
        this.city = city;
        this.club = club;
    }

    public PlayerDescription(String fullName) {
        this(fullName, Gender.UNKNOWN, NA, NA);
    }

    public PlayerDescription() {
        // JPA
    }

    public String getShortName() {
        StringBuilder sb = new StringBuilder();
        String[] splitFullName = fullName.split(SPACE);
        for (int i = 0; i < splitFullName.length - 1; i++) {
            sb.append(splitFullName[i].substring(0, 1));
            sb.append(". ");
        }
        sb.append(splitFullName[splitFullName.length - 1]);

        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerDescription other = (PlayerDescription) obj;
        if (!Objects.equals(this.fullName, other.fullName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.fullName);
        return hash;
    }

    @Override
    public String toString() {
        return "Player{" + "fullName=" + fullName + '}';
    }
}
