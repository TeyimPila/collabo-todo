package collabo.todo

class BuddyList {

    String name
    String description
    User owner

    static belongsTo = User
    static hasMany = [ members : BuddyListMember ]

    static constraints = {
        name(blank:false)
        description(blank:true)
    }


    String toString() {
        return "$name - $description - $owner.userName"
    }

    String dump() {
        return "Name: ${name}, Description: ${description}, Owner: ${owner}"
    }
}
