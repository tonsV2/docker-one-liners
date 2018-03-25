package dk.fitfit.oneliner.domain

import javax.persistence.*
import java.util.ArrayList
import java.util.Arrays

@Entity
class OneLiner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long = 0
    var line: String = ""
    var name: String = ""
    var description: String? = null
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "one_liner_tags",
            joinColumns = arrayOf(JoinColumn(name = "one_liner_id", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "tag_id", referencedColumnName = "id")))
    var tags: MutableList<Tag> = ArrayList()
}
