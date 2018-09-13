package fr.epf.demoseptembre.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Event(@Id @GeneratedValue var id: Int? =  null, var name: String? = null, var date: String?, var Promotion: String? = null) {

}