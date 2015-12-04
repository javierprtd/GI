USE [master]
GO
DROP DATABASE GI
GO
CREATE DATABASE GI
GO
USE GI
GO

/****** Object:  Table [dbo].[tGlucometria]    Script Date: 04/11/2015 11:13:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tGlucometria](
	[ID] [int] NOT NULL,
	[NumSS] [int] NOT NULL,
	[Fecha_Horal] [datetime] NOT NULL,
	[Valor] [real] NULL,
 CONSTRAINT [PK_tGlucometria] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tPaciente]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tPaciente](
	[NumSS] [int] NOT NULL,
	[DNI_NIE] [varchar](50) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Apellidos] [varchar](50) NOT NULL,
	[Sexo] [varchar](10) NOT NULL,
	[FechaNacimiento] [date] NOT NULL,
	[Direccion] [varchar](80) NULL,
	[Poblacion] [varchar](50) NULL,
	[Provincia] [varchar](50) NULL,
	[CodigoPostal] [varchar](50) NULL,
	[Pais] [varchar](3) NULL,
	[Telefono] [varchar](50) NULL,
	[e_mail] [varchar](50) NULL,
 CONSTRAINT [PK_tPaciente] PRIMARY KEY CLUSTERED 
(
	[NumSS] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tPais]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tPais](
	[Codigo] [varchar](3) NOT NULL,
	[Descripcion] [varchar](50) NULL,
 CONSTRAINT [PK_tPais] PRIMARY KEY CLUSTERED 
(
	[Codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tPantalla]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tPantalla](
	[pantalla] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tPantalla] PRIMARY KEY CLUSTERED 
(
	[pantalla] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tPermiso]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tPermiso](
	[rolName] [varchar](50) NOT NULL,
	[pantalla] [varchar](50) NOT NULL,
	[acceso] [bit] NOT NULL,
	[modificacion] [bit] NOT NULL,
 CONSTRAINT [PK_tPermiso] PRIMARY KEY CLUSTERED 
(
	[rolName] ASC,
	[pantalla] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tRol]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tRol](
	[rolName] [varchar](50) NOT NULL,
	[rolDes] [varchar](255) NULL,
	[admin] [bit] NOT NULL,
 CONSTRAINT [PK_tRol] PRIMARY KEY CLUSTERED 
(
	[rolName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tUsuario]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tUsuario](
	[nombre] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[rolName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_tUsuario] PRIMARY KEY CLUSTERED 
(
	[nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tValoresGlucometria]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tValoresGlucometria](
	[Nombre] [varchar](50) NULL,
	[ValorMinimo] [real] NOT NULL,
	[ValorMaximo] [real] NOT NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[tGlucometria]  WITH CHECK ADD  CONSTRAINT [FK_tGlucometria_tPaciente] FOREIGN KEY([NumSS])
REFERENCES [dbo].[tPaciente] ([NumSS])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tGlucometria] CHECK CONSTRAINT [FK_tGlucometria_tPaciente]
GO
ALTER TABLE [dbo].[tPaciente]  WITH CHECK ADD  CONSTRAINT [FK_tPaciente_tPais] FOREIGN KEY([Pais])
REFERENCES [dbo].[tPais] ([Codigo])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tPaciente] CHECK CONSTRAINT [FK_tPaciente_tPais]
GO
ALTER TABLE [dbo].[tPermiso]  WITH CHECK ADD  CONSTRAINT [FK_tPermiso_tPantalla] FOREIGN KEY([pantalla])
REFERENCES [dbo].[tPantalla] ([pantalla])
GO
ALTER TABLE [dbo].[tPermiso] CHECK CONSTRAINT [FK_tPermiso_tPantalla]
GO
ALTER TABLE [dbo].[tPermiso]  WITH CHECK ADD  CONSTRAINT [FK_tPermiso_tRol] FOREIGN KEY([rolName])
REFERENCES [dbo].[tRol] ([rolName])
GO
ALTER TABLE [dbo].[tPermiso] CHECK CONSTRAINT [FK_tPermiso_tRol]
GO
ALTER TABLE [dbo].[tUsuario]  WITH CHECK ADD  CONSTRAINT [FK_tUsuario_tRol] FOREIGN KEY([rolName])
REFERENCES [dbo].[tRol] ([rolName])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tUsuario] CHECK CONSTRAINT [FK_tUsuario_tRol]
GO
/****** Object:  StoredProcedure [dbo].[InsertaUsuario]    Script Date: 04/11/2015 11:13:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER OFF
GO


DELETE tPais;
GO
INSERT INTO tPais VALUES('ABW', 'ARUBA');
INSERT INTO tPais VALUES('AFG', 'AFGANISTÁN');
INSERT INTO tPais VALUES('AGO', 'ANGOLA');
INSERT INTO tPais VALUES('AIA', 'ANGUILA');
INSERT INTO tPais VALUES('ALA', 'ÅLAND');
INSERT INTO tPais VALUES('ALB', 'ALBANIA');
INSERT INTO tPais VALUES('AND', 'ANDORRA');
INSERT INTO tPais VALUES('ANT', 'ANTILLAS NEERLANDESAS');
INSERT INTO tPais VALUES('ARE', 'EMIRATOS ÁRABES UNIDOS');
INSERT INTO tPais VALUES('ARG', 'ARGENTINA');
INSERT INTO tPais VALUES('ARM', 'ARMENIA');
INSERT INTO tPais VALUES('ASM', 'SAMOA ESTADOUNIDENSE');
INSERT INTO tPais VALUES('ATG', 'ANTIGUA Y BARBUDA');
INSERT INTO tPais VALUES('AUS', 'AUSTRALIA');
INSERT INTO tPais VALUES('AUT', 'AUSTRIA');
INSERT INTO tPais VALUES('AZE', 'AZERBAIYÁN');
INSERT INTO tPais VALUES('BDI', 'BURUNDI');
INSERT INTO tPais VALUES('BEL', 'BÉLGICA');
INSERT INTO tPais VALUES('BEN', 'BENÍN');
INSERT INTO tPais VALUES('BFA', 'BURKINA FASO');
INSERT INTO tPais VALUES('BGD', 'BANGLADÉS');
INSERT INTO tPais VALUES('BGR', 'BULGARIA');
INSERT INTO tPais VALUES('BHR', 'BARÉIN');
INSERT INTO tPais VALUES('BHS', 'BAHAMAS');
INSERT INTO tPais VALUES('BIH', 'BOSNIA Y HERZEGOVINA');
INSERT INTO tPais VALUES('BLM', 'SAN BARTOLOMÉ');
INSERT INTO tPais VALUES('BLR', 'BIELORRUSIA');
INSERT INTO tPais VALUES('BLZ', 'BELICE');
INSERT INTO tPais VALUES('BMU', 'BERMUDAS');
INSERT INTO tPais VALUES('BOL', 'BOLIVIA');
INSERT INTO tPais VALUES('BRA', 'BRASIL');
INSERT INTO tPais VALUES('BRB', 'BARBADOS');
INSERT INTO tPais VALUES('BRN', 'BRUNÉI');
INSERT INTO tPais VALUES('BTN', 'BUTÁN');
INSERT INTO tPais VALUES('BVT', 'ISLA BOUVET');
INSERT INTO tPais VALUES('BWA', 'BOTSUANA');
INSERT INTO tPais VALUES('CAF', 'REPÚBLICA CENTROAFRICANA');
INSERT INTO tPais VALUES('CAN', 'CANADÁ');
INSERT INTO tPais VALUES('CCK', 'ISLAS COCOS');
INSERT INTO tPais VALUES('CHL', 'CHILE');
INSERT INTO tPais VALUES('CHN', 'CHINA');
INSERT INTO tPais VALUES('CIV', 'COSTA DE MARFIL');
INSERT INTO tPais VALUES('CMR', 'CAMERÚN');
INSERT INTO tPais VALUES('COD', 'REP. DEM. DEL CONGO');
INSERT INTO tPais VALUES('COG', 'REPÚBLICA DEL CONGO');
INSERT INTO tPais VALUES('COK', 'ISLAS COOK');
INSERT INTO tPais VALUES('COL', 'COLOMBIA');
INSERT INTO tPais VALUES('COM', 'COMORAS');
INSERT INTO tPais VALUES('CPV', 'CABO VERDE');
INSERT INTO tPais VALUES('CRI', 'COSTA RICA');
INSERT INTO tPais VALUES('CUB', 'CUBA');
INSERT INTO tPais VALUES('CXR', 'ISLA DE NAVIDAD');
INSERT INTO tPais VALUES('CYM', 'ISLAS CAIMÁN');
INSERT INTO tPais VALUES('CYP', 'CHIPRE');
INSERT INTO tPais VALUES('CZE', 'REPÚBLICA CHECA');
INSERT INTO tPais VALUES('DEU', 'ALEMANIA');
INSERT INTO tPais VALUES('DMA', 'DOMINICA');
INSERT INTO tPais VALUES('DNK', 'DINAMARCA');
INSERT INTO tPais VALUES('DOM', 'REPÚBLICA DOMINICANA');
INSERT INTO tPais VALUES('DZA', 'ARGELIA');
INSERT INTO tPais VALUES('ECU', 'ECUADOR');
INSERT INTO tPais VALUES('EGY', 'EGIPTO');
INSERT INTO tPais VALUES('ERI', 'ERITREA');
INSERT INTO tPais VALUES('ESH', 'REPÚBLICA ÁRABE SAHARAUI DEMOCRÁTICA');
INSERT INTO tPais VALUES('ESP', 'ESPAÑA');
INSERT INTO tPais VALUES('EST', 'ESTONIA');
INSERT INTO tPais VALUES('ETH', 'ETIOPÍA');
INSERT INTO tPais VALUES('FIN', 'FINLANDIA');
INSERT INTO tPais VALUES('FJI', 'FIYI');
INSERT INTO tPais VALUES('FLK', 'ISLAS MALVINAS');
INSERT INTO tPais VALUES('FRA', 'FRANCIA');
INSERT INTO tPais VALUES('FRO', 'ISLAS FEROE');
INSERT INTO tPais VALUES('FSM', 'MICRONESIA');
INSERT INTO tPais VALUES('GAB', 'GABÓN');
INSERT INTO tPais VALUES('GBR', 'REINO UNIDO');
INSERT INTO tPais VALUES('GEO', 'GEORGIA');
INSERT INTO tPais VALUES('GGY', 'GUERNSEY');
INSERT INTO tPais VALUES('GHA', 'GHANA');
INSERT INTO tPais VALUES('GIB', 'GIBRALTAR');
INSERT INTO tPais VALUES('GIN', 'GUINEA');
INSERT INTO tPais VALUES('GLP', 'GUADALUPE');
INSERT INTO tPais VALUES('GMB', 'GAMBIA');
INSERT INTO tPais VALUES('GNB', 'GUINEA-BISSAU');
INSERT INTO tPais VALUES('GNQ', 'GUINEA ECUATORIAL');
INSERT INTO tPais VALUES('GRC', 'GRECIA');
INSERT INTO tPais VALUES('GRD', 'GRANADA');
INSERT INTO tPais VALUES('GRL', 'GROENLANDIA');
INSERT INTO tPais VALUES('GTM', 'GUATEMALA');
INSERT INTO tPais VALUES('GUF', 'GUAYANA FRANCESA');
INSERT INTO tPais VALUES('GUM', 'GUAM');
INSERT INTO tPais VALUES('GUY', 'GUYANA');
INSERT INTO tPais VALUES('HKG', 'HONG KONG');
INSERT INTO tPais VALUES('HMD', 'ISLAS HEARD Y MCDONALD');
INSERT INTO tPais VALUES('HND', 'HONDURAS');
INSERT INTO tPais VALUES('HRV', 'CROACIA');
INSERT INTO tPais VALUES('HTI', 'HAITÍ');
INSERT INTO tPais VALUES('HUN', 'HUNGRÍA');
INSERT INTO tPais VALUES('IDN', 'INDONESIA');
INSERT INTO tPais VALUES('IMN', 'ISLA DE MAN');
INSERT INTO tPais VALUES('IND', 'INDIA');
INSERT INTO tPais VALUES('IRL', 'IRLANDA');
INSERT INTO tPais VALUES('IRN', 'IRÁN');
INSERT INTO tPais VALUES('IRQ', 'IRAK');
INSERT INTO tPais VALUES('ISL', 'ISLANDIA');
INSERT INTO tPais VALUES('ISR', 'ISRAEL');
INSERT INTO tPais VALUES('ITA', 'ITALIA');
INSERT INTO tPais VALUES('JAM', 'JAMAICA');
INSERT INTO tPais VALUES('JEY', 'JERSEY');
INSERT INTO tPais VALUES('JOR', 'JORDANIA');
INSERT INTO tPais VALUES('JPN', 'JAPÓN');
INSERT INTO tPais VALUES('KAZ', 'KAZAJISTÁN');
INSERT INTO tPais VALUES('KEN', 'KENIA');
INSERT INTO tPais VALUES('KGZ', 'KIRGUISTÁN');
INSERT INTO tPais VALUES('KHM', 'CAMBOYA');
INSERT INTO tPais VALUES('KIR', 'KIRIBATI');
INSERT INTO tPais VALUES('KNA', 'SAN CRISTÓBAL Y NIEVES');
INSERT INTO tPais VALUES('KOR', 'COREA DEL SUR');
INSERT INTO tPais VALUES('KWT', 'KUWAIT');
INSERT INTO tPais VALUES('LAO', 'LAOS');
INSERT INTO tPais VALUES('LBN', 'LÍBANO');
INSERT INTO tPais VALUES('LBR', 'LIBERIA');
INSERT INTO tPais VALUES('LBY', 'LIBIA');
INSERT INTO tPais VALUES('LCA', 'SANTA LUCÍA');
INSERT INTO tPais VALUES('LIE', 'LIECHTENSTEIN');
INSERT INTO tPais VALUES('LSO', 'LESOTO');
INSERT INTO tPais VALUES('LTU', 'LITUANIA');
INSERT INTO tPais VALUES('LUX', 'LUXEMBURGO');
INSERT INTO tPais VALUES('LVA', 'LETONIA');
INSERT INTO tPais VALUES('MAC', 'MACAO');
INSERT INTO tPais VALUES('MAF', 'SAN MARTÍN');
INSERT INTO tPais VALUES('MAR', 'MARRUECOS');
INSERT INTO tPais VALUES('MCO', 'MÓNACO');
INSERT INTO tPais VALUES('MDA', 'MOLDAVIA');
INSERT INTO tPais VALUES('MDG', 'MADAGASCAR');
INSERT INTO tPais VALUES('MDV', 'MALDIVAS');
INSERT INTO tPais VALUES('MEX', 'MÉXICO');
INSERT INTO tPais VALUES('MHL', 'ISLAS MARSHALL');
INSERT INTO tPais VALUES('MKD', 'REPÚBLICA DE MACEDONIA');
INSERT INTO tPais VALUES('MLI', 'MALÍ');
INSERT INTO tPais VALUES('MLT', 'MALTA');
INSERT INTO tPais VALUES('MMR', 'BIRMANIA');
INSERT INTO tPais VALUES('MNE', 'MONTENEGRO');
INSERT INTO tPais VALUES('MNG', 'MONGOLIA');
INSERT INTO tPais VALUES('MNP', 'ISLAS MARIANAS DEL NORTE');
INSERT INTO tPais VALUES('MOZ', 'MOZAMBIQUE');
INSERT INTO tPais VALUES('MRT', 'MAURITANIA');
INSERT INTO tPais VALUES('MSR', 'MONTSERRAT');
INSERT INTO tPais VALUES('MTQ', 'MARTINICA');
INSERT INTO tPais VALUES('MUS', 'MAURICIO');
INSERT INTO tPais VALUES('MWI', 'MALAUI');
INSERT INTO tPais VALUES('MYS', 'MALASIA');
INSERT INTO tPais VALUES('MYT', 'MAYOTTE');
INSERT INTO tPais VALUES('NAM', 'NAMIBIA');
INSERT INTO tPais VALUES('NCL', 'NUEVA CALEDONIA');
INSERT INTO tPais VALUES('NER', 'NÍGER');
INSERT INTO tPais VALUES('NFK', 'NORFOLK');
INSERT INTO tPais VALUES('NGA', 'NIGERIA');
INSERT INTO tPais VALUES('NIC', 'NICARAGUA');
INSERT INTO tPais VALUES('NIU', 'NIUE');
INSERT INTO tPais VALUES('NLD', 'PAÍSES BAJOS');
INSERT INTO tPais VALUES('NOR', 'NORUEGA');
INSERT INTO tPais VALUES('NPL', 'NEPAL');
INSERT INTO tPais VALUES('NRU', 'NAURU');
INSERT INTO tPais VALUES('NZL', 'NUEVA ZELANDA');
INSERT INTO tPais VALUES('OMN', 'OMÁN');
INSERT INTO tPais VALUES('PAK', 'PAKISTÁN');
INSERT INTO tPais VALUES('PAN', 'PANAMÁ');
INSERT INTO tPais VALUES('PCN', 'ISLAS PITCAIRN');
INSERT INTO tPais VALUES('PER', 'PERÚ');
INSERT INTO tPais VALUES('PHL', 'FILIPINAS');
INSERT INTO tPais VALUES('PLW', 'PALAOS');
INSERT INTO tPais VALUES('PNG', 'PAPÚA NUEVA GUINEA');
INSERT INTO tPais VALUES('POL', 'POLONIA');
INSERT INTO tPais VALUES('PRI', 'PUERTO RICO');
INSERT INTO tPais VALUES('PRK', 'COREA DEL NORTE');
INSERT INTO tPais VALUES('PRT', 'PORTUGAL');
INSERT INTO tPais VALUES('PRY', 'PARAGUAY');
INSERT INTO tPais VALUES('PSE', 'AUTORIDAD NACIONAL PALESTINA');
INSERT INTO tPais VALUES('PYF', 'POLINESIA FRANCESA');
INSERT INTO tPais VALUES('QAT', 'CATAR');
INSERT INTO tPais VALUES('REU', 'REUNIÓN');
INSERT INTO tPais VALUES('ROU', 'RUMANÍA');
INSERT INTO tPais VALUES('RUS', 'RUSIA');
INSERT INTO tPais VALUES('RWA', 'RUANDA');
INSERT INTO tPais VALUES('SAU', 'ARABIA SAUDITA');
INSERT INTO tPais VALUES('SEN', 'SENEGAL');
INSERT INTO tPais VALUES('SGS', 'ISLAS GEORGIAS DEL SUR Y SANDWICH DEL SUR');
INSERT INTO tPais VALUES('SHN', 'SANTA HELENA, A. Y T.');
INSERT INTO tPais VALUES('SLB', 'ISLAS SALOMÓN');
INSERT INTO tPais VALUES('SLV', 'EL SALVADOR');
INSERT INTO tPais VALUES('SMR', 'SAN MARINO');
INSERT INTO tPais VALUES('SPM', 'SAN PEDRO Y MIQUELÓN');
INSERT INTO tPais VALUES('SRB', 'SERBIA');
INSERT INTO tPais VALUES('STP', 'SANTO TOMÉ Y PRÍNCIPE');
INSERT INTO tPais VALUES('SVK', 'ESLOVAQUIA');
INSERT INTO tPais VALUES('SVN', 'ESLOVENIA');
INSERT INTO tPais VALUES('TCD', 'CHAD');
INSERT INTO tPais VALUES('USA', 'ESTADOS UNIDOS');
INSERT INTO tPais VALUES('VCT', 'SAN VICENTE Y LAS GRANADINAS');
INSERT INTO tPais VALUES('WSM', 'SAMOA');
GO

DELETE tValoresGlucometria;
GO
INSERT INTO tValoresGlucometria VALUES('Normoglicemia', 60.0,110.0);
INSERT INTO tValoresGlucometria VALUES('Hiperglicemia', 111.0,500.0);
INSERT INTO tValoresGlucometria VALUES('Hipoglucemia', 0.0,59.0);
GO


DELETE tPantalla;
GO
INSERT INTO tPantalla VALUES('LOGIN');
INSERT INTO tPantalla VALUES('USUARIOS');
GO

DELETE tRol;
INSERT INTO tRol VALUES('administrador', 'administrador',1);
INSERT INTO tRol VALUES('administrativo', 'administrativo',0);
INSERT INTO tRol VALUES('medico', 'medico',0);
INSERT INTO tRol VALUES('paciente', 'paciente',0);
GO

DELETE tPaciente;
GO
INSERT INTO tPaciente VALUES(1, '1', 'N1', 'AP1', 'HOMBRE', '1971-03-10', 'CASA 1', 'MALAGA', 'MALAGA', '29001', 'ESP', '111111111', '1@MAIL.COM');
INSERT INTO tPaciente VALUES(2, '2', 'N2', 'AP2', 'HOMBRE', '1998-05-05', 'CASA 2', 'MALAGA', 'MALAGA', '29002', 'CRI', '222222222', '2@MAIL.COM');
INSERT INTO tPaciente VALUES(3, '3', 'N3', 'AP3', 'MUJER', '2003-03-03', 'CASA 3', 'MALAGA', 'MALAGA', '29003', 'ESP', '3333333333', '3@MAIL.COM');
INSERT INTO tPaciente VALUES(4, '4', 'N4', 'AP4', 'MUJER', '2004-04-04', 'CASA 4', 'PARIS', 'PARIS', '2131231', 'FRA', '444444444', '4@MAIL.COM');
INSERT INTO tPaciente VALUES(5, '5', 'N5', 'AP5', 'MUJER', '2005-05-05', 'CASA 5', 'MALAGA', 'MALAGA', '29005', 'EST', '555555555', '5@MAIL.COM');
GO

GO
DELETE tUsuario;
GO
INSERT INTO tUsuario VALUES('admin', 'admin','administrador');
INSERT INTO tUsuario VALUES('juan', 'juan','administrador');
INSERT INTO tUsuario VALUES('maria', 'maria','paciente');
INSERT INTO tUsuario VALUES('pedro', 'pedro','administrativo');
INSERT INTO tUsuario VALUES('ana', 'ana','medico');
GO

DELETE tPermiso;
GO
INSERT INTO tPermiso VALUES('administrador','LOGIN',1,1);
INSERT INTO tPermiso VALUES('administrador','USUARIOS',1,1);
INSERT INTO tPermiso VALUES('administrativo','LOGIN',1,1);
INSERT INTO tPermiso VALUES('administrativo','USUARIOS',1,0);
INSERT INTO tPermiso VALUES('medico','LOGIN',1,1);
INSERT INTO tPermiso VALUES('medico','USUARIOS',0,0);
INSERT INTO tPermiso VALUES('paciente','LOGIN',1,1);
INSERT INTO tPermiso VALUES('paciente','USUARIOS',0,0);
GO

