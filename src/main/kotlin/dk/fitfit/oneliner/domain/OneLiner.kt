package dk.fitfit.oneliner.domain

import javax.persistence.*

@Entity
@Table(uniqueConstraints = [
    UniqueConstraint(columnNames = ["name", "line"])
])
open class OneLiner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0
    var name: String = ""
    var line: String = ""
    var description: String? = null
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "one_liner_tags",
            joinColumns = [(JoinColumn(name = "one_liner_id", referencedColumnName = "id"))],
            inverseJoinColumns = [(JoinColumn(name = "tag_id", referencedColumnName = "id"))])
    var tags: MutableList<Tag> = ArrayList<Tag>()
}
