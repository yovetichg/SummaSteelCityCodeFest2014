-- Questions
CREATE TABLE question_response (
	id INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id)
) ENGINE=INNODB;

CREATE TABLE question (
	question_response_id INT NOT NULL,
	question VARCHAR(200),
	is_first BOOLEAN NOT NULL DEFAULT false,
	yes_id INT,
	no_id INT,
	PRIMARY KEY (question_response_id),
	INDEX question_response_idx (question_response_id),
	FOREIGN KEY (question_response_id) REFERENCES question_response(id) ON DELETE CASCADE,
	FOREIGN KEY (yes_id) REFERENCES question_response(id) ON DELETE NO ACTION,
	FOREIGN KEY (no_id) REFERENCES question_response(id) ON DELETE NO ACTION
) ENGINE=INNODB;

CREATE TABLE event_category (
	question_response_id INT NOT NULL,
	description VARCHAR(500),
	PRIMARY KEY (question_response_id),
	INDEX question_response_idx (question_response_id),
	FOREIGN KEY (question_response_id) REFERENCES question_response(id) ON DELETE CASCADE
) ENGINE=INNODB;