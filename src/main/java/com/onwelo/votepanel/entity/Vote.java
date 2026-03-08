package com.onwelo.votepanel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vote", uniqueConstraints = @UniqueConstraint(columnNames = {"election_id", "voter_id"}))
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq_gen")
    @SequenceGenerator(name = "vote_seq_gen", sequenceName = "vote_seq", allocationSize = 1)
    private Long id;

    @Column(name = "election_id")
    private Long electionId;
    @Column(name = "voter_id")
    private Long voterId;
    @Column(name = "choice_id")
    private Long choiceId;

    @ManyToOne
    @JoinColumn(name = "choice_id", insertable = false, updatable = false)
    private Choice choice;

}
