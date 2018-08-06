package com.github.jasonheo

import java.io.File

import com.typesafe.config.ConfigFactory
import pureconfig.{CamelCase, ConfigFieldMapping, ProductHint}

import scala.io.Source

case class DB(name: String,
              table: String,
              user: String,
              pass: String)

case class MigrationConfig(sourceDB: DB,
                           where: String,
                           fields: List[String], // 요거 Array[String]으로 하면 안 된다
                           targetDB: DB,
                           reportMigrationResult: Boolean)

object Migrator {
  def main(args: Array[String]): Unit = {
    val migrationConfig = getConfig("./migration.conf")

    println(migrationConfig.targetDB.user)
  }

  def getConfig(configPath: String): MigrationConfig = {
    // class의 변수명과 config의 key를 모두 camelCase로 맞춘다
    implicit def hint[T] = ProductHint[T](ConfigFieldMapping(CamelCase, CamelCase))

    val migrationConfig = pureconfig.loadConfigOrThrow[MigrationConfig](ConfigFactory.parseFile(new File(configPath)))

    migrationConfig
  }
}
