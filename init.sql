/*
-- Query: SELECT * FROM creditos.maestros
LIMIT 0, 1000

-- Date: 2016-12-29 14:29
*/
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (1,'1','Estados civiles',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (2,'2','Niveles educativos',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (3,'3','Tipos de vivienda',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (4,'4','Zonas de vivienda',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (5,'5','Ocupaciones',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (6,'6','Soltero',1);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (7,'7','Casado',1);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (8,'8','Divorciado',1);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (9,'9','Universitario',2);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (10,'10','Bachillerato',2);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (11,'11','Propia',3);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (12,'12','Arrendada',3);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (13,'13','Rural',4);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (14,'14','Urbana',4);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (15,'15','Empleado',5);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (16,'16','Independiente',5);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (17,'17','Jubilado',5);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (18,'18','Tipo de empresa',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (19,'19','Tipo de contrato',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (20,'20','Afinidades',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (21,'21','Textil',18);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (22,'22','Agropecuaria',18);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (23,'23','Técnologia',18);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (24,'24','Indefinido',19);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (25,'25','Temporal',19);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (26,'26','Conocido',20);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (27,'27','Familiar',20);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (30,'30','Tipos de credito',NULL);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (31,'CV','1.2',30);
INSERT INTO `maestros` (`ID`,`CODIGO`,`DESCRIPCION`,`PARENT_ID`) VALUES (32,'CL','1.9',30);
commit;
