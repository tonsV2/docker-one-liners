package dk.fitfit.oneliner.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class OneLiner {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oneliner_gen")
	@SequenceGenerator(name = "oneliner_gen", sequenceName = "oneliner_seq")
	private long id;
	private String line;
	private String name;
	private String description;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "one_liner_tags",
			joinColumns = @JoinColumn(name = "one_liner_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private List<Tag> tags = new ArrayList<>();

	public OneLiner() {
	}

	public OneLiner(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setTag(Tag tag) {
		this.tags.add(tag);
	}

	public void setTags(Tag... tags) {
		this.tags.addAll(Arrays.asList(tags));
	}
}
