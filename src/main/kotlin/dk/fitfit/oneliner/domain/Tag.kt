package dk.fitfit.oneliner.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed

import javax.persistence.*

@Entity
@Indexed
@Table(indexes = [
    Index(name = "tag_name_idx", columnList = "name"),
    Index(name = "tag_description_idx", columnList = "description")
])
open class Tag(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0,
    @Column(unique = true)
    var name: String = "",
    @Field
    var description: String = "",
    @Transient
    open var rank: Long = 0,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    var oneLiners: MutableList<OneLiner> = ArrayList<OneLiner>()
) {
    constructor(name: String, description: String) : this(0, name, description, 0, ArrayList())
    constructor(id: Long, name: String, description: String, rank: Long) : this(id, name, description, rank, ArrayList())
    constructor() : this(0, "", "", 0, ArrayList())
}
