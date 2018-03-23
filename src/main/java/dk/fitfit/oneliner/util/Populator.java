package dk.fitfit.oneliner.util;

import dk.fitfit.oneliner.domain.OneLiner;
import dk.fitfit.oneliner.domain.Tag;
import dk.fitfit.oneliner.service.OneLinerServiceInterface;
import dk.fitfit.oneliner.service.TagServiceInterface;
import org.springframework.stereotype.Service;

// TODO: Bad practice... Use a CommandLineRunner or something other than @Service
@Service
public class Populator {
	private final OneLinerServiceInterface oneLinerService;
	private final TagServiceInterface tagService;

	public Populator(OneLinerServiceInterface oneLinerService, TagServiceInterface tagService) {
		this.oneLinerService = oneLinerService;
		this.tagService = tagService;
		initialize();
	}

	public void initialize() {
		Tag maven = createTag("mvn", "Java build tool");
		Tag alias = createTag("alias", "Suitable for command line alias");
		Tag npm = createTag("npm", "Node package manager");
		Tag spring = createTag("spring", "Java framework");
		Tag docker = createTag("docker", "Container system");
		Tag db = createTag("db", "Database related");
		Tag mysql = createTag("mysql", "Mysql the database vendor acquired by oracle");
		Tag server = createTag("server", "server...");
		Tag mongo = createTag("mongo", "Mongodb nosql db");
		Tag noSql = createTag("NoSql", "NoSql database...");
		Tag sql = createTag("sql", "Structured query language");
		Tag nginx = createTag("nginx", "web server and more");
		Tag apache = createTag("apache", "web server");
		Tag java = createTag("java", "my main programming language");
		Tag javascript = createTag("javascript", "Scripting language");
		Tag postgresql = createTag("postgresql", "Great open source database");
		Tag linux = createTag("linux", "Open source OS");
		Tag opensource = createTag("opensource", "The way to code");

		createOneLiner("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest",
				"mysql",
				"User: root\n" +
						"Source: https://hub.docker.com/_/mysql/\n" +
						"Command: mysql --auto-rehash -u root -h 192.168.0.3 -p",
				db, mysql, server, sql);

		createOneLiner("docker run --name mongodb -p 27017:27017 -p 28017:28017 -dt mongo:latest",
				"mongodb",
				"Not much to add...",
				mongo, db, noSql);

		createOneLiner("docker run --name postgresql -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres:9.6.2-alpine",
				"postgresql",
				"psql -h localhost -p 5432 -U postgres\n" +
						"CREATE USER user WITH PASSWORD 'password';\n" +
						"CREATE DATABASE liftlog OWNER liftlog;\n",
				postgresql, db, linux, opensource, sql);

		createOneLiner("docker run -v \"$PWD\":/app -w /app node:alpine npm",
				"npm",
				"Node package manager...",
				npm, javascript, alias);

		createOneLiner("docker run --rm williamyeh/wrk",
				"wrk",
				"Modern HTTP benchmarking tool... https://github.com/wg/wrk",
				linux, opensource, alias);

		createOneLiner("docker run -it --rm -v \"$PWD\":/app -v \"$HOME\"/.m2:/root/.m2 -w /app maven:3.5-jdk-8-alpine mvn",
				"mvn",
				"Java Build Tool",
				linux, opensource, alias, java);

		oneLinerService.findAll();
	}

	private Tag createTag(String name, String description) {
		Tag tag = new Tag(name, description);
		return tagService.save(tag);
	}

	private OneLiner createOneLiner(String line, String name, String description, Tag... tags) {
		OneLiner oneLiner = new OneLiner();
		oneLiner.setLine(line);
		oneLiner.setName(name);
		oneLiner.setDescription(description);
		oneLiner.setTags(tags);
		return oneLinerService.save(oneLiner);
	}

}
