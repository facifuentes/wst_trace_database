package com.edu.usbcali.wst_database_trace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;

import com.edu.usbcali.wst_database_trace.model.TBRelease;

public interface TBReleaseRepository extends JpaRepository<TBRelease, Long> {

	/*@Query(value = "select * from tbrelease where r_release=?1", nativeQuery = true)
	List<TBRelease> getReleasebyR(String r_release);
	
	@Query(value = "select * from f_comparacion(?1)", nativeQuery = true)
	List<Object[]> getComparationOne(String release1);*/
	
	@Query(value = "select * from auditoria.tbrelease where r_release=?1", nativeQuery = true)
	List<TBRelease> getReleasebyR(String r_release);
	
	@Query(value="select nspname esquemas from pg_catalog.pg_namespace where nspname not in ('pg_toast','pg_toast_temp_1','pg_temp_1','pg_catalog','information_schema','auditoria')",nativeQuery = true)
	List<Object[]> getAllSchemas();
	
	@Query(value="select distinct r_esquema,r_release, r_fecha, r_lineabase from auditoria.tbrelease where r_esquema=?1 order by 3", nativeQuery = true)
	List<Object[]> getAllReleasesbySchema(String schema);
	
	@Query(value = "select * from auditoria.f_selectrelease(?1,?2)", nativeQuery = true)
	List<Object[]> getRelease(String schema, String r_release);
	
	@Query(value = "select * from auditoria.f_comparationbyschema(?1,?2,?3)", nativeQuery = true)
	List<Object[]> getComparation(String schema,String releasenew, String releaseold);
	
	@Modifying
	@Query(value = "insert into auditoria.tbrelease (r_esquema,r_release,r_campo,r_estructura,r_huella,r_fecha)(select ?1,* from auditoria.f_selectrelease(?1,?2))", nativeQuery = true)
	@Transactional
	void createReleaseDB(String schema, String r_release);
	
	
	@Modifying
	@Query(value = "UPDATE auditoria.tbrelease SET r_lineabase=?1 WHERE r_release=?2 and r_esquema=?3", nativeQuery = true)
	@Transactional
	void updateBaseLine(boolean estado,String idRelease, String esquema);
	
}
