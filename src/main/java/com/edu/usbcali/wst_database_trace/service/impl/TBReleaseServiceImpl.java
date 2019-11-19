package com.edu.usbcali.wst_database_trace.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.usbcali.wst_database_trace.model.ReleaseSchema;
import com.edu.usbcali.wst_database_trace.model.Schema;
import com.edu.usbcali.wst_database_trace.model.TBComparacion;
import com.edu.usbcali.wst_database_trace.model.TBRelease;
import com.edu.usbcali.wst_database_trace.repository.TBReleaseRepository;
import com.edu.usbcali.wst_database_trace.service.api.TBReleaseService;

@Service
@Scope("singleton")
public class TBReleaseServiceImpl implements TBReleaseService {

	@Autowired
	TBReleaseRepository releaseRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TBRelease> getRelease(String schema, String release) throws Exception {
		 return releaseRepository.getReleasebyR(release);
		/* List<Object[]> results =return results.stream().map(result -> new TBRelease((String) result[0], (String) result[1], (String) result[2],
				(String) result[3], (Timestamp) result[4],(Long))).collect(Collectors.toList());*/
	}


	@Override
	public void createReleaseDB(String schema, String release) throws Exception{
		releaseRepository.createReleaseDB(schema, release);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Schema> getAllSchemas() throws Exception {
		List<Object[]> results = releaseRepository.getAllSchemas();
		return results.stream().map(result -> new Schema((String) result[0])).collect(Collectors.toList());
	}

	@Override
	public List<ReleaseSchema> getAllReleasesbySchema(String schema)throws Exception {
		List<Object[]> results = releaseRepository.getAllReleasesbySchema(schema);
		return results.stream().map(result -> new ReleaseSchema((String) result[0],(String) result[1],(Timestamp) result[2], (boolean) result[3])).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<TBComparacion> getComparation(String schema, String releasenew, String releaseold) throws Exception {
		List<Object[]> results =  releaseRepository.getComparation(schema,releasenew,releaseold);
		return results.stream().map(result -> new TBComparacion((String) result[0], (String) result[1], (String) result[2],
				(String) result[3], (String) result[4],(String) result[5],(String) result[6],(String) result[7])).collect(Collectors.toList());
	}


	@Override
	public void updateBaseLine(boolean estado, String release, String schema) throws Exception {
		releaseRepository.updateBaseLine(estado, release, schema);
		
	}

}
