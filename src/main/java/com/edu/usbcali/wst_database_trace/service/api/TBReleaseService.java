package com.edu.usbcali.wst_database_trace.service.api;

import java.util.List;

import com.edu.usbcali.wst_database_trace.model.ReleaseSchema;
import com.edu.usbcali.wst_database_trace.model.Schema;
import com.edu.usbcali.wst_database_trace.model.TBComparacion;
import com.edu.usbcali.wst_database_trace.model.TBRelease;

public interface TBReleaseService {

	List<Schema> getAllSchemas() throws Exception;

	List<ReleaseSchema> getAllReleasesbySchema(String schema) throws Exception;

	List<TBRelease> getRelease(String schema, String release) throws Exception;

	List<TBComparacion> getComparation(String schema, String releasenew, String releaseold) throws Exception;

	void createReleaseDB(String schema, String release) throws Exception;

	void updateBaseLine(boolean estado, String release, String schema) throws Exception;

	List<TBRelease> getReleaseDatabyR(String r_release) throws Exception;

	List<ReleaseSchema> getAllReleasesDatabySchema(String schema) throws Exception;

	List<TBComparacion> getComparationData(String schema, String releasenew, String releaseold) throws Exception;

	void createReleaseData(String schema, String r_release) throws Exception;

	void updateDataBaseLine(boolean estado, String idRelease, String esquema) throws Exception;

}
