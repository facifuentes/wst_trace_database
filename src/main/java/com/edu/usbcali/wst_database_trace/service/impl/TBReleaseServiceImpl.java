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
	}

	@Override
	public void createReleaseDB(String schema, String release) throws Exception {
		releaseRepository.createReleaseDB(schema, release);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Schema> getAllSchemas() throws Exception {
		List<Object[]> results = releaseRepository.getAllSchemas();
		return results.stream().map(result -> new Schema((String) result[0])).collect(Collectors.toList());
	}

	@Override
	public List<ReleaseSchema> getAllReleasesbySchema(String schema) throws Exception {
		List<Object[]> results = releaseRepository.getAllReleasesbySchema(schema);
		return results.stream().map(result -> new ReleaseSchema((String) result[0], (String) result[1],
				(Timestamp) result[2], (boolean) result[3])).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<TBComparacion> getComparation(String schema, String releasenew, String releaseold) throws Exception {
		List<Object[]> results = releaseRepository.getComparation(schema, releasenew, releaseold);
		return results.stream()
				.map(result -> new TBComparacion((String) result[0], (String) result[1], (String) result[2],
						(String) result[3], (String) result[4], (String) result[5], (String) result[6],
						(String) result[7]))
				.collect(Collectors.toList());
	}

	@Override
	public void updateBaseLine(boolean estado, String release, String schema) throws Exception {
		releaseRepository.updateBaseLine(estado, release, schema);

	}

	// Control Parametros

	@Override
	public List<TBRelease> getReleaseDatabyR(String r_release) throws Exception {

		List<Object[]> results = releaseRepository.getReleaseDatabyR(r_release);

		return results.stream().map(result -> new TBRelease((String) result[1], (String) result[2], (String) result[3],
				(Timestamp) result[4], (boolean) result[6])).collect(Collectors.toList());
	}

	@Override
	public List<ReleaseSchema> getAllReleasesDatabySchema(String schema) throws Exception {
		List<Object[]> results = releaseRepository.getAllReleasesDatabySchema(schema);
		return results.stream().map(result -> new ReleaseSchema((String) result[0], (String) result[1],
				(Timestamp) result[2], (boolean) result[3])).collect(Collectors.toList());

	}

	@Override
	public List<TBComparacion> getComparationData(String schema, String releasenew, String releaseold)
			throws Exception {
		List<Object[]> results = releaseRepository.getComparationData(schema, releasenew, releaseold);
		return results.stream().map(result -> new TBComparacion((String) result[0], (String) result[1],
				(String) result[2], (String) result[3], (String) result[4], (String) result[5]))
				.collect(Collectors.toList());

	}

	@Override
	public void createReleaseData(String schema, String r_release) throws Exception {
		System.out.print("schema"+schema+"release"+r_release);
		releaseRepository.createReleaseData(schema, r_release);
	}

	@Override
	public void updateDataBaseLine(boolean estado, String idRelease, String esquema) throws Exception {
		releaseRepository.updateDataBaseLine(estado, idRelease, esquema);
	}

}
