package a

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
open class Reservation {

    @Id
    @GeneratedValue
    var id: Long? = null

    var reservationName: String? = null

    constructor() {
        // why JPA why??
    }

    constructor(reservationName: String) {
        this.reservationName = reservationName
    }

    override fun toString(): String {
        return "Reservation{id=$id, reservationName='$reservationName'}"
    }
}