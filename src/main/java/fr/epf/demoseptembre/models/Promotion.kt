package fr.epf.demoseptembre.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Promotion(@Id @GeneratedValue var id: Int? =  null, var name: String? = null, var num: Int? = null) {

}