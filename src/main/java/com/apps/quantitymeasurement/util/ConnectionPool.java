package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ConnectionPool {

	private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());

	private static ConnectionPool instance;

	private List<Connection> availableConnections;
	private List<Connection> usedConnections;

	private final int poolSize;
	private final String dbUrl;
	private final String dbUsername;
	private final String dbPassword;
	private final String driverClass;
	private final String testQuery;

	/**
	 * Private constructor to initialize the pool
	 */
	private ConnectionPool() throws SQLException {

		ApplicationConfig config = ApplicationConfig.getInstance();

		this.poolSize = config.getIntProperty("db.pool-size", 10);
		this.dbUrl = config.getProperty("db.url");
		this.dbUsername = config.getProperty("db.username");
		this.dbPassword = config.getProperty("db.password");
		this.driverClass = config.getProperty("db.driver");
		this.testQuery = config.getProperty("db.hikari.connection-test-query", "SELECT 1");

		availableConnections = new ArrayList<>();
		usedConnections = new ArrayList<>();

		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Database Driver not found", e);
		}

		initializeConnections();
	}

	/**
	 * Singleton instance
	 */
	public static synchronized ConnectionPool getInstance() throws SQLException {

		if (instance == null) {
			instance = new ConnectionPool();
		}

		return instance;
	}

	/**
	 * Initialize pool connections
	 */
	private void initializeConnections() throws SQLException {

		for (int i = 0; i < poolSize; i++) {

			Connection conn = createConnection();
			availableConnections.add(conn);

		}

		logger.info("Connection pool initialized with " + poolSize + " connections");
	}

	/**
	 * Create new DB connection
	 */
	private Connection createConnection() throws SQLException {

		return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
	}

	/**
	 * Get connection from pool
	 */
	public synchronized Connection getConnection() throws SQLException {

		if (!availableConnections.isEmpty()) {

			Connection conn = availableConnections.remove(0);
			usedConnections.add(conn);
			return conn;
		}

		if (getTotalConnectionCount() < poolSize) {

			Connection conn = createConnection();
			usedConnections.add(conn);
			return conn;
		}

		throw new SQLException("Maximum pool size reached. No connections available.");
	}

	/**
	 * Release connection back to pool
	 */
	public synchronized void releaseConnection(Connection connection) {

		if (connection == null)
			return;

		usedConnections.remove(connection);
		availableConnections.add(connection);
	}

	/**
	 * Validate connection
	 */
	public boolean validateConnection(Connection connection) {

		try (Statement stmt = connection.createStatement()) {

			stmt.execute(testQuery);
			return true;

		} catch (SQLException e) {

			logger.warning("Connection validation failed: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Close all connections
	 */
	public synchronized void closeAll() {

		try {

			for (Connection conn : availableConnections) {
				conn.close();
			}

			for (Connection conn : usedConnections) {
				conn.close();
			}

			availableConnections.clear();
			usedConnections.clear();

			logger.info("All connections closed");

		} catch (SQLException e) {

			logger.severe("Error closing connections: " + e.getMessage());
		}
	}

	public int getAvailableConnectionCount() {
		return availableConnections.size();
	}

	public int getUsedConnectionCount() {
		return usedConnections.size();
	}

	public int getTotalConnectionCount() {
		return availableConnections.size() + usedConnections.size();
	}

	/**
	 * Debug info
	 */
	@Override
	public String toString() {

		return "ConnectionPool {" + "availableConnections=" + availableConnections.size() + ", usedConnections="
				+ usedConnections.size() + ", total=" + getTotalConnectionCount() + '}';
	}

	/**
	 * Test main
	 */
	public static void main(String[] args) {

		try {

			ConnectionPool pool = ConnectionPool.getInstance();

			Connection conn1 = pool.getConnection();

			logger.info("Validate connection: " + (pool.validateConnection(conn1) ? "Success" : "Failure"));

			logger.info("Available connections after acquiring 1: " + pool.getAvailableConnectionCount());

			logger.info("Used connections after acquiring 1: " + pool.getUsedConnectionCount());

			pool.releaseConnection(conn1);

			logger.info("Available connections after releasing 1: " + pool.getAvailableConnectionCount());

			logger.info("Used connections after releasing 1: " + pool.getUsedConnectionCount());

			pool.closeAll();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}