package dk.fitfit.doconelin.domain;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String name;
	private String description;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "one_liner_tag",
		joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "one_liner_id", referencedColumnName = "id"))
	@JsonIgnore
	private List<OneLiner> oneLiners;
	@Transient
	private int rank;

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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
