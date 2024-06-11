USE installdb;

INSERT INTO ADDRESS (
  id,
  apt_unit_number,
  street_address,
  city,
  state,
  zipcode
) VALUES
  (1,"apt 2h","123 main Street","New York","NY",10022),
  (2,"apt 5b","123 main Street","New York","NY",10022),
  (3,"apt 5c","123 main Street","New York","NY",10022),
  (4,"apt 1102","456 bridge Street","New York","NY",11211),
  (5,"apt 3a","789 Roos Street","New York","NY",10044);

INSERT INTO PERSON (
  id,
  first_name,
  last_name,
  slack_id,
  email,
  phone_number
) VALUES 
  (1,"John","Valjohn","ASD","JValjohn@domain.com","1234567890"),
  (2,"Tom","Jerry","FGH","TJerry@domain.com",""),
  (3,"Paul","Eadee","","PEadee@domain.com","4591267893"),
  (4,"Julie","Ah","QWE","JAh@domain.com","2583691743"),
  (5,"Anton","Yo","RTY","AYo@domain.com","1597534682");

INSERT INTO HARDWARE (
    id,
    name
) VALUES
  (1,"antenna"),
  (2,"outdoor-router"),
  (3,"indoor-router"),
  (4,"RJ45"),
  (5,"outdoor-ethernet-cable")
  ;

INSERT INTO INSTALLER_STATUS (
  id,
  name
) VALUES
  (1,"LEAD"),
  (2,"ASSISTANT");

INSERT INTO MEMBER_STATUS (
  id,
  name
) VALUES
  (1,"ACTIVE"),
  (2,"INACTIVE");

INSERT INTO INSTALLER (
  id,
  person_id,
  role_given_date,
  type
) VALUES
  (1,1,STR_TO_DATE('11-22-15','%m-%d-%y'),1),
  (2,2,STR_TO_DATE('03-10-14','%m-%d-%y'),2),
  (3,4,STR_TO_DATE('11-19-13','%m-%d-%y'),1);
  -- (1,1,STR_TO_DATE('11-22-15','%m-%d-%y'),"LEAD"),
  -- (2,2,STR_TO_DATE('03-10-14','%m-%d-%y'),"ASSISTANT"),
  -- (3,4,STR_TO_DATE('11-19-13','%m-%d-%y'),"LEAD");

INSERT INTO MEMBER (
  id,
  person_id,
  role_given_date,
  status
) VALUES
(1,1,STR_TO_DATE('03-10-15','%m-%d-%y'),1),
(2,3,STR_TO_DATE('11-22-15','%m-%d-%y'),1),
(3,4,STR_TO_DATE('11-19-14','%m-%d-%y'),1),
(4,5,STR_TO_DATE('07-12-17','%m-%d-%y'),1);
-- (1,1,STR_TO_DATE('03-10-15','%m-%d-%y'),"ACTIVE"),
-- (2,3,STR_TO_DATE('11-22-15','%m-%d-%y'),"ACTIVE"),
-- (3,4,STR_TO_DATE('11-19-14','%m-%d-%y'),"ACTIVE"),
-- (4,5,STR_TO_DATE('07-12-17','%m-%d-%y'),"ACTIVE");

INSERT INTO INSTALL_REQUEST_TYPE (
  id,
  name
) VALUES
  (1,"T_DIY"),
  (2,"T_NODE_INSTALL"),
  (3,"T_CABLERUN"),
  (4,"T_NODE_INSTALL_AND_CABLERUN");

INSERT INTO INSTALL_REQUEST (
  id,
  install_request_type_id,
  requester_person_id,
  address_id,
  lead_installer_id,
  requester_notes,
  lead_notes,
  floor_number,
  created_date,
  claimed_date,
  completed_date
) VALUES
  (1,2,1,5,3,"Note by JVm","Note by JAi1",3,'2015-02-22','2015-02-27','2015-03-10'),
  (2,2,3,4,3,"Note by PEm","Note by JAi2",11,'2015-11-11','2015-11-14','2015-11-22'),
  (3,1,4,1,3,"Note by JAm","Note by JAi3",2 ,'2014-11-19','2014-11-19','2014-11-19'),
  (4,3,5,2,1,"Note by AYm","Note by JVi1",5,'2017-07-07','2017-07-10','2017-07-12'),
  (5,3,4,3,1,"Note by JAm2","Note by JVi2",5,'2017-08-12','2017-08-13',null);

INSERT INTO INSTALL (
  id,
  install_request_id,
  address_id,
  status
) VALUES
  (1,1,5,"ACTIVE"),
  (2,2,4,"ACTIVE"),
  (3,3,1,"ACTIVE"),
  (4,4,2,"ACTIVE");

INSERT INTO NODE (
  id,
  install_request_id,
  address_id,
  status
) VALUES
  (1,1,5,"ACTIVE"),
  (2,2,4,"ACTIVE"),
  (3,3,2,"ACTIVE");

INSERT INTO INSTALL_X_NODE (
  node_id,
  install_id
) VALUES
  (1,1),
  (2,2),
  (3,3),
  (3,4);

INSERT INTO MEMBER_X_INSTALL (
  install_id,
  member_id
) VALUES
  (1,1),
  (2,2),
  (3,3),
  (4,3);

INSERT INTO MEMBER_X_NODE (
  node_id,
  member_id
) VALUES
  (1,1),
  (2,2),
  (3,3);

INSERT INTO INSTALL_REQUEST_X_HARDWARE(
  install_request_id,
  hardware_id
)
VALUES
  (1,1),
  (1,2),
  (1,3),
  (1,4),
  (2,2),
  (2,3),
  (2,4),
  (3,1),
  (3,2),
  (3,3),
  (3,4),
  (4,3),
  (4,4),
  (5,3),
  (5,4);

INSERT INTO INSTALL_X_HARDWARE(
  install_id,
  hardware_id
)
VALUES
  (1,3),
  (1,4),
  (2,3),
  (2,4),
  (3,3),
  (3,4),
  (4,3),
  (4,4);

INSERT INTO NODE_X_HARDWARE(
  node_id,
  hardware_id
)
VALUES
  (1,1),
  (1,2),
  (2,2),
  (3,1),
  (3,2);

INSERT INTO INSTALL_REQUEST_PARTICIPANT(
  install_request_id,
  installer_id
)
VALUES
  (1,2),
  (2,1),
  (4,2);

INSERT INTO INSTALL_REQUEST_SLACK_THREAD(
  id,
  install_request_id,
  thread_url
)
VALUES
  (1,1,"www.slackXXYY2.com"),
  (2,3,"www.slackXXYY3.com"),
  (4,4,"www.slackXXYY5.com"),
  (5,5,"www.slackXXYY6.com");

INSERT INTO INSTALL_REQUEST_PREFERED_DATES(
  id,
  selected,
  install_request_id,
  appointement_start_time,
  appointement_end_time
)
VALUES
  (1,TRUE,5,'2017-08-17 12:30:00-04:00','2017-08-17 14:30:00-04:00'),
  (2,FALSE,5,'2017-08-18 08:10:00-04:00','2017-08-18 10:10:00-04:00'),
  (3,FALSE,5,'2017-08-20 17:45:00-04:00','2017-08-20 20:45:00-04:00');

-- the only uncompleted sample INSTALL_REQUEST is cablerun -> no los but leaving comment bellow for convenience
-- INSERT INTO INSTALL_REQUEST_LOS (
--   install_request_id,
--   node_id
-- )
-- VALUES

