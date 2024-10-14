package sample_mn_app_02

import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tbl_user")
data class User (
    @Id
    val id: Long,

    @Column
    val name: String,

    @Column
    val age: Int
)