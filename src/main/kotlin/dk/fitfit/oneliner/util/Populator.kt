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

// TODO: Untested
// alias java='docker run -it --net=host openjdk:8-jre-alpine -v "$PWD":/app -w /app java'
// alias java='docker run -it --net=host openjdk:8-jdk-alpine -v "$PWD":/app -w /app javac'
// make groups... tag: java-dev, javascript-dev, go-dev?, database,

    private final fun initialize() {
        val maven = createTag("mvn", "Java build tool")
        val alias = createTag("alias", "Suitable for command line alias")
        val npm = createTag("npm", "Node package manager")
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
//                postgresql, db, linux, opensource, sql, docker)
                postgresql, db, linux, opensource, sql)

        createOneLiner("docker run --rm -it --net=host -v \"\$PWD\":/app -w /app node:alpine npm",
                "npm",
                "Node package manager...",
                npm, javascript, alias)

        createOneLiner("docker run --rm --net=host williamyeh/wrk",
                "wrk",
                "Modern HTTP benchmarking tool... https://github.com/wg/wrk",
                linux, opensource, alias)

        createOneLiner("docker run --rm  -it --net=host -v \"\$PWD\":/app -v \"\$HOME\"/.m2:/root/.m2 -w /app maven:3.5-jdk-8-alpine mvn",
                "mvn",
                "Java Build Tool",
                linux, alias, java, maven, apache)

        createOneLiner("docker run --rm -ti -v /var/run/docker.sock:/var/run/docker.sock quay.io/vektorlab/ctop:latest",
                "ctop",
                "Docker Container Top",
                linux, opensource, alias, docker)

        createOneLiner("docker run -it -v /var/run/docker.sock:/var/run/docker.sock moncho/dry",
                "dry",
                "Docker Container Top",
                linux, opensource, alias, docker)

        createOneLiner("docker run -v /var/run/docker.sock:/run/docker.sock -ti -e TERM tomastomecek/sen",
                "sen",
                "Docker Container Top",
                linux, opensource, alias, docker)

        createOneLiner("docker run -it --rm postgres:alpine psql",
                "psql",
                "Postgresql client",
                alias, postgresql, opensource, db)

        createOneLiner("docker run -it --rm mariadb mysql",
                "mysql",
                "Mysql client",
                alias, mysql, opensource, db)

        createOneLiner("docker-compose",
                "d-c",
                "Docker compose aliased to d-c",
                alias, docker, opensource)

        createOneLiner("docker-compose",
                "dc",
                "Docker compose aliased to dc",
                alias, docker, opensource)

        createOneLiner("docker run -it --rm --name nginx -v \"\$PWD\":/usr/share/nginx/html:rw -p 8000:80 nginx:alpine",
                "webserv_nginx",
                "Current folder exposed over http using nginx... Configuration of permissions might be required",
                alias, docker, opensource, nginx)

        createOneLiner("docker run -it --rm --name php -v \"\$PWD\":/src -w /src --net=host php:cli-alpine php -S localhost:8000",
                "webserv_php",
                "Current folder exposed over http using php... Configuration of permissions might be required",
                alias, docker, opensource)

        createOneLiner("docker run -it --rm --name python -v \"\$PWD\":/src -w /src --net=host python:alpine python3 -m http.server 8000",
                "webserv",
                "Current folder exposed over http using python... Configuration of permissions might be required",
                alias, docker, opensource)

        createOneLiner("ruby -run -ehttpd . -p8000",
                "webserv",
                "Current folder exposed over http using ruby",
                alias, opensource)

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
        oneLiner.tags = mutableListOf(*tags)
        return oneLinerService.save(oneLiner)
    }

}
