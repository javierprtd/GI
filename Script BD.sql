USE [GI]
GO
/****** Object:  User [invitado]    Script Date: 12/08/2015 06:47:00 ******/
CREATE USER [invitado] FOR LOGIN [invitado] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[tLaboratorio]    Script Date: 12/08/2015 06:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tLaboratorio](
	[ID_LABORATORIO] [int] IDENTITY(1,1) NOT NULL,
	[NOMBRE_LABORATORIO] [varchar](80) NULL,
 CONSTRAINT [PK_tLaboratorio] PRIMARY KEY CLUSTERED 
(
	[ID_LABORATORIO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tPantalla]    Script Date: 12/08/2015 06:47:00 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tRol]    Script Date: 12/08/2015 06:47:00 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tUsuario]    Script Date: 12/08/2015 06:47:00 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tPermiso]    Script Date: 12/08/2015 06:47:00 ******/
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
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tMedicamento]    Script Date: 12/08/2015 06:47:00 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tMedicamento](
	[ID_MEDICAMENTO] [int] NOT NULL,
	[NOMBRE_MEDICAMENTO] [varchar](80) NULL,
	[CANTIDAD_DISPONIBLE] [int] NOT NULL,
	[LABORATORIO] [int] NOT NULL,
 CONSTRAINT [PK_tMedicamento] PRIMARY KEY CLUSTERED 
(
	[ID_MEDICAMENTO] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  ForeignKey [FK_tMedicamento_tLaboratorio]    Script Date: 12/08/2015 06:47:00 ******/
ALTER TABLE [dbo].[tMedicamento]  WITH CHECK ADD  CONSTRAINT [FK_tMedicamento_tLaboratorio] FOREIGN KEY([LABORATORIO])
REFERENCES [dbo].[tLaboratorio] ([ID_LABORATORIO])
GO
ALTER TABLE [dbo].[tMedicamento] CHECK CONSTRAINT [FK_tMedicamento_tLaboratorio]
GO
/****** Object:  ForeignKey [FK_tPermiso_tPantalla]    Script Date: 12/08/2015 06:47:00 ******/
ALTER TABLE [dbo].[tPermiso]  WITH CHECK ADD  CONSTRAINT [FK_tPermiso_tPantalla] FOREIGN KEY([pantalla])
REFERENCES [dbo].[tPantalla] ([pantalla])
GO
ALTER TABLE [dbo].[tPermiso] CHECK CONSTRAINT [FK_tPermiso_tPantalla]
GO
/****** Object:  ForeignKey [FK_tPermiso_tRol]    Script Date: 12/08/2015 06:47:00 ******/
ALTER TABLE [dbo].[tPermiso]  WITH CHECK ADD  CONSTRAINT [FK_tPermiso_tRol] FOREIGN KEY([rolName])
REFERENCES [dbo].[tRol] ([rolName])
GO
ALTER TABLE [dbo].[tPermiso] CHECK CONSTRAINT [FK_tPermiso_tRol]
GO
/****** Object:  ForeignKey [FK_tUsuario_tRol]    Script Date: 12/08/2015 06:47:00 ******/
ALTER TABLE [dbo].[tUsuario]  WITH CHECK ADD  CONSTRAINT [FK_tUsuario_tRol] FOREIGN KEY([rolName])
REFERENCES [dbo].[tRol] ([rolName])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[tUsuario] CHECK CONSTRAINT [FK_tUsuario_tRol]
GO
