CREATE TABLE CURRENCY
   (	"CODE" VARCHAR2(5 BYTE) NOT NULL ENABLE, 
	"NAME" VARCHAR2(100 BYTE), 
	"EXCHANGERATE" NUMBER(10,5), 
	 CONSTRAINT "CURRENCY_PK" PRIMARY KEY ("CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   );
   
   
ALTER TABLE PLACEMENT 
ADD (
    CURRENCY VARCHAR2(5),
    AMOUNT_IN_BAM NUMBER(30,5),
	SOCIALRISK VARCHAR2(50)
);

ALTER TABLE HRORGANIZATION 
ADD (
    EMAIL VARCHAR2(100)
);


  CREATE TABLE APPLICATIONTRANSFER
   (	
    "ID" NUMBER NOT NULL ENABLE, 
	"APPLICATION" NUMBER, 
	"FROM_ORG" NUMBER, 
	"TO_ORG" NUMBER, 
	"FROM_USER" NUMBER, 
	"TO_USER" NUMBER, 
	"INITIATION_DATE" DATE, 
	"STATUS" VARCHAR2(20 BYTE), 
	"RESPOND_DATE" DATE, 
	"NOTE" VARCHAR2(1000 BYTE), 
	 CONSTRAINT "APPLICATIONTRANSFER_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "APPLICATIONTRANSFER_FK1" FOREIGN KEY ("APPLICATION")
	  REFERENCES APPLICATION ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  

ALTER TABLE APPLICATION
ADD (
		CURRENT_USER NUMBER,
		CURRENT_ORG NUMBER
);


  CREATE TABLE PLACEMENTTYPECATEGORY
   (	
	"ID" NUMBER NOT NULL ENABLE, 
	"CODE" VARCHAR2(50 BYTE), 
	"NAME" VARCHAR2(100 BYTE), 
	 CONSTRAINT "PLACEMENTTYPECATEGORY_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  
  
  ALTER TABLE PLACEMENTTYPE 
  ADD (
  CATEGORY NUMBER
  );
  
  CONSTRAINT "PLACEMENTTYPE_FK1" FOREIGN KEY ("CATEGORY")
	  REFERENCES PLACEMENTTYPECATEGORY ("ID") ENABLE
  
  
  
  
  CREATE TABLE TRANSFERRULES
   (	
    "ID" NUMBER NOT NULL ENABLE, 
	"FROMORG" NUMBER NOT NULL ENABLE, 
	"TOORG" NUMBER NOT NULL ENABLE, 
	 CONSTRAINT "TRANSFERRULES_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "TRANSFERRULES_FK1" FOREIGN KEY ("FROMORG")
	  REFERENCES HRORGANIZATION ("ID") ENABLE, 
	 CONSTRAINT "TRANSFERRULES_FK2" FOREIGN KEY ("TOORG")
	  REFERENCES HRORGANIZATION ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  CREATE TABLE DOCUMENTRECEIVE
   (	
    "ID" NUMBER NOT NULL ENABLE, 
	"DOCUMENT" NUMBER NOT NULL ENABLE, 
	"FROMUSER" NUMBER NOT NULL ENABLE, 
	"TOUSER" NUMBER NOT NULL ENABLE, 
	"RECEIVEDATE" DATE NOT NULL ENABLE, 
	"ACTIVE" NUMBER NOT NULL ENABLE, 
	 CONSTRAINT "DOCUMENTRECEIVE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "DOCUMENTRECEIVE_FK1" FOREIGN KEY ("DOCUMENT")
	  REFERENCES DOCUMENT ("ID") ENABLE, 
	 CONSTRAINT "DOCUMENTRECEIVE_FK2" FOREIGN KEY ("FROMUSER")
	  REFERENCES HRUSER ("ID") ENABLE, 
	 CONSTRAINT "DOCUMENTRECEIVE_FK3" FOREIGN KEY ("TOUSER")
	  REFERENCES HRUSER ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
  
  
  ALTER TABLE PLACEMENT MODIFY NAME VARCHAR2(500);

 
  