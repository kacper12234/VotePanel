-- Schema for tests
-- Explicit sequences expected by Hibernate ID generators
CREATE SEQUENCE IF NOT EXISTS voter_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS election_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS choice_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS vote_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE voter (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    personal_number VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    blocked BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT ux_voter_personal_number UNIQUE (personal_number)
);

CREATE TABLE election (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE choice (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    election_id BIGINT NOT NULL REFERENCES election(id)
);

CREATE TABLE vote (
    id BIGSERIAL PRIMARY KEY,
    choice_id BIGINT NOT NULL,
    election_id BIGINT NOT NULL,
    voter_id BIGINT NOT NULL,
    CONSTRAINT fk_vote_choice FOREIGN KEY (choice_id) REFERENCES choice(id),
    CONSTRAINT fk_vote_election FOREIGN KEY (election_id) REFERENCES election(id),
    CONSTRAINT fk_vote_voter FOREIGN KEY (voter_id) REFERENCES voter(id)
);

-- Ensure one vote per voter per election regardless of choice
CREATE UNIQUE INDEX ux_vote_voter_election ON vote(voter_id, election_id);
