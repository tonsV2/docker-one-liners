package dk.fitfit.doconelin.domain;

import javax.persistence.*;

@Entity
@Table(indexes = {
		@Index(name = "tag_name_idx", columnList = "name"),
		@Index(name = "tag_description_idx", columnList = "description")
})
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String name;
	private String description;

	private Tag() {
		// For hibernate
	}

	public Tag(String name, String description) {
		this.name = name;
		this.description = description;
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
}
