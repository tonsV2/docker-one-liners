package dk.fitfit.oneliner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(indexes = {
		@Index(name = "tag_name_idx", columnList = "name"),
		@Index(name = "tag_description_idx", columnList = "description")
})
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(unique = true)
	private String name;
	private String description;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
	private List<OneLiner> oneLiners;
	@Transient
	private long rank;

	public Tag() {
		// For hibernate
	}

	public Tag(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Tag(long id, String name, String description, long rank) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.rank = rank;
	}

	public long getId() {
		return id;
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

	public long getRank() {
		return rank;
	}
}
