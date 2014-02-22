-- Create data
INSERT INTO question_response (id) VALUES
(1),(2),(3),(4),(5),(6),(7),(8),(9),(10),
(11),(12),(13),(14),(15),(16),(17),(18),(19);

INSERT INTO question (question_response_id, question, yes_id, no_id, is_first) VALUES
(1,"Are you looking for a job?",2,6,true);

INSERT INTO question (question_response_id, question, yes_id, no_id) VALUES
(2,"Do you have a goal occupation?",3,10),
(3,"Do you have an accurate and up to date resume?",4,11),
(4,"Have you successfully completed an interview in the last 24 months that resulted in a job offer?",5,12),
(5,"Are you in need of networking opportunities?",14,13),
(6,"Are you interested in learning new skills?",7,9),
(7,"Are you interested in Computer workshops?",16,8),
(8,"Are you interested in Short-Term Training  programs in HealthCare, Energy, or Manufacturing?",17,19),
(9,"Are you interested in advancing your Soft Skills (Networking, Professional Development)?",18,19);

INSERT INTO event_category (question_response_id, description) VALUES 
(10,"Interest Assessment"),
(11,"Resume Workshops"),
(12,"Interview Workshops"),
(13,"Hiring/Recruitment"),
(14,"Interest Assessment"),
(15,"Networking/Job Clubs"),
(16,"Computer Workshops"),
(17,"Short-Term Training"),
(18,"Soft Skills Workshops"),
(19,"All Events");