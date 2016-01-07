drop table Klassen;
drop table Studenten;
drop table StudentenInGruppen;
drop table Gruppen;
drop table Aufgaben;
drop table Aufgabenpools;
drop table Dozenten;

create table Dozenten (
    DozentenID varchar(3) Primary Key,
    Name varchar(20),
    Vorname varchar(20),
    Passwort varchar(20) not null
);

create table Klassen(
    Klassenbezeichnung varchar(8) Primary Key,
    AnzahlSchüler number(2),
    Klassendozent varchar(3),
    Foreign Key (Klassendozent) references Dozenten
);

create table Studenten(
    StudentenID varchar(10) Primary Key,
    Name varchar(20) not null,
    Nachname varchar(20) not null,
    Bild varchar2(30), /* Default "Default Pfad"*/
    Klasse varchar(8),
    Foreign Key (Klasse) references Klassen (Klassenbezeichnung)
);

create table Aufgabenpool(
    AufgabenpoolID number Primary Key,
    Dozent varchar(3),
    Fach varchar(3),
    Beschreibung  long,
    Foreign key (Dozent) references Dozenten (DozentenID)
); 

create table Aufgaben(
    AufgabenId number(3) Primary Key,
    Bezeichnung varchar(20),
    Beschreibung long,
    Kategorie varchar(10),
    Bearbeitungszeit varchar(5),
    Pool number,
    Foreign Key (pool) references Aufgabenpool(AufgabenpoolID)  
    );
    
create table Gruppen(
    GruppenID number(3) Primary Key,
    Gruppenleiter varchar(10),
    GruppenGroesse number(2),
    Aufgabe number(3),
    Foreign Key (Gruppenleiter) references Studenten(StudentenID),
    Foreign Key (Aufgabe) references Aufgaben(AufgabenId)
);

Create table StudentenInGruppen(
    GruppenID number(3),
    StudentenID varchar(10),
    Foreign Key (GruppenID) references Gruppen(GruppenID),
    Foreign Key (StudentenID) references Studenten(StudentenID)
);






