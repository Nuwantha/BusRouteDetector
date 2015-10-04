Drop DataBase BusRouter;
Create Database BusRouter;
USE BusRouter;

CREATE TABLE BusRoute (
	RouteNo varchar(8) NOT NULL,
    StartLocation varchar(50) NOT NULL,
	EndLocation varchar(50) NOT NULL,
	BusFair Double(4,2),
	Distance Double(10,4),
	CONSTRAINT PRIMARY KEY(RouteNo)
) ENGINE=InnoDB;



CREATE TABLE Halt(
	HaltNo INT(10) NOT NULL AUTO_INCREMENT,
    Name varchar(50) NOT NULL,
	CONSTRAINT PRIMARY KEY(HaltNo)
) ENGINE=InnoDB;

CREATE TABLE RouteHaltDetail(
	RouteNo varchar(8) NOT NULL,
    HaltNo INT(10) NOT NULL,
    BusFair Double(4,2),
	Distance Double(5,2),
	CONSTRAINT PRIMARY KEY(HaltNo,RouteNo),
	CONSTRAINT FOREIGN KEY(HaltNo) REFERENCES Halt(HaltNo),
	CONSTRAINT FOREIGN KEY(RouteNo) REFERENCES BusRoute(RouteNo)	
) ENGINE=InnoDB;

INSERT INTO BusRoute values("297","Galle","Elpitiya",'66.00','48');
INSERT INTO BusRoute values("370","Galle","Baddegama",'36','19.2');
INSERT INTO BusRoute values("343","Galle","Pilana",'32','14.2');
INSERT INTO BusRoute values("514","Galle","WakWella",'18','8.2');
INSERT INTO BusRoute values("529","Galle","Imaduwa",'49','32.8');
INSERT INTO BusRoute values("350","Galle","Matara",'61','44.3');

INSERT INTO BusRoute values("346/1","Matara","Dickwella",'36','20.2');









INSERT INTO Halt (name) values("SarenthuKade Junc");
INSERT INTO Halt (name) values("Beligaha Junc");
INSERT INTO Halt (name) values("Richard Pathirana School");
INSERT INTO Halt (name) values("Ariyagaha Junc");
INSERT INTO Halt (name) values("Meepawala Sanasa Bank");
INSERT INTO Halt (name) values("Poddala Junc");
INSERT INTO Halt (name) values("Aduranvilla");
INSERT INTO Halt (name) values("Idigaswatiya Junc");
INSERT INTO Halt (name) values("Sadarawala");


INSERT INTO Halt (name) values("Sri Langama Provincial Office");
INSERT INTO Halt (name) values("Makuluwa Junc");
INSERT INTO Halt (name) values("Bataduwa");
INSERT INTO Halt (name) values("Ginigalthuduwa");
INSERT INTO Halt (name) values("Pinnaduwa");
INSERT INTO Halt (name) values("Wadukade");
INSERT INTO Halt (name) values("Mulana Junc");
INSERT INTO Halt (name) values("Pilana");

INSERT INTO Halt (name) values("Sangamitta College");
INSERT INTO Halt (name) values("Kalegana Junc");
INSERT INTO Halt (name) values("Hapugala");

INSERT INTO Halt (name) values("Dewata Junc");
INSERT INTO Halt (name) values("Unawatuna Teaching Colledge");
INSERT INTO Halt (name) values("Dalawalla");
INSERT INTO Halt (name) values("Thalpe");
INSERT INTO Halt (name) values("Habaraduwa");
INSERT INTO Halt (name) values("Koggala Main Gate");
INSERT INTO Halt (name) values("Kathaluwa Junc");
INSERT INTO Halt (name) values("Gurukanda Junc");
INSERT INTO Halt (name) values("Sri Lanka Hemiton Village");
INSERT INTO Halt (name) values("Thiththagalla Junc");
INSERT INTO Halt (name) values("Dikkumura");
INSERT INTO Halt (name) values("Andragoda Temple");
INSERT INTO Halt (name) values("Horadugoda");
INSERT INTO Halt (name) values("Kodagoda");



INSERT INTO Halt (name) values("Belhengoda");
INSERT INTO Halt (name) values("Ahangama");
INSERT INTO Halt (name) values("Goviyapana");
INSERT INTO Halt (name) values("Midigama Market");
INSERT INTO Halt (name) values("Kubalgama Junc");
INSERT INTO Halt (name) values("Weligama Bus Stand");
INSERT INTO Halt (name) values("Weligama pohora gabadawa");
INSERT INTO Halt (name) values("Polathumodara");
INSERT INTO Halt (name) values("Mirrissa junc");
INSERT INTO Halt (name) values("Thalaramba School");
INSERT INTO Halt (name) values("Kaburugamuwa Junc");
INSERT INTO Halt (name) values("Walgama");
INSERT INTO Halt (name) values("Nupe");



INSERT INTO Halt (name) values("Welewatta Navimana Temple Road");
INSERT INTO Halt (name) values("Ruhunu Campus");
INSERT INTO Halt (name) values("Devundara");
INSERT INTO Halt (name) values("kapugama ");
INSERT INTO Halt (name) values("Gandara");
INSERT INTO Halt (name) values("Thalalla");
INSERT INTO Halt (name) values("Pathegama Junc");
INSERT INTO Halt (name) values("Hundeniya");
INSERT INTO Halt (name) values("Polgahamula");


INSERT INTO RouteHaltDetail values('370','1','8','2');
INSERT INTO RouteHaltDetail values('370','2','12','3.6');
INSERT INTO RouteHaltDetail values('370','3','15','5.3');
INSERT INTO RouteHaltDetail values('370','4','18','7.5');
INSERT INTO RouteHaltDetail values('370','5','22','9.4');
INSERT INTO RouteHaltDetail values('370','6','26','11.5');
INSERT INTO RouteHaltDetail values('370','7','30','13.5');
INSERT INTO RouteHaltDetail values('370','8','32','15.6');
INSERT INTO RouteHaltDetail values('370','9','34','17.3');

INSERT INTO RouteHaltDetail values('343','10','8','1.7');
INSERT INTO RouteHaltDetail values('343','11','12','3.2');
INSERT INTO RouteHaltDetail values('343','12','15','5.3');
INSERT INTO RouteHaltDetail values('343','13','18','6.8');
INSERT INTO RouteHaltDetail values('343','14','22','8.8');
INSERT INTO RouteHaltDetail values('343','15','26','10.7');
INSERT INTO RouteHaltDetail values('343','16','30','12');

INSERT INTO RouteHaltDetail values('514','17','8','2');
INSERT INTO RouteHaltDetail values('514','18','12','3.6');
INSERT INTO RouteHaltDetail values('514','19','15','5.6');

INSERT INTO RouteHaltDetail values('529','10','8','1.7');
INSERT INTO RouteHaltDetail values('529','21','12','2.9');
INSERT INTO RouteHaltDetail values('529','22','15','4.7');
INSERT INTO RouteHaltDetail values('529','23','18','7.2');
INSERT INTO RouteHaltDetail values('529','24','22','9.7');
INSERT INTO RouteHaltDetail values('529','25','26','12.5');
INSERT INTO RouteHaltDetail values('529','26','30','14.4');
INSERT INTO RouteHaltDetail values('529','27','32','16.5');
INSERT INTO RouteHaltDetail values('529','28','34','18.2');
INSERT INTO RouteHaltDetail values('529','29','36','20.7');
INSERT INTO RouteHaltDetail values('529','30','38','22.5');
INSERT INTO RouteHaltDetail values('529','31','40','24.7');
INSERT INTO RouteHaltDetail values('529','32','42','26.8');
INSERT INTO RouteHaltDetail values('529','33','44','29.1');
INSERT INTO RouteHaltDetail values('529','34','47','31.7');


INSERT INTO RouteHaltDetail values('350','10','8','1.7');
INSERT INTO RouteHaltDetail values('350','21','12','2.9');
INSERT INTO RouteHaltDetail values('350','22','15','4.7');
INSERT INTO RouteHaltDetail values('350','23','18','7.2');
INSERT INTO RouteHaltDetail values('350','24','22','9.7');
INSERT INTO RouteHaltDetail values('350','25','26','12.5');
INSERT INTO RouteHaltDetail values('350','26','30','14.4');
INSERT INTO RouteHaltDetail values('350','27','32','16.5');
INSERT INTO RouteHaltDetail values('350','35','34','17.7');
INSERT INTO RouteHaltDetail values('350','36','36','19.5');
INSERT INTO RouteHaltDetail values('350','37','38','20.9');
INSERT INTO RouteHaltDetail values('350','38','40','22.4');
INSERT INTO RouteHaltDetail values('350','39','42','24.7');
INSERT INTO RouteHaltDetail values('350','40','44','27.7');
INSERT INTO RouteHaltDetail values('350','41','47','29.2');
INSERT INTO RouteHaltDetail values('350','42','49','30.9');
INSERT INTO RouteHaltDetail values('350','43','52','32.9');
INSERT INTO RouteHaltDetail values('350','44','53','35.6');
INSERT INTO RouteHaltDetail values('350','45','55','37.6');
INSERT INTO RouteHaltDetail values('350','46','57','39.9');
INSERT INTO RouteHaltDetail values('350','47','59','42.4');


INSERT INTO RouteHaltDetail values('346/1','48','8','1.7');
INSERT INTO RouteHaltDetail values('346/1','49','12','3.5');
INSERT INTO RouteHaltDetail values('346/1','50','15','5.6');
INSERT INTO RouteHaltDetail values('346/1','51','18','7.6');
INSERT INTO RouteHaltDetail values('346/1','52','22','8.7');
INSERT INTO RouteHaltDetail values('346/1','53','26','11.7');
INSERT INTO RouteHaltDetail values('346/1','54','30','13.7');
INSERT INTO RouteHaltDetail values('346/1','55','32','15.5');
INSERT INTO RouteHaltDetail values('346/1','56','34','17.5');


