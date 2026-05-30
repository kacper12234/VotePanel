package com.onwelo.votepanel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voter_seq_gen")
    @SequenceGenerator(name = "voter_seq_gen", sequenceName = "voter_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "personal_number", unique = true)
    private String personalNumber;
    private String address;
    private boolean blocked;

    public void block() {
        blocked = true;
    }

    public void unlock() {
        blocked = false;
    }
}
