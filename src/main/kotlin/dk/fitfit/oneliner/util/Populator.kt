package dk.fitfit.oneliner.util

import dk.fitfit.oneliner.domain.OneLiner
import dk.fitfit.oneliner.domain.Tag
import dk.fitfit.oneliner.service.OneLinerServiceInterface
import dk.fitfit.oneliner.service.TagServiceInterface
import org.springframework.stereotype.Service

// TODO: Bad practice... Use a CommandLineRunner or something other than @Service
@Service
class Populator(private val oneLinerService: OneLinerServiceInterface, private val tagService: TagServiceInterface) {

    init {
        initialize()
    }

    fun initialize() {
        val maven = createTag("mvn", "Java build tool")
        val alias = createTag("alias", "Suitable for command line alias")
        val npm = createTag("npm", "Node package manager")
        val spring = createTag("spring", "Java framework")
        val docker = createTag("docker", "Container system")
        val db = createTag("db", "Database related")
        val mysql = createTag("mysql", "Mysql the database vendor acquired by oracle")
        val server = createTag("server", "server...")
        val mongo = createTag("mongo", "Mongodb nosql db")
        val noSql = createTag("NoSql", "NoSql database...")
        val sql = createTag("sql", "Structured query language")
        val nginx = createTag("nginx", "web server and more")
        val apache = createTag("apache", "web server")
        val java = createTag("java", "my main programming language")
        val javascript = createTag("javascript", "Scripting language")
        val postgresql = createTag("postgresql", "Great open source database")
        val linux = createTag("linux", "Open source OS")
        val opensource = createTag("opensource", "The way to code")

        createOneLiner("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest",
                "mysql",
                "User: root\n" +
                        "Source: https://hub.docker.com/_/mysql/\n" +
                        "Command: mysql --auto-rehash -u root -h 192.168.0.3 -p",
                db, mysql, server, sql)

        createOneLiner("docker run --name mongodb -p 27017:27017 -p 28017:28017 -dt mongo:latest",
                "mongodb",
                "Not much to add...",
                mongo, db, noSql)

        createOneLiner("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine",
                "postgresql",
                "psql -h localhost -p 5432 -U postgres\n" +
                        "CREATE USER user WITH PASSWORD 'password';\n" +
                        "CREATE DATABASE liftlog OWNER liftlog;\n",
                postgresql, db, linux, opensource, sql)

        createOneLiner("docker run --rm -it -v \"\$PWD\":/app -w /app node:alpine npm",
                "npm",
                "Node package manager...",
                npm, javascript, alias)

        createOneLiner("docker run --rm williamyeh/wrk",
                "wrk",
                "Modern HTTP benchmarking tool... https://github.com/wg/wrk",
                linux, opensource, alias)

        createOneLiner("docker run -it --rm -v \"\$PWD\":/app -v \"\$HOME\"/.m2:/root/.m2 -w /app maven:3.5-jdk-8-alpine mvn",
                "mvn",
                "Java Build Tool",
                linux, opensource, alias, java)

        createOneLiner("docker run --rm -ti -v /var/run/docker.sock:/var/run/docker.sock quay.io/vektorlab/ctop:latest",
                "ctop",
                "Docker Container Top",
                linux, opensource, alias, docker)

        createOneLiner("docker-compose",
                "d-c",
                "Docker compose aliased to d-c",
                alias, docker)

        oneLinerService.findAll()
    }

    private fun createTag(name: String, description: String): Tag {
        val tag = Tag(name, description)
        return tagService.save(tag)
    }

    private fun createOneLiner(line: String, name: String, description: String, vararg tags: Tag): OneLiner {
        val oneLiner = OneLiner()
        oneLiner.line = line
        oneLiner.name = name
        oneLiner.description = description
        oneLiner.tags = listOf(*tags)
        return oneLinerService.save(oneLiner)
    }

}