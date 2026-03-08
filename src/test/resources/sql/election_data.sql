INSERT INTO voter (id, name, personal_number, address, blocked)
VALUES (1, 'Alice', 'PN1', 'Addr 1', FALSE),
       (2, 'Bob', 'PN2', 'Addr 2', FALSE),
       (3, 'John', 'PN3', 'Addr 3', FALSE);

INSERT INTO election (id, name, date)
VALUES (2, 'General', CURRENT_DATE);

INSERT INTO choice (id, name, election_id)
VALUES (3, 'Choice A', 2),
       (4, 'Choice B', 2);

INSERT INTO vote (voter_id, choice_id, election_id)
VALUES (1, 3,2),
       (2, 4,2),
       (3, 3,2);