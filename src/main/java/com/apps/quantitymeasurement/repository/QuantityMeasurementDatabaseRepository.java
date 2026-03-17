
package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

	private static final Logger logger = Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

	private static QuantityMeasurementDatabaseRepository instance;

	private ConnectionPool connectionPool;

	private static final String INSERT_QUERY = "INSERT INTO quantity_measurement_entity "
			+ "(this_value,this_unit,this_measurement_type," + "that_value,that_unit,that_measurement_type,"
			+ "operation,result_value,result_unit,result_measurement_type,"
			+ "result_string,is_error,error_message,created_at,updated_at)"
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),NOW())";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM quantity_measurement_entity ORDER BY created_at DESC";

	private static final String SELECT_BY_OPERATION = "SELECT * FROM quantity_measurement_entity WHERE operation=? ORDER BY created_at DESC";

	private static final String SELECT_BY_MEASUREMENT_TYPE = "SELECT * FROM quantity_measurement_entity WHERE this_measurement_type=? ORDER BY created_at DESC";

	private static final String DELETE_ALL_QUERY = "DELETE FROM quantity_measurement_entity";

	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM quantity_measurement_entity";

	private QuantityMeasurementDatabaseRepository() {
		try {
			connectionPool = ConnectionPool.getInstance();
			initializeDatabase();
		} catch (Exception e) {
			throw new DatabaseException("Failed to initialize repository", e);
		}
	}

	private void initializeDatabase() {

		String schema = "CREATE TABLE IF NOT EXISTS quantity_measurement_entity("
				+ "id BIGINT AUTO_INCREMENT PRIMARY KEY," + "this_value DOUBLE," + "this_unit VARCHAR(50),"
				+ "this_measurement_type VARCHAR(50)," + "that_value DOUBLE," + "that_unit VARCHAR(50),"
				+ "that_measurement_type VARCHAR(50)," + "operation VARCHAR(20)," + "result_value DOUBLE,"
				+ "result_unit VARCHAR(50)," + "result_measurement_type VARCHAR(50)," + "result_string VARCHAR(255),"
				+ "is_error BOOLEAN," + "error_message VARCHAR(500),"
				+ "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

		try {

			Connection conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();

			stmt.execute(schema);

			closeResources(stmt, conn);

		} catch (Exception e) {

			logger.warning("Schema initialization failed: " + e.getMessage());
		}
	}

	public static synchronized QuantityMeasurementDatabaseRepository getInstance() {

		if (instance == null) {
			instance = new QuantityMeasurementDatabaseRepository();
		}

		return instance;
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {

			conn = connectionPool.getConnection();
			stmt = conn.prepareStatement(INSERT_QUERY);

			stmt.setDouble(1, entity.getThisValue());
			stmt.setString(2, entity.getThisUnit());
			stmt.setString(3, entity.getThisMeasurementType());
			stmt.setDouble(4, entity.getThatValue());
			stmt.setString(5, entity.getThatUnit());
			stmt.setString(6, entity.getThatMeasurementType());
			stmt.setString(7, entity.getOperation());
			stmt.setDouble(8, entity.getResultValue());
			stmt.setString(9, entity.getResultUnit());
			stmt.setString(10, entity.getResultMeasurementType());
			stmt.setString(11, entity.getResultString());
			stmt.setBoolean(12, entity.isError());
			stmt.setString(13, entity.getErrorMessage());

			stmt.executeUpdate();

			logger.info("Measurement saved");

		} catch (Exception e) {

			throw new DatabaseException("Error saving measurement", e);

		} finally {

			closeResources(stmt, conn);
		}
	}

	@Override
	public List<QuantityMeasurementEntity> getAllMeasurements() {

		List<QuantityMeasurementEntity> list = new ArrayList<>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			conn = connectionPool.getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(SELECT_ALL_QUERY);

			while (rs.next()) {
				list.add(mapResultSetToEntity(rs));
			}

		} catch (Exception e) {

			throw new DatabaseException("Error fetching measurements", e);

		} finally {

			closeResources(rs, stmt, conn);
		}

		return list;
	}

	public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {

		List<QuantityMeasurementEntity> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = connectionPool.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_OPERATION);

			stmt.setString(1, operation);

			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(mapResultSetToEntity(rs));
			}

		} catch (Exception e) {

			throw new DatabaseException("Error fetching by operation", e);

		} finally {

			closeResources(rs, stmt, conn);
		}

		return list;
	}

	public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {

		List<QuantityMeasurementEntity> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = connectionPool.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_MEASUREMENT_TYPE);

			stmt.setString(1, measurementType);

			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(mapResultSetToEntity(rs));
			}

		} catch (Exception e) {

			throw new DatabaseException("Error fetching by type", e);

		} finally {

			closeResources(rs, stmt, conn);
		}

		return list;
	}

	public void deleteAll() {

		try {

			Connection conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();

			stmt.executeUpdate(DELETE_ALL_QUERY);

			closeResources(stmt, conn);

		} catch (Exception e) {

			throw new DatabaseException("Error deleting measurements", e);
		}
	}

	public int getTotalCount() {

		try {

			Connection conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(COUNT_QUERY);

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {

			throw new DatabaseException("Error counting measurements", e);
		}

		return 0;
	}

	public String getPoolStatistics() {

		return "Available Connections: " + connectionPool.getAvailableConnectionCount() + ", Used Connections: "
				+ connectionPool.getUsedConnectionCount();
	}

	public void releaseResources() {

		connectionPool.closeAll();
	}

	private QuantityMeasurementEntity mapResultSetToEntity(ResultSet rs) throws SQLException {

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

//		entity.setId(rs.getLong("id"));
		entity.setThisValue(rs.getDouble("this_value"));
		entity.setThisUnit(rs.getString("this_unit"));
		entity.setThisMeasurementType(rs.getString("this_measurement_type"));

		entity.setThatValue(rs.getDouble("that_value"));
		entity.setThatUnit(rs.getString("that_unit"));
		entity.setThatMeasurementType(rs.getString("that_measurement_type"));

		entity.setOperation(rs.getString("operation"));

		entity.setResultValue(rs.getDouble("result_value"));
		entity.setResultUnit(rs.getString("result_unit"));
		entity.setResultMeasurementType(rs.getString("result_measurement_type"));

		entity.setResultString(rs.getString("result_string"));
		entity.setError(rs.getBoolean("is_error"));
		entity.setErrorMessage(rs.getString("error_message"));

		return entity;
	}

	private void closeResources(ResultSet rs, Statement stmt, Connection conn) {

		try {

			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				connectionPool.releaseConnection(conn);

		} catch (Exception e) {

			logger.warning("Resource closing error: " + e.getMessage());
		}
	}

	private void closeResources(Statement stmt, Connection conn) {

		try {

			if (stmt != null)
				stmt.close();
			if (conn != null)
				connectionPool.releaseConnection(conn);

		} catch (Exception e) {

			logger.warning("Resource closing error: " + e.getMessage());
		}
	}
}
