package com.onwelo.votepanel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choice_seq_gen")
    @SequenceGenerator(name = "choice_seq_gen", sequenceName = "choice_seq", allocationSize = 1)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @Getter
    @Column(name = "election_id", insertable = false, updatable = false)
    private Long electionId;

    @OneToMany(mappedBy = "choice")
    private List<Vote> votes;
}
