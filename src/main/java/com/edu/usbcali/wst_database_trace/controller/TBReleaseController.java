package com.edu.usbcali.wst_database_trace.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.usbcali.wst_database_trace.model.ReleaseSchema;
import com.edu.usbcali.wst_database_trace.model.Response;
import com.edu.usbcali.wst_database_trace.model.Schema;
import com.edu.usbcali.wst_database_trace.model.TBComparacion;
import com.edu.usbcali.wst_database_trace.model.TBRelease;
import com.edu.usbcali.wst_database_trace.service.api.TBReleaseService;


@RestController
@RequestMapping("/api/dbtrace/")
public class TBReleaseController {

	@Autowired
	TBReleaseService releaseService;
	
	
	@GetMapping("getAllSchema")
	public List<Schema> getAllSchemas() {
		List<Schema> list=null;
		try {
			list = releaseService.getAllSchemas();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping("getAllReleasesbySchema/{schema}")
	public List<ReleaseSchema> getAllReleasesbySchema(@PathVariable("schema") String schema) {
		List<ReleaseSchema> list=null;
		try {
			list = releaseService.getAllReleasesbySchema(schema);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping("getRelease/{schema}/{release}")
	public List<TBRelease> getRelease(@PathVariable("schema") String schema,@PathVariable("release") String release) {
		List<TBRelease> list=null;
		try {
			list = releaseService.getRelease(schema, release);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@PostMapping("createReleaseDB/{schema}")
	public ResponseEntity<?> createReleaseDB(@PathVariable("schema") String schema) {
		try {
			Date objDate = new Date(); 
		
	        String strDateFormat = "dd-MM-yyyy_hh:mm:ss_a"; 
	        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); 
			String release="Release_DB_"+objSDF.format(objDate).toString();
			releaseService.createReleaseDB(schema, release);
			return ResponseEntity.ok().body(releaseService.getRelease(schema, release));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Response(400,"No es posible crear la huella"));
		}
	}
	
	@PutMapping("updateBaseLine/{schema}/{releasenuevo}/{releaseant}")
	public ResponseEntity<?> updateBaseLine(@PathVariable("schema") String schema,@PathVariable("releasenuevo") String releasenuevo,@PathVariable("releaseant") String releaseant) {
		try {
			releaseService.updateBaseLine(false, releaseant, schema);
			releaseService.updateBaseLine(true, releasenuevo, schema);
			return ResponseEntity.ok().body(new Response(200,"Linea Base Actualizada"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Response(400,"No es posible actualizar la linea base"));
		}
	}
	
	@GetMapping("getComparation/{schema}/{releasenew}/{releaseold}")
	public List<TBComparacion> getComparation(@PathVariable("schema") String schema,@PathVariable("releasenew") String releasenew,@PathVariable("releaseold") String releaseold) {
		List<TBComparacion> list=null;
		try {
			list = releaseService.getComparation(schema,releasenew, releaseold);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
