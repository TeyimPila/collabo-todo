package collabo.todo

class Authority {

    static hasMany=[people:User]

    String description
    String authority="ROLE_"

    static def constraints = {
        authority(blank:false)
        description()
    }
}
