CREATE TABLE task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      timestamp TIMESTAMP,
                      completed_at TIMESTAMP,
                      text CLOB,
                      is_completed BOOLEAN
);
