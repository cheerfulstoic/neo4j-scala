package org.neo4j.scala

import org.neo4j.kernel.EmbeddedGraphDatabase

/**
 * Interface for a GraphDatabaseServiceProvider
 * must be implemented by and Graph Database Service Provider
 */
trait GraphDatabaseServiceProvider {
  val ds: DatabaseService
}


/**
 * provides a specific Database Service
 * in this case an embedded database service
 */
trait EmbeddedGraphDatabaseServiceProvider extends GraphDatabaseServiceProvider {

  /**
   * directory where to store the data files
   */
  def neo4jStoreDir: String

  /**
   * using an instance of an embedded graph database
   */
  val ds: DatabaseService = DatabaseServiceImpl(new EmbeddedGraphDatabase(neo4jStoreDir))
}

/**
 * provides a specific Database Service
 * in this case an singleton embedded database service
 */
trait SingletonEmbeddedGraphDatabaseServiceProvider extends GraphDatabaseServiceProvider {

  object Provider {
    val ds: DatabaseService = DatabaseServiceImpl(new EmbeddedGraphDatabase(neo4jStoreDir))
  }

  /**
   * directory where to store the data files
   */
  def neo4jStoreDir: String

  /**
   * using an instance of an embedded graph database
   */
  val ds: DatabaseService = Provider.ds
}