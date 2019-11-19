CREATE TABLE auditoria.tbrelease
(
    r_release text COLLATE pg_catalog."default" NOT NULL,
    r_campo text COLLATE pg_catalog."default" NOT NULL,
    r_estructura text COLLATE pg_catalog."default",
    r_huella text COLLATE pg_catalog."default",
    r_fecha timestamp with time zone,
    r_id serial NOT NULL,
    r_esquema text COLLATE pg_catalog."default" NOT NULL,
    r_lineaBase boolean DEFAULT false,
    CONSTRAINT id_release PRIMARY KEY (r_id),
    CONSTRAINT release_campo UNIQUE (r_release, r_campo)

)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE auditoria.tbrelease
    OWNER to postgres;