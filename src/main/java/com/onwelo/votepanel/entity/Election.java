package com.onwelo.votepanel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "election")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "election_seq_gen")
    @SequenceGenerator(name = "election_seq_gen", sequenceName = "election_seq", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate date;
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<Choice> choices;

}
