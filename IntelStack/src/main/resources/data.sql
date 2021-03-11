DROP TABLE IF EXISTS INTEL_STACK;  
CREATE TABLE INTEL_STACK (  
id long AUTO_INCREMENT  PRIMARY KEY,  
stackElements VARCHAR(500),
capacity int,
top int
);  