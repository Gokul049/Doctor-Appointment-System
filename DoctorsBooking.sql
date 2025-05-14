CREATE TABLE patients (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(100) NOT NULL,
  age VARCHAR(3) NOT NULL,
  gender VARCHAR(7) NOT NULL,
  PRIMARY KEY (id)  
);

CREATE TABLE doctors (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(255) NOT NULL,
  department VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE appointments (
  id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
  patient_id MEDIUMINT(8) NOT NULL,
  doctor_id MEDIUMINT(8) NOT NULL,
  appointment_date DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (patient_id) REFERENCES patients (id),
  FOREIGN KEY (doctor_id) REFERENCES doctors (id)
);
