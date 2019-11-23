
CREATE TABLE auditoria.tbreleasedata
(
    tbd_id serial NOT NULL,
    tbd_release text COLLATE pg_catalog."default" NOT NULL,
    tbd_campo text COLLATE pg_catalog."default",
    tbd_huella text COLLATE pg_catalog."default",
    tbd_fecha timestamp with time zone,
    tbd_esquema text COLLATE pg_catalog."default" NOT NULL,
    tbd_lineabase boolean DEFAULT false,
    CONSTRAINT tbreleasedata_pkey PRIMARY KEY (tbd_id)
)WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE auditoria.tbreleasedata
    OWNER to postgres;


create or replace function auditoria.f_selectreleasedata(pschema varchar,prelease varchar)
returns table(tbd_release text,tbd_campo text,tbd_huella text,tbd_fecha timestamp with time zone)
as $$
begin
return query
EXECUTE 'select '||quote_literal(prelease)||' tbd_release, datos.item tbd_campo, encode(auditoria.digest(datos::text,' ||quote_literal(e'sha256')||'),'||quote_literal(e'hex')||') tbd_huella, current_timestamp tbd_fecha from (select item, valor from '||pschema||'.parametros) datos' ;
end;
$$ language plpgsql;


create or replace function auditoria.f_comparationdata(pschema VARCHAR,preleasenew VARCHAR, preleaseold VARCHAR)
returns table(releasenuevo text,camponuevo text, huellanueva text, releaseanterior text,huellaanterior text,estado text)
as $$
begin
return query
select r.tbd_release releasenuevo, r.tbd_campo camponuevo,  r.tbd_huella huellanueva,l.tbd_release releaseanterior ,l.tbd_huella huellaanterior,
CASE WHEN l.tbd_campo is Null THEN 'NUEVO' ELSE 
(CASE WHEN l.tbd_huella=r.tbd_huella THEN 'OK' ELSE 'MODIFICADO' END) 
END AS Estado
FROM auditoria.tbreleasedata r
LEFT JOIN auditoria.tbreleasedata l on r.tbd_campo=l.tbd_campo and l.tbd_release=preleaseold  and l.tbd_esquema=pschema 
where r.tbd_release=preleasenew  and r.tbd_esquema=pschema
UNION 
select preleasenew,null,null, tbd_release, tbd_huella,'ELIMINADO' 
from auditoria.tbreleasedata 
where tbd_campo not in (select tbd_campo from auditoria.tbreleasedata where tbd_release=preleasenew and tbd_esquema=pschema) and tbd_release= preleaseold and tbd_esquema=pschema ;
end;
$$ language plpgsql;