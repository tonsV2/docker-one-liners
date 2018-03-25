package dk.fitfit.oneliner.domain

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
@Table(indexes = arrayOf(Index(name = "tag_name_idx", columnList = "name"), Index(name = "tag_description_idx", columnList = "description")))
class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long = 0,
    @Column(unique = true)
    var name: String = "",
    var description: String = "",
    @Transient
    var rank: Long = 0,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    var oneLiners: List<OneLiner>? = null
) {
    constructor(name: String, description: String, oneLiners: List<OneLiner>?) : this(0, name, description, 0, oneLiners)
    constructor(name: String, description: String) : this(0, name, description, 0, null)
    constructor() : this(0, "", "", 0, null)
}
