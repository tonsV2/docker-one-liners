package dk.fitfit.doconelin.service;

import dk.fitfit.doconelin.domain.OneLiner;
import dk.fitfit.doconelin.domain.Tag;
import dk.fitfit.doconelin.repository.OneLinerRepository;
import dk.fitfit.doconelin.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OneLinerService implements OneLinerServiceInterface {
	private final OneLinerRepository repository;
	private final TagRepository tagRepository;

	public OneLinerService(OneLinerRepository repository, TagRepository tagRepository) {
		this.repository = repository;
		this.tagRepository = tagRepository;

		Tag dbTag = new Tag("db", "Database related");
		Tag dbTagSave = tagRepository.saveAndFlush(dbTag);

		Tag mysqlTag = new Tag("mysql", "Mysql the database vendor acquired by oracle");
		Tag mysqlTagSave = tagRepository.saveAndFlush(mysqlTag);

		Tag serverTag = new Tag("server", "server...");
		Tag serverTagSave = tagRepository.saveAndFlush(serverTag);

		Tag mongoTag = new Tag("mongo", "Mongodb nosql db");
		tagRepository.saveAndFlush(mongoTag);

		Tag noSqlTag = new Tag("NoSql", "NoSql database...");
		tagRepository.saveAndFlush(noSqlTag);

		tagRepository.flush();

		OneLiner oneLiner = createOneLiner("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest",
				"User: root\n" +
				"Source: https://hub.docker.com/_/mysql/\n" +
				"Command: mysql --auto-rehash -u root -h 192.168.0.3 -p",
				dbTag,
				mysqlTag,
				serverTag);

		OneLiner save = repository.save(oneLiner);

		OneLiner oneLiner2 = createOneLiner("docker run --name mysql -e MYSQL_ROOT_PASSWORD=skummet -p 3306:3306 -dt mysql:latest",
				"Not much to add...",
				mongoTag, dbTag, noSqlTag);

		OneLiner save2 = repository.save(oneLiner2);
	}

	private OneLiner createOneLiner(String line, String description, Tag... tags) {
		OneLiner oneLiner = new OneLiner();
		oneLiner.setLine(line);
		oneLiner.setDescription(description);
		oneLiner.setTags(tags);
		return oneLiner;
	}

	public List<OneLiner> findByTags(List<Tag> tags) {
		List<String> tagNames = new ArrayList<>();
		for (Tag tag : tags) {
			tagNames.add(tag.getName());
		}
		return repository.findByTagsName(tagNames);
	}

	@Override
	public List<OneLiner> findByTagNames(List<String> tags) {
		return repository.findByTagsName(tags);
	}

	@Override
	public List<OneLiner> findAll() {
		return repository.findAll();
	}
}
