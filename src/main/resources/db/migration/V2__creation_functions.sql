CREATE EXTENSION pgcrypto;

create or replace function auditoria.f_selectrelease(pschema varchar,prelease varchar)
returns table(r_release character varying,r_campo text ,r_estructura text,r_huella text,r_fecha timestamp with time zone)
as $$
begin
return query
SELECT  prelease r_release, estructura.tabla || '.' || estructura.name r_campo,(estructura.tabla || '.' || estructura.name || '.' || estructura.type) r_estructura ,encode(auditoria.digest(estructura::text, 'sha256'),'hex') r_huella , current_timestamp r_fecha
	from
(SELECT  c.relname AS tabla,
    f.attnum AS number,  
    f.attname AS name,  
    f.attnum,  
    f.attnotnull AS notnull,  
    pg_catalog.format_type(f.atttypid,f.atttypmod) AS type,  
    CASE  
        WHEN p.contype = 'p' THEN 't'  
        ELSE 'f'  
    END AS primarykey,  
    CASE  
        WHEN p.contype = 'u' THEN 't'  
        ELSE 'f'
    END AS uniquekey,
    CASE
        WHEN p.contype = 'f' THEN g.relname
    END AS foreignkey,
    CASE
        WHEN p.contype = 'f' THEN p.confkey
    END AS foreignkey_fieldnum,
    CASE
        WHEN p.contype = 'f' THEN g.relname
    END AS foreignkey,
    CASE
        WHEN p.contype = 'f' THEN p.conkey
    END AS foreignkey_connnum,
    CASE
        WHEN f.atthasdef = 't' THEN d.adsrc
    END AS default
FROM pg_attribute f  
    JOIN pg_class c ON c.oid = f.attrelid  
    JOIN pg_type t ON t.oid = f.atttypid  
    LEFT JOIN pg_attrdef d ON d.adrelid = c.oid AND d.adnum = f.attnum  
    LEFT JOIN pg_namespace n ON n.oid = c.relnamespace  
    LEFT JOIN pg_constraint p ON p.conrelid = c.oid AND f.attnum = ANY (p.conkey)  
    LEFT JOIN pg_class AS g ON p.confrelid = g.oid  
WHERE c.relkind = 'r'::char 
    AND n.nspname = pschema 
 	AND c.relname <> 'tbrelease'
    AND f.attnum > 0 ORDER BY c.relname,number) estructura;
end;
$$ language plpgsql;


create or replace function f_comparationbyschema(pschema VARCHAR,preleasenew VARCHAR, preleaseold VARCHAR)
returns table(releasenuevo text,camponuevo text, estructuranueva text, huellanueva text, releaseanterior text, estructuraanterior text,huellaanterior text,estado text)
as $$
begin
return query
select r.r_release releasenuevo, r.r_campo camponuevo, r.r_estructura estructuranueva, r.r_huella huellanueva,l.r_release releaseanterior, l.r_estructura estructuraanterior ,l.r_huella huellaanterior,
CASE WHEN l.r_campo is Null THEN 'NUEVO' ELSE 
(CASE WHEN l.r_huella=r.r_huella THEN 'OK' ELSE 'MODIFICADO' END) 
END AS Estado
FROM auditoria.tbrelease r
LEFT JOIN auditoria.tbrelease l on r.r_campo=l.r_campo and l.r_release=preleaseold  and l.r_esquema=pschema 
where r.r_release=preleasenew  and r.r_esquema=pschema
UNION 
select preleasenew,null,null,null, r_release,r_estructura, r_huella,'ELIMINADO' 
from auditoria.tbrelease 
where r_campo not in (select r_campo from auditoria.tbrelease where r_release=preleasenew and r_esquema=pschema) and r_release= preleaseold and r_esquema=pschema ;
end;
$$ language plpgsql;