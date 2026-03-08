INSERT INTO voter (id, name, personal_number, address, blocked) VALUES
    (1, 'Alice', 'PN1', 'Addr 1', FALSE),
    (2, 'Bob', 'PN2', 'Addr 2', TRUE);

INSERT INTO election (id, name, date) VALUES
    (1, 'General', CURRENT_DATE);

INSERT INTO choice (id, name, election_id) VALUES
    (1, 'Choice A', 1),
    (2, 'Choice B', 1);
